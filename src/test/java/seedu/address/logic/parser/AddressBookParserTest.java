package seedu.address.logic.parser;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertTrue;
import static seedu.address.logic.Messages.MESSAGE_INVALID_COMMAND_FORMAT;
import static seedu.address.logic.Messages.MESSAGE_UNKNOWN_COMMAND;
import static seedu.address.logic.parser.CliSyntax.PREFIX_ASSIGNMENT;
import static seedu.address.logic.parser.CliSyntax.PREFIX_GRADE;
import static seedu.address.testutil.Assert.assertThrows;
import static seedu.address.testutil.TypicalIndexes.INDEX_FIRST_PERSON;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.junit.jupiter.api.Test;

import seedu.address.logic.commands.AddCommand;
import seedu.address.logic.commands.ClearCommand;
import seedu.address.logic.commands.DeleteCommand;
import seedu.address.logic.commands.EditCommand;
import seedu.address.logic.commands.EditCommand.EditPersonDescriptor;
import seedu.address.logic.commands.ExitCommand;
import seedu.address.logic.commands.FindCommand;
import seedu.address.logic.commands.GradeCommand;
import seedu.address.logic.commands.HelpCommand;
import seedu.address.logic.commands.ListCommand;
import seedu.address.logic.parser.exceptions.ParseException;
import seedu.address.model.person.Assignments;
import seedu.address.model.person.Grade;
import seedu.address.model.person.Person;
import seedu.address.model.person.PersonContainsKeywords;
import seedu.address.testutil.EditPersonDescriptorBuilder;
import seedu.address.testutil.PersonBuilder;
import seedu.address.testutil.PersonUtil;

public class AddressBookParserTest {

    private final AddressBookParser parser = new AddressBookParser();

    @Test
    public void parseCommand_add() throws Exception {
        Person person = new PersonBuilder().build();
        AddCommand command = (AddCommand) parser.parseCommand(PersonUtil.getAddCommand(person));
        assertEquals(new AddCommand(person), command);
    }
    @Test
    public void parseCommand_clear() throws Exception {
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD) instanceof ClearCommand);
        assertTrue(parser.parseCommand(ClearCommand.COMMAND_WORD + " 3") instanceof ClearCommand);
    }

    @Test
    public void parseCommand_delete() throws Exception {
        DeleteCommand command = (DeleteCommand) parser.parseCommand(
                DeleteCommand.COMMAND_WORD + " " + INDEX_FIRST_PERSON.getOneBased());
        assertEquals(new DeleteCommand(INDEX_FIRST_PERSON), command);
    }

    @Test
    public void parseCommand_edit() throws Exception {
        Person person = new PersonBuilder().build();
        EditPersonDescriptor descriptor = new EditPersonDescriptorBuilder(person).build();
        EditCommand command = (EditCommand) parser.parseCommand(EditCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased() + " " + PersonUtil.getEditPersonDescriptorDetails(descriptor));
        assertEquals(new EditCommand(INDEX_FIRST_PERSON, descriptor), command);
    }

    @Test
    public void parseCommand_exit() throws Exception {
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD) instanceof ExitCommand);
        assertTrue(parser.parseCommand(ExitCommand.COMMAND_WORD + " 3") instanceof ExitCommand);
    }

    @Test
    public void parseCommand_find() throws Exception {
        // Fallback behavior: no prefixes -> name keywords
        List<String> keywords = Arrays.asList("foo", "bar", "baz");
        FindCommand command = (FindCommand) parser.parseCommand(
                FindCommand.COMMAND_WORD + " " + keywords.stream().collect(Collectors.joining(" ")));
        assertEquals(new FindCommand(new PersonContainsKeywords(keywords, List.of(), List.of(), List.of())), command);
    }

    @Test
    public void parseCommand_grade() throws Exception {
        Grade grade = new Grade("78.50");
        Assignments q1 = Assignments.Q1;
        GradeCommand command;
        command = (GradeCommand) parser.parseCommand(GradeCommand.COMMAND_WORD + " "
                + INDEX_FIRST_PERSON.getOneBased()
                + " " + PREFIX_GRADE + grade.value
                + " " + PREFIX_ASSIGNMENT + q1.name());
        assertEquals(new GradeCommand(INDEX_FIRST_PERSON, grade, q1), command);
    }

    @Test
    public void parseCommand_help() throws Exception {
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD) instanceof HelpCommand);
        assertTrue(parser.parseCommand(HelpCommand.COMMAND_WORD + " 3") instanceof HelpCommand);
    }

    @Test
    public void parseCommand_list() throws Exception {
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD) instanceof ListCommand);
        assertTrue(parser.parseCommand(ListCommand.COMMAND_WORD + " 3") instanceof ListCommand);
    }

    @Test
    public void parseCommand_unrecognisedInput_throwsParseException() {
        assertThrows(ParseException.class, String.format(MESSAGE_INVALID_COMMAND_FORMAT, HelpCommand.MESSAGE_USAGE), ()
            -> parser.parseCommand(""));
    }

    @Test
    public void parseCommand_unknownCommand_throwsParseException() {
        assertThrows(ParseException.class, MESSAGE_UNKNOWN_COMMAND, () -> parser.parseCommand("unknownCommand"));
    }

    @Test
    public void fuzzyMatch_addCommand() throws Exception {
        assertEquals(AddCommand.COMMAND_WORD, parser.fuzzyMatch(AddCommand.FUZZY_COMMAND_WORD));
    }

    @Test
    public void fuzzyMatch_clearCommand() throws Exception {
        assertEquals(ClearCommand.COMMAND_WORD, parser.fuzzyMatch(ClearCommand.FUZZY_COMMAND_WORD));
    }

    @Test
    public void fuzzyMatch_deleteCommand() throws Exception {
        assertEquals(DeleteCommand.COMMAND_WORD, parser.fuzzyMatch(DeleteCommand.FUZZY_COMMAND_WORD));
    }

    @Test
    public void fuzzyMatch_exitCommand() throws Exception {
        assertEquals(ExitCommand.COMMAND_WORD, parser.fuzzyMatch(ExitCommand.FUZZY_COMMAND_WORD));
    }

    @Test
    public void fuzzyMatch_findCommand() throws Exception {
        assertEquals(FindCommand.COMMAND_WORD, parser.fuzzyMatch(FindCommand.FUZZY_COMMAND_WORD));
    }

    @Test
    public void fuzzyMatch_gradeCommand() throws Exception {
        assertEquals(GradeCommand.COMMAND_WORD, parser.fuzzyMatch(GradeCommand.FUZZY_COMMAND_WORD));
    }

    @Test
    public void fuzzyMatch_helpCommand() throws Exception {
        assertEquals(HelpCommand.COMMAND_WORD, parser.fuzzyMatch(HelpCommand.FUZZY_COMMAND_WORD));
    }

    @Test
    public void fuzzyMatch_listCommand() throws Exception {
        assertEquals(ListCommand.COMMAND_WORD, parser.fuzzyMatch(ListCommand.FUZZY_COMMAND_WORD));
    }
}
