package seedu.address.storage;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

import com.fasterxml.jackson.annotation.JsonCreator;
import com.fasterxml.jackson.annotation.JsonProperty;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.Assignments;
import seedu.address.model.person.AttendMap;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.GradeMap;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TeleHandle;
import seedu.address.model.person.TutorialClass;
import seedu.address.model.person.TutorialGroup;
import seedu.address.model.tag.Tag;

/**
 * Jackson-friendly version of {@link Person}.
 */
class JsonAdaptedPerson {

    public static final String MISSING_FIELD_MESSAGE_FORMAT = "Person's %s field is missing!";

    private final String name;
    private final String phone;
    private final String email;
    private final String teleHandle;
    private final String tutorialGroup;
    private final LinkedHashMap<String, String> gradeMap;
    private final LinkedHashMap<String, String> attendMap;
    private final List<JsonAdaptedTag> tags = new ArrayList<>();

    /**
     * Constructs a {@code JsonAdaptedPerson} with the given person details.
     */
    @JsonCreator
    public JsonAdaptedPerson(@JsonProperty("name") String name, @JsonProperty("phone") String phone,
                             @JsonProperty("email") String email, @JsonProperty("teleHandle") String teleHandle,
                             @JsonProperty("tutorialGroup") String tutorialGroup,
                             @JsonProperty("gradeMap") LinkedHashMap<String, String> gradeMap,
                             @JsonProperty("attendMap") LinkedHashMap<String, String> attendMap,
                             @JsonProperty("tags") List<JsonAdaptedTag> tags) {
        this.name = name;
        this.phone = phone;
        this.email = email;
        this.teleHandle = teleHandle;
        this.tutorialGroup = tutorialGroup;
        this.gradeMap = gradeMap;
        this.attendMap = attendMap;
        if (tags != null) {
            this.tags.addAll(tags);
        }
    }

    /**
     * Converts a given {@code Person} into this class for Jackson use.
     */
    public JsonAdaptedPerson(Person source) {
        name = source.getName().fullName;
        phone = source.getPhone().value;
        email = source.getEmail().value;
        teleHandle = source.getTeleHandle().value;
        tutorialGroup = source.getTutorialGroup().value;
        gradeMap = (LinkedHashMap<String, String>) source.getGradeMap().toStringMap();
        attendMap = (LinkedHashMap<String, String>) source.getAttendMap().toStringMap();
        tags.addAll(source.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList()));
    }

    /**
     * Converts this Jackson-friendly adapted person object into the model's {@code Person} object.
     *
     * @throws IllegalValueException if there were any data constraints violated in the adapted person.
     */
    public Person toModelType() throws IllegalValueException {
        final List<Tag> personTags = new ArrayList<>();
        for (JsonAdaptedTag tag : tags) {
            personTags.add(tag.toModelType());
        }

        if (name == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName()));
        }
        if (!Name.isValidName(name)) {
            throw new IllegalValueException(Name.MESSAGE_CONSTRAINTS);
        }
        final Name modelName = new Name(name);

        if (phone == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName()));
        }
        if (!Phone.isValidPhone(phone)) {
            throw new IllegalValueException(Phone.MESSAGE_CONSTRAINTS);
        }
        final Phone modelPhone = new Phone(phone);

        if (email == null) {
            throw new IllegalValueException(String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName()));
        }
        if (!Email.isValidEmail(email)) {
            throw new IllegalValueException(Email.MESSAGE_CONSTRAINTS);
        }
        final Email modelEmail = new Email(email);

        if (teleHandle == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, TeleHandle.class.getSimpleName()));
        }
        if (!TeleHandle.isValidTeleHandle(teleHandle)) {
            throw new IllegalValueException(TeleHandle.MESSAGE_CONSTRAINTS);
        }
        final TeleHandle modelTeleHandle = new TeleHandle(teleHandle);

        if (tutorialGroup == null) {
            throw new IllegalValueException(
                String.format(MISSING_FIELD_MESSAGE_FORMAT, TutorialGroup.class.getSimpleName()));
        }
        if (!TutorialGroup.isValidTutorial(tutorialGroup)) {
            throw new IllegalValueException(TutorialGroup.MESSAGE_CONSTRAINTS);
        }
        final TutorialGroup modelTutorialGroup = new TutorialGroup(tutorialGroup);

        if (gradeMap == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, GradeMap.class.getSimpleName()));
        }
        if (!GradeMap.isValidGradeMap(gradeMap)) {
            throw new IllegalValueException(GradeMap.MESSAGE_CONSTRAINTS);
        }
        final GradeMap modelGradeMap = new GradeMap();
        for (Map.Entry<String, String> entry : gradeMap.entrySet()) {
            modelGradeMap.put(Assignments.fromString(entry.getKey()), new Grade(entry.getValue()));
        }

        if (attendMap == null) {
            throw new IllegalValueException(
                    String.format(MISSING_FIELD_MESSAGE_FORMAT, AttendMap.class.getSimpleName()));
        }
        if (!AttendMap.isValidAttendMap(attendMap)) {
            throw new IllegalValueException(AttendMap.MESSAGE_CONSTRAINTS);
        }
        final AttendMap modelAttendMap = new AttendMap();
        for (Map.Entry<String, String> entry : attendMap.entrySet()) {
            TutorialClass tutClass = TutorialClass.fromString(entry.getKey());
            int attended = Integer.parseInt(entry.getValue());
            if (attended == 1) {
                modelAttendMap.markPresent(tutClass);
            } else {
                modelAttendMap.markAbsent(tutClass);
            }
        }

        final Set<Tag> modelTags = new HashSet<>(personTags);
        return new Person(modelName,
                modelPhone,
                modelEmail,
                modelTeleHandle,
                modelTutorialGroup,
                modelGradeMap,
                modelAttendMap, modelTags);
    }

}
