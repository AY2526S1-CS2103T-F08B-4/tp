package seedu.address.model.util;

import java.util.Arrays;
import java.util.Set;
import java.util.stream.Collectors;

import seedu.address.model.AddressBook;
import seedu.address.model.ReadOnlyAddressBook;
import seedu.address.model.person.AttendMap;
import seedu.address.model.person.Email;
import seedu.address.model.person.GradeMap;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TeleHandle;
import seedu.address.model.person.TutorialGroup;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods for populating {@code AddressBook} with sample data.
 */
public class SampleDataUtil {
    public static Person[] getSamplePersons() {
        return new Person[] {
            new Person(new Name("Alex Yeoh"), new Phone("87438807"), new Email("alexyeoh@example.com"),
                new TeleHandle("@alex"),
                new TutorialGroup("TG01"),
                new GradeMap(),
                new AttendMap(),
                getTagSet("Needs Help")),
            new Person(new Name("Bernice Yu"), new Phone("99272758"), new Email("berniceyu@example.com"),
                new TeleHandle("@bernice"),
                new TutorialGroup("TG02"),
                new GradeMap(),
                new AttendMap(),
                getTagSet("Top Student")),
            new Person(new Name("Charlotte Oliveiro"), new Phone("93210283"), new Email("charlotte@example.com"),
                new TeleHandle("@charlotte"),
                new TutorialGroup("TG03"),
                new GradeMap(),
                new AttendMap(),
                getTagSet("Tutorial3")),
            new Person(new Name("David Li"), new Phone("91031282"), new Email("lidavid@example.com"),
                new TeleHandle("@david"),
                new TutorialGroup("TG01"),
                new GradeMap(),
                new AttendMap(),
                getTagSet()),
            new Person(new Name("Irfan Ibrahim"), new Phone("92492021"), new Email("irfan@example.com"),
                new TeleHandle("@irfan"),
                new TutorialGroup("TG02"),
                new GradeMap(),
                new AttendMap(),
                getTagSet()),
            new Person(new Name("Roy Balakrishnan"), new Phone("92624417"), new Email("royb@example.com"),
                new TeleHandle("@roy"),
                new TutorialGroup("TG03"),
                new GradeMap(),
                new AttendMap(),
                getTagSet("Needs Help"))
        };
    }

    public static ReadOnlyAddressBook getSampleAddressBook() {
        AddressBook sampleAb = new AddressBook();
        for (Person samplePerson : getSamplePersons()) {
            sampleAb.addPerson(samplePerson);
        }
        return sampleAb;
    }

    /**
     * Returns a tag set containing the list of strings given.
     */
    public static Set<Tag> getTagSet(String... strings) {
        return Arrays.stream(strings)
            .map(Tag::new)
            .collect(Collectors.toSet());
    }

}
