package sample;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;

import java.util.*;

public class Controller {

    private static boolean addContact = false;

    private static String regularExpression = "[^0-9]";
    private TextField firstnameInput, lastnameInput, phoneInput, emailInput, adressInput, yearBirthdayInput;
    private static PhoneBook phoneBook = new PhoneBook();

    public Controller() {
        firstnameInput = new TextField();
        firstnameInput.setPromptText("first name");
        firstnameInput.setMaxWidth(120);

        lastnameInput = new TextField();
        lastnameInput.setPromptText("last name");
        lastnameInput.setMaxWidth(120);

        phoneInput = new TextField();
        phoneInput.setPromptText("phone number");
        phoneInput.setMaxWidth(120);

        emailInput = new TextField();
        emailInput.setPromptText("email");
        emailInput.setMaxWidth(80);

        adressInput = new TextField();
        adressInput.setPromptText("address");
        adressInput.setMaxWidth(120);

        yearBirthdayInput = new TextField();
        yearBirthdayInput.setPromptText("birthday year");
        yearBirthdayInput.setMaxWidth(80);

    }

    public ArrayList<Person> nameSearchKlic() {
        ArrayList<Person> persons = new ArrayList<>();
        for (Person person : phoneBook.getTreeMap().values()) {
            if (!(TableVievClass.getSomethingInput().getText().equals("")) &&
                    person.getFirstname().equalsIgnoreCase(TableVievClass.getSomethingInput().getText())) {
                persons.add(person);

            } else if (!(TableVievClass.getSomethingInput().getText().equals(""))
                    && person.getLastname().equalsIgnoreCase(TableVievClass.getSomethingInput().getText())) {
                persons.add(person);

            } else if (TableVievClass.getSomethingInput().getText().equals("") && person.getFirstname().equals("") ||
                    TableVievClass.getSomethingInput().getText().equals("") && person.getLastname().equals("")) {
                persons.add(person);
            }
        }
        firstnameInput.clear();
        lastnameInput.clear();
        return persons;
    }

    public ArrayList<Person> partNameSearchKlic() {
        ArrayList<Person> persons = new ArrayList<>();

        for (Person person : phoneBook.getTreeMap().values()) {
            if (TableVievClass.getSomethingInput().getText().equals("") && person.getFirstname().equals("") ||
                    TableVievClass.getSomethingInput().getText().equals("") && person.getLastname().equals("")) {
                persons.add(person);
            } else if (!TableVievClass.getSomethingInput().getText().equals("") && !person.getFirstname().equals("") &&
                    person.getFirstname().substring(0, 1).equalsIgnoreCase(TableVievClass.getSomethingInput().getText().substring(0, 1))) {
                persons.add(person);
            } else if (!TableVievClass.getSomethingInput().getText().equals("") && !person.getLastname().equals("") &&
                    person.getLastname().substring(0, 1).equalsIgnoreCase(TableVievClass.getSomethingInput().getText().substring(0, 1))) {
                persons.add(person);
            }
        }
        TableVievClass.getSomethingInput().clear();
        return persons;
    }

    public ArrayList<Person> phoneSearchKlic() {

        ArrayList<Person> persons = new ArrayList<>();
        for (Person person : phoneBook.getTreeMap().values()) {
            if (TableVievClass.getSomethingInput().getText() != "" &&
                    person.getPhone().equalsIgnoreCase(TableVievClass.getSomethingInput().getText())) {
                persons.add(person);
            } else if (!(TableVievClass.getSomethingInput().getText().equals(""))) {
                for (PhoneClass phone : person.getAllNumbers()) {
                    if (phone.getTelephone().equalsIgnoreCase(TableVievClass.getSomethingInput().getText())) {
                        persons.add(person);
                    }
                }
            }
        }
        TableVievClass.getSomethingInput().clear();
        return persons;
    }

    public ArrayList<Person> ageSearchKlic() {

        ArrayList<Person> persons = new ArrayList<>();
        int temp;
        for (Person person : phoneBook.getTreeMap().values()) {
            temp = person.getAge();
            if (
                    String.format("%d", temp).equals(TableVievClass.getSomethingInput().getText())) {
                persons.add(person);
            }
        }
        TableVievClass.getSomethingInput().clear();
        return persons;
    }

    public Person emailSearchKlic() {

        ObservableList<Person> personsSelected;
        personsSelected = TableVievClass.getTable().getSelectionModel().getSelectedItems();

        for (Person person : phoneBook.getTreeMap().values()) {
            if (person.getEmail().equals(personsSelected.get(0).getEmail())) {
                return person;
            }
        }
        return null;
    }


    public ObservableList<Person> getMapPersons() {
        ObservableList<Person> persons = FXCollections.observableArrayList();
        persons.addAll(phoneBook.getTreeMap().values());

        return persons;
    }

