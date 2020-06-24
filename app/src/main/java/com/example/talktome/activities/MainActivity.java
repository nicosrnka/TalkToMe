package com.example.talktome.activities;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.example.talktome.R;
import com.example.talktome.models.AddACaregiver;
import com.example.talktome.models.LoginReturn;
import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

import java.io.Console;
import java.io.IOException;
import java.util.HashMap;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class MainActivity extends AppCompatActivity {

    private EditText Email;
    private EditText Password;
    private Button Login;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
        boolean isLogin = myPreferences.getBoolean("login", false);
        if(isLogin){
            openActivity();
        }
        Email = (EditText) findViewById(R.id.Email);
        Password = (EditText) findViewById(R.id.Password);
        Login = (Button) findViewById(R.id.Login);
        Login.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view) {
                validate(Email.getText().toString(), Password.getText().toString());
               // openActivity();
            }
        });
    }

    private void validate(String email, String password){
        if(email.isEmpty() || password.isEmpty()){
            showFailDialog();
        }else{
            System.out.println(email);
            System.out.println(password);
            Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5000/api/Login/").addConverterFactory(GsonConverterFactory.create()).build();
           // AddACaregiver c = new AddACaregiver(FirstName.getText().toString(), LastName.getText().toString(), PhoneNumber.getText().toString(), em);
            JsonApi jsonPlaceholder = retrofit.create(JsonApi.class);
            Map<String, String> params = new HashMap<String, String>();
            params.put("email", email);
            params.put("password", password);
            Call<Integer> call = jsonPlaceholder.getLogin(params);
            call.enqueue(new Callback<Integer>() {
                @Override
                public void onResponse(Call<Integer> call, Response<Integer> response) {
                    if(response.isSuccessful()){
                        showSuccessDialog();
                        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(MainActivity.this);
                        SharedPreferences.Editor editor = myPreferences.edit();
                        editor.putBoolean("login", true);
                        editor.putInt("id", response.body().intValue());
                        editor.putString("email", email);
                        editor.commit();
                        System.out.println("Body: " + response.body().intValue());

                        System.out.println("geht");
                    }else{
                        showFailDialog();
                        System.out.println("ned");
                    }
                }
                @Override
                public void onFailure(Call<Integer> call, Throwable t) {

                }
            });
        }
    }

    private void showFailDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Fehler bei der Anmeldung!");
        alert.setMessage("Anmeldung fehlgeschlagen. Versuchen Sie es bitte erneut.");
        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {

            }
        });
        alert.create().show();
    }
    private void showSuccessDialog(){
        AlertDialog.Builder alert = new AlertDialog.Builder(this);
        alert.setTitle("Anmeldung erfolgreich!");
        alert.setMessage("");
        alert.setPositiveButton("Okay", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                openActivity();
            }
        });
        alert.create().show();
    }

    private void openActivity(){
        Intent intent = new Intent(this, WorkingSpace.class);
        startActivity(intent);
    }
}