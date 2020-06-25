package com.example.talktome.helper;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import com.example.talktome.R;
import com.example.talktome.activities.EditCaregivers;
import com.example.talktome.activities.JsonApi;
import com.example.talktome.models.CaregiverModel;

import org.jetbrains.annotations.NotNull;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class GetCaregiverFromBackend {

    private Context context;

    public GetCaregiverFromBackend(@NotNull Context context) {
        this.context = context;
    }

    public List<CaregiverModel> GetCaregiver() {
        List<CaregiverModel> caregivers = new ArrayList<CaregiverModel>();

        //
        //LOCAL STORAGE GET
        //
        SharedPreferences myPreferences = PreferenceManager.getDefaultSharedPreferences(this.context);
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
                if (response.isSuccessful()) {
                    for (CaregiverModel caregiver : response.body()) {
                        // System.out.println(caregiver.getFirstName());
                        caregivers.add(new CaregiverModel(caregiver.getId(), caregiver.getFirstName(), caregiver.getLastName(), caregiver.getPhoneNumber()));
                    }
                    //RecyclerView rv = findViewById(R.id.rv);
                    //adapter = new CaregiversAdapter(caregivers);
                    //adapter.notifyDataSetChanged();

                    //rv.setAdapter(adapter);
                }
            }

            @Override
            public void onFailure(Call<List<CaregiverModel>> call, Throwable t) {

            }
        });

        return caregivers;
    }
}
