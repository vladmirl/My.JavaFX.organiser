package untitled;

import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

/**
 * Created by Володимир on 15.04.2016.
 */
public class StringPropertyTest {
    private StringProperty firstName = new SimpleStringProperty(this, "firstName", "");

    //returns the stringPropertyObject
    public StringProperty firstNameProperty() {
        return firstName;
    }

    public String getFirstName() {
        return firstName.get();
    }

    public void setFirstName(String firstName) {
        this.firstName.set(firstName);
    }
}
