package com.example.talktome;

/**
 * Instrumented test, which will execute on an Android device.
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */

class ContactModel {
    public String id;
    public String name;
    public String mobileNumber;

    public ContactModel(String id, String name, String mobileNumber) {
        this.id = id;
        this.name = name;
        this.mobileNumber = mobileNumber;
    }
}
