package sample;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Cursor;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.HBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontPosture;
import javafx.scene.text.FontWeight;

/**
 * Created by Володимир on 16.04.2016.
 */
public class TableVievClass {

    private static TextField addSomethingInput = new TextField();
    private static Button inputSomethingButton = new Button();
    private static Button exitButton = new Button("EXIT");
    private static TableView table = new TableView<>();

    public static Scene getScene(Controller controller) {
        Scene scene;
        Group layout = new Group();
        table = new TableView<>();

        Label label = new Label(" contacts :");
        label.setLayoutX(200);
        label.setLayoutY(50);
        label.setPrefSize(500, 30);
        label.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:#000066;-fxbackground-color:lightgrey;");
        label.setAlignment(Pos.CENTER);

        TableColumn<Person, String> firstNameColumn = new TableColumn<>("First name");
        firstNameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));
        firstNameColumn.setMinWidth(120);

        TableColumn<Person, String> surnameColumn = new TableColumn<>("Last name");
        surnameColumn.setMinWidth(120);
        surnameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        TableColumn<Person, String> phoneColumn = new TableColumn<>("phone number");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));
        phoneColumn.setMinWidth(120);

        TableColumn<Person, TelephoneNumber> phoneTipeColumn = new TableColumn<>(" phone kind");
        phoneTipeColumn.setCellValueFactory(new PropertyValueFactory<>("TipePhone"));
        phoneTipeColumn.setMinWidth(120);

        TableColumn<Person, Integer> ageColumn = new TableColumn("Age");
        ageColumn.setMinWidth(50);
        ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Person, Integer> yearBirthdayColumn = new TableColumn("Birthday year");
        yearBirthdayColumn.setMaxWidth(100);
        yearBirthdayColumn.setCellValueFactory(new PropertyValueFactory<>("yearBirth"));

        TableColumn<Person, String> adressColumn = new TableColumn("Adress");
        adressColumn.setMinWidth(150);
        adressColumn.setCellValueFactory(new PropertyValueFactory<>("adress"));

        TableColumn<Person, String> emailColumn = new TableColumn("Email/key");
        emailColumn.setMinWidth(100);
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        Button addButton = new Button("Save contact");
        addButton.setMinWidth(60);
        addButton.setOnAction(e -> table.getItems().add(controller.addButtonClicked(MenuClass.kindPhoneNumb())));
        addButton.setStyle("-fx-font:  bold italic 10pt Helvetica;");
        addButton.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 10));

        Button deleteButton = new Button("Delete contact");
        deleteButton.setOnAction(e -> controller.deleteButtonClicked(table));
        deleteButton.setMinWidth(60);
        deleteButton.setStyle("-fx-font:  bold italic 10pt Helvetica;");
        deleteButton.setFont(Font.font("Helvetica", FontWeight.BOLD, FontPosture.ITALIC, 10));

        exitButton.setMinWidth(50);
        exitButton.setLayoutX(920);
        exitButton.setLayoutY(10);

        CheckBox checkBox = new CheckBox("COLOR AQUA");
        checkBox.setPrefWidth(100);
        checkBox.setLayoutX(800);
        checkBox.setLayoutY(10);
        checkBox.setStyle("-fx-font:  bold italic 9pt Helvetica;");

        Button showMap = new Button("import contacts");
        showMap.setLayoutX(830);
        showMap.setLayoutY(45);
        showMap.setOnAction(e -> {
            boolean import_ = false;
            String quant = String.format("%d", controller.getPhoneBook().getTreeMap().size());

            import_ = ConfirmBox.display("sure to save " + quant + " contacts to file \"All organiser contacts.txt\" ?", "import of contacts");
            if (import_) {
                Controller.getPhoneBook().saveToFile();
                checkBox.setText(quant + " contacts\nwos imported");
            }
        });

        HBox hBox = new HBox();
        hBox.setPrefWidth(1500);
        hBox.setPrefHeight(30);
        hBox.setLayoutY(600);
        hBox.setPadding(new Insets(10, 10, 10, 10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(controller.getFirstnameInput(), controller.getLastnameInput(),
                controller.getPhoneInput(), controller.getEmailInput(), controller.getAdressInput(),
                controller.getYearBirthdayInput(), addButton, deleteButton);

        table.setTableMenuButtonVisible(true);
        table.setCursor(Cursor.HAND);
        // table.setStyle("-fx-font: 14pt Arial;");
        table.setStyle("-fx-background-radius:20;-fx-border-radius:20;-fx-backgroundcolor:#ffefd5;-fx-border-width:3pt;-fx-border-color:#cd853f;-fx-fontweight:bold;-fx-font-size:14pt; -fx-font-family:Georgia; -fx-fontstyle:italic");

        table.setLayoutX(40);
        table.setLayoutY(80);
        table.setPrefSize(900, 500);

        inputSomethingButton.setMinWidth(200);
        inputSomethingButton.setLayoutX(330);
        inputSomethingButton.setLayoutY(20);
        inputSomethingButton.setStyle("-fx-font:  bold italic 10pt Helvetica;");

        addSomethingInput.setMinWidth(200);
        addSomethingInput.setLayoutX(580);
        addSomethingInput.setLayoutY(20);
        addSomethingInput.setStyle("-fx-font:  bold italic 10pt Helvetica;");

        table.setItems(controller.getMapPersons());
        table.getColumns().addAll(firstNameColumn, surnameColumn, phoneColumn, phoneTipeColumn, ageColumn,
                adressColumn, emailColumn, yearBirthdayColumn);
        table.getSelectionModel().select(0);

        layout.getChildren().addAll(table, hBox, label, inputSomethingButton, addSomethingInput,
                MenuClass.getBorder(controller), exitButton, showMap, checkBox);

        scene = new Scene(layout, Color.AQUA);

        checkBox.setOnAction(e -> {
            if (checkBox.isSelected()) {
                scene.setFill(Color.DARKCYAN);
                checkBox.setText("DARK CYAN");
            } else {
                scene.setFill(Color.AQUA);
                checkBox.setText(" AQUA");
            }
        });

        return scene;
    }

    public static TextField getSomethingInput() {
        return addSomethingInput;
    }

    public static Button getInputSomethingButton() {
        return inputSomethingButton;
    }

    public static TableView getTable() {
        return table;
    }

    public static Button getExitButton() {
        return exitButton;
    }
}
