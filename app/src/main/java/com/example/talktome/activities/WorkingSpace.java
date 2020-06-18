package com.example.talktome.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.speech.RecognizerIntent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Toast;

import com.example.talktome.R;
import com.example.talktome.calltypes.CallFactory;
import com.example.talktome.calltypes.CallTypes;
import com.example.talktome.calltypes.GeneralCall;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.Locale;

//import voice_control.SpeechInputHandler;

public class WorkingSpace extends AppCompatActivity {

    //private SpeechInputHandler speechInputHandler;

    private static final int PERMISSIONS_REQUEST = 100;
    private static String currentContactName;
    private CallTypes currentCallType;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_space);
        //this.speechInputHandler = new SpeechInputHandler();
    }

    public void gotoSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    private void requestCall() {
        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST);
        } else {
            GeneralCall call = CallFactory.getCall(currentCallType, getApplicationContext());
            call.tryCallingName(currentContactName);
        }
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NotNull String[] permissions, @NotNull int[] grantResults) {
        if (requestCode == PERMISSIONS_REQUEST && grantResults[0] == PackageManager.PERMISSION_GRANTED) {
            requestCall();
        }
    }

    public void getSpeechInput(View view)
    {
        Intent intent = new Intent(RecognizerIntent.ACTION_RECOGNIZE_SPEECH);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE_MODEL, RecognizerIntent.LANGUAGE_MODEL_FREE_FORM);
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.getDefault());

        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, 1);
        }
        else
        {
            Toast.makeText(this, "Your Device Don't Support Speech Input", Toast.LENGTH_SHORT).show();
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && requestCode == 1)
        {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            //this.speechInputHandler.handle(result.get(0));
        }
    }
}