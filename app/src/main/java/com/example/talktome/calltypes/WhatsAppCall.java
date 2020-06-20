package com.example.talktome.calltypes;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import com.example.talktome.models.CaregiverModel;
import com.example.talktome.models.ContactModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.List;

public class WhatsAppCall extends GeneralCall {
    public WhatsAppCall(Context context) {
        super(context);
    }

    public void callCaregiver(@NotNull CaregiverModel caregiver) {
        List<ContactModel> contacts = getContactByNumber(caregiver.phonenumber);
        if (contacts.size() == 1) {
            callContact(contacts.get(0));
        }
    }

    @NotNull
    private List<ContactModel> getContactByNumber(String phonenumber) {
        phonenumber = internationalizePhonenumber(phonenumber).replaceAll("\\s","").substring(1);
        List<ContactModel> contacts = new ArrayList<>();
        ContentResolver resolver = appContext.getContentResolver();
        Cursor cursor = resolver.query(
                ContactsContract.Data.CONTENT_URI,
                null,
                ContactsContract.Data.MIMETYPE + " = 'vnd.android.cursor.item/vnd.com.whatsapp.voip.call' AND " + ContactsContract.Data.DATA1 + " = '" + phonenumber + "@s.whatsapp.net'",
                null,
                ContactsContract.Contacts.DISPLAY_NAME);

        assert cursor != null;
        while (cursor.moveToNext()) {

            ContactModel contact = new ContactModel(
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Data._ID)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE))
            );
            contacts.add(contact);
        }
        cursor.close();

        return contacts;
    }

    protected List<ContactModel> getContactsByName(String searchString) {
        List<ContactModel> contacts = new ArrayList<>();
        ContentResolver resolver = appContext.getContentResolver();
        Cursor cursor = resolver.query(
                ContactsContract.Data.CONTENT_URI,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " LIKE " + "'%" + searchString + "%' AND " + ContactsContract.Data.MIMETYPE + " = 'vnd.android.cursor.item/vnd.com.whatsapp.voip.call'",
                null,
                ContactsContract.Contacts.DISPLAY_NAME);

        assert cursor != null;
        while (cursor.moveToNext()) {

            ContactModel contact = new ContactModel(
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Data._ID)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Data.DISPLAY_NAME)),
                    cursor.getString(cursor.getColumnIndex(ContactsContract.Data.MIMETYPE))
            );
            contacts.add(contact);
        }
        cursor.close();

        return contacts;
    }

    protected void callContact(@NotNull ContactModel contactModel) {
        if (contactModel.mimeType.equals("vnd.android.cursor.item/vnd.com.whatsapp.voip.call")) {
            Intent i = new Intent();
            i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            i.setAction(Intent.ACTION_VIEW);
            i.setDataAndType(Uri.parse("content://com.android.contacts/data/" + contactModel.id), contactModel.mimeType);
            i.setPackage("com.whatsapp");
            appContext.startActivity(i);
        }
    }
}
