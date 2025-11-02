package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_ENTERED_MULTIPLE_INDEXES;
import static seedu.address.logic.Messages.MESSAGE_MISSING_FIELDS;
import static seedu.address.logic.Messages.MESSAGE_MISSING_INDEX;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.util.StringUtil;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Assignments;
import seedu.address.model.person.Email;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Name;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TeleHandle;
import seedu.address.model.person.TutorialClass;
import seedu.address.model.person.TutorialGroup;
import seedu.address.model.tag.Tag;

/**
 * Contains utility methods used for parsing strings in the various *Parser classes.
 */
public class ParserUtil {

    public static final String MESSAGE_INVALID_INDEX = "Index must be a non-zero positive integer.";

    /**
     * Parses {@code oneBasedIndex} into an {@code Index} and returns it. Leading and trailing whitespaces will be
     * trimmed.
     *
     * @throws ParseException if the specified index is invalid (not non-zero unsigned integer).
     */
    public static Index parseIndex(String oneBasedIndex) throws ParseException {
        String trimmedIndex = oneBasedIndex.trim();
        String[] parts = trimmedIndex.split("\\s+");
        if (!StringUtil.isNonZeroUnsignedInteger(trimmedIndex)) {
            // if there is one an only one digit in the string
            if (trimmedIndex.matches("^\\D*\\d\\D*$")) {
                try {
                    if (Integer.parseInt(trimmedIndex) <= 0) {
                        throw new ParseException(MESSAGE_MISSING_INDEX);
                    }
                    throw new ParseException(MESSAGE_MISSING_FIELDS);
                } catch (NumberFormatException e) {
                    throw new ParseException(MESSAGE_MISSING_FIELDS);
                }
            }
            if (parts.length > 1) {
                throw new ParseException(MESSAGE_ENTERED_MULTIPLE_INDEXES);
            } else {
                throw new ParseException(MESSAGE_MISSING_INDEX);
            }
        }
        return Index.fromOneBased(Integer.parseInt(trimmedIndex));
    }

    /**
     * Parses a string of one-based indices into a list of {@code Index} objects.
     *
     * @param oneBasedIndices a string containing space-separated one-based indices.
     * @return a list of {@code Index} objects.
     * @throws ParseException if any of the specified indices are invalid.
     */
    public static List<Index> parseIndices(String oneBasedIndices) throws ParseException {
        String[] indexStrings = oneBasedIndices.trim().split("\\s+");
        if (indexStrings.length == 0) {
            throw new ParseException(MESSAGE_INVALID_INDEX);
        }
        List<Index> indices = new ArrayList<>();
        for (String indexString : indexStrings) {
            indices.add(parseIndex(indexString));
        }
        return indices;
    }

    /**
     * Parses a {@code String name} into a {@code Name}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code name} is invalid.
     */
    public static Name parseName(String name) throws ParseException {
        requireNonNull(name);
        String trimmedName = name.trim();
        if (!Name.isValidName(trimmedName)) {
            throw new ParseException(Name.MESSAGE_CONSTRAINTS);
        }
        return new Name(trimmedName);
    }

    /**
     * Parses a {@code String phone} into a {@code Phone}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code phone} is invalid.
     */
    public static Phone parsePhone(String phone) throws ParseException {
        requireNonNull(phone);
        String trimmedPhone = phone.trim();
        if (!Phone.isValidPhone(trimmedPhone)) {
            throw new ParseException(Phone.MESSAGE_CONSTRAINTS);
        }
        return new Phone(trimmedPhone);
    }

    /**
     * Parses a {@code String teleHandle} into an {@code TeleHandle}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code teleHandle} is invalid.
     */
    public static TeleHandle parseTeleHandle(String teleHandle) throws ParseException {
        requireNonNull(teleHandle);
        String trimmedTeleHandle = teleHandle.trim();
        if (!TeleHandle.isValidTeleHandle(trimmedTeleHandle)) {
            throw new ParseException(TeleHandle.MESSAGE_CONSTRAINTS);
        }
        return new TeleHandle(trimmedTeleHandle);
    }

