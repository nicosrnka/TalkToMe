package com.example.talktome.activities;

import android.content.Context;
import android.content.SharedPreferences;
import android.preference.PreferenceManager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.example.talktome.R;
import com.example.talktome.helper.CaregiversAdapter;
import com.example.talktome.models.AddACaregiver;
import com.example.talktome.models.CaregiverGet;
import com.example.talktome.models.CaregiverModel;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class EditCaregivers extends AppCompatActivity {

    private List<CaregiverModel> caregivers;
    private CaregiversAdapter adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        setContentView(R.layout.activity_edit_caregivers);
        RecyclerView rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        caregivers = new ArrayList<>();
        this.initializeData();

    }

    private void initializeData(){
        //
        //LOCAL STORAGE GET
        //
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(EditCaregivers.this);
        String email = myPreferences.getString("email", "");
        //
        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5000/api/Caregiver/").addConverterFactory(GsonConverterFactory.create()).build();
        // AddACaregiver c = new AddACaregiver(FirstName.getText().toString(), LastName.getText().toString(), PhoneNumber.getText().toString(), em);
        JsonApi jsonPlaceholder = retrofit.create(JsonApi.class);
        Map<String, String> params = new HashMap<String, String>();
        params.put("email", email);
        Call<List<CaregiverModel>> call = jsonPlaceholder.getCaregivers(params);
        call.enqueue(new Callback<List<CaregiverModel>>() {
            @Override
            public void onResponse(Call<List<CaregiverModel>> call, Response<List<CaregiverModel>> response) {
                if(response.isSuccessful()){
                    for(CaregiverModel caregiver: response.body()) {
                       // System.out.println(caregiver.getFirstName());
                        caregivers.add(new CaregiverModel(caregiver.getId(), caregiver.getFirstName(), caregiver.getLastName(), caregiver.getPhoneNumber()));
                    }
                    RecyclerView rv = findViewById(R.id.rv);
                    adapter = new CaregiversAdapter(caregivers);
                    adapter.notifyDataSetChanged();

                    rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<CaregiverModel>> call, Throwable t) {

            }
        });
        //caregivers = new ArrayList<>();
        //caregivers.add(new CaregiverModel("Emma", "Wilson", "+434324324234234"));
        //caregivers.add(new CaregiverModel("Lavery", "Maiss", "+32434545654745"));
        //caregivers.add(new CaregiverModel("Lillie", "Watts", "+56465653435"));
        //caregivers.add(new CaregiverModel("Jane", "Doe", "+43660332423445345"));
    }

    //private void changeCaregivers(){
//
    //    for (CaregiverModel caregiver: caregivers){
    //        System.out.println(caregiver.getFirstName());
    //    }
    //    for (CaregiverModel caregiver: caregivers){
    //        Retrofit retrofit = new Retrofit.Builder().baseUrl("http://10.0.2.2:5000/api/Caregiver/").addConverterFactory(GsonConverterFactory.create()).build();
    //        JsonApi jsonPlaceholder = retrofit.create(JsonApi.class);
    //        Call<Integer> call = jsonPlaceholder.changeCareviger(caregiver);
    //        call.enqueue(new Callback<Integer>() {
    //            @Override
    //            public void onResponse(Call<Integer> call, Response<Integer> response) {
    //                if(response.isSuccessful()){
    //                    {
    //                        Context context = getApplicationContext();
    //                        CharSequence text = "Erfolgreich geändert!";
    //                        int duration = Toast.LENGTH_SHORT;
    //
    //                        Toast toast = Toast.makeText(context, text, duration);
    //                        // toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
    //                        toast.show();
    //                    }
    //                }else{
    //                    {
    //                        // Toast.makeText(AddCaregiver.this, "Fehler beim Hinzufügen!", Toast.LENGTH_SHORT).show();
    //                        Context context = getApplicationContext();
    //                        CharSequence text = "Fehler beim Ändern!";
    //                        int duration = Toast.LENGTH_SHORT;
//
    //                        Toast toast = Toast.makeText(context, text, duration);
    //                        // toast.setGravity(Gravity.CENTER_VERTICAL, 0, 0);
    //                        toast.show();
    //                        System.out.println(response.code());
    //                        System.out.println("passt ned");
    //                    }
    //                }
    //            }
//
    //            @Override
    //            public void onFailure(Call<Integer> call, Throwable t) {
//
    //            }
    //        });
    //    }
    //}
}