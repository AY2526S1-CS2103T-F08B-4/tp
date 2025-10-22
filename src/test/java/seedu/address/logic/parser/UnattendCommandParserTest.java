package seedu.address.logic.parser;

import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALCLASS;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseFailure;
import static seedu.address.logic.parser.CommandParserTestUtil.assertParseSuccess;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import org.junit.jupiter.api.Test;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.commands.UnattendCommand;
import seedu.address.model.person.TutorialClass;


public class UnattendCommandParserTest {

    private UnattendCommandParser parser = new UnattendCommandParser();

    @Test
    public void parse_validArgs_success() {
        Index targetIndex = INDEX_FIRST_PERSON;
        TutorialClass validTutClass = TutorialClass.t1;

        String userInput = targetIndex.getOneBased() + " " + PREFIX_TUTORIALCLASS + validTutClass.name();

        UnattendCommand expectedCommand = new UnattendCommand(targetIndex, validTutClass);
        assertParseSuccess(parser, userInput, expectedCommand);
    }

    @Test
    public void parse_missingTutorialClass_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " ";

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnattendCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidIndex_failure() {
        String userInput = "a " + PREFIX_TUTORIALCLASS + "t1";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnattendCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_invalidTutorialClass_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_TUTORIALCLASS + "t12";

        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnattendCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }

    @Test
    public void parse_extraArguments_failure() {
        Index targetIndex = INDEX_FIRST_PERSON;
        String userInput = targetIndex.getOneBased() + " " + PREFIX_TUTORIALCLASS + "t1 hello";
        String expectedMessage = String.format(MESSAGE_INVALID_COMMAND_FORMAT, UnattendCommand.MESSAGE_USAGE);
        assertParseFailure(parser, userInput, expectedMessage);
    }
}
