/**
 * Controller for the user management interface in the coordinator view of the ticket system.
 * Manages display and interaction with user profiles and event assignments. Provides functionality
 * for viewing user details, assigning coordinators to events, and managing event-coordinator
 * relationships. Displays users with profile images from Gravatar and shows detailed event
 * information including assigned coordinators.
 */
package dk.easv.ticket_system.Controllers.Coordinator;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.Models.EventModel;
import dk.easv.ticket_system.Models.UserModel;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ScrollPane;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.Pane;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class CUserController {
    // Event details display labels
    public Label lblEventTitle;           // Label for displaying selected event's title
    public Label lblLocationEvent;        // Label for displaying selected event's location
    public Label lblEventDate;            // Label for displaying selected event's date range
    public Label lblEventTime;            // Label for displaying selected event's time range
    public Label lblTicketsSold;          // Label for displaying number of tickets sold (unused)
    public Label lblVipPackage;           // Label for displaying VIP package information (unused)
    public Label lblGeneralAdmission;     // Label for displaying general admission info (unused)

    // Coordinator assignment display
    public Label lblCoordinatorsAssigned; // Label header for assigned coordinators section
    public Label lblCoordinatorsAmount;   // Label showing count of assigned coordinators
    public Label lblEventAssignees;       // Label showing names of assigned coordinators
    public Label lblRemoveAssignee;       // Clickable label to remove coordinator assignments

    // Main layout components
    @FXML
    private FlowPane flowPane;            // Main container for the user management view
    private UserModel userModel;          // Model for accessing user data
    private EventModel eventModel;        // Model for accessing event data

    // UI panels and containers
    @FXML
    private Pane customPane1;             // Container for user list
    @FXML
    private VBox vbox1;                   // Vertical layout for user list items
    @FXML
    private Pane customPane2;             // Container for user details
    @FXML
    private Pane customPane3;             // Container for event list
    @FXML
    private VBox vbox3;                   // Vertical layout for event list items
    @FXML
    private Pane customPane4;             // Container for event details

    // User details display labels
    @FXML
    private Label lblFirstName;           // Label for displaying selected user's first name
    @FXML
    private Label lblLastName;            // Label for displaying selected user's last name
    @FXML
    private Label lblEmail;               // Label for displaying selected user's email
    @FXML
    private Label lblPhoneNumber;         // Label for displaying selected user's phone number
    @FXML
    private Label lblRole;                // Label for displaying selected user's role
    @FXML
    private Label lblName;                // Label for displaying selected user's full name
    @FXML
    private Label lblUserCreated;         // Label for user creation timestamp (unused)
    @FXML
    private Button btnNewUser;            // Button to create new users (handler not implemented)
    @FXML
    private ScrollPane scpScrollPane;     // Scrollable container for lists

    // Selection tracking
    private Button selectedUserButton;    // Reference to currently selected user button
    private Button selectedEventButton;   // Reference to currently selected event button
    @FXML
    private ImageView imgSelectedUser;    // Image view for selected user's profile picture

    /**
     * Constructs the controller and initializes the data models.
     * Sets up access to user and event data through the respective models.
     */
    public CUserController() {
        try {
            userModel = new UserModel();
            eventModel = new EventModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Initializes the controller.
     * Populates user and event lists, sets up initial selections,
     * configures event handlers, and sets appropriate dimensions.
     */
    @FXML
    public void initialize() {
        showUserList();
        showEventList();

        // Automatically select the first user if available
        if (!vbox1.getChildren().isEmpty() && vbox1.getChildren().getFirst() instanceof Button firstUserButton) {
            // Get the associated user from userModel
            if (!userModel.getObservableUsers().isEmpty()) {
                User firstUser = userModel.getObservableUsers().getFirst();
                // Programmatically trigger the button click to select the first user
                updateSelectedUser(firstUser, firstUserButton);
            }
        }

        // Automatically select the first event if available
        if (!vbox3.getChildren().isEmpty() && vbox3.getChildren().getFirst() instanceof Button firstEventButton) {
            // Get the associated event from eventModel
            if (!eventModel.getObservableEvents().isEmpty()) {
                Event firstEvent = eventModel.getObservableEvents().getFirst();
                // Programmatically trigger the button click to select the first event
                updateSelectedEvent(firstEvent, firstEventButton);
            }
        }

        // Add click handler for the remove assignee label
        lblRemoveAssignee.setOnMouseClicked(event -> handleRemoveAssignee());

        // Set scroll pane height based on screen size for proper display
        scpScrollPane.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() - 110);
    }

    /**
     * Populates the user list with buttons representing each user.
     * Each button displays user name, email, and profile image from Gravatar.
     * Sets up click handlers to select users and show their details.
     */
    public void showUserList() {
        vbox1.getChildren().clear(); // Clear existing children
        for (User user : userModel.getObservableUsers()) {
            // Create button for each user
            Button button1 = new Button();
            button1.setPrefSize(460, 75);
            button1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
            vbox1.getChildren().add(button1);
            AnchorPane anchorPaneUser1 = new AnchorPane();
            button1.setGraphic(anchorPaneUser1);

            // Display user's full name
            Label labelName1 = new Label(user.getFirstName() + " " + user.getLastName());
            labelName1.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000;");
            anchorPaneUser1.getChildren().add(labelName1);
            AnchorPane.setTopAnchor(labelName1, 10.0);
            AnchorPane.setLeftAnchor(labelName1, 68.0);

            // Display user's email
            Label labelEmail1 = new Label(user.getEmail());
            labelEmail1.setStyle("-fx-font-size: 12px; -fx-text-fill: #555;");
            anchorPaneUser1.getChildren().add(labelEmail1);
            AnchorPane.setTopAnchor(labelEmail1, 30.0);
            AnchorPane.setLeftAnchor(labelEmail1, 68.0);

            // Load Gravatar image based on email
            ImageView imageViewUser1 = new ImageView();
            imageViewUser1.setFitHeight(50.0);
            imageViewUser1.setFitWidth(50.0);
            String gravatarUrl = getGravatarUrl(user.getEmail());
            imageViewUser1.setImage(new Image(gravatarUrl));
            anchorPaneUser1.getChildren().add(imageViewUser1);
            AnchorPane.setTopAnchor(imageViewUser1, 8.0);
            AnchorPane.setLeftAnchor(imageViewUser1, 6.0);

            // Set up click handler to select this user
            button1.setOnAction(event -> updateSelectedUser(user, button1));
        }
    }

    /**
     * Updates the UI when a user is selected.
     * Highlights the selected user button, updates the user details display,
     * and loads the user's Gravatar profile image.
     *
     * @param user The user object that was selected
     * @param button The button associated with the selected user
     */
    private void updateSelectedUser(User user, Button button) {
        // Reset previous selection styling if any
        if (selectedUserButton != null) {
            selectedUserButton.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
        }

        // Update selection and highlight selected button
        selectedUserButton = button;
        selectedUserButton.setUserData(user);
        selectedUserButton.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");

        // Update user details in the display
        lblName.setText(user.getFirstName() + " " + user.getLastName());
        lblEmail.setText(user.getEmail());
        lblFirstName.setText(user.getFirstName());
        lblLastName.setText(user.getLastName());
        lblPhoneNumber.setText(user.getPhoneNumber());

        // Set appropriate role label text based on role ID
        if (user.getRoleID() == 1) {
            lblRole.setText("      Admin");
        } else if (user.getRoleID() == 2) {
            lblRole.setText("  Coordinator");
        }

        // Load and display user's Gravatar image
        String gravatarUrl = getGravatarUrl(user.getEmail());
        imgSelectedUser.setImage(new Image(gravatarUrl));

        System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getEmail() + " " + user.getPhoneNumber() + " " + user.getRoleID());
    }

    /**
     * Generates a Gravatar URL for the given email address.
     * Uses MD5 hashing of the email to create the URL according to Gravatar's API.
     *
     * @param email The email address to generate a Gravatar URL for
     * @return The complete Gravatar URL with default image and size parameters
     */
    private String getGravatarUrl(String email) {
        String baseUrl = "https://www.gravatar.com/avatar/";
        String emailHash = md5Hash(email.trim().toLowerCase());
        return baseUrl + emailHash + "?d=identicon&s=200"; // Default to 'identicon' if no image is found, size 200px
    }

    /**
     * Generates an MD5 hash of the input string.
     * Used for creating Gravatar URLs which require email addresses to be hashed.
     *
     * @param input The string to hash (typically an email address)
     * @return The MD5 hash as a 32-character hexadecimal string
     */
    private String md5Hash(String input) {
        try {
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");
            byte[] messageDigest = md.digest(input.getBytes());
            java.math.BigInteger number = new java.math.BigInteger(1, messageDigest);
            return String.format("%032x", number); // Convert to a 32-character hexadecimal string
        } catch (java.security.NoSuchAlgorithmException e) {
            throw new RuntimeException("MD5 algorithm not found", e);
        }
    }

    /**
     * Populates the event list with buttons representing each event.
     * Each button displays event title, location, and an icon.
     * Sets up click handlers to select events and show their details.
     */
    public void showEventList() {
        vbox3.getChildren().clear(); // Clear existing children

        for (Event event : eventModel.getObservableEvents()) {
            // Create button for each event
            Button button = new Button();
            button.setPrefSize(460, 75);
            button.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
            vbox3.getChildren().add(button);

            // Set up layout for event button content
            AnchorPane anchorPaneEvent = new AnchorPane();
            button.setGraphic(anchorPaneEvent);

            // Display event title
            Label labelName = new Label(event.geteventTitle());
            labelName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000;");
            anchorPaneEvent.getChildren().add(labelName);
            AnchorPane.setTopAnchor(labelName, 10.0);
            AnchorPane.setLeftAnchor(labelName, 68.0);

            // Display event icon
            ImageView imageViewEvent = new ImageView();
            imageViewEvent.setFitHeight(50.0);
            imageViewEvent.setFitWidth(50.0);
            anchorPaneEvent.getChildren().add(imageViewEvent);
            imageViewEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/festival.png"))));
            AnchorPane.setTopAnchor(imageViewEvent, 8.0);
            AnchorPane.setLeftAnchor(imageViewEvent, 6.0);

            // Display event location
            Label labelLocation = new Label(event.getLocation());
            anchorPaneEvent.getChildren().add(labelLocation);
            AnchorPane.setTopAnchor(labelLocation, 30.0);
            AnchorPane.setLeftAnchor(labelLocation, 68.0);

            // Set up click handler to select this event
            button.setOnAction(events -> updateSelectedEvent(event, button));
        }
    }

    /**
     * Updates the UI when an event is selected.
     * Highlights the selected event button, updates the event details display,
     * and shows the list of coordinators assigned to the event.
     *
     * @param event The event object that was selected
     * @param button The button associated with the selected event
     */
    private void updateSelectedEvent(Event event, Button button) {
        // Reset previous selection styling if any
        if (selectedEventButton != null) {
            selectedEventButton.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
        }

        // Update selection and highlight selected button
        selectedEventButton = button;
        selectedEventButton.setUserData(event); // Store event reference for later use
        selectedEventButton.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");

        // Update event details in the display
        lblEventTitle.setText(event.geteventTitle());
        lblLocationEvent.setText(event.getLocation());

        // Format and display event date range
        if (event.getEventEndDate() != null) {
            lblEventDate.setText(event.geteventStartDate().toString() + " - " + event.getEventEndDate().toString());
        } else {
            lblEventDate.setText(event.geteventStartDate().toString());
        }

        // Format and display event time range
        if (event.geteventEndTime() != null) {
            lblEventTime.setText(event.geteventStartTime() + " - " + event.geteventEndTime());
        } else {
            lblEventTime.setText(event.geteventStartTime());
        }

        // Get coordinators assigned to this event and update the UI
        try {
            java.util.List<User> assignedCoordinators = eventModel.getCoordinatorsForEvent(event.getEventID());

            // Update coordinator count
            int coordinatorCount = assignedCoordinators.size();
            lblCoordinatorsAmount.setText(String.valueOf(coordinatorCount));

            // Debug log of assigned coordinators
            for (User coordinator : assignedCoordinators) {
                System.out.println("Coordinator in UI: ID=" + coordinator.getUserID() +
                        ", firstName=" + coordinator.getFirstName() +
                        ", lastName=" + coordinator.getLastName());
            }

            // Format assigned coordinators as a multi-line list
            StringBuilder assigneesList = new StringBuilder();
            for (int i = 0; i < assignedCoordinators.size(); i++) {
                User coordinator = assignedCoordinators.get(i);
                assigneesList.append(coordinator.getFirstName()).append(" ").append(coordinator.getLastName());
                // Add a new line between entries
                if (i < assignedCoordinators.size() - 1) {
                    assigneesList.append("\n");
                }
            }

            lblEventAssignees.setText(assigneesList.toString());

        } catch (Exception e) {
            // Handle case where no coordinators are assigned
            lblCoordinatorsAmount.setText("0");
            lblEventAssignees.setText("None");
            e.printStackTrace();
        }

        System.out.println(event.geteventTitle() + " " + event.getLocation() + " " + event.geteventStartDate() + " " + event.getEventEndDate() + " " + event.geteventStartTime() + " " + event.geteventEndTime() + " " + event.geteventDescription());
    }

    /**
     * Opens a dialog to remove assigned coordinators from the selected event.
     * Lists all assigned coordinators with buttons to remove each one.
     * Updates the UI after coordinator removal to reflect changes.
     */
    private void handleRemoveAssignee() {
        if (selectedEventButton == null) return;

        Event event = (Event) selectedEventButton.getUserData();
        try {
            // Get coordinators assigned to this event
            java.util.List<User> assignedCoordinators = eventModel.getCoordinatorsForEvent(event.getEventID());
            if (assignedCoordinators.isEmpty()) {
                return; // No coordinators to remove
            }

            // Create the dialog
            Stage popupStage = new Stage();
            popupStage.initModality(Modality.APPLICATION_MODAL);
            popupStage.setTitle("Remove Coordinator Assignment");

            // Set up dialog layout
            VBox layout = new VBox(10);
            layout.setPadding(new javafx.geometry.Insets(20));
            layout.getChildren().add(new Label("Select coordinator to remove:"));

            // Create buttons for each coordinator
            for (User coordinator : assignedCoordinators) {
                Button btn = new Button(coordinator.getFirstName() + " " + coordinator.getLastName());
                btn.setPrefWidth(200);
                btn.setOnAction(e -> {
                    try {
                        // Remove the selected coordinator
                        eventModel.removeCoordinatorFromEvent(coordinator, event);
                        popupStage.close();

                        // Refresh the event list and details
                        showEventList();

                        // Reselect the current event to update the UI
                        if (selectedEventButton != null) {
                            updateSelectedEvent(event, selectedEventButton);
                        }
                    } catch (Exception ex) {
                        ex.printStackTrace();
                    }
                });
                layout.getChildren().add(btn);
            }

            // Add cancel button
            Button cancelBtn = new Button("Cancel");
            cancelBtn.setPrefWidth(200);
            cancelBtn.setOnAction(e -> popupStage.close());
            layout.getChildren().add(cancelBtn);

            Scene scene = new Scene(layout);
            popupStage.setScene(scene);
            popupStage.showAndWait();

        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Handles assignment of the selected user to the selected event.
     * Verifies that both a user and event are selected before attempting assignment.
     * Updates the event list and coordinator information after assignment.
     *
     * @param actionEvent The action event
     */
    public void HandleBtnAssignToEvent(ActionEvent actionEvent) {
        // Verify that both a user and event are selected
        if (selectedUserButton != null && selectedEventButton != null) {
            User user = (User) selectedUserButton.getUserData();
            Event event = (Event) selectedEventButton.getUserData();
            try {
                // Assign the coordinator to the event
                eventModel.assignCoordinatorToEvent(user, event);
                // Refresh the event list to show updated assignments
                showEventList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}