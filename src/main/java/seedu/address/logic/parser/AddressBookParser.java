package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;

import java.util.List;
import java.util.logging.Logger;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.apache.commons.text.similarity.LevenshteinDistance;

import seedu.address.commons.core.LogsCenter;
import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.AttendCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.Command;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GradeCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.commands.UnattendCommand;
import seedu.address.logic.parser.exceptions.ParseException;

/**
 * Parses user input.
 */
public class AddressBookParser {

    /**
     * Used for initial separation of command word and args.
     */
    private static final Pattern BASIC_COMMAND_FORMAT = Pattern.compile("(?<commandWord>\\S+)(?<arguments>.*)");
    private static final Logger logger = LogsCenter.getLogger(AddressBookParser.class);
    /**
     * Used for fuzzy match
     */
    private static final int MAX_DIST_THRESHOLD = 1;
    private static final List<String> COMMAND_KEYWORDS = List.of(
        AddCommand.COMMAND_WORD, EditCommand.COMMAND_WORD, DeleteCommand.COMMAND_WORD,
        ClearCommand.COMMAND_WORD, FindCommand.COMMAND_WORD, ListCommand.COMMAND_WORD,
        ExitCommand.COMMAND_WORD, HelpCommand.COMMAND_WORD, GradeCommand.COMMAND_WORD,
        AttendCommand.COMMAND_WORD, UnattendCommand.COMMAND_WORD
    );
    /**
     * Parses user input into command for execution.
     *
     * @param userInput full user input string
     * @return the command based on the user input
     * @throws ParseException if the user input does not conform the expected format
     */
    public Command parseCommand(String userInput) throws ParseException {
        final Matcher matcher = BASIC_COMMAND_FORMAT.matcher(userInput.trim());
        if (!matcher.matches()) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE));
        }

        String commandWord = matcher.group("commandWord");
        final String arguments = matcher.group("arguments");

        commandWord = fuzzyMatch(commandWord);
        // Note to developers: Change the log level in config.json to enable lower level (i.e., FINE, FINER and lower)
        // log messages such as the one below.
        // Lower level log messages are used sparingly to minimize noise in the code.
        logger.fine("Command word: " + commandWord + "; Arguments: " + arguments);

        switch (commandWord) {

        case AddCommand.COMMAND_WORD:
            return new AddCommandParser().parse(arguments);

        case EditCommand.COMMAND_WORD:
            return new EditCommandParser().parse(arguments);

        case DeleteCommand.COMMAND_WORD:
            return new DeleteCommandParser().parse(arguments);

        case ClearCommand.COMMAND_WORD:
            return new ClearCommand();

        case FindCommand.COMMAND_WORD:
            return new FindCommandParser().parse(arguments);

        case ListCommand.COMMAND_WORD:
            return new ListCommand();

        case ExitCommand.COMMAND_WORD:
            return new ExitCommand();

        case HelpCommand.COMMAND_WORD:
            return new HelpCommand();

        case GradeCommand.COMMAND_WORD:
            return new GradeCommandParser().parse(arguments);

        case AttendCommand.COMMAND_WORD:
            return new AttendCommandParser().parse(arguments);

        case UnattendCommand.COMMAND_WORD:
            return new UnattendCommandParser().parse(arguments);

        default:
            logger.finer("This user input caused a ParseException: " + userInput);
            throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
        }
    }

    /**
     * Attempts to fuzzy match the given command word to a known keyword.
     * Checks for an exact (case-sensitive) match first. If none is found, compares
     * the input against known command keywords using Levenshtein distance with a
     * maximum allowed distance of 1 and returns the closest matching keyword when
     * within that threshold.
     *
     * @param commandWord the command word to match
     * @return the matched keyword (exact or fuzzy)
     * @throws ParseException if no close match is found
     */
    public String fuzzyMatch(String commandWord) throws ParseException {
        final LevenshteinDistance levDist = new LevenshteinDistance(MAX_DIST_THRESHOLD);
        if (COMMAND_KEYWORDS.contains(commandWord)) {
            return commandWord;
        }
        for (String keyword : COMMAND_KEYWORDS) {
            if (levDist.apply(keyword, commandWord) != -1) {
                return keyword;
            }
        }
        throw new ParseException(MESSAGE_UNKNOWN_COMMAND);
    }
}
