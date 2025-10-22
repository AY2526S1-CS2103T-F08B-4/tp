package seedu.address.storage;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static seedu.address.storage.JsonAdaptedPerson.MISSING_FIELD_MESSAGE_FORMAT;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.BENSON;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.model.person.AttendMap;
import seedu.address.model.person.Email;
import seedu.address.model.person.GradeMap;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TeleHandle;
import seedu.address.model.person.TutorialGroup;

public class JsonAdaptedPersonTest {
    private static final String INVALID_NAME = "R@chel";
    private static final String INVALID_PHONE = "+651234";
    private static final String INVALID_TELEHANDLE = "rachel";
    private static final String INVALID_TUTORIAL_GROUP = "Tutori@L1";
    private static final String INVALID_EMAIL = "example.com";
    private static final String INVALID_GRADE = "131.53";
    private static final String INVALID_ATTEND_VALUE = "2";
    private static final LinkedHashMap<String, String> INVALID_GRADE_MAP = new LinkedHashMap<>(
            Map.ofEntries(Map.entry("Finals", INVALID_GRADE))
    );
    private static final LinkedHashMap<String, String> INVALID_ATTEND_MAP = new LinkedHashMap<>(
            Map.ofEntries(Map.entry("t1", INVALID_ATTEND_VALUE))
    );
    private static final String INVALID_TAG = "#friend";

    private static final String VALID_NAME = BENSON.getName().toString();
    private static final String VALID_PHONE = BENSON.getPhone().toString();
    private static final String VALID_EMAIL = BENSON.getEmail().toString();
    private static final String VALID_TELEHANDLE = BENSON.getTeleHandle().toString();
    private static final String VALID_TUTORIAL_GROUP = BENSON.getTutorialGroup().toString();
    private static final LinkedHashMap<String, String> VALID_GRADE_MAP;
    private static final LinkedHashMap<String, String> VALID_ATTEND_MAP;
    static {
        VALID_GRADE_MAP = (LinkedHashMap<String, String>) BENSON.getGradeMap().toStringMap();
        VALID_ATTEND_MAP = (LinkedHashMap<String, String>) BENSON.getAttendMap().toStringMap();
    }
    // to fit line width checkstyle requirement
    private static final List<JsonAdaptedTag> VALID_TAGS = BENSON.getTags().stream()
            .map(JsonAdaptedTag::new)
            .collect(Collectors.toList());

    @Test
    public void toModelType_validPersonDetails_returnsPerson() throws Exception {
        JsonAdaptedPerson person = new JsonAdaptedPerson(BENSON);
        assertEquals(BENSON, person.toModelType());
    }

    @Test
    public void toModelType_invalidName_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(INVALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_TELEHANDLE,
                        VALID_TUTORIAL_GROUP,
                        VALID_GRADE_MAP,
                        VALID_ATTEND_MAP,
                        VALID_TAGS);
        String expectedMessage = Name.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullName_throwsIllegalValueException() {
        JsonAdaptedPerson person;
        person = new JsonAdaptedPerson(null,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_TELEHANDLE,
                VALID_TUTORIAL_GROUP,
                VALID_GRADE_MAP,
                VALID_ATTEND_MAP,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Name.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME,
                        INVALID_PHONE,
                        VALID_EMAIL,
                        VALID_TELEHANDLE,
                        VALID_TUTORIAL_GROUP,
                        VALID_GRADE_MAP,
                        VALID_ATTEND_MAP,
                        VALID_TAGS);
        String expectedMessage = Phone.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullPhone_throwsIllegalValueException() {
        JsonAdaptedPerson person;
        person = new JsonAdaptedPerson(VALID_NAME,
                null,
                VALID_EMAIL,
                VALID_TELEHANDLE,
                VALID_TUTORIAL_GROUP,
                VALID_GRADE_MAP,
                VALID_ATTEND_MAP,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Phone.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME,
                VALID_PHONE,
                INVALID_EMAIL,
                VALID_TELEHANDLE,
                VALID_TUTORIAL_GROUP,
                VALID_GRADE_MAP,
                VALID_ATTEND_MAP,
                VALID_TAGS);
        String expectedMessage = Email.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullEmail_throwsIllegalValueException() {
        JsonAdaptedPerson person;
        person = new JsonAdaptedPerson(VALID_NAME,
                VALID_PHONE,
                null,
                VALID_TELEHANDLE,
                VALID_TUTORIAL_GROUP,
                VALID_GRADE_MAP,
                VALID_ATTEND_MAP,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, Email.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTeleHandle_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                INVALID_TELEHANDLE,
                VALID_TUTORIAL_GROUP,
                VALID_GRADE_MAP,
                VALID_ATTEND_MAP,
                VALID_TAGS);
        String expectedMessage = TeleHandle.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTeleHandle_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                null,
                VALID_TUTORIAL_GROUP,
                VALID_GRADE_MAP,
                VALID_ATTEND_MAP,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TeleHandle.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTutorialGroup_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_TELEHANDLE,
                INVALID_TUTORIAL_GROUP,
                VALID_ATTEND_MAP,
                VALID_GRADE_MAP,
                VALID_TAGS);
        String expectedMessage = TutorialGroup.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullTutorialGroup_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_TELEHANDLE,
                null,
                VALID_GRADE_MAP,
                VALID_ATTEND_MAP,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, TutorialGroup.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidTags_throwsIllegalValueException() {
        List<JsonAdaptedTag> invalidTags = new ArrayList<>(VALID_TAGS);
        invalidTags.add(new JsonAdaptedTag(INVALID_TAG));
        JsonAdaptedPerson person =
                new JsonAdaptedPerson(VALID_NAME,
                        VALID_PHONE,
                        VALID_EMAIL,
                        VALID_TELEHANDLE,
                        VALID_TUTORIAL_GROUP,
                        VALID_GRADE_MAP,
                        VALID_ATTEND_MAP,
                        invalidTags);
        assertThrows(IllegalValueException.class, person::toModelType);
    }

    @Test
    public void toModelType_invalidGradeMap_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_TELEHANDLE,
                VALID_TUTORIAL_GROUP,
                INVALID_GRADE_MAP,
                VALID_ATTEND_MAP,
                VALID_TAGS);
        String expectedMessage = GradeMap.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullGradeMap_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_TELEHANDLE,
                VALID_TUTORIAL_GROUP,
                null,
                VALID_ATTEND_MAP,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, GradeMap.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_invalidAttendMap_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_TELEHANDLE,
                VALID_TUTORIAL_GROUP,
                VALID_GRADE_MAP,
                INVALID_ATTEND_MAP,
                VALID_TAGS);
        String expectedMessage = AttendMap.MESSAGE_CONSTRAINTS;
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

    @Test
    public void toModelType_nullAttendMap_throwsIllegalValueException() {
        JsonAdaptedPerson person = new JsonAdaptedPerson(VALID_NAME,
                VALID_PHONE,
                VALID_EMAIL,
                VALID_TELEHANDLE,
                VALID_TUTORIAL_GROUP,
                VALID_GRADE_MAP,
                null,
                VALID_TAGS);
        String expectedMessage = String.format(MISSING_FIELD_MESSAGE_FORMAT, AttendMap.class.getSimpleName());
        assertThrows(IllegalValueException.class, expectedMessage, person::toModelType);
    }

}
