package com.example.talktome.calltypes;

import android.Manifest;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;

import com.example.talktome.activities.WorkingSpace;

import org.jetbrains.annotations.NotNull;

public class CallRequester {

    private static final int PERMISSIONS_REQUEST = 100;

    private String currentContactName;

    public void setCurrentContactName(String contactName){
        this.currentContactName = contactName;
    }

    private CallTypes currentCallType;

    public void setCurrentCallType(CallTypes callType) {
        this.currentCallType = callType;
    }

    private Context context;

    private WorkingSpace workingSpace;

    public CallRequester(@NotNull Context context, @NotNull WorkingSpace workingSpace){
        this.context = context;
        this.workingSpace = workingSpace;
    }

    public void requestCall() {

        if (this.context.checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
                || this.context.checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            this.workingSpace.requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST);
        } else {
            GeneralCall call = CallFactory.getCall(currentCallType, this.context);
            call.tryCallingName(currentContactName);
        }
    }

    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            requestCall();
        }
    }
}
