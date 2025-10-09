package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's grade in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidGradeConstructor(String)}
 */
public class Grade {

    public static final String MESSAGE_CONSTRAINTS_CONSTRUCT =
            "Grade should be a number between 0.00 and 100.00, with exactly 2 decimal places.";

    /**
     * Strict validation for constructor, exactly 2 decimal places for standardization.
     * Matches 0.00 to 99.99 or exactly 100.00
     */
    public static final String VALIDATION_REGEX_CONSTRUCT = "^(100\\.00|\\d{1,2}\\.\\d{2})$";

    public final String value;

    /**
     * Constructs a {@code Grade}.
     *
     * @param value A valid grade number with exactly 2 decimal places.
     */
    public Grade(String value) {
        requireNonNull(value);
        checkArgument(isValidGradeConstructor(value), MESSAGE_CONSTRAINTS_CONSTRUCT);
        this.value = value;
    }

    /**
     * Returns true if a given string is a valid grade for the constructor.
     * Valid grade: 0.00 to 100.00 and has exactly 2 decimal places
     *
     * @param test the grade string to validate
     * @return true if string is a valid grade
     */
    public static boolean isValidGradeConstructor(String test) {
        return test.matches(VALIDATION_REGEX_CONSTRUCT);
    }

    @Override
    public String toString() {
        return this.value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof Grade)) {
            return false;
        }

        Grade otherGrade = (Grade) other;
        return value.equals(otherGrade.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
