package com.example.talktome.models;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

public class ContactModel {
    public String id;
    public String name;
    public String mobileNumber;
    public String mimeType;

    public ContactModel(String id, String name, String mobileNumber, String mimeType) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
        this.mimeType = mimeType;
    }

    public ContactModel(String id, String name, String mimeType) {
        this.id = id;
        this.name = name;
        this.mimeType = mimeType;
    }
}
