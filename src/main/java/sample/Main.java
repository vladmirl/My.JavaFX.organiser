


import javafx.application.Application;
import javafx.scene.Scene;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import sample.*;

/**
 * Created by Володимир on 10.04.2016.
 */
public class Main extends Application {

    private Scene passScene, scene;
    private static Stage window1;
    private Controller controller = new Controller();

    public void start(Stage primaryStage) throws Exception {

        window1 = primaryStage;
        window1.setTitle("Organiser");
        window1.setWidth(400);
        window1.setHeight(200);

        Scene scene = TableVievClass.getScene(controller);
        TableVievClass.getExitButton().setOnAction(e->closeProgramm());

        GridPane grid = PasswordClass.getPasswordGrid(window1, scene);

        passScene = new Scene(grid, 300, 200);
        window1.setScene(passScene);

        window1.show();
    }

    public static void closeProgramm() {
        boolean answer = ConfirmBox.display( "Sure you want to exit","exit of book");
        if (answer) {
            window1.close();
        }
    }

    public static void main(String[] args) {
        launch(args);
    }
}
