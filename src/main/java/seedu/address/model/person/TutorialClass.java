package seedu.address.model.person;

import java.util.Arrays;

/**
 * Represents a tutorial class in the address book.
 */
public enum TutorialClass {
    t1,
    t2,
    t3,
    t4,
    t5,
    t6,
    t7,
    t8,
    t9,
    t10,
    t11;

    public static final String MESSAGE_CONSTRAINTS =
            "Tutorial class should be one of the following: "
                    + Arrays.toString(TutorialClass.getAllTutorialClass());
    /**
     * Returns an array of all tutorial class.
     */
    public static TutorialClass[] getAllTutorialClass() {
        return TutorialClass.values();
    }

    /**
     * Returns true if a given string is a valid TutorialClass type.
     */
    public static boolean isValidTutorialClass(String test) {
        for (TutorialClass tutClass : TutorialClass.values()) {
            if (tutClass.name().equals(test)) {
                return true;
            }
        }
        return false;
    }

    /**
     * Returns the tutorial class corresponding to the given string.
     * Throws IllegalArgumentException if no such tutorial class exists.
     */
    public static TutorialClass fromString(String part) {
        for (TutorialClass tutClass : TutorialClass.values()) {
            if (tutClass.name().equals(part)) {
                return tutClass;
            }
        }
        throw new IllegalArgumentException("No tutorial class with name: " + part);
    }

    /**
     * Returns a user-friendly description of the tutorial class type.
     */
    public String getDescription() {
        switch (this) {
        case t1: return "Tutorial 1";
        case t2: return "Tutorial 2";
        case t3: return "Tutorial 3";
        case t4: return "Tutorial 4";
        case t5: return "Tutorial 5";
        case t6: return "Tutorial 6";
        case t7: return "Tutorial 7";
        case t8: return "Tutorial 8";
        case t9: return "Tutorial 9";
        case t10: return "Tutorial 10";
        case t11: return "Tutorial 11";
        default: return "";
        }
    }
}
