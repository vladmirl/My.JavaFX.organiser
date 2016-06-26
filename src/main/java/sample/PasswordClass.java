package sample;

import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;

/**
 * Created by Володимир on 16.04.2016.
 */
public class PasswordClass {

    public static GridPane getPasswordGrid(Stage window1, Scene scene) {

        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10, 10, 10, 10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Username");
        GridPane.setConstraints(nameLabel, 0, 0);

        TextField nameInput = new TextField("Vova");
        GridPane.setConstraints(nameInput, 1, 0);

        Label passwordLabel = new Label("Password");
        GridPane.setConstraints(passwordLabel, 0, 1);

        PasswordField passInput = new PasswordField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1, 1);


        Button enterButton = new Button("ENTER");
        enterButton.setPrefWidth(100);
        enterButton.setStyle("-fx-font:  bold italic 12pt Arial;-fx-text-fill: #660000;  -fx-background-color: #ff99ff; -fx-border-width: 3px; -fx-border-radius: 30; -fx-background-radius: 30;-fx-border-color: #660066;");
        GridPane.setConstraints(enterButton, 1, 2);
        enterButton.setOnAction(e -> {
            if (passInput.getText().equals("Vova")) {
                window1.setWidth(1000);
                window1.setHeight(700);
                window1.setScene(scene);
            } else {
                passwordLabel.setText("Incorrect pass!");
                passInput.clear();
            }
        });

        grid.getChildren().addAll(nameLabel, nameInput, passwordLabel, passInput, enterButton);

        return grid;
    }
}
