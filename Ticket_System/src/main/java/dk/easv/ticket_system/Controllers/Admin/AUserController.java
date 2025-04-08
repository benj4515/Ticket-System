package dk.easv.ticket_system.Controllers.Admin;

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
import javafx.scene.layout.*;
import javafx.stage.Modality;
import javafx.stage.Screen;
import javafx.stage.Stage;

import java.io.IOException;
import java.util.Objects;

public class AUserController {
    public Label lblEventTitle;
    public Label lblLocationEvent;
    public Label lblEventDate;
    public Label lblEventTime;
    public Label lblTicketsSold;
    public Label lblVipPackage;
    public Label lblGeneralAdmission;
    public Label lblCoordinatorsAssigned;
    public Button btnDeleteEvent;
    public Button btnAssignToEvent;
    public Label lblCoordinatorsAmount;
    public Label lblEventAssignees;
    public Label lblRemoveAssignee;
    @FXML
    private FlowPane flowPane;
    private UserModel userModel;
    private EventModel eventModel;
    @FXML
    private Pane customPane1;
    @FXML
    private VBox vbox1;
    @FXML
    private Pane customPane2;
    @FXML
    private Pane customPane3;
    @FXML
    private VBox vbox3;
    @FXML
    private Pane customPane4;
    @FXML
    private Label lblFirstName;
    @FXML
    private Label lblLastName;
    @FXML
    private Label lblEmail;
    @FXML
    private Label lblPhoneNumber;
    @FXML
    private Label lblRole;
    @FXML
    private Label lblName;
    @FXML
    private Label lblUserCreated;
    @FXML
    private Button btnNewUser;
    @FXML
    private ScrollPane scpScrollPane;
    private Button selectedUserButton;
    private Button selectedEventButton;
    @FXML
    private ImageView imgSelectedUser;