    /**
     * Parses a {@code String email} into an {@code Email}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code email} is invalid.
     */
    public static Email parseEmail(String email) throws ParseException {
        requireNonNull(email);
        String trimmedEmail = email.trim();
        if (!Email.isValidEmail(trimmedEmail)) {
            throw new ParseException(Email.MESSAGE_CONSTRAINTS);
        }
        return new Email(trimmedEmail);
    }

    /**
     * Parses a {@code String tag} into a {@code Tag}.
     * Leading and trailing whitespaces will be trimmed.
     *
     * @throws ParseException if the given {@code tag} is invalid.
     */
    public static Tag parseTag(String tag) throws ParseException {
        requireNonNull(tag);
        String trimmedTag = tag.trim();
        if (!Tag.isValidTagName(trimmedTag)) {
            throw new ParseException(Tag.MESSAGE_CONSTRAINTS);
        }
        return new Tag(trimmedTag);
    }

    /**
     * Parses {@code Collection<String> tags} into a {@code Set<Tag>}.
     */
    public static Set<Tag> parseTags(Collection<String> tags) throws ParseException {
        requireNonNull(tags);
        final Set<Tag> tagSet = new HashSet<>();
        for (String tagName : tags) {
            tagSet.add(parseTag(tagName));
        }
        return tagSet;
    }

    /**
     * Parses {@code String grade} into a {@code Grade}.
     */
    public static Grade parseGrade(String grade) throws ParseException {
        requireNonNull(grade);
        String trimmedGrade = grade.trim();
        float floatGrade;

        // Allow empty grade values, which will reset it to the default value
        if (trimmedGrade.isEmpty()) {
            return new Grade(" ");
        }

        // Ensure its numeric
        try {
            floatGrade = Float.parseFloat(trimmedGrade);
        } catch (NumberFormatException e) {
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }

        // Round to 2 d.p.
        String formattedGrade = String.format("%.2f", floatGrade);

        if (!Grade.isValidGrade(formattedGrade)) {
            throw new ParseException(Grade.MESSAGE_CONSTRAINTS);
        }
        return new Grade(formattedGrade);
    }

    /**
     * Parses {@code String assignment} into an {@code Assignments}.
     */
    public static Assignments parseAssignment(String assignment) throws ParseException {
        requireNonNull(assignment);
        String trimmedAssignment = assignment.trim();

        // Don't allow empty assignment values, inside command
        if (trimmedAssignment.isEmpty() || !Assignments.isValidAssignment(trimmedAssignment)) {
            throw new ParseException(Assignments.MESSAGE_CONSTRAINTS);
        }
        return Assignments.fromString(trimmedAssignment);
    }

    /**
     * Parses {@code String tutorialGroup} into an {@code TutorialGroup}.
     */
    public static TutorialGroup parseTutorialGroup(String tutorialGroup) throws ParseException {
        requireNonNull(tutorialGroup);
        String trimmedTutorialGroup = tutorialGroup.trim().toUpperCase();
        String storedTutorialGroup = "TG";
        try {
            // Parse the numeric part and format it to 2 digits for flexibility
            if (trimmedTutorialGroup.startsWith("TG")) {
                String numberPart = trimmedTutorialGroup.substring(2);
                int parsedNumber = Integer.parseInt(numberPart);
                storedTutorialGroup += String.format("%02d", parsedNumber);
            }
        } catch (NumberFormatException e) {
            throw new ParseException(TutorialGroup.MESSAGE_CONSTRAINTS);
        }

        if (!TutorialGroup.isValidTutorialGroup(storedTutorialGroup)) {
            throw new ParseException(TutorialGroup.MESSAGE_CONSTRAINTS);
        }
        return new TutorialGroup(storedTutorialGroup);
    }

    /**
     * Parses {@code String tutClass} into an {@code TutorialClass}.
     */
    public static TutorialClass parseTutorialClass(String tutClass) throws ParseException {
        requireNonNull(tutClass);
        String trimmedTutClass = tutClass.trim();

        // Don't allow empty tutorial class values, inside command
        if (trimmedTutClass.isEmpty() || !TutorialClass.isValidTutorialClass(trimmedTutClass)) {
            throw new ParseException(TutorialClass.MESSAGE_CONSTRAINTS);
        }
        return TutorialClass.fromString(trimmedTutClass);
    }
}
