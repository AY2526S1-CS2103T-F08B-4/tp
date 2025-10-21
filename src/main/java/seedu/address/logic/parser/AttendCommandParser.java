package seedu.address.logic.parser;

import static java.util.Objects.requireNonNull;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ATTEND;

import seedu.address.commons.core.index.Index;
import seedu.address.commons.exceptions.IllegalValueException;
import seedu.address.logic.commands.AttendCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.TutorialClass;

/**
 * Parses input arguments and creates a new AttendCommand object
 */
public class AttendCommandParser implements Parser<AttendCommand> {

    /**
     * Parses the given {@code String} of arguments in the context of the AttendCommand
     * and returns a AttendCommand object for execution.
     *
     * @throws ParseException if the user input does not conform the expected format
     */
    public AttendCommand parse(String args) throws ParseException {
        requireNonNull(args);
        ArgumentMultimap argMultimap = ArgumentTokenizer.tokenize(args,
                PREFIX_ATTEND);

        Index index;
        TutorialClass tutClass;

        try {
            index = ParserUtil.parseIndex(argMultimap.getPreamble());

            String tutorialClassName = argMultimap.getValue(PREFIX_ATTEND).orElse("");
            tutClass = ParserUtil.parseTutorialClass(tutorialClassName);
        } catch (IllegalValueException | NumberFormatException ive) {
            throw new ParseException(String.format(MESSAGE_INVALID_COMMAND_FORMAT,
                    AttendCommand.MESSAGE_USAGE), ive);
        }

        argMultimap.verifyNoDuplicatePrefixesFor(PREFIX_ATTEND);

        return new AttendCommand(index, tutClass);
    }

}
