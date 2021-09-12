package com.example.prettyalphas.network;

import com.example.prettyalphas.models.Property;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface API {
    @GET("/properties") Call<List<Property>> getProperties();
}
