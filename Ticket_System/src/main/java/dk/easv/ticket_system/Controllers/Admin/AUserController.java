/**
 * Controller for the Admin User interface that manages users and their event assignments.
 * This controller handles displaying users, events, and managing the relationships between them.
 * It provides functionality for viewing user details, event information, assigning users to events,
 * and removing assignments.
 */
package dk.easv.ticket_system.Controllers.Admin;

import dk.easv.ticket_system.BE.Event;
import dk.easv.ticket_system.BE.User;
import dk.easv.ticket_system.BLL.Util.UserSession;
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
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.List;
import java.util.Objects;

public class AUserController {
    // UI components for displaying event details
    public Label lblEventTitle;                // Label to display the title of the selected event
    public Label lblLocationEvent;             // Label to display the location of the selected event
    public Label lblEventDate;                 // Label to display the date of the selected event
    public Label lblEventTime;                 // Label to display the time of the selected event
    public Label lblTicketsSold;               // Label to display the number of tickets sold for the event
    public Label lblVipPackage;                // Label to display VIP package information
    public Label lblGeneralAdmission;          // Label to display general admission information
    public Label lblCoordinatorsAssigned;      // Label to display coordinators assigned text
    public Button btnDeleteEvent;              // Button to delete the selected event
    public Button btnAssignToEvent;            // Button to assign a coordinator to the selected event
    public Label lblCoordinatorsAmount;        // Label to display the number of coordinators assigned
    public Label lblEventAssignees;            // Label to display the names of assigned coordinators
    public Label lblRemoveAssignee;            // Label that acts as a button to remove an assignee
    public ImageView imgCreateUser;
    public ImageView imgDeleteEvent;
    public ImageView imgEditUser;
    private UserModel userModel;               // Model for user data management
    private EventModel eventModel;             // Model for event data management
    @FXML
    private VBox vbox1;                        // VBox containing user buttons
    @FXML
    private VBox vbox3;                        // VBox containing event buttons
    @FXML
    private Label lblFirstName;                // Label to display the first name of the selected user
    @FXML
    private Label lblLastName;                 // Label to display the last name of the selected user
    @FXML
    private Label lblEmail;                    // Label to display the email of the selected user
    @FXML
    private Label lblPhoneNumber;              // Label to display the phone number of the selected user
    @FXML
    private Label lblRole;                     // Label to display the role of the selected user
    @FXML
    private Label lblName;                     // Label to display the full name of the selected user
    @FXML
    private ScrollPane scpScrollPane;          // ScrollPane for the main content
    private Button selectedUserButton;         // Keeps track of the currently selected user button
    private Button selectedEventButton;        // Keeps track of the currently selected event button
    @FXML
    private ImageView imgSelectedUser;         // ImageView for the avatar of the selected user

