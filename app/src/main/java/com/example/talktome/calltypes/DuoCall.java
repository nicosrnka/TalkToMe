package com.example.talktome.calltypes;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;

import com.example.talktome.models.CaregiverModel;
import com.example.talktome.models.ContactModel;

import org.jetbrains.annotations.NotNull;

public class DuoCall extends GeneralCall {
    public DuoCall(Context context) {
        super(context);
    }

    public void callCaregiver(@NotNull CaregiverModel caregiver) {
        Intent i = new Intent("com.google.android.apps.tachyon.action.CALL");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setPackage("com.google.android.apps.tachyon");
        i.setData(Uri.parse("tel:" + internationalizePhonenumber(caregiver.getPhoneNumber())));
        appContext.startActivity(i);
    }

    protected void callContact(@NotNull ContactModel contactModel) {
        Intent i = new Intent("com.google.android.apps.tachyon.action.CALL");
        i.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
        i.setPackage("com.google.android.apps.tachyon");
        i.setData(Uri.parse("tel:" + internationalizePhonenumber(contactModel.mobileNumber)));
        appContext.startActivity(i);
    }
}
