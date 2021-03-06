package com.example.talktome.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.talktome.activities.JsonApi;
import com.example.talktome.R;
import com.example.talktome.models.AddACaregiver;
import com.example.talktome.models.CaregiverModel;
import com.example.talktome.*;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import org.json.JSONObject;

import java.util.prefs.Preferences;

import okhttp3.HttpUrl;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class AddCaregiver extends AppCompatActivity {
    private EditText FirstName;
    private EditText LastName;
    private EditText PhoneNumber;
    private Button SendData;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_caregiver);
        FirstName = (EditText) findViewById(R.id.firstname_text_input_edit_text);
        LastName = (EditText) findViewById(R.id.lastname_text_input_edit_text);
        PhoneNumber = (EditText) findViewById(R.id.phone_text_input_edit_text);
        SendData = (Button) findViewById(R.id.setting_add_caregiver_button);
        SendData.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                sendToApi();
            }
        });

    }
    private void sendToApi(){
        SharedPreferences pref = PreferenceManager.getDefaultSharedPreferences(AddCaregiver.this);
        String em = pref.getString("email", "");
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5000/api/Caregiver/").addConverterFactory(GsonConverterFactory.create()).build();
        AddACaregiver c = new AddACaregiver(FirstName.getText().toString(), LastName.getText().toString(), PhoneNumber.getText().toString(), em);
        JsonApi jsonApi = retrofit.create(JsonApi.class);
        JsonApi jsonPlaceholder = retrofit.create(JsonApi.class);
        Call<AddACaregiver> call = jsonPlaceholder.addCaregiver(c);
        call.enqueue(new Callback<AddACaregiver>() {
            @Override
            public void onResponse(Call<AddACaregiver> call, Response<AddACaregiver> response) {
                if(response.isSuccessful()){
                    {
                        FirstName.setText("");
                        LastName.setText("");
                        PhoneNumber.setText("");
                        System.out.println("Passt");
                        Context context = getApplicationContext();
                        CharSequence text = "Hinzugefügt!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                       // toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                    }
                }else{
                    {
                       // Toast.makeText(AddCaregiver.this, "Fehler beim Hinzufügen!", Toast.LENGTH_SHORT).show();
                        Context context = getApplicationContext();
                        CharSequence text = "Fehler beim Hinzufügen!";
                        int duration = Toast.LENGTH_SHORT;

                        Toast toast = Toast.makeText(context, text, duration);
                        // toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
                        toast.show();
                        System.out.println(response.code());
                        System.out.println("passt ned");
                    }
                }
            }

            @Override
            public void onFailure(Call<AddACaregiver> call, Throwable t) {

            }
        });


    }
}