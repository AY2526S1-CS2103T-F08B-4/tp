package seedu.address.logic.commands;

import static java.util.Objects.requireNonNull;
import static seedu.address.commons.util.CollectionUtil.requireAllNonNull;
import static seedu.address.logic.parser.CliSyntax.PREFIX_TUTORIALCLASS;
import static seedu.address.model.Model.PREDICATE_SHOW_ALL_PERSONS;

import java.util.Arrays;
import java.util.List;
import java.util.Set;

import seedu.address.commons.core.index.Index;
import seedu.address.logic.Messages;
import seedu.address.logic.commands.exceptions.CommandException;
import seedu.address.model.Model;
import seedu.address.model.person.AttendMap;
import seedu.address.model.person.Email;
import seedu.address.model.person.GradeMap;
import seedu.address.model.person.Name;
import seedu.address.model.person.Person;
import seedu.address.model.person.Phone;
import seedu.address.model.person.TeleHandle;
import seedu.address.model.person.TutorialClass;
import seedu.address.model.person.TutorialGroup;
import seedu.address.model.tag.Tag;

/**
 * Marks attendance for an existing person in the address book.
 */
public class AttendCommand extends Command {

    public static final String COMMAND_WORD = "attend";
    public static final String FUZZY_COMMAND_WORD = "attendd";

    public static final String MESSAGE_USAGE = COMMAND_WORD
            + ": Marks the attendance of the given tutorial class of the person identified by "
            + "the index number used in the displayed person list.\n"
            + "Parameters: INDEX (must be a positive integer) "
            + PREFIX_TUTORIALCLASS + "TUTORIAL_CLASS\n"
            + "The tutorial class must be one of the following: "
            + Arrays.toString(TutorialClass.getAllTutorialClass()) + ".\n"
            + "Example: " + COMMAND_WORD + " 1 c/t1";

    public static final String MESSAGE_EDIT_PERSON_SUCCESS = "Attendance marked: %1$s";
    public static final String MESSAGE_DUPLICATE_PERSON = "This person already exists in the address book.";

    private final Index index;
    private final TutorialClass tutClass;

    /**
     * @param index of the person in the filtered person list
     * @param tutClass the tutorial class to be marked as present
     */
    public AttendCommand(Index index, TutorialClass tutClass) {
        requireAllNonNull(index, tutClass);

        this.index = index;
        this.tutClass = tutClass;
    }

    /**
     * Creates and returns a {@code Person} with the details of {@code personToEdit}
     * edited with {@code editPersonDescriptor}.
     */
    private Person createAttendancePerson(Person personToEdit) {
        assert personToEdit != null;

        Name name = personToEdit.getName();
        Phone phone = personToEdit.getPhone();
        Email email = personToEdit.getEmail();
        TeleHandle teleHandle = personToEdit.getTeleHandle();
        TutorialGroup tutorialGroup = personToEdit.getTutorialGroup();
        GradeMap gradeMap = personToEdit.getGradeMap();
        AttendMap attendMap = personToEdit.getAttendMap();
        // Removed clone behaviour here since logic would've applied
        // to other fields as well. Either way, normal behaviour would not
        // cause sharing of objects as attributes, and is thus fine to take directly.
        attendMap.markPresent(tutClass);
        Set<Tag> tags = personToEdit.getTags();

        return new Person(name, phone, email, teleHandle, tutorialGroup, gradeMap, attendMap, tags);
    }

    @Override
    public CommandResult execute(Model model) throws CommandException {
        requireNonNull(model);
        List<Person> lastShownList = model.getFilteredPersonList();

        if (index.getZeroBased() >= lastShownList.size()) {
            throw new CommandException(Messages.MESSAGE_INVALID_PERSON_DISPLAYED_INDEX);
        }

        Person personToEdit = lastShownList.get(index.getZeroBased());
        Person attendancePerson = createAttendancePerson(personToEdit);

        if (!personToEdit.isSamePerson(attendancePerson) && model.hasPerson(attendancePerson)) {
            throw new CommandException(MESSAGE_DUPLICATE_PERSON);
        }

        model.setPerson(personToEdit, attendancePerson);
        model.updateFilteredPersonList(PREDICATE_SHOW_ALL_PERSONS);
        return new CommandResult(String.format(MESSAGE_EDIT_PERSON_SUCCESS, Messages.format(attendancePerson)));
    }

    @Override
    public boolean equals(Object other) {
        if (other == this) {
            return true;
        }

        // instanceof handles nulls
        if (!(other instanceof AttendCommand)) {
            return false;
        }

        AttendCommand e = (AttendCommand) other;
        return index.equals(e.index)
                && tutClass.equals(e.tutClass);
    }

}
