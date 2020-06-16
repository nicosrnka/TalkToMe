package com.example.talktome;

import android.app.AlertDialog;
import android.content.ContextWrapper;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.provider.Settings;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.io.Console;
import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;

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
               // validate(Email.getText().toString(), Password.getText().toString());
                openActivity();
            }
        });
    }

    private void validate(String email, String password){
        if(email.isEmpty() || password.isEmpty()){
            showFailDialog();
        }else{
            System.out.println(email);
            System.out.println(password);
            OkHttpClient client = new OkHttpClient();
            String url = "http://10.0.2.1:5000/api/Login/Login?email=" + email + "&password=" + password;
            System.out.println(url);
            Request request = new Request.Builder().url(url).build();
            client.newCall(request).enqueue(new Callback() {
                @Override
                public void onFailure(Call call, IOException e) {
                    System.out.println("Fail");
                }

                @Override
                public void onResponse(Call call, Response response) throws IOException {
                    if(response.isSuccessful()){
                        System.out.println(response.body().toString());
                    }else{
                        System.out.println(response.body().toString());
                    }
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