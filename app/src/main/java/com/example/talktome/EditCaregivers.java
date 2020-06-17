package com.example.talktome;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class EditCaregivers extends AppCompatActivity {

    private List<CaregiverModel> caregivers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_caregivers);
        RecyclerView rv = findViewById(R.id.rv);
        rv.setHasFixedSize(true);
        LinearLayoutManager llm = new LinearLayoutManager(getApplicationContext());
        rv.setLayoutManager(llm);
        this.initializeData();
        CaregiversAdapter adapter = new CaregiversAdapter(caregivers);
        rv.setAdapter(adapter);
    }

    private void initializeData(){
        caregivers = new ArrayList<>();
        caregivers.add(new CaregiverModel("Emma", "Wilson", "+434324324234234"));
        caregivers.add(new CaregiverModel("Lavery", "Maiss", "+32434545654745"));
        caregivers.add(new CaregiverModel("Lillie", "Watts", "+56465653435"));
        caregivers.add(new CaregiverModel("Jane", "Doe", "+43660332423445345"));
    }
}