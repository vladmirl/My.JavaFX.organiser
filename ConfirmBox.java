package sample;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.VBox;
import javafx.stage.Modality;
import javafx.stage.Stage;

/**
 * Created by Володимир on 14.04.2016.
 */
public class ConfirmBox {
    private static boolean answer;
    private static Stage window;

    public static boolean display(String massage, String title) {
        window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle(title);
        window.setMinWidth(550);
        window.setMinHeight(200);

        window.setOnCloseRequest(e -> e.consume());

        Label label = new Label();
        label.setText(massage);

        Button yesButton = new Button("YES");
        Button notButton = new Button("NO");

        yesButton.setOnAction(e -> {
            answer = true;
            window.close();
        });

        notButton.setOnAction(e -> {
            answer = false;
            window.close();
        });

        VBox vBox = new VBox();
        vBox.getChildren().addAll(label, yesButton, notButton);
        vBox.setAlignment(Pos.CENTER);
        Scene scene = new Scene(vBox);
        window.setScene(scene);
        window.showAndWait();

        return answer;
    }
}
