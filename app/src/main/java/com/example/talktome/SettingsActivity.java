package com.example.talktome;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class SettingsActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_settings);
    }

    public void gotoAddCaregiverSetting(View view) {
        Intent intent = new Intent(this, AddCaregiver.class);
        startActivity(intent);
    }

    public void gotoEditCaregiversSetting(View view) {
    }
}