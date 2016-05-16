package sample;


import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;

import java.util.*;

/**
 * Created by Володимир on 15.04.2016.
 */
public class SearchWindow {

    private static Text builderText;
    private static TextField inputString;
    private static Label label;
    private static TextArea textArea;

    private static Button searchButton;

    public static void display(String value, ArrayList<Person> persons) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(value);
        window.setMinWidth(400);

        StringBuilder builder = new StringBuilder();

        for (Person person : persons) {
            builder.append(person.getContact());
        }

        builderText = new Text(builder.toString());
        inputString = new TextField();
        label = new Label();

        inputString.setLayoutY(50);
        inputString.setPromptText("enter email hire");

        label.setLayoutY(50);
        label.setLayoutX(175);
        label.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 10));
        label.setStyle("-fx-font: bold italic 20pt Georgia;-fx-text-fill:#000066;-fxbackground-color:lightgrey;");
        label.textProperty().bind(inputString.textProperty());

        textArea = new TextArea();
        textArea.setMaxWidth(390);
        textArea.setMinHeight(400);
        textArea.setLayoutY(100);
        if (persons.size() != 0) {
            textArea.setText(builderText.getText());
        } else {
            textArea.setText("contacts not found,\ntry to search again by email");
        }
        textArea.setStyle("-fx-background-radius:20;-fx-border-radius:20;-fx-backgroundcolor:#ffefd5;-fx-border-width:3pt;-fx-border-color:#cd853f;-fx-fontweight:bold;-fx-font-size:14pt; -fx-font-family:Georgia; -fx-fontstyle:italic");

        searchButton = new Button("SEARCH by Email");
        searchButton.setOnAction(e -> {
            boolean found = false;
            for (Person person : Controller.getPhoneBook().getTreeMap().values()) {
                if (inputString.getText().equals(person.getEmail())) {
                    found = true;
                    textArea.setText(person.getContact());
                    textArea.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:#000066;-fxbackground-color:lightgrey;");
                    inputString.clear();

                } else {

                    continue;
                }
            }
            if (!found) {
                textArea.setText("such contacts doesn't exist");
            }
        });

        Group group = new Group();
        group.getChildren().addAll(searchButton, label, inputString, textArea);

        Scene scene = new Scene(group, 400, 550);
        scene.setFill(Color.GREEN);

        window.setScene(scene);
        window.showAndWait();
    }

    public static void changeContactDisplay(String massage, String title, Controller controller) {

        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(400);

        inputString = new TextField();
        label = new Label();

        inputString.setLayoutY(50);
        inputString.setPromptText(massage);

        label.setLayoutY(50);
        label.setLayoutX(175);
        label.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 10));
        label.setStyle("-fx-font: bold italic 20pt Georgia;-fx-text-fill:#000066;-fxbackground-color:lightgrey;");
        label.textProperty().bind(inputString.textProperty());

        textArea = new TextArea();
        textArea.setMaxWidth(390);
        textArea.setMinHeight(400);
        textArea.setLayoutY(100);
        textArea.setText(controller.emailSearchKlic().getContact());
        textArea.setStyle("-fx-background-radius:20;-fx-border-radius:20;-fx-backgroundcolor:#ffefd5;-fx-border-width:3pt;-fx-border-color:#cd853f;-fx-fontweight:bold;-fx-font-size:14pt; -fx-font-family:Georgia; -fx-fontstyle:italic");

        searchButton = new Button(title);
        searchButton.setOnAction(e -> {
            boolean change = false;

            if (massage.equals("input new first name hire")) {
                change = ConfirmBox.display("are you sure you want to change first name: " +
                        controller.emailSearchKlic().getFirstname() + " to " + inputString.getText(), "change first name");
                if (change)
                    controller.emailSearchKlic().setFirstname(inputString.getText());
                window.setTitle("first name is changed");
            } else if (massage.equals("input new surname hire")) {
                change = ConfirmBox.display("are you sure you want to change last name: " +
                        controller.emailSearchKlic().getLastname() + " to " + inputString.getText(), "change last name");
                if (change)
                    controller.emailSearchKlic().setLarstname(inputString.getText());
                window.setTitle("last name is changed");
            } else if (massage.equals("input new phone hire")) {
                change = ConfirmBox.display("are you sure you want to change main phone number: " +
                        controller.emailSearchKlic().getPhone() + " to " + inputString.getText(), "change main phone number");
                if (change)
                    controller.emailSearchKlic().setPhone(inputString.getText());
                controller.emailSearchKlic().setTipePhone(MenuClass.kindPhoneNumb());
                window.setTitle("main phone is changed");
            } else if (massage.equals("input new address hire")) {
                change = ConfirmBox.display("are you sure you want to change address: " +
                        controller.emailSearchKlic().getAdress() + " to " + inputString.getText(), "change address");
                if (change)
                    controller.emailSearchKlic().setAdress(inputString.getText());
                window.setTitle("address is changed");
            } else if (massage.equals("input new birth year hire")) {
                change = ConfirmBox.display("are you sure you want to change birth year" +
                        controller.emailSearchKlic().getYearBirth() + " to " + inputString.getText()+
                        "\n(mandatory from 1901 till 2016)! in other case year will get equals '0' ",
                        "change birth year(mandatory from 1901 till 2016)");
                if (change)
                    controller.emailSearchKlic().setYearBirth(Integer.parseInt(inputString.getText()));
                window.setTitle("age is changed");
            } else if (massage.equals("input new numbers hire")) {
                for (PhoneClass phone : controller.emailSearchKlic().getAllNumbers()) {
                    if (!controller.emailSearchKlic().getAllNumbers().isEmpty()) {
                        change = ConfirmBox.display("are you sure you want to change phone: " + phone.getTelephone() +
                                " to " + inputString.getText(), "change phone");
                        if (change) {
                            phone.setTipePhone(MenuClass.kindPhoneNumb());
                            phone.setTelephone(inputString.getText());
                            window.setTitle("other phone number is changed");
                        }
                        break;
                    }
                }
                if (controller.emailSearchKlic().getAllNumbers().isEmpty()) {
                    change = ConfirmBox.display("sure to create new phone number: " + inputString.getText(), "create new phone");
                    if (change) {
                        controller.emailSearchKlic().addPhone(inputString.getText(), MenuClass.kindPhoneNumb());
                        window.setTitle("new phone is created");
                    }
                }
            }
            inputString.clear();
            textArea.setText(controller.emailSearchKlic().getContact());
            controller.getPhoneBook().setSerialisibe();
            TableVievClass.getTable().getItems().setAll(controller.getMapPersons());
        });

        Group group = new Group();
        group.getChildren().addAll(searchButton, label, inputString, textArea);

        Scene scene = new Scene(group, 400, 550);
        scene.setFill(Color.GREEN);

        window.setScene(scene);
        window.showAndWait();
    }
}
