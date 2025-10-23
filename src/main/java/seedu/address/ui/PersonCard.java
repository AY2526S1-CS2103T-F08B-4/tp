package seedu.address.ui;

import java.util.Comparator;

import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.Region;
import seedu.address.model.person.Person;

/**
 * A UI component that displays information of a {@code Person}.
 */
public class PersonCard extends UiPart<Region> {

    private static final String FXML = "PersonListCard.fxml";

    /**
     * Note: Certain keywords such as "location" and "resources" are reserved keywords in JavaFX.
     * As a consequence, UI elements' variable names cannot be set to such keywords
     * or an exception will be thrown by JavaFX during runtime.
     *
     * @see <a href="https://github.com/se-edu/addressbook-level4/issues/336">The issue on AddressBook level 4</a>
     */

    public final Person person;

    @FXML
    private HBox cardPane;
    @FXML
    private Label name;
    @FXML
    private Label id;
    @FXML
    private Label phone;
    @FXML
    private Label teleHandle;
    @FXML
    private Label email;
    @FXML
    private Label grade;
    @FXML
    private FlowPane tutorialGroupAndTags;
    @FXML
    private Label attend;

    /**
     * Creates a {@code PersonCode} with the given {@code Person} and index to display.
     */
    public PersonCard(Person person, int displayedIndex) {
        super(FXML);
        this.person = person;
        id.setText(displayedIndex + ". ");
        name.setText(person.getName().fullName);
        phone.setText(person.getPhone().value);
        teleHandle.setText(person.getTeleHandle().value);
        String tutorialGroup = person.getTutorialGroup().value;
        if (!tutorialGroup.isEmpty()) {
            Label tutorialLabel = new Label(tutorialGroup);
            tutorialLabel.getStyleClass().add("tutorial-label");
            tutorialGroupAndTags.getChildren().add(tutorialLabel);
        }
        email.setText(person.getEmail().value);
        grade.setText(getGradeText());
        attend.setText(getAttendText());
        person.getTags().stream()
            .sorted(Comparator.comparing(tag -> tag.tagName))
            .forEach(tag -> tutorialGroupAndTags.getChildren().add(new Label(tag.tagName)));
    }

    /**
     * Returns the average grade text to be displayed.
     * Verbose subclass can override this method to provide different grade text.
     */
    protected String getGradeText() {
        String overallGrade = person.getOverallGrade().value;
        return overallGrade.isBlank() ? "No grades yet." : "Overall grade: " + overallGrade;
    }

    /**
     * Returns the overall attendance text to be displayed.
     * Verbose subclass can override this method to provide different attendance text.
     */
    protected String getAttendText() {
        return "Overall attendance: " + person.getOverallAttendance();
    }
}
