package seedu.address.testutil;


import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTORIAL1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTORIAL2;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEHANDLE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEHANDLE_BOB;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

import seedu.address.model.AddressBook;
import seedu.address.model.person.Person;

/**
 * A utility class containing a list of {@code Person} objects to be used in tests.
 */
public class TypicalPersons {

    // TODO: Update typical persons to be more context-specific
    public static final Person ALICE = new PersonBuilder().withName("Alice Pauline")
            .withTeleHandle("@alice")
            .withEmail("alice@example.com")
            .withPhone("94351253")
            .withTutorialGroup("TG01")
            .withGradeMap()
            .withAttendMap()
            .withTags("friends").build();
    public static final Person BENSON = new PersonBuilder().withName("Benson Meier")
            .withTeleHandle("@benson")
            .withEmail("johnd@example.com")
            .withPhone("98765432")
            .withTutorialGroup("TG02")
            .withGradeMap()
            .withAttendMap()
            .withTags("owesMoney", "friends").build();
    public static final Person CARL = new PersonBuilder().withName("Carl Kurz")
            .withPhone("95352563")
            .withTutorialGroup("TG01")
            .withEmail("heinz@example.com")
            .withTeleHandle("@carl")
            .withGradeMap()
            .withAttendMap().build();
    public static final Person DANIEL = new PersonBuilder().withName("Daniel Meier")
            .withPhone("87652533")
            .withEmail("cornelia@example.com")
            .withTeleHandle("@daniel")
            .withTutorialGroup("TG03")
            .withGradeMap()
            .withAttendMap()
            .withTags("friends").build();
    public static final Person ELLE = new PersonBuilder().withName("Elle Meyer")
            .withPhone("9482224")
            .withTutorialGroup("TG02")
            .withEmail("werner@example.com")
            .withTeleHandle("@elle")
            .withGradeMap()
            .withAttendMap().build();
    public static final Person FIONA = new PersonBuilder().withName("Fiona Kunz")
            .withPhone("9482427")
            .withTutorialGroup("TG03")
            .withEmail("lydia@example.com")
            .withTeleHandle("@fiona")
            .withGradeMap()
            .withAttendMap().build();
    public static final Person GEORGE = new PersonBuilder().withName("George Best")
            .withPhone("9482442")
            .withTutorialGroup("TG01")
            .withEmail("anna@example.com")
            .withTeleHandle("@george")
            .withGradeMap()
            .withAttendMap().build();

    // Manually added
    public static final Person HOON = new PersonBuilder().withName("Hoon Meier").withPhone("8482424")
            .withTutorialGroup("TG01").withEmail("stefan@example.com").withTeleHandle("@hoonie").build();
    public static final Person IDA = new PersonBuilder().withName("Ida Mueller").withPhone("8482131")
            .withTutorialGroup("TG01").withEmail("hans@example.com").withTeleHandle("@idaknow").build();

    // Manually added - Person's details found in {@code CommandTestUtil}
    public static final Person AMY = new PersonBuilder().withName(VALID_NAME_AMY).withPhone(VALID_PHONE_AMY)
            .withTutorialGroup("TG01").withEmail(VALID_EMAIL_AMY)
            .withTeleHandle(VALID_TELEHANDLE_AMY).withTags(VALID_TAG_TUTORIAL2).build();
    public static final Person BOB = new PersonBuilder().withName(VALID_NAME_BOB).withPhone(VALID_PHONE_BOB)
            .withTutorialGroup("TG02").withEmail(VALID_EMAIL_BOB)
            .withTeleHandle(VALID_TELEHANDLE_BOB).withTags(VALID_TAG_TUTORIAL1, VALID_TAG_TUTORIAL2)
            .build();

    public static final String KEYWORD_MATCHING_MEIER = "Meier"; // A keyword that matches MEIER

    private TypicalPersons() {} // prevents instantiation

    /**
     * Returns an {@code AddressBook} with all the typical persons.
     */
    public static AddressBook getTypicalAddressBook() {
        AddressBook ab = new AddressBook();
        for (Person person : getTypicalPersons()) {
            ab.addPerson(person);
        }
        return ab;
    }

    public static List<Person> getTypicalPersons() {
        return new ArrayList<>(Arrays.asList(ALICE, BENSON, CARL, DANIEL, ELLE, FIONA, GEORGE));
    }
}
