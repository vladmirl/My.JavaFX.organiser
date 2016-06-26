package untitled;

import sample.Person;

import java.io.*;
import java.util.ArrayList;
import java.util.Scanner;
import java.util.TreeMap;

/**
 * Created by Володимир on 13.04.2016.
 */
public class FileBook implements Serializable {
    private ArrayList<Person> book = new ArrayList<>();
    private Person[] Book;
    private Person[] newBook;

    public FileBook() {
    }

    /*public void writePersons() {

        book.add(new Person());
        book.add(new Person());
        book.add(new Person());

        Book = new Person[book.size()];

        book.toArray(Book);

        try {
            ObjectOutputStream outputPerson = new ObjectOutputStream(new FileOutputStream("PhoneBook.txt"));
            outputPerson.writeObject(Book);
            outputPerson.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();

        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void showMapContacts() {
        try {
            ObjectInputStream inputPerson = new ObjectInputStream(new FileInputStream("PhoneBook.txt"));
            newBook = (Person[]) inputPerson.readObject();
            inputPerson.close();

        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (ClassNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        }

        for (Person person : Book) {
            System.out.println(person.getContact());
        }
    }*/


    public static void main(String[] args) {
        // FileBook FB = new FileBook();
        // FB.writePersons();
        // FB.showMapContacts();


        Scanner scanner = new Scanner(System.in);
        File file;
        System.out.println("print file name");
        file = new File(scanner.nextLine());
        try {
            file.createNewFile();
            FileOutputStream fileOutputStream = new FileOutputStream(file);
            String inputline;
            System.out.println("the file is created");
            while (true) {
                inputline = scanner.nextLine();
                if (inputline.equals("exit")) {
                    fileOutputStream.close();
                    System.out.println("Is saved");
                    break;
                }
                fileOutputStream.write(inputline.getBytes());
            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        try (BufferedInputStream fis = new BufferedInputStream(new FileInputStream(file))) {
            byte[] buff = new byte[fis.available()];

            System.out.println("first line-----------------");

            fis.read(buff);

            System.out.println("last line==================");

            System.out.println(new String(buff));

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
        /*
        SfferedReader input;
        BufferedWriter output;
      try{
           input = new BufferedReader(new FileReader("PhoneBook.txt"));

           output = new BufferedWriter(new FileWriter("PhoneBook.txt"));

          int c;
          while ((c=input.read())!=-1){
              output.write(c);
              System.out.println(input.readLine());
          }
      }catch (IOException e){}*/
        /*try {
            PrintWriter pw = new PrintWriter("PhoneBook.txt");
            pw.write("fucked hello world");
            pw.append("kkuukku").append("\n");
            pw.close();
        } catch (IOException e) {}*/







        /* import com.sun.javafx.geom.BaseBounds;
        import com.sun.javafx.geom.transform.BaseTransform;
        import com.sun.javafx.scene.BoundsAccessor;
        import com.sun.javaws.jnl.JavaFXAppDesc;
        import javafx.application.Application;
        import javafx.application.Platform;
        import javafx.collections.FXCollections;
        import javafx.collections.ObservableList;
        import javafx.geometry.Insets;
        import javafx.geometry.Pos;
        import javafx.scene.Cursor;
        import javafx.scene.Group;
        import javafx.scene.Node;
        import javafx.scene.Scene;
        import javafx.scene.control.*;
        import javafx.scene.control.cell.PropertyValueFactory;
        import javafx.scene.effect.Effect;
        import javafx.scene.image.Image;
        import javafx.scene.image.ImageView;
        import javafx.scene.layout.GridPane;
        import javafx.scene.layout.HBox;
        import javafx.scene.layout.StackPane;
        import javafx.scene.layout.VBox;
        import javafx.scene.paint.Color;
        import javafx.stage.Stage;
        import sample.*;

        import javax.swing.text.TabableView;
        import java.io.FileNotFoundException;
        import java.io.IOException;
        import java.io.Serializable;
        import java.util.TreeMap;


 public class Main extends Application{

    // private static PhoneBook phoneBook = new PhoneBook();
    // private static TextField firstnameInput, lastnameInput, phoneInput, emailInput;

    private TableView table;
    private  Scene passScene, scene;

    private static Stage window1;
    private Controller controller = new Controller();

    public void start(Stage primaryStage)throws Exception{


        window1 =  primaryStage;
        window1.setTitle("Organiser");
        window1.setWidth(400);
        window1.setHeight(200);
        VBox layout = TableVievClass.getVBox(controller);
        scene = new Scene( layout, Color.AQUA);
        window1.setScene(scene);

        Label label  = new Label(" contacts :");
        label.setLayoutX(200);
        label.setLayoutY(70);
        label.setPrefSize(500, 30);
        label.setStyle("-fx-font: bold italic 16pt Georgia;-fx-text-fill:#000066;-fxbackground-color:lightgrey;");
        label.setAlignment(Pos.CENTER);


        TableColumn<Person, String> fnameColumn = new TableColumn<>("First name");
        //fnameColumn.setEditable(true);
        fnameColumn.setCellValueFactory(new PropertyValueFactory<>("firstname"));


        TableColumn<Person, String> snameColumn = new TableColumn<>("Last name");
            snameColumn.setMinWidth(130);
        snameColumn.setCellValueFactory(new PropertyValueFactory<>("lastname"));

        TableColumn<Person, String> phoneColumn = new TableColumn<>("phone number");
        phoneColumn.setCellValueFactory(new PropertyValueFactory<>("phone"));

        TableColumn<Person, Integer> ageColumn = new TableColumn("Age");
       ageColumn.setMinWidth(70);
       ageColumn.setCellValueFactory(new PropertyValueFactory<>("age"));

        TableColumn<Person, Integer> yearBirthdayColumn = new TableColumn("Year of birthday");
       yearBirthdayColumn.setMaxWidth(150);
        yearBirthdayColumn.setCellValueFactory(new PropertyValueFactory<>("yearBirth"));

        TableColumn<Person, String> adressColumn = new TableColumn("Adress");
       adressColumn.setMinWidth(150);
       adressColumn.setCellValueFactory(new PropertyValueFactory<>("adress"));

        TableColumn<Person, String> emailColumn = new TableColumn("Email");
       emailColumn.setMinWidth(100);
       emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));

        Button addButton = new Button("Add");
        addButton.setOnAction(e-> table.getItems().add(controller.addButtonClicked()));

        Button deleteButton = new Button("Delete");
        deleteButton.setOnAction(e->controller.deleteButtonClicked(table));

        Button exitButton = new Button("EXIT");
        exitButton.setOnAction(e->
            closeProgramm());

        Button showMap = new Button("Show MAP");
        showMap.setOnAction(e->System.out.println(controller.getPhoneBook().getSerialisible().size()));

        CheckBox checkBox = new CheckBox("anything");
        checkBox.setPrefWidth(100);
        checkBox .setLayoutX(800);
        checkBox.setLayoutY(50);
        checkBox.setOnAction(e ->

                NewWindow.display(controller.searchButtonCliecd())
        );

        HBox hBox = new HBox();
        hBox.setPrefWidth(1500);
        hBox.setPadding(new Insets(10,10,10,10));
        hBox.setSpacing(10);
        hBox.getChildren().addAll(controller.getFirstnameInput(),controller.getLastnameInput(),
                controller.getPhoneInput(), controller.getEmailInput(),
                addButton, deleteButton, exitButton, showMap, checkBox);

        table = new TableView<>();
        table.setTableMenuButtonVisible(true);
        table.setCursor(Cursor.HAND);
        table.setStyle("-fx-font: 14pt Arial;");
        table.setLayoutX(100);
        table.setLayoutY(100);
        table.setPrefSize(800, 500);


        table.setItems(controller.getMapPersons());
        table.getColumns().addAll(fnameColumn, snameColumn, phoneColumn, ageColumn, adressColumn, emailColumn, yearBirthdayColumn );
        layout.getChildren().addAll(table,hBox,label,MenuClass.getBorder(controller));

        //password window
        GridPane grid = new GridPane();
        grid.setPadding(new Insets(10,10,10,10));
        grid.setVgap(8);
        grid.setHgap(10);

        Label nameLabel = new Label("Username");
        GridPane.setConstraints(nameLabel, 0, 0);

        TextField nameInput = new TextField("Vova");
        GridPane.setConstraints(nameInput, 1, 0);

        Label passwordLabel = new Label("Password");
        GridPane.setConstraints(passwordLabel, 0 ,1);

        PasswordField passInput = new PasswordField();
        passInput.setPromptText("password");
        GridPane.setConstraints(passInput, 1 ,1);


        Button enterButton = new Button("ENTER");
        enterButton.setPrefWidth(100);
        enterButton.setStyle("-fx-font:  bold italic 12pt Arial;-fx-text-fill: #660000;  -fx-background-color: #ff99ff; -fx-border-width: 3px; -fx-border-radius: 30; -fx-background-radius: 30;-fx-border-color: #660066;");
        GridPane.setConstraints(enterButton, 1, 2);
        enterButton.setOnAction(e -> {
            if(passInput.getText().equals("")) {
                window1.setWidth(1000);
                window1.setHeight(700);
                window1.setScene(scene);
            }
            else{
                passwordLabel.setText("Incorrect pass!");
                passInput.clear();
            }
        });

        grid.getChildren().addAll(nameLabel, nameInput, passwordLabel, passInput, enterButton);
        passScene = new Scene(grid, 300, 200);
        window1.setScene(passScene);

        window1.show();
    }


    public void addButtonClicked(){

        Person person = new Person();

        person.setFirstname(controller.getFirstnameInput().getText());
        person.setLarstname(controller.getLastnameInput().getText());
        person.setPhone(controller.getPhoneInput().getText());
        person.setEmail(controller.getEmailInput().getText());

        controller.getPhoneBook().getTreeMap().put(person.getEmail(), person);

            controller.getPhoneBook().setSerialisibe(controller.getPhoneBook().getTreeMap());


        table.getItems().add(person);
       controller.getFirstnameInput().clear();
        controller.getLastnameInput().clear();
        controller.getPhoneInput().clear();
        controller.getEmailInput().clear();
    }


    public static void closeProgramm(){
        boolean answer = ConfirmBox.display("Title", "Sure you want to exit");
        if (answer) {
            window1.close();
        }
    }
    public static void main(String[] args){
        launch(args);
    }
}*/
