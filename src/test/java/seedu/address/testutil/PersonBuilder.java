package seedu.address.testutil;

import java.util.HashSet;
import java.util.LinkedHashMap;
import java.util.Set;

import seedu.address.model.person.Assignments;
import seedu.address.model.person.AttendMap;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.GradeMap;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TeleHandle;
import seedu.address.model.tag.Tag;
import seedu.address.model.util.SampleDataUtil;

/**
 * A utility class to help with building Person objects.
 */
public class PersonBuilder {

    public static final String DEFAULT_NAME = "Amy Bee";
    public static final String DEFAULT_PHONE = "85355255";
    public static final String DEFAULT_EMAIL = "amy@gmail.com";
    public static final String DEFAULT_TELEHANDLE = "@amy";

    private Name name;
    private Phone phone;
    private Email email;
    private TeleHandle teleHandle;
    private GradeMap gradeMap;
    private AttendMap attendMap;
    private Set<Tag> tags;

    /**
     * Creates a {@code PersonBuilder} with the default details.
     */
    public PersonBuilder() {
        name = new Name(DEFAULT_NAME);
        phone = new Phone(DEFAULT_PHONE);
        email = new Email(DEFAULT_EMAIL);
        teleHandle = new TeleHandle(DEFAULT_TELEHANDLE);
        gradeMap = new GradeMap();
        attendMap = new AttendMap();
        tags = new HashSet<>();
    }

    /**
     * Initializes the PersonBuilder with the data of {@code personToCopy}.
     */
    public PersonBuilder(Person personToCopy) {
        name = personToCopy.getName();
        phone = personToCopy.getPhone();
        email = personToCopy.getEmail();
        teleHandle = personToCopy.getTeleHandle();
        gradeMap = personToCopy.getGradeMap();
        attendMap = personToCopy.getAttendMap();
        tags = new HashSet<>(personToCopy.getTags());
    }

    /**
     * Sets the {@code Name} of the {@code Person} that we are building.
     */
    public PersonBuilder withName(String name) {
        this.name = new Name(name);
        return this;
    }

    /**
     * Parses the {@code tags} into a {@code Set<Tag>} and set it to the {@code Person} that we are building.
     */
    public PersonBuilder withTags(String ... tags) {
        this.tags = SampleDataUtil.getTagSet(tags);
        return this;
    }

    /**
     * Sets the {@code TeleHandle} of the {@code Person} that we are building.
     */
    public PersonBuilder withTeleHandle(String teleHandle) {
        this.teleHandle = new TeleHandle(teleHandle);
        return this;
    }

    /**
     * Sets the {@code Phone} of the {@code Person} that we are building.
     */
    public PersonBuilder withPhone(String phone) {
        this.phone = new Phone(phone);
        return this;
    }

    /**
     * Sets the {@code Email} of the {@code Person} that we are building.
     */
    public PersonBuilder withEmail(String email) {
        this.email = new Email(email);
        return this;
    }

    /**
     * Sets the {@code GradeMap} of the {@code Person} that we are building.
     */
    public PersonBuilder withGradeMap(LinkedHashMap<Assignments, Grade> gradeMap) {
        this.gradeMap = new GradeMap(gradeMap);
        return this;
    }

    /**
     * Sets the default {@code GradeMap} of the {@code Person} that we are building.
     */
    public PersonBuilder withGradeMap() {
        this.gradeMap = new GradeMap();
        return this;
    }

    /**
     * Sets the default {@code AttendMap} of the {@code Person} that we are building.
     */
    public PersonBuilder withAttendMap() {
        this.attendMap = new AttendMap();
        return this;
    }

    public Person build() {
        return new Person(name, phone, email, teleHandle, gradeMap, attendMap, tags);
    }
}