    public Person addButtonClicked(TelephoneNumber kindNumber) {

        Person person = new Person();
        person.setTipePhone(kindNumber);
        person.setFirstname(firstnameInput.getText());
        person.setLarstname(lastnameInput.getText());
        person.setPhone(phoneInput.getText());

        for (Person person1 : phoneBook.getTreeMap().values()) {

            if (emailInput.getText().equals(person1.getEmail()) || emailInput.getText().equals("")) {

                addContact = ConfirmBox.display("such email/key allrady exist, or is equal to null\n" +
                        "to save uniq key without real web account press yes\n" +
                        "to cancel press no", "ERROR incorrect email/key");
                if (addContact) {
                    person.setEmail("key:" + person.hashCode());
                    emailInput.clear();
                    break;
                } else {
                    break;
                }
            } else if (!emailInput.getText().equals(person1.getEmail())) {
                continue;
            }
        }
        if (!emailInput.getText().equals(""))
            person.setEmail(emailInput.getText());
        emailInput.clear();

        person.setAdress(adressInput.getText());
        if (yearBirthdayInput.getText().equals("") ||
                !yearBirthdayInput.getText().equals(yearBirthdayInput.getText().replaceAll(regularExpression, ""))) {
            person.setYearBirth(0);
        } else {
            person.setYearBirth(Integer.parseInt(yearBirthdayInput.getText().replaceAll(regularExpression, "")));
        }

        phoneBook.getTreeMap().put(person.getEmail(), person);

        phoneBook.setSerialisibe();

        firstnameInput.clear();
        lastnameInput.clear();
        phoneInput.clear();
        emailInput.clear();
        adressInput.clear();
        yearBirthdayInput.clear();

        return person;
    }

    public ArrayList<Person> addPhoneKlic(TableView table, TelephoneNumber kindNumber) {
        boolean addTelephone = false;
        ArrayList<Person> persons = new ArrayList<>();
        ObservableList<Person> personsSelected;
        personsSelected = table.getSelectionModel().getSelectedItems();

        for (Person person : phoneBook.getTreeMap().values()) {
            if (person.getEmail().equals(personsSelected.get(0).getEmail()) &&
                    !(TableVievClass.getSomethingInput().getText().equals(""))) {

                person.addPhone(TableVievClass.getSomethingInput().getText(), kindNumber);
                persons.add(person);
                phoneBook.setSerialisibe();
                TableVievClass.getSomethingInput().clear();
                return persons;
            }
            if (TableVievClass.getSomethingInput().getText().equals("") && !addTelephone) {
                addTelephone = ConfirmBox.display("you've entered empty number.\n" +
                        "to save empty number press yes\n" + "to cancel press no", " incorrect phone number");

                if (addTelephone && person.getEmail().equals(personsSelected.get(0).getEmail())) {
                    TableVievClass.getSomethingInput().setText("empty number");
                    person.addPhone(TableVievClass.getSomethingInput().getText(), kindNumber);
                    persons.add(person);
                    phoneBook.setSerialisibe();
                    TableVievClass.getSomethingInput().clear();
                    return persons;
                } else if (addTelephone && !(person.getEmail().equals(personsSelected.get(0).getEmail()))) {
                    continue;

                } else {

                    break;
                }
            }
        }
        return persons;
    }

    public void deleteButtonClicked(TableView table) {
        ObservableList<Person> personsSelected, personsAll;
        personsAll = table.getItems();
        boolean delete = false;

        personsSelected = table.getSelectionModel().getSelectedItems();

        for (Person person : phoneBook.getTreeMap().values()) {
            if (person.getEmail().equals(personsSelected.get(0).getEmail())) {
                delete = ConfirmBox.display("Sure you want to delete selected contact?  :" + person.getContact(), "delete contact");
                if (delete) {
                    phoneBook.getTreeMap().remove(person.getEmail());
                    phoneBook.setSerialisibe();
                    personsSelected.forEach(personsAll::remove);

                } else {
                    break;
                }
            }
        }
    }

    public ArrayList<Person> showAllContactsKlic() {
        ArrayList<Person> persons = new ArrayList<>();
        for (Person person : phoneBook.getTreeMap().values()) {
            persons.add(person);
        }
        return persons;
    }

    public TextField getFirstnameInput() {

        return firstnameInput;
    }

    public TextField getLastnameInput() {

        return lastnameInput;
    }

    public TextField getPhoneInput() {

        return phoneInput;
    }

    public TextField getEmailInput() {

        return emailInput;
    }

    public TextField getAdressInput() {

        return adressInput;
    }

    public TextField getYearBirthdayInput() {

        return yearBirthdayInput;
    }

    public static PhoneBook getPhoneBook() {
        return phoneBook;
    }
}
