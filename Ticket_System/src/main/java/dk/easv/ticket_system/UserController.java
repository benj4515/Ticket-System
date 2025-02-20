package dk.easv.ticket_system;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;
import javafx.geometry.Insets;

import java.util.Objects;

public class UserController {
    @FXML
    private FlowPane flowPane;

    @FXML
    public void initialize() {
        pane1();

        pane2();

        pane3();

        pane4();
    }

    public void pane1(){
        Pane customPane1 = new Pane();
        customPane1.setPrefSize(460, 485);
        flowPane.getChildren().add(customPane1);
        customPane1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");
        VBox vbox1 = new VBox();
        customPane1.getChildren().add(vbox1);
        Label label1 = new Label("Users");
        label1.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #000; -fx-padding: 16px;");
        vbox1.getChildren().add(label1);

        Button button1 = new Button(); // TODO: Make dynamic with a loop later.
        button1.setPrefSize(460, 75);
        button1.setStyle("-fx-background-color: #EFF6FF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
        vbox1.getChildren().add(button1);
        AnchorPane anchorPaneUser1 = new AnchorPane();
        button1.setGraphic(anchorPaneUser1);
        Label labelName1 = new Label("John Cooper");
        labelName1.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000;");
        anchorPaneUser1.getChildren().add(labelName1);
        AnchorPane.setTopAnchor(labelName1, 10.0);
        AnchorPane.setLeftAnchor(labelName1, 68.0);
        ImageView imageViewUser1 = new ImageView();
        imageViewUser1.setFitHeight(50.0);
        imageViewUser1.setFitWidth(50.0);
        anchorPaneUser1.getChildren().add(imageViewUser1);
        imageViewUser1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/john.png"))));
        AnchorPane.setTopAnchor(imageViewUser1, 8.0);
        AnchorPane.setLeftAnchor(imageViewUser1, 6.0);
        Label labelEmail1 = new Label("john@example.com");
        anchorPaneUser1.getChildren().add(labelEmail1);
        AnchorPane.setTopAnchor(labelEmail1, 30.0);
        AnchorPane.setLeftAnchor(labelEmail1, 68.0);

        Button button2 = new Button(); // TODO: Make dynamic with a loop later.
        button2.setPrefSize(460, 75);
        button2.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
        vbox1.getChildren().add(button2);
        AnchorPane anchorPaneUser2 = new AnchorPane();
        button2.setGraphic(anchorPaneUser2);
        Label labelName2 = new Label("Sarah Wilson");
        labelName2.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000;");
        anchorPaneUser2.getChildren().add(labelName2);
        AnchorPane.setTopAnchor(labelName2, 10.0);
        AnchorPane.setLeftAnchor(labelName2, 68.0);
        ImageView imageViewUser2 = new ImageView();
        imageViewUser2.setFitHeight(50.0);
        imageViewUser2.setFitWidth(50.0);
        anchorPaneUser2.getChildren().add(imageViewUser2);
        imageViewUser2.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/sarah.png"))));
        AnchorPane.setTopAnchor(imageViewUser2, 8.0);
        AnchorPane.setLeftAnchor(imageViewUser2, 6.0);
        Label labelEmail2 = new Label("sarah@example.com");
        anchorPaneUser2.getChildren().add(labelEmail2);
        AnchorPane.setTopAnchor(labelEmail2, 30.0);
        AnchorPane.setLeftAnchor(labelEmail2, 68.0);

        Button button3 = new Button(); // TODO: Make dynamic with a loop later.
        button3.setPrefSize(460, 75);
        button3.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
        vbox1.getChildren().add(button3);
        AnchorPane anchorPaneUser3 = new AnchorPane();
        button3.setGraphic(anchorPaneUser3);
        Label labelName3 = new Label("Micael Brown");
        labelName3.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000;");
        anchorPaneUser3.getChildren().add(labelName3);
        AnchorPane.setTopAnchor(labelName3, 10.0);
        AnchorPane.setLeftAnchor(labelName3, 68.0);
        ImageView imageViewUser3 = new ImageView();
        imageViewUser3.setFitHeight(50.0);
        imageViewUser3.setFitWidth(50.0);
        anchorPaneUser3.getChildren().add(imageViewUser3);
        imageViewUser3.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/michael.png"))));
        AnchorPane.setTopAnchor(imageViewUser3, 8.0);
        AnchorPane.setLeftAnchor(imageViewUser3, 6.0);
        Label labelEmail3 = new Label("michael@example.com");
        anchorPaneUser3.getChildren().add(labelEmail3);
        AnchorPane.setTopAnchor(labelEmail3, 30.0);
        AnchorPane.setLeftAnchor(labelEmail3, 68.0);
    }

    public void pane2(){
        Pane customPane2 = new Pane();
        customPane2.setPrefSize(460, 485);
        flowPane.getChildren().add(customPane2);
        customPane2.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");
        VBox vbox2 = new VBox();
        customPane2.getChildren().add(vbox2);
        Label label2 = new Label("User Details");
        label2.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #000; -fx-padding: 16px;");
        vbox2.getChildren().add(label2);

        AnchorPane anchorPaneUserDetails = new AnchorPane();
        customPane2.getChildren().add(anchorPaneUserDetails);
        Label labelName2 = new Label("John Cooper");
        labelName2.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #000;");
        anchorPaneUserDetails.getChildren().add(labelName2);
        AnchorPane.setTopAnchor(labelName2, 70.0);
        AnchorPane.setLeftAnchor(labelName2, 100.0);
        Label labelUserDetail = new Label("User since Jan 2025");
        labelUserDetail.setStyle("-fx-font-size: 14px; -fx-text-fill: #6B7280;");
        anchorPaneUserDetails.getChildren().add(labelUserDetail);
        AnchorPane.setTopAnchor(labelUserDetail, 100.0);
        AnchorPane.setLeftAnchor(labelUserDetail, 100.0);
        ImageView imageViewUserDetails = new ImageView();
        imageViewUserDetails.setFitHeight(70.0);
        imageViewUserDetails.setFitWidth(70.0);
        imageViewUserDetails.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/john.png"))));
        anchorPaneUserDetails.getChildren().add(imageViewUserDetails);
        AnchorPane.setTopAnchor(imageViewUserDetails, 60.0);
        AnchorPane.setLeftAnchor(imageViewUserDetails, 20.0);
        Label labelUserName = new Label("First Name");
        labelUserName.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-text-fill: #4B5563;");
        anchorPaneUserDetails.getChildren().add(labelUserName);
        AnchorPane.setTopAnchor(labelUserName, 160.0);
        AnchorPane.setLeftAnchor(labelUserName, 25.0);
        Label labelUserNameValue = new Label("John");
        labelUserNameValue.setStyle("-fx-font-size: 18px; -fx-font-weight: 400; -fx-text-fill: #000;");
        anchorPaneUserDetails.getChildren().add(labelUserNameValue);
        AnchorPane.setTopAnchor(labelUserNameValue, 185.0);
        AnchorPane.setLeftAnchor(labelUserNameValue, 25.0);
        Label labelUserLastName = new Label("Last Name");
        labelUserLastName.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-text-fill: #4B5563;");
        anchorPaneUserDetails.getChildren().add(labelUserLastName);
        AnchorPane.setTopAnchor(labelUserLastName, 160.0);
        AnchorPane.setLeftAnchor(labelUserLastName, 230.0);
        Label labelUserLastNameValue = new Label("Cooper");
        labelUserLastNameValue.setStyle("-fx-font-size: 18px; -fx-font-weight: 400; -fx-text-fill: #000;");
        anchorPaneUserDetails.getChildren().add(labelUserLastNameValue);
        AnchorPane.setTopAnchor(labelUserLastNameValue, 185.0);
        AnchorPane.setLeftAnchor(labelUserLastNameValue, 230.0);
        Label labelUserEmail = new Label("Email");
        labelUserEmail.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-text-fill: #4B5563;");
        anchorPaneUserDetails.getChildren().add(labelUserEmail);
        AnchorPane.setTopAnchor(labelUserEmail, 240.0);
        AnchorPane.setLeftAnchor(labelUserEmail, 30.0);
        Label labelUserEmailValue = new Label("john@example.com");
        labelUserEmailValue.setStyle("-fx-font-size: 18px; -fx-font-weight: 400; -fx-text-fill: #000;");
        anchorPaneUserDetails.getChildren().add(labelUserEmailValue);
        AnchorPane.setTopAnchor(labelUserEmailValue, 265.0);
        AnchorPane.setLeftAnchor(labelUserEmailValue, 30.0);
        Label labelUserPhone = new Label("Phone");
        labelUserPhone.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-text-fill: #4B5563;");
        anchorPaneUserDetails.getChildren().add(labelUserPhone);
        AnchorPane.setTopAnchor(labelUserPhone, 240.0);
        AnchorPane.setLeftAnchor(labelUserPhone, 230.0);
        Label labelUserPhoneValue = new Label("+1 (555) 123-4567");
        labelUserPhoneValue.setStyle("-fx-font-size: 18px; -fx-font-weight: 400; -fx-text-fill: #000;");
        anchorPaneUserDetails.getChildren().add(labelUserPhoneValue);
        AnchorPane.setTopAnchor(labelUserPhoneValue, 265.0);
        AnchorPane.setLeftAnchor(labelUserPhoneValue, 230.0);
        Button buttonAssign = new Button("Assign to Event");
        buttonAssign.setStyle("-fx-background-color: #1D4ED8; -fx-text-fill: #FFF; -fx-font-size: 16px; -fx-font-weight: 700; -fx-background-radius: 8px;");
        buttonAssign.setPrefSize(185, 46);
        anchorPaneUserDetails.getChildren().add(buttonAssign);
        AnchorPane.setTopAnchor(buttonAssign, 420.0);
        AnchorPane.setLeftAnchor(buttonAssign, 20.0);
        Label labelUserType = new Label("Coordinator");
        labelUserType.setStyle("-fx-font-size: 15px; -fx-font-weight: 700; -fx-text-fill: #166534; -fx-background-color: #b2fbcd; -fx-background-radius: 8px; -fx-text-alignment: center;");
        labelUserType.setPrefSize(100, 30);
        anchorPaneUserDetails.getChildren().add(labelUserType);
        AnchorPane.setTopAnchor(labelUserType, 430.0);
        AnchorPane.setLeftAnchor(labelUserType, 340.0);
    }

    public void pane3(){
        Pane customPane3 = new Pane();
        customPane3.setPrefSize(460, 485);
        flowPane.getChildren().add(customPane3);
        customPane3.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");
        VBox vbox1 = new VBox();
        customPane3.getChildren().add(vbox1);
        Label label1 = new Label("Your Events");
        label1.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #000; -fx-padding: 16px;");
        vbox1.getChildren().add(label1);

        Button button1 = new Button(); // TODO: Make dynamic with a loop later.
        button1.setPrefSize(460, 75);
        button1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 2px; -fx-border-color: #E5E7EB; -fx-border-width: 1 0 1 0;");
        vbox1.getChildren().add(button1);
        AnchorPane anchorPaneUser1 = new AnchorPane();
        button1.setGraphic(anchorPaneUser1);
        Label labelName1 = new Label("Summer Music Festival");
        labelName1.setStyle("-fx-font-size: 14px; -fx-font-weight: bold; -fx-text-fill: #000;");
        anchorPaneUser1.getChildren().add(labelName1);
        AnchorPane.setTopAnchor(labelName1, 10.0);
        AnchorPane.setLeftAnchor(labelName1, 68.0);
        ImageView imageViewUser1 = new ImageView();
        imageViewUser1.setFitHeight(50.0);
        imageViewUser1.setFitWidth(50.0);
        anchorPaneUser1.getChildren().add(imageViewUser1);
        imageViewUser1.setImage(new Image(Objects.requireNonNull(getClass().getResourceAsStream("/Images/festival.png"))));
        AnchorPane.setTopAnchor(imageViewUser1, 8.0);
        AnchorPane.setLeftAnchor(imageViewUser1, 6.0);
        Label labelEmail1 = new Label("Central Park, New York");
        anchorPaneUser1.getChildren().add(labelEmail1);
        AnchorPane.setTopAnchor(labelEmail1, 30.0);
        AnchorPane.setLeftAnchor(labelEmail1, 68.0);
    }

    public void pane4(){
        Pane customPane4 = new Pane();
        customPane4.setPrefSize(460, 485);
        flowPane.getChildren().add(customPane4);
        customPane4.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");
        VBox vbox4 = new VBox();
        vbox4.setPrefSize(460, 485);
        vbox4.setSpacing(10);
        vbox4.setPadding(new Insets(24));
        customPane4.getChildren().add(vbox4);
        Label label4 = new Label("Summer Music Festival");
        label4.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #000;");
        vbox4.getChildren().add(label4);

        Label labelEventDetails = new Label("Central Park, New York");
        labelEventDetails.setStyle("-fx-font-size: 16px;  -fx-text-fill: #6B7280;");
        vbox4.getChildren().add(labelEventDetails);
        Label labelEventDate = new Label("July 15, 2025");
        labelEventDate.setStyle("-fx-font-size: 16px; -fx-text-fill: #6B7280;");
        vbox4.getChildren().add(labelEventDate);
        Label labelEventTime = new Label("2:00 PM - 10:00 PM");
        labelEventTime.setStyle("-fx-font-size: 16px; -fx-text-fill: #6B7280;");
        vbox4.getChildren().add(labelEventTime);

        HBox hboxSoldTickets = new HBox();
        hboxSoldTickets.setAlignment(javafx.geometry.Pos.CENTER);
        Label labelSoldTickets = new Label("Tickets Sold");
        labelSoldTickets.setStyle("-fx-font-size: 16px; -fx-text-fill: #6B7280;");
        hboxSoldTickets.getChildren().add(labelSoldTickets);
        Region region1 = new Region();
        HBox.setHgrow(region1, Priority.ALWAYS);
        hboxSoldTickets.getChildren().add(region1);
        Label labelSoldTicketsValue = new Label("1,500");
        labelSoldTicketsValue.setStyle("-fx-font-size: 16px; -fx-text-fill: #000;");
        hboxSoldTickets.getChildren().add(labelSoldTicketsValue);
        vbox4.getChildren().add(hboxSoldTickets);

        HBox hboxTicket1 = new HBox();
        Label labelTicket1 = new Label("VIP Package");
        labelTicket1.setStyle("-fx-font-size: 16px; -fx-text-fill: #6B7280;");
        hboxTicket1.getChildren().add(labelTicket1);
        Region region2 = new Region();
        HBox.setHgrow(region2, Priority.ALWAYS);
        hboxTicket1.getChildren().add(region2);
        Label labelTicket1Value = new Label("$199");
        labelTicket1Value.setStyle("-fx-font-size: 16px; -fx-text-fill: #000;");
        hboxTicket1.getChildren().add(labelTicket1Value);
        vbox4.getChildren().add(hboxTicket1);

        HBox hboxTicket2 = new HBox();
        Label labelTicket2 = new Label("General Admission");
        labelTicket2.setStyle("-fx-font-size: 16px; -fx-text-fill: #6B7280;");
        hboxTicket2.getChildren().add(labelTicket2);
        Region region3 = new Region();
        HBox.setHgrow(region3, Priority.ALWAYS);
        hboxTicket2.getChildren().add(region3);
        Label labelTicket2Value = new Label("$59");
        labelTicket2Value.setStyle("-fx-font-size: 16px; -fx-text-fill: #000;");
        hboxTicket2.getChildren().add(labelTicket2Value);
        vbox4.getChildren().add(hboxTicket2);

        HBox hboxCoordinators = new HBox();
        hboxCoordinators.setAlignment(javafx.geometry.Pos.CENTER);
        Label labelCoordinators = new Label("Coordinators Assigned");
        labelCoordinators.setStyle("-fx-font-size: 16px; -fx-text-fill: #6B7280;");
        hboxCoordinators.getChildren().add(labelCoordinators);
        Region region4 = new Region();
        HBox.setHgrow(region4, Priority.ALWAYS);
        hboxCoordinators.getChildren().add(region4);
        Label labelCoordiantorsAmount = new Label("1");
        labelCoordiantorsAmount.setStyle("-fx-font-size: 16px; -fx-text-fill: #000;");
        hboxCoordinators.getChildren().add(labelCoordiantorsAmount);
        vbox4.getChildren().add(hboxCoordinators);

        HBox hboxAssigned1 = new HBox();
        hboxAssigned1.setAlignment(javafx.geometry.Pos.CENTER);
        Label labelAssigned1 = new Label("Sarah Wilson");
        labelAssigned1.setStyle("-fx-font-size: 16px; -fx-text-fill: #6B7280;");
        hboxAssigned1.getChildren().add(labelAssigned1);
        Region region5 = new Region();
        HBox.setHgrow(region5, Priority.ALWAYS);
        hboxAssigned1.getChildren().add(region5);
        Label labelAssigned1Remove = new Label("Remove");
        labelAssigned1Remove.setStyle("-fx-font-size: 16px; -fx-text-fill: #0059ff;");
        hboxAssigned1.getChildren().add(labelAssigned1Remove);
        vbox4.getChildren().add(hboxAssigned1);
    }
}