    /**
     * Constructor that initializes the user and event models.
     */
    public AUserController() {
        try {
            userModel = new UserModel();
            eventModel = new EventModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * Opens a new modal window for creating a new user.
     *
     * @param actionEvent The action event that triggered this method
     * @throws IOException If the FXML file cannot be loaded
     */
    @FXML
    public void onNewUser(ActionEvent actionEvent) throws IOException {
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Admin/ACreateUser.fxml"));
        Parent root = loader.load();
        Stage stage = new Stage();
        stage.setScene(new Scene(root));
        stage.setTitle("New User");
        stage.initModality(Modality.APPLICATION_MODAL);
        stage.show();
    }

    /**
     * Initializes the controller.
     * Displays the user and event lists, selects first items, and sets up event handlers.
     */
    @FXML
    public void initialize() {
        imgCreateUser.setImage(new Image(getClass().getResourceAsStream("/Images/person_add.png")));
        imgDeleteEvent.setImage(new Image(getClass().getResourceAsStream("/Images/delete.png")));
        imgEditUser.setImage(new Image(getClass().getResourceAsStream("/Images/edit.png")));

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

        // Set the scroll pane height to match the screen height
        scpScrollPane.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() - 110);
    }

    /**
     * Populates the user list with user data from the model.
     * Creates a button for each user with their name, email, and avatar.
     */
    public void showUserList() {
        vbox1.getChildren().clear(); // Clear existing children
        for (User user : userModel.getObservableUsers()) {
            Button button1 = new Button();
            button1.setPrefSize(460, 75);
            button1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
            vbox1.getChildren().add(button1);
            AnchorPane anchorPaneUser1 = new AnchorPane();
            button1.setGraphic(anchorPaneUser1);

            // Set up name label
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

            // Set up button action
            button1.setOnAction(event -> updateSelectedUser(user, button1));
        }
    }

    /**
     * Updates the UI with details of the selected user.
     * Also changes the button style to indicate selection.
     *
     * @param user The User object that was selected
     * @param button The button that was clicked
     */
    private void updateSelectedUser(User user, Button button) {
        // Reset previous selected button style
        if (selectedUserButton != null) {
            selectedUserButton.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
        }
        // Update selection and style
        selectedUserButton = button;
        selectedUserButton.setUserData(user);
        selectedUserButton.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");

        // Update user details in UI
        lblName.setText(user.getFirstName() + " " + user.getLastName());
        lblEmail.setText(user.getEmail());
        lblFirstName.setText(user.getFirstName());
        lblLastName.setText(user.getLastName());
        lblPhoneNumber.setText(user.getPhoneNumber());
        // Set role text based on roleID
        if (user.getRoleID() == 1) {
            lblRole.setText("      Admin");
        } else if (user.getRoleID() == 2) {
            lblRole.setText("  Coordinator");
        }

        // Update avatar image
        String gravatarUrl = getGravatarUrl(user.getEmail());
        imgSelectedUser.setImage(new Image(gravatarUrl));

        // Debug log
        System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getEmail() + " " + user.getPhoneNumber() + " " + user.getRoleID());    }

    /**
     * Generates a Gravatar URL based on the user's email address.
     * This URL is used to display the user's avatar image.
     *
     * @param email The email address to generate the avatar for
     * @return URL string for the Gravatar image
     */
    private String getGravatarUrl(String email) {
        String baseUrl = "https://www.gravatar.com/avatar/";
        String emailHash = md5Hash(email.trim().toLowerCase());
        return baseUrl + emailHash + "?d=identicon&s=200"; // Default to 'identicon' if no image is found, size 200px
    }

