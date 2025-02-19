package dk.easv.ticket_system;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.*;

import java.util.Objects;

public class UserController {
    @FXML
    private FlowPane flowPane;

    @FXML
    public void initialize() {
        pane1();

        pane2();

        pane3();

        Pane customPane4 = new Pane();
        customPane4.setPrefSize(460, 485);
        flowPane.getChildren().add(customPane4);
        customPane4.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");
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
        Pane customPane1 = new Pane();
        customPane1.setPrefSize(460, 385);
        flowPane.getChildren().add(customPane1);
        customPane1.setStyle("-fx-background-color: #FFF; -fx-background-radius: 8px; -fx-effect: dropshadow(three-pass-box, rgba(0,0,0,0.2), 10, 0, 0, 0);");
        VBox vbox1 = new VBox();
        customPane1.getChildren().add(vbox1);
        Label label1 = new Label("User Details");
        label1.setStyle("-fx-font-size: 24px; -fx-font-weight: bold; -fx-text-fill: #000; -fx-padding: 16px;");
        vbox1.getChildren().add(label1);

        AnchorPane anchorPaneUserDetails = new AnchorPane();
        customPane1.getChildren().add(anchorPaneUserDetails);
        Label labelName1 = new Label("John Cooper");
        labelName1.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-text-fill: #000;");
        anchorPaneUserDetails.getChildren().add(labelName1);
        AnchorPane.setTopAnchor(labelName1, 30.0);
        AnchorPane.setLeftAnchor(labelName1, 68.0);
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
}
