package seedu.address.model.person;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.AppUtil.checkArgument;

/**
 * Represents a Person's Tutorial Group in the address book.
 * Guarantees: immutable; is valid as declared in {@link #isValidTutorial(String)}
 */
public class TutorialGroup {

    public static final String MESSAGE_CONSTRAINTS = "Tutorial group should start with 'TG' followed by digits, "
            + "or be left blank to indicate no tutorial group assigned.";

    public static final String VALIDATION_REGEX = "(TG\\d+)?";
    public static final String DEFAULT_TUTORIAL_GROUP = "";

    public final String value;

    /**
     * Constructs a {@code Tutorial}.
     *
     * @param tutorialGroup A valid tutorial name.
     */
    public TutorialGroup(String tutorialGroup) {
        requireNonNull(tutorialGroup);
        checkArgument(isValidTutorial(tutorialGroup), MESSAGE_CONSTRAINTS);
        value = tutorialGroup;
    }

    public static boolean isValidTutorial(String test) {
        return test.matches(VALIDATION_REGEX);
    }

    @Override
    public String toString() {
        return value;
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof TutorialGroup)) {
            return false;
        }

        TutorialGroup otherTutorialGroup = (TutorialGroup) other;
        return value.equals(otherTutorialGroup.value);
    }

    @Override
    public int hashCode() {
        return value.hashCode();
    }

}
