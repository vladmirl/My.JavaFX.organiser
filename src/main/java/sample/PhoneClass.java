package sample;

import java.io.Serializable;

/**
 * Created by Володимир on 18.04.2016.
 */
public class PhoneClass implements Serializable {

    private String telephone;
    private TelephoneNumber tipePhone;

    PhoneClass() {

        telephone = "";
    }

    public void setTelephone(String telephone) {
        this.telephone = telephone;
    }

    public String getTelephone() {
        return telephone;
    }


    public void setTipePhone(TelephoneNumber tipePhone) {
        this.tipePhone = tipePhone;
    }

    public TelephoneNumber getTipePhone() {
        return tipePhone;
    }
}

