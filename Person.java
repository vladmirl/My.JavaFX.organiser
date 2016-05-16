package sample;

import java.io.Serializable;
import java.util.ArrayList;


/**
 * Created by Володимир on 10.04.2016.
 */
public class Person implements Serializable {
    private String firstname;
    private String lastname;
    private String phone;
    private int age;
    private int yearBirth;
    private String adress;
    private String email;
    private TelephoneNumber TipePhone;
    private ArrayList<PhoneClass> numbersList;

    private static String regularExpression = "[^0-9,+]";

    public Person() {
        numbersList = new ArrayList<>();

        yearBirth = 0;
    }

    public Person(String fname, String lname, String phone) {
        firstname = fname;
        lastname = lname;
        this.phone = phone;
        adress = "Earth";
        email = "Earth@com";
    }

    public void setTipePhone(TelephoneNumber tipePhone) {
        TipePhone = tipePhone;
    }

    public void setFirstname(String fname) {
        firstname = fname;
    }

    public void setLarstname(String lname) {
        lastname = lname;
    }

    public void setPhone(String phone) {
        this.phone = phone.replaceAll(regularExpression, "");
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public void setAdress(String adress) {
        this.adress = adress;
    }

    public void setYearBirth(Integer yearBirth) {
        if (yearBirth > 2016 || yearBirth < 1900) {
            this.yearBirth = 0;
        } else {
            this.yearBirth = yearBirth;
            age = 2016 - yearBirth;
        }
    }

    public void addPhone(String otherNumber, TelephoneNumber tipePhone) {

        PhoneClass newPhone = new PhoneClass();
        newPhone.setTipePhone(tipePhone);
        newPhone.setTelephone(otherNumber);

        numbersList.add(newPhone);
    }

    public String getFirstname() {
        return firstname;
    }

    public String getPhone() {
        return phone;
    }

    public int getAge() {
        return age;
    }

    public int getYearBirth() {
        return yearBirth;
    }

    public String getAdress() {
        return adress;
    }

    public String getEmail() {
        return email;
    }

    public TelephoneNumber getTipePhone() {
        return TipePhone;
    }

    public String getLastname() {
        return lastname;
    }

    public ArrayList<PhoneClass> getAllNumbers() {

        return numbersList;
    }

    public String getContact() {
        StringBuilder builder = new StringBuilder();

        builder.append("\nthe firstname is: " + firstname + ";\nthe lastname is: " + lastname +
                ";\nthe phone number is: " + phone + ";\nthe age of the person is: " + age
                + ";\nthe year of birthday is: " + yearBirth + "\nemail: " + email + "\naddress: " + adress);
        for (PhoneClass phone : numbersList) {
            builder.append("\n" + phone.getTipePhone() + " phone: ");
            builder.append(phone.getTelephone());
        }
        builder.append("\n\n");

        return builder.toString();
    }

}


