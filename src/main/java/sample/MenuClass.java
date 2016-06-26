package sample;

import javafx.scene.control.CheckMenuItem;
import javafx.scene.control.Menu;
import javafx.scene.control.MenuBar;
import javafx.scene.control.MenuItem;
import javafx.scene.layout.BorderPane;

/**
 * Created by Володимир on 16.04.2016.
 */
public class MenuClass {

    private static CheckMenuItem homePhone = new CheckMenuItem("HOME");
    private static CheckMenuItem workPhone = new CheckMenuItem("WORK");
    private static CheckMenuItem privatePhone = new CheckMenuItem("PRIVATE");
    private static CheckMenuItem mobilePhone = new CheckMenuItem("MOBILE");
    private static CheckMenuItem internetPhone = new CheckMenuItem("INTERNET");

    public static BorderPane getBorder(Controller controller) {
        Menu searchMenu = new Menu("Search contacts");
        searchMenu.setStyle("-fx-font:  bold italic 10pt Helvetica;");

        MenuItem NameSearch = new MenuItem("search by name");
        NameSearch.setOnAction(e -> {

            TableVievClass.getInputSomethingButton().setText("click hire to search by name/surname");
            TableVievClass.getInputSomethingButton().setOnAction(event ->
                    SearchWindow.display(NameSearch.getText(), controller.nameSearchKlic()));
            TableVievClass.getSomethingInput().setPromptText("input name or surname hire");
            TableVievClass.getSomethingInput().clear();
        });

        MenuItem partNameSearch = new MenuItem("search by first letter of name");
        partNameSearch.setOnAction(e -> {

            TableVievClass.getInputSomethingButton().setText("click hire to search by name/surname");
            TableVievClass.getInputSomethingButton().setOnAction(event ->
                    SearchWindow.display(NameSearch.getText(), controller.partNameSearchKlic()));
            TableVievClass.getSomethingInput().setPromptText("input name or surname hire");
            TableVievClass.getSomethingInput().clear();
        });

        MenuItem phoneSearch = new MenuItem("search by phone number");
        phoneSearch.setOnAction(e -> {

            TableVievClass.getInputSomethingButton().setText("click hire to search by phone");
            TableVievClass.getInputSomethingButton().setOnAction(event ->
                    SearchWindow.display(NameSearch.getText(), controller.phoneSearchKlic()));
            TableVievClass.getSomethingInput().setPromptText("input phone number hire");
            TableVievClass.getSomethingInput().clear();
        });

        MenuItem ageSearch = new MenuItem("search by age");
        ageSearch.setOnAction(e -> {
            TableVievClass.getInputSomethingButton().setText("click hire to search by age");
            TableVievClass.getInputSomethingButton().setOnAction(event ->
                    SearchWindow.display(ageSearch.getText(), controller.ageSearchKlic()));
            TableVievClass.getSomethingInput().setPromptText("input age hire");
            TableVievClass.getSomethingInput().clear();

        });

        MenuItem showAllContacts = new MenuItem("show all contacts ");
        showAllContacts.setOnAction(e -> {
            Controller.getPhoneBook().readFromFile();
            SearchWindow.display("all contacts:", controller.showAllContactsKlic());
        });

        searchMenu.getItems().addAll(NameSearch, partNameSearch, phoneSearch, ageSearch, showAllContacts);

        Menu phoneKing = new Menu("phone tip");
        phoneKing.setStyle("-fx-font:  bold italic 10pt Helvetica;");
        phoneKing.getItems().addAll(homePhone, workPhone, privatePhone, mobilePhone, internetPhone);


        Menu changeContact = new Menu("Change contact");
        changeContact.setStyle("-fx-font:  bold italic 10pt Helvetica;");

        MenuItem addPhone = new MenuItem("Add new phone number");
        addPhone.setOnAction(e -> {

            TableVievClass.getInputSomethingButton().setText("click hire to add new phone");
            TableVievClass.getInputSomethingButton().setOnAction(event -> {
                boolean add = ConfirmBox.display("sure to add new phone number to the selected contact", "add new  phone number");
                if (add) {
                    SearchWindow.display(addPhone.getText(),
                            controller.addPhoneKlic(TableVievClass.getTable(), kindPhoneNumb()));
                    TableVievClass.getSomethingInput().setPromptText("input phone number hire");
                    TableVievClass.getSomethingInput().clear();
                }
            });
        });

        MenuItem changeFirstname = new MenuItem("change first name");
        changeFirstname.setOnAction(e -> SearchWindow.changeContactDisplay("input new first name hire", changeFirstname.getText(), controller));

        MenuItem changeLastname = new MenuItem("change last name");
        changeLastname.setOnAction(e -> SearchWindow.changeContactDisplay("input new surname hire", changeLastname.getText(), controller));

        MenuItem changePhone = new MenuItem("change main phone number");
        changePhone.setOnAction(e -> SearchWindow.changeContactDisplay("input new phone hire", changePhone.getText(), controller));

        MenuItem changePhones = new MenuItem("change other phones");
        changePhones.setOnAction(e -> SearchWindow.changeContactDisplay("input new numbers hire", changePhones.getText(), controller));

        MenuItem changeAddress = new MenuItem("change address");
        changeAddress.setOnAction(e -> SearchWindow.changeContactDisplay("input new address hire", changeAddress.getText(), controller));

        MenuItem changeYearBirth = new MenuItem("change birth year");
        changeYearBirth.setOnAction(e -> SearchWindow.changeContactDisplay("input new birth year hire", changeYearBirth.getText(), controller));

        changeContact.getItems().addAll(addPhone, changeFirstname, changeLastname, changePhone, changePhones, changeAddress, changeYearBirth);

        MenuBar menuBar = new MenuBar();
        menuBar.getMenus().addAll(searchMenu, changeContact, phoneKing);
        BorderPane borderPane = new BorderPane();
        borderPane.setTop(menuBar);
        return borderPane;
    }

    public static TelephoneNumber kindPhoneNumb() {
        if (homePhone.isSelected()) {
            return TelephoneNumber.HOME;
        } else if (workPhone.isSelected()) {
            return TelephoneNumber.WORK;
        } else if (privatePhone.isSelected()) {
            return TelephoneNumber.PRIVATE;
        } else if (mobilePhone.isSelected()) {
            return TelephoneNumber.MOBILE;
        } else if (internetPhone.isSelected()) {
            return TelephoneNumber.INTERNET;
        }
        return TelephoneNumber.MOBILE;
    }
}
