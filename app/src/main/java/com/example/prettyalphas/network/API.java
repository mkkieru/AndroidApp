package com.example.prettyalphas.network;

import com.example.prettyalphas.models.Property;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Query;

public interface API {
    @GET("/properties")
    Call<List<Property>> getProperties();

    @GET("/properties")
    Call<List<Property>> getPropertiesInASpecificLocation(
            @Query("location") String location
    );

}