    /**
     * Generates an MD5 hash of the input string.
     * Used for creating Gravatar email hashes.
     *
     * @param input The string to hash
     * @return MD5 hash as a hexadecimal string
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
     * Populates the event list with buttons representing events associated with the logged-in user.
     * Each button displays event title, location, and an icon.
     * Sets up click handlers to select events and show their details.
     */
    public void showEventList() {
        vbox3.getChildren().clear(); // Clear existing children to prevent duplication

        try {
            // Get the currently logged-in user
            User loggedInUser = UserSession.getLoggedInUser();
            System.out.println("Logged in user: " + loggedInUser);

            if (loggedInUser == null) {
                System.out.println("No user logged in");
                return;
            }

            for (Event event : eventModel.getObservableEvents()) {
                // Check if this user is assigned to this event
                List<User> assignedCoordinators = eventModel.getCoordinatorsForEvent(event.getEventID());
                boolean userAssigned = false;

                // Check if the logged-in user is one of the assigned coordinators
                for (User coordinator : assignedCoordinators) {
                    if (coordinator.getUserID() == loggedInUser.getUserID()) {
                        userAssigned = true;
                        break;
                    }
                }

                // Skip this event if the user isn't assigned to it and isn't an admin
                // Role ID 1 = Admin, they can see all events
                if (!userAssigned && loggedInUser.getRoleID() != 1) {
                    continue; // Skip to next event
                }

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
        } catch (Exception e) {
            e.printStackTrace();
            System.out.println("Error loading events: " + e.getMessage());
        }
    }

    /**
     * Updates the UI with details of the selected event.
     * Changes button style to indicate selection, displays event information,
     * and lists coordinators assigned to the event.
     *
     * @param event The Event object that was selected
     * @param button The button that was clicked
     */
    private void updateSelectedEvent(Event event, Button button) {
        // Reset previous selected button style
        if (selectedEventButton != null) {
            selectedEventButton.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
        }
        // Update selection and style
        selectedEventButton = button;
        selectedEventButton.setUserData(event);
        selectedEventButton.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");

        // Update event details in UI
        lblEventTitle.setText(event.geteventTitle());
        lblLocationEvent.setText(event.getLocation());

        // Format and display event dates
        if (event.getEventEndDate() != null) {
            lblEventDate.setText(event.geteventStartDate().toString() + " - " + event.getEventEndDate().toString());
        } else {
            lblEventDate.setText(event.geteventStartDate().toString());
        }

        // Format and display event times
        if (event.geteventEndTime() != null) {
            lblEventTime.setText(event.geteventStartTime() + " - " + event.geteventEndTime());
        } else {
            lblEventTime.setText(event.geteventStartTime());
        }

        // Get coordinators assigned to this event
        try {
            java.util.List<User> assignedCoordinators = eventModel.getCoordinatorsForEvent(event.getEventID());

            // Update coordinator count
            int coordinatorCount = assignedCoordinators.size();
            lblCoordinatorsAmount.setText(String.valueOf(coordinatorCount));

            // Debug log for coordinators
            for (User coordinator : assignedCoordinators) {
                System.out.println("Coordinator in UI: ID=" + coordinator.getUserID() +
                        ", firstName=" + coordinator.getFirstName() +
                        ", lastName=" + coordinator.getLastName());
            }

            // Format coordinator names for display
            StringBuilder assigneesList = new StringBuilder();
            for (int i = 0; i < assignedCoordinators.size(); i++) {
                User coordinator = assignedCoordinators.get(i);
                assigneesList.append(coordinator.getFirstName()).append(" ").append(coordinator.getLastName());
                // Add a new line instead of a comma
                if (i < assignedCoordinators.size() - 1) {
                    assigneesList.append("\n");
                }
            }

            // Display coordinator names
            lblEventAssignees.setText(assigneesList.toString());

        } catch (Exception e) {
            // Handle error case with defaults
            lblCoordinatorsAmount.setText("0");
            lblEventAssignees.setText("None");
            e.printStackTrace();
        }

        // Debug log
        System.out.println(event.geteventTitle() + " " + event.getLocation() + " " + event.geteventStartDate() + " " + event.getEventEndDate() + " " + event.geteventStartTime() + " " + event.geteventEndTime() + " " + event.geteventDescription());
    }



    /**
     * Handles the remove assignee action.
     * Displays a modal dialog with a list of coordinators assigned to the current event.
     * User can select a coordinator to remove from the event assignment.
     * Nothing happens if no event is selected or no coordinators are assigned.
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
     * Handles the assign to event button action.
     * Assigns the currently selected user to the currently selected event as a coordinator.
     * No action is taken if either a user or event is not selected.
     * After assignment, the event list is refreshed to show the updated coordinator count.
     *
     * @param actionEvent The action event that triggered this method
     */
    public void HandleBtnAssignToEvent(ActionEvent actionEvent) {
        if (selectedUserButton != null && selectedEventButton != null) {
            User user = (User) selectedUserButton.getUserData();
            Event event = (Event) selectedEventButton.getUserData();
            try {
                eventModel.assignCoordinatorToEvent(user, event);
                showEventList(); // Refresh the event list
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    public void btnHandleDeleteEvent(ActionEvent actionEvent) throws Exception {

        if (selectedEventButton != null) {
        eventModel.deleteEvent((Event) selectedEventButton.getUserData());
        }else
        {
            System.out.println("No event selected");
        }
    }

    public void HandleBtnEditUser(ActionEvent actionEvent) {

        if (selectedUserButton != null) {
            User user = (User) selectedUserButton.getUserData();
            try {
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/dk/easv/ticket_system/Admin/AEditUser.fxml"));
                Parent root = loader.load();
                AEditUserController editUserController = loader.getController();
                editUserController.setUser(user);
                Stage stage = new Stage();
                stage.setScene(new Scene(root));
                stage.setTitle("Edit User");
                stage.initModality(Modality.APPLICATION_MODAL);
                stage.showAndWait();

                // Refresh the user list after editing
                showUserList();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }
}