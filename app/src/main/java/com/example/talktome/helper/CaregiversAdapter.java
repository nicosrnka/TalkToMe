package com.example.talktome.helper;

import android.support.annotation.NonNull;
import android.support.design.widget.TextInputLayout;
import android.support.v7.widget.CardView;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.talktome.R;
import com.example.talktome.models.CaregiverModel;

import java.util.List;
import java.util.Objects;

public class CaregiversAdapter extends RecyclerView.Adapter<CaregiversAdapter.CaregiverViewHolder> {

    public static class CaregiverViewHolder extends RecyclerView.ViewHolder {
        CardView cv;
        TextInputLayout firstname;
        TextInputLayout lastname;
        TextInputLayout phone;

        CaregiverViewHolder(View itemView) {
            super(itemView);
            cv = itemView.findViewById(R.id.cv);
            firstname = (TextInputLayout)itemView.findViewById(R.id.firstname_text_input_layout);
            lastname = (TextInputLayout)itemView.findViewById(R.id.lastname_text_input_layout);
            phone = (TextInputLayout)itemView.findViewById(R.id.phone_text_input_layout);
        }
    }

    public List<CaregiverModel> caregivers;

    public CaregiversAdapter(List<CaregiverModel> caregivers){
        this.caregivers = caregivers;
    }

    @NonNull
    @Override
    public CaregiversAdapter.CaregiverViewHolder onCreateViewHolder(@NonNull ViewGroup viewGroup, int i) {
        View v = LayoutInflater.from(viewGroup.getContext()).inflate(R.layout.caregiver_edit_item, viewGroup, false);
        return new CaregiverViewHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull CaregiversAdapter.CaregiverViewHolder caregiverViewHolder, int i) {
        Objects.requireNonNull(caregiverViewHolder.firstname.getEditText()).setText(caregivers.get(i).firstname);
        Objects.requireNonNull(caregiverViewHolder).lastname.getEditText().setText(caregivers.get(i).lastname);
        Objects.requireNonNull(caregiverViewHolder).phone.getEditText().setText(caregivers.get(i).phonenumber);
    }

    @Override
    public int getItemCount() {
        return this.caregivers.size();
    }
}
