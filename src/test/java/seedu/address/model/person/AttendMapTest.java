package seedu.address.model.person;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;

import org.junit.jupiter.api.Test;

public class AttendMapTest {

    @Test
    public void constructor_initializesAllTutorialClassWithZero() {
        AttendMap attendMap = new AttendMap();
        for (TutorialClass tut : TutorialClass.values()) {
            assertFalse(attendMap.isPresent(tut));
        }
    }

    @Test
    public void markPresent_marksAsPresent() {
        AttendMap attendMap = new AttendMap();
        attendMap.markPresent(TutorialClass.t1);
        assertTrue(attendMap.isPresent(TutorialClass.t1));
    }

    @Test
    public void markAbsent_marksAsAbsent() {
        AttendMap attendMap = new AttendMap();
        attendMap.markPresent(TutorialClass.t2);
        attendMap.markAbsent(TutorialClass.t2);
        assertFalse(attendMap.isPresent(TutorialClass.t2));
    }

    @Test
    public void getOverallAttendance_allAbsent_returnsZeroOutOfTotal() {
        AttendMap attendMap = new AttendMap();
        String expected = "0/" + TutorialClass.values().length;
        assertEquals(expected, attendMap.getOverallAttendance());
    }

    @Test
    public void getOverallAttendance_allPresent_returnsCorrectRatio() {
        AttendMap attendMap = new AttendMap();
        attendMap.markPresent(TutorialClass.t1);
        attendMap.markPresent(TutorialClass.t2);
        attendMap.markPresent(TutorialClass.t3);
        attendMap.markPresent(TutorialClass.t4);
        attendMap.markPresent(TutorialClass.t5);
        attendMap.markPresent(TutorialClass.t6);
        attendMap.markPresent(TutorialClass.t7);
        attendMap.markPresent(TutorialClass.t8);
        attendMap.markPresent(TutorialClass.t9);
        attendMap.markPresent(TutorialClass.t10);
        attendMap.markPresent(TutorialClass.t11);
        String expected = "11/" + TutorialClass.values().length;
        assertEquals(expected, attendMap.getOverallAttendance());
    }

    @Test
    public void equals_sameObject_returnsTrue() {
        AttendMap attendMap = new AttendMap();
        assertTrue(attendMap.equals(attendMap));
    }

    @Test
    public void equals_differentObject_returnsFalse() {
        AttendMap attendMap = new AttendMap();
        assertFalse(attendMap.equals("String obj"));
    }

    @Test
    public void equals_identicalMaps_returnsTrue() {
        AttendMap attendMap = new AttendMap();
        AttendMap copyOfAttendMap = new AttendMap(attendMap);
        assertEquals(attendMap, copyOfAttendMap);
    }

    @Test
    public void equals_differentMaps_returnsFalse() {
        AttendMap attendMap = new AttendMap();
        AttendMap differentAttendMap = new AttendMap();
        differentAttendMap.markPresent(TutorialClass.t1);
        assertNotEquals(attendMap, differentAttendMap);
    }
}
