package com.example.talktome.activities;

import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

import com.example.talktome.R;

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
        Intent intent = new Intent(this, EditCaregivers.class);
        startActivity(intent);
    }

    public void gotoAddIPSetting(View view) {
        Intent intent = new Intent(this, AddIp.class);
        startActivity(intent);
    }

    public void Logout(View view){
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(SettingsActivity.this);
        SharedPreferences.Editor editor = myPreferences.edit();
        editor.putBoolean("login", false);
        editor.commit();
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }
}