    public AUserController() {
        try {
            userModel = new UserModel();
            eventModel = new EventModel();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

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

        scpScrollPane.setPrefHeight(Screen.getPrimary().getVisualBounds().getHeight() - 110);
    }

    public void showUserList() {
        for (User user : userModel.getObservableUsers()) {
            Button button1 = new Button();
            button1.setPrefSize(460, 75);
            button1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
            vbox1.getChildren().add(button1);
            AnchorPane anchorPaneUser1 = new AnchorPane();
            button1.setGraphic(anchorPaneUser1);

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

            button1.setOnAction(event -> updateSelectedUser(user, button1));
        }
    }

    private void updateSelectedUser(User user, Button button) {
        if (selectedUserButton != null) {
            selectedUserButton.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
        }
        selectedUserButton = button;
        selectedUserButton.setUserData(user);
        selectedUserButton.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");

        lblName.setText(user.getFirstName() + " " + user.getLastName());
        lblEmail.setText(user.getEmail());
        lblFirstName.setText(user.getFirstName());
        lblLastName.setText(user.getLastName());
        lblPhoneNumber.setText(user.getPhoneNumber());
        if (user.getRoleID() == 1) {
            lblRole.setText("      Admin");
        } else if (user.getRoleID() == 2) {
            lblRole.setText("  Coordinator");
        }

        String gravatarUrl = getGravatarUrl(user.getEmail());
        imgSelectedUser.setImage(new Image(gravatarUrl));

        System.out.println(user.getFirstName() + " " + user.getLastName() + " " + user.getEmail() + " " + user.getPhoneNumber() + " " + user.getRoleID());    }

    // Method to get Gravatar URL
    private String getGravatarUrl(String email) {
        String baseUrl = "https://www.gravatar.com/avatar/";
        String emailHash = md5Hash(email.trim().toLowerCase());
        return baseUrl + emailHash + "?d=identicon&s=200"; // Default to 'identicon' if no image is found, size 200px
    }

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

    public void showEventList() {
        vbox3.getChildren().clear(); // Clear existing children

        for (Event event : eventModel.getObservableEvents()) {
            Button button = new Button();
            button.setPrefSize(460, 75);
            button.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
            button.setUserData(event); // Set the event as user data
            vbox3.getChildren().add(button);
            AnchorPane anchorPaneEvent = new AnchorPane();
            button.setGraphic(anchorPaneEvent);
            Label labelName = new Label(event.geteventTitle());
            labelName.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000;");
            anchorPaneEvent.getChildren().add(labelName);
            AnchorPane.setTopAnchor(labelName, 10.0);
            AnchorPane.setLeftAnchor(labelName, 68.0);
            ImageView imageViewEvent = new ImageView();
            imageViewEvent.setFitHeight(50.0);
            imageViewEvent.setFitWidth(50.0);
            anchorPaneEvent.getChildren().add(imageViewEvent);
            imageViewEvent.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/festival.png"))));
            AnchorPane.setTopAnchor(imageViewEvent, 8.0);
            AnchorPane.setLeftAnchor(imageViewEvent, 6.0);
            Label labelLocation = new Label(event.getLocation());
            anchorPaneEvent.getChildren().add(labelLocation);
            AnchorPane.setTopAnchor(labelLocation, 30.0);
            AnchorPane.setLeftAnchor(labelLocation, 68.0);

            button.setOnAction(events -> updateSelectedEvent(event, button));
        }
    }

    private void updateSelectedEvent(Event event, Button button) {
        if (selectedEventButton != null) {
            selectedEventButton.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
        }
        selectedEventButton = button;
        selectedEventButton.setUserData(event); // Set the event as user data
        selectedEventButton.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");

        lblEventTitle.setText(event.geteventTitle());
        lblLocationEvent.setText(event.getLocation());
        if (event.getEventEndDate() != null) {
            lblEventDate.setText(event.geteventStartDate().toString() + " - " + event.getEventEndDate().toString());
        } else {
            lblEventDate.setText(event.geteventStartDate().toString());
        }
        //lblEventTime.setText(event.getEventEndDate().toString()); // TODO: There is only EventDate in the DB not Start Date or End Date.
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

            // Add this inside updateSelectedEvent after retrieving coordinators
            for (User coordinator : assignedCoordinators) {
                System.out.println("Coordinator in UI: ID=" + coordinator.getUserID() +
                        ", firstName=" + coordinator.getFirstName() +
                        ", lastName=" + coordinator.getLastName());
            }

            // Update assigned coordinators names - each on a new line
            StringBuilder assigneesList = new StringBuilder();
            for (int i = 0; i < assignedCoordinators.size(); i++) {
                User coordinator = assignedCoordinators.get(i);
                assigneesList.append(coordinator.getFirstName()).append(" ").append(coordinator.getLastName());
                // Add a new line instead of a comma
                if (i < assignedCoordinators.size() - 1) {
                    assigneesList.append("\n");
                }
            }


            lblEventAssignees.setText(assigneesList.toString());

        } catch (Exception e) {
            lblCoordinatorsAmount.setText("0");
            lblEventAssignees.setText("None");
            e.printStackTrace();
        }

        System.out.println(event.geteventTitle() + " " + event.getLocation() + " " + event.geteventStartDate() + " " + event.getEventEndDate() + " " + event.geteventStartTime() + " " + event.geteventEndTime() + " " + event.geteventDescription());
    }
    public void HandlebtnDeleteEvent(ActionEvent actionEvent) {

        if (selectedEventButton != null) {
            Event event = (Event) selectedEventButton.getUserData();
            try {
                System.out.printf(event.getEventID()+"");
                eventModel.deleteEvent(event);
                showEventList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

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


    public void HandleBtnAssignToEvent(ActionEvent actionEvent) {
        if (selectedUserButton != null && selectedEventButton != null) {
            User user = (User) selectedUserButton.getUserData();
            Event event = (Event) selectedEventButton.getUserData();
            try {
                eventModel.assignCoordinatorToEvent(user, event);
                showEventList();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }
}
