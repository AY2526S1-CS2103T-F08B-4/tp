package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.commands.CommandTestUtil.VALID_EMAIL_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_GRADE_AMY;
import static seedu.address.logic.commands.CommandTestUtil.VALID_NAME_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_PHONE_BOB;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TAG_TUTORIAL1;
import static seedu.address.logic.commands.CommandTestUtil.VALID_TELEHANDLE_BOB;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalPersons.ALICE;
import static seedu.address.testutil.TypicalPersons.BOB;

import org.junit.jupiter.api.Test;

import seedu.address.testutil.PersonBuilder;

public class PersonTest {

    @Test
    public void asObservableList_modifyList_throwsUnsupportedOperationException() {
        Person person = new PersonBuilder().build();
        assertThrows(UnsupportedOperationException.class, () -> person.getTags().remove(0));
    }

    @Test
    public void isSamePerson() {
        // same object -> returns true
        assertTrue(ALICE.isSamePerson(ALICE));

        // null -> returns false
        assertFalse(ALICE.isSamePerson(null));

        // same name, all other attributes different -> returns true
        Person editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).withEmail(VALID_EMAIL_BOB)
                .withTeleHandle(VALID_TELEHANDLE_BOB).withTags(VALID_TAG_TUTORIAL1).build();
        assertTrue(ALICE.isSamePerson(editedAlice));

        // different name, all other attributes same -> returns false
        editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.isSamePerson(editedAlice));

        // name differs in case, all other attributes same -> returns false
        Person editedBob = new PersonBuilder(BOB).withName(VALID_NAME_BOB.toLowerCase()).build();
        assertFalse(BOB.isSamePerson(editedBob));

        // name has trailing spaces, all other attributes same -> returns false
        String nameWithTrailingSpaces = VALID_NAME_BOB + " ";
        editedBob = new PersonBuilder(BOB).withName(nameWithTrailingSpaces).build();
        assertFalse(BOB.isSamePerson(editedBob));
    }

    @Test
    public void equals() {
        // same values -> returns true
        Person aliceCopy = new PersonBuilder(ALICE).build();
        assertTrue(ALICE.equals(aliceCopy));

        // same object -> returns true
        assertTrue(ALICE.equals(ALICE));

        // null -> returns false
        assertFalse(ALICE.equals(null));

        // different type -> returns false
        assertFalse(ALICE.equals(5));

        // different person -> returns false
        assertFalse(ALICE.equals(BOB));

        // different name -> returns false
        Person editedAlice = new PersonBuilder(ALICE).withName(VALID_NAME_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different phone -> returns false
        editedAlice = new PersonBuilder(ALICE).withPhone(VALID_PHONE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different email -> returns false
        editedAlice = new PersonBuilder(ALICE).withEmail(VALID_EMAIL_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different teleHandle -> returns false
        editedAlice = new PersonBuilder(ALICE).withTeleHandle(VALID_TELEHANDLE_BOB).build();
        assertFalse(ALICE.equals(editedAlice));

        // different grade -> returns false
        GradeMap gradeMap = ALICE.getGradeMap();
        GradeMap clonedGradeMap = (GradeMap) gradeMap.clone();
        clonedGradeMap.put(Assignments.Q1, new Grade(VALID_GRADE_AMY));
        editedAlice = new PersonBuilder(ALICE).withGradeMap(clonedGradeMap).build();
        assertFalse(ALICE.equals(editedAlice));

        // different attendance, mark present -> returns false
        AttendMap attendMap = ALICE.getAttendMap();
        AttendMap diffAttendMap = new AttendMap(attendMap);
        diffAttendMap.markPresent(TutorialClass.t1);
        editedAlice = new PersonBuilder(ALICE).withAttendMap(diffAttendMap).build();
        assertFalse(ALICE.equals(editedAlice));

        // different attendance, mark absent -> returns false
        AttendMap attendMap1 = ALICE.getAttendMap();
        attendMap1.markPresent(TutorialClass.t1);
        AttendMap diffAttendMap1 = new AttendMap(attendMap1);
        diffAttendMap1.markAbsent(TutorialClass.t1);
        editedAlice = new PersonBuilder(ALICE).withAttendMap(diffAttendMap1).build();
        assertFalse(ALICE.equals(editedAlice));
        attendMap1.markAbsent(TutorialClass.t1);

        // same attendance -> returns true
        AttendMap attendMap2 = ALICE.getAttendMap();
        AttendMap sameAttendMap = new AttendMap(attendMap2);
        Person sameAlice = new PersonBuilder(ALICE).withAttendMap(sameAttendMap).build();
        assertTrue(ALICE.equals(sameAlice));

        // different tags -> returns false
        editedAlice = new PersonBuilder(ALICE).withTags(VALID_TAG_TUTORIAL1).build();
        assertFalse(ALICE.equals(editedAlice));

    }

    @Test
    public void toStringMethod() {
        String expected = Person.class.getCanonicalName() + "{name=" + ALICE.getName() + ", phone=" + ALICE.getPhone()
                + ", email=" + ALICE.getEmail() + ", teleHandle=" + ALICE.getTeleHandle()
                + ", tutorialGroup=" + ALICE.getTutorialGroup()
                + ", gradeMap=" + ALICE.getGradeMap()
                + ", attendMap=" + ALICE.getAttendMap()
                + ", tags=" + ALICE.getTags() + "}";
        assertEquals(expected, ALICE.toString());
    }
}
