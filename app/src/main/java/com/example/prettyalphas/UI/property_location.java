package com.example.prettyalphas.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prettyalphas.Constants;
import com.example.prettyalphas.R;
import com.example.prettyalphas.adapters.PropertyListAdapter;
import com.example.prettyalphas.models.Property;
import com.example.prettyalphas.network.API;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class property_location extends AppCompatActivity {
    @BindView(R.id.recyclerView2) RecyclerView mRecyclerView2;
    @BindView(R.id.errorTextView2) TextView mErrorTextView2;
    @BindView(R.id.progressBar2) ProgressBar mProgressBar2;

    private PropertyListAdapter mAdapter;
    public List<Property> restaurants;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_location);
        ButterKnife.bind(this);

        Intent intent = getIntent();
        String location = intent.getStringExtra("location");

        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);

        Call<List<Property>> call = api.getPropertiesInASpecificLocation(location);

        call.enqueue(new Callback<List<Property>>() {
            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    restaurants = response.body();
                    mAdapter = new PropertyListAdapter(property_location.this, restaurants);
                    mRecyclerView2.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(property_location.this);
                    mRecyclerView2.setLayoutManager(layoutManager);
                    mRecyclerView2.setHasFixedSize(true);

                    showRestaurants();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<List<Property>> call, Throwable t) {
                Toast.makeText(property_location.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                hideProgressBar();
                showFailureMessage();
            }
        });
    }
    private void showFailureMessage() {
        mErrorTextView2.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView2.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView2.setText("Something went wrong. Please try again later");
        mErrorTextView2.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        mRecyclerView2.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar2.setVisibility(View.GONE);
    }
}