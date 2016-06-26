package untitled;


import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.VBox;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import sample.Person;

/**
 * Created by Володимир on 14.04.2016.
 */
public class NewWindow  {

    public static void display(Person person){
        Stage window = new Stage();
        window.initModality(Modality.APPLICATION_MODAL);
        window.setTitle("the contact is found");
        window.setMinWidth(400);

        VBox vBox = new VBox();

        Scene scene = new Scene(vBox);

        Text nameLabel = new Text( person.getFirstname());
        Text surnameLabel = new Text( person.getLastname());
        Text phoneLabel = new Text( person.getPhone());

        Button closeButtun = new Button("close the window");
        closeButtun.setOnAction(e->window.close());

        vBox.getChildren().addAll(nameLabel, surnameLabel, phoneLabel, closeButtun);
        vBox.setAlignment(Pos.CENTER);


        /////////////////////////////////////////////////////////////////////////////////////////////
        StringPropertyTest propertyTest = new StringPropertyTest();
        propertyTest.firstNameProperty().addListener((v, oldValue, newValue)->{

            window.setTitle(newValue);
            System.out.println("name change to:"+newValue);
            System.out.println("firstNameProperty():"+propertyTest.firstNameProperty());
            System.out.println("getFirstName()"+propertyTest.getFirstName());

        });

        Button changeButon = new Button("submit");


        /////////////////////////////////////////////////////////////////////////////////////////////////////


        window.setScene(scene);
        window.showAndWait();
    }
}
