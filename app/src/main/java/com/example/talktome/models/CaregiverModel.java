package com.example.talktome.models;

public class CaregiverModel {
    public String firstname;
    public String lastname;
    public String phonenumber;
    public String Email;
    public CaregiverModel(String firstname, String lastname, String phonenumber) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
    }
    public CaregiverModel(String firstname, String lastname, String phonenumber, String email) {
        this.firstname = firstname;
        this.lastname = lastname;
        this.phonenumber = phonenumber;
        this.Email = email;
    }
}
