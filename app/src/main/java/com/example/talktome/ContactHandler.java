package com.example.talktome;

import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.provider.ContactsContract;

import org.jetbrains.annotations.NotNull;

import java.text.MessageFormat;
import java.util.ArrayList;
import java.util.List;

public class ContactHandler {
    Context appContext;

    public ContactHandler(Context context) {
        appContext = context;
    }

    public void tryCallingName(String name) {
        ContactHandler contactHandler = new ContactHandler(appContext);
        List<ContactModel> contacts = contactHandler.getContactsByName(name);
        try {
            contactHandler.handleContactList(contacts);
        } catch (IllegalArgumentException ex) {
            new MessageSpeaker(appContext, ex.getMessage());
        }
    }

    @NotNull
    private List<ContactModel> getContactsByName(String searchString) {
        List<ContactModel> contacts = new ArrayList<>();
        ContentResolver contentResolver = appContext.getContentResolver();
        Cursor cursor = contentResolver.query(
                ContactsContract.Contacts.CONTENT_URI,
                null,
                ContactsContract.Contacts.DISPLAY_NAME + " LIKE " + "'%" + searchString + "%'",
                null,
                null);

        assert cursor != null;
        if (cursor.getCount() > 0) {
            while (cursor.moveToNext()) {
                String id = cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts._ID));
                if (cursor.getInt(cursor.getColumnIndex(ContactsContract.Contacts.HAS_PHONE_NUMBER)) > 0) {
                    Cursor cursorInfo = contentResolver.query(
                            ContactsContract.CommonDataKinds.Phone.CONTENT_URI,
                            null,
                            ContactsContract.CommonDataKinds.Phone.CONTACT_ID + " = ?",
                            new String[]{id},
                            null
                    );

                    assert cursorInfo != null;
                    if (cursorInfo.moveToNext()) {
                        ContactModel contact = new ContactModel(
                                id,
                                cursor.getString(cursor.getColumnIndex(ContactsContract.Contacts.DISPLAY_NAME)),
                                cursorInfo.getString(cursorInfo.getColumnIndex(ContactsContract.CommonDataKinds.Phone.NUMBER))
                        );
                        contacts.add(contact);
                    }
                    cursorInfo.close();
                }
            }
            cursor.close();
        }

        return contacts;
    }

    private void handleContactList(@NotNull List<ContactModel> contacts) {
        if (contacts.isEmpty()) {
            String text = "Der Kontakt konnte nicht gefunden werden.";
            throw new IllegalArgumentException(text);
        } else if (contacts.size() == 1) {
            this.callContact(contacts.get(0));
        } else if (contacts.size() == 2) {
            String text = MessageFormat.format("Möchten Sie {0} oder {1} anrufen?", contacts.get(0).name, contacts.get(1).name);
            throw new IllegalArgumentException(text);
        } else {
            List<ContactModel> commaSeparated = contacts.subList(0, contacts.size() - 2);
            StringBuilder text = new StringBuilder("Möchten Sie ");
            for (ContactModel contact:
                    commaSeparated) {
                text.append(contact.name).append(", ");
            }
            String orText = MessageFormat.format("{0} oder {1} anrufen?", contacts.get(contacts.size() - 2).name, contacts.get(contacts.size() - 1).name);
            text.append(orText);
            throw new IllegalArgumentException(text.toString());
        }
    }

    private void callContact(@NotNull ContactModel contactModel) {
        Intent i = new Intent(Intent.ACTION_CALL);
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setData(Uri.parse("tel:" + contactModel.mobileNumber));
        appContext.startActivity(i);
    }
}
