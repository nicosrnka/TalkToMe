package com.example.talktome.activities;

import com.example.talktome.models.AddACaregiver;
import com.example.talktome.models.CaregiverGet;
import com.example.talktome.models.CaregiverModel;
import com.example.talktome.models.LoginReturn;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.PUT;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonApi {

    @POST("addCaregiver")
    Call<AddACaregiver> addCaregiver(@Body AddACaregiver post);
    @GET("login")
    Call<Integer> getLogin(@QueryMap Map<String, String> params);
    @GET("getCaregivers")
    Call<List<CaregiverModel>> getCaregivers(@QueryMap Map<String, String> params);
    @PUT("editCaregiver")
    Call<Integer> changeCareviger(@Body CaregiverModel post);
}
