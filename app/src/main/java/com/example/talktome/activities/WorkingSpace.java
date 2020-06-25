package com.example.talktome.activities;

import android.Manifest;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.graphics.drawable.AnimationDrawable;
import android.speech.RecognizerIntent;
import android.support.constraint.ConstraintLayout;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.telecom.Call;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.example.talktome.R;
import com.example.talktome.activities.SettingsActivity;
import com.example.talktome.calltypes.CallFactory;
import com.example.talktome.calltypes.CallTypes;
import com.example.talktome.calltypes.GeneralCall;
import com.example.talktome.helper.GetCaregiverFromBackend;
import com.example.talktome.helper.MessageSpeaker;
import com.example.talktome.models.CaregiverModel;

import org.jetbrains.annotations.NotNull;
import org.w3c.dom.Text;

import java.util.ArrayList;
import java.util.Locale;

import voice_control.IAssistant;
import voice_control.VoiceControl;
import voice_control.call_assistant.CallAssistant;
import voice_control.commands.ICommand;
import voice_control.smallTalk_assistant.SmallTalkAssistant;
import voice_control.speechRecognizer.AdvancedRecognitionListener;
import voice_control.speechRecognizer.AdvancedSpeechRecognizer;
import voice_control.tvcontrol_assistant.TvControlAssistant;

import android.content.Context;

public class WorkingSpace extends AppCompatActivity {

    private VoiceControl voiceControl;
    private AdvancedSpeechRecognizer speechListner;

    private CallAssistant callAssistant;

    private static final int PERMISSIONS_REQUEST = 100;

    private String currentContactName;

    public void setCurrentContactName(String contactName){
        this.currentContactName = contactName;
    }

    private CallTypes currentCallType;

    public void setCurrentCallType(CallTypes callType) {
        this.currentCallType = callType;
    }

    private TextView textViewR;

    // Set a caregiver to be connected to an emergency call.
    public void setEmergencyCaregiver(@NotNull CaregiverModel emergencyCaregiver){
        this.callAssistant.setEmergencyCaregiver(emergencyCaregiver);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_working_space);

        ConstraintLayout cL = findViewById(R.id.activity_working_space);
        AnimationDrawable aD = (AnimationDrawable) cL.getBackground();
        aD.setEnterFadeDuration(2000);
        aD.setExitFadeDuration(4000);
        aD.start();

        textViewR = (TextView) findViewById(R.id.txvResult);

        Context context = textViewR.getContext();

        GetCaregiverFromBackend getCaregiverFromBackend = new GetCaregiverFromBackend(this.getApplicationContext());
        if(getCaregiverFromBackend.GetCaregiver().isEmpty())
        {
            this.callAssistant = new CallAssistant(context, this, null);
        }
        else{
            this.callAssistant = new CallAssistant(context, this, getCaregiverFromBackend.GetCaregiver().get(0));
        }
        this.voiceControl = new VoiceControl(new IAssistant[] {this.callAssistant, new SmallTalkAssistant(), new TvControlAssistant(context)});

        //this.speechListner = new AdvancedSpeechRecognizer(context, this);
        //this.speechListner.start();
    }

    public void gotoSettings(View view) {
        Intent intent = new Intent(this, SettingsActivity.class);
        startActivity(intent);
    }

    public void requestCall() {

        if (checkSelfPermission(Manifest.permission.READ_CONTACTS) != PackageManager.PERMISSION_GRANTED
                || checkSelfPermission(Manifest.permission.CALL_PHONE) != PackageManager.PERMISSION_GRANTED) {
            requestPermissions(new String[]{Manifest.permission.READ_CONTACTS, Manifest.permission.CALL_PHONE}, PERMISSIONS_REQUEST);
        } else {
            GeneralCall call = CallFactory.getCall(currentCallType, this.getApplicationContext());
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
        intent.putExtra(RecognizerIntent.EXTRA_LANGUAGE, Locale.GERMAN);

        if (intent.resolveActivity(getPackageManager()) != null)
        {
            startActivityForResult(intent, 1);
        }
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data)
    {
        super.onActivityResult(requestCode, resultCode, data);

        if (resultCode == RESULT_OK && data != null && requestCode == 1)
        {
            ArrayList<String> result = data.getStringArrayListExtra(RecognizerIntent.EXTRA_RESULTS);
            this.handleSpeechInput(result.get(0));
        }
    }
/*
    public void handleText(View view)
    {
        TextView textView = (TextView) findViewById(R.id.usertest_textinput);
        String input = textView.getText().toString();

        this.handleSpeechInput(input);
    }
*/
    // Process speech input threw voice control and reads the message aloud.
    public void handleSpeechInput(String formulation){

        ICommand command = this.voiceControl.processSpeechInput(formulation);
        this.textViewR.setText(command.getMessage());
        command.execute();

        // Reads the message aloud
        MessageSpeaker messageSpeaker = new MessageSpeaker(getApplicationContext(), command.getMessage());
        messageSpeaker.onInit(1);
    }
}