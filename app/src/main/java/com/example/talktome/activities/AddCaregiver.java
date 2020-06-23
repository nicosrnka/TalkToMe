package com.example.talktome.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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
        SharedPreferences pref = getPreferences(Context.MODE_PRIVATE);
        String em = pref.getString("email", "");
        em = "nico.srnka@gmail.com";
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
                        System.out.println("Passt");
                    }
                }else{
                    {
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