package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class TutorialClassTest {

    @Test
    public void isValidTutorialClass_validTutorialClasses_returnTrue() {
        assertTrue(TutorialClass.isValidTutorialClass("t1"));
        assertTrue(TutorialClass.isValidTutorialClass("t5"));
        assertTrue(TutorialClass.isValidTutorialClass("t11"));
    }

    @Test
    public void isValidTutorialClass_invalidTutorialClasses_returnFalse() {
        assertFalse(TutorialClass.isValidTutorialClass("t0"));
        assertFalse(TutorialClass.isValidTutorialClass("t12"));
        assertFalse(TutorialClass.isValidTutorialClass("T1")); // case-sensitive
        assertFalse(TutorialClass.isValidTutorialClass("tutorial1"));
        assertFalse(TutorialClass.isValidTutorialClass(""));
        assertFalse(TutorialClass.isValidTutorialClass(" "));
        assertFalse(TutorialClass.isValidTutorialClass(null));
    }

    @Test
    public void fromString_validInput_success() {
        for (TutorialClass tutClass : TutorialClass.values()) {
            assert(TutorialClass.fromString(tutClass.name()) == tutClass);
        }
    }

    @Test
    public void fromString_validInput_returnsEnumValue() {
        assertEquals(TutorialClass.t1, TutorialClass.fromString("t1"));
        assertEquals(TutorialClass.t11, TutorialClass.fromString("t11"));
    }

    @Test
    public void fromString_invalidInput_throwsException() {
        assertThrows(IllegalArgumentException.class, () -> TutorialClass.fromString("t0"));
        assertThrows(IllegalArgumentException.class, () -> TutorialClass.fromString("T1"));
        assertThrows(IllegalArgumentException.class, () -> TutorialClass.fromString("tutorial1"));
        assertThrows(IllegalArgumentException.class, () -> TutorialClass.fromString(""));
    }
}
