package seedu.address.logic.commands;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;
import static seedu.address.testutil.TypicalIndexes.INDEX_SECOND_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.model.person.TutorialClass;

public class AttendCommandTest {

    @Test
    public void equals() {
        AttendCommand commandWithTut1 = new AttendCommand(INDEX_FIRST_PERSON, TutorialClass.t1);

        // same values -> returns true
        AttendCommand commandWithSameTut1 = new AttendCommand(INDEX_FIRST_PERSON, TutorialClass.t1);
        assertTrue(commandWithTut1.equals(commandWithSameTut1));

        // same object -> returns true
        assertTrue(commandWithTut1.equals(commandWithTut1));

        // null -> returns false
        assertFalse(commandWithTut1.equals(null));

        // different types -> returns false
        assertFalse(commandWithTut1.equals(new ClearCommand()));

        // different index -> returns false
        assertFalse(commandWithTut1.equals(new AttendCommand(INDEX_SECOND_PERSON, TutorialClass.t1)));

        // different tutorial class -> returns false
        assertFalse(commandWithTut1.equals(new AttendCommand(INDEX_FIRST_PERSON, TutorialClass.t7)));
    }
}
