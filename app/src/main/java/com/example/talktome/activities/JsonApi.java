package com.example.talktome.activities;

import com.example.talktome.models.AddACaregiver;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.Field;
import retrofit2.http.FieldMap;
import retrofit2.http.FormUrlEncoded;
import retrofit2.http.GET;
import retrofit2.http.POST;
import retrofit2.http.Path;
import retrofit2.http.Query;
import retrofit2.http.QueryMap;
import retrofit2.http.Url;

public interface JsonApi {

    @POST("addCaregiver")
    Call<AddACaregiver> addCaregiver(@Body AddACaregiver post);
}
