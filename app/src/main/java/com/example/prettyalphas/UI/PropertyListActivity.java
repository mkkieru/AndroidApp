package com.example.prettyalphas.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.content.SharedPreferences;
import android.os.Bundle;
import android.preference.PreferenceManager;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.SearchView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prettyalphas.Constants;
import com.example.prettyalphas.R;
import com.example.prettyalphas.adapters.PropertyListAdapter;
import com.example.prettyalphas.models.Property;
import com.example.prettyalphas.network.API;
import com.example.prettyalphas.util.OnPropertySelectedListener;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class PropertyListActivity extends AppCompatActivity implements OnPropertySelectedListener {
    @BindView(R.id.recyclerView) RecyclerView mRecyclerView;
    @BindView(R.id.errorTextView)
    TextView mErrorTextView;
    @BindView(R.id.progressBar)
    ProgressBar mProgressBar;

    private static final String TAG = PropertyListActivity.class.getSimpleName();


    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

    private PropertyListAdapter mAdapter;
    public List<Property> restaurants;

    private Integer mPosition;
    List<Property> mRestaurants;

    private OnPropertySelectedListener restaurantSelectedListener;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_all_properties);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);

        /*
        mRecentAddress = mSharedPreferences.getString(Constants.PREFERENCES_LOCATION_KEY, null);

        if(mRecentAddress != null){
            fetchRestaurants(mRecentAddress);
        }
        */
        fetchProperties();
    }
    @Override
    public void onRestaurantSelected(Integer position, List<Property> restaurants) {
        mPosition = position;
        mRestaurants = restaurants;
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_search, menu);
        ButterKnife.bind(this);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(this);
        mEditor = mSharedPreferences.edit();

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) menuItem.getActionView();

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String location) {
                addToSharedPreferences(location);
                fetchProperties();
                Toast.makeText(PropertyListActivity.this, "PROPERTIES IN : " + location, Toast.LENGTH_SHORT).show();
                return false;
            }


            @Override
            public boolean onQueryTextChange(String location) {
                return false;
            }
        });

        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void showFailureMessage() {
        mErrorTextView.setText("Something went wrong. Please check your Internet connection and try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showUnsuccessfulMessage() {
        mErrorTextView.setText("Something went wrong. Please try again later");
        mErrorTextView.setVisibility(View.VISIBLE);
    }

    private void showRestaurants() {
        mRecyclerView.setVisibility(View.VISIBLE);
    }

    private void hideProgressBar() {
        mProgressBar.setVisibility(View.GONE);
    }

    private void addToSharedPreferences(String location) {
        //mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }

    private void fetchProperties(){
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);

        Call<List<Property>> call = api.getProperties();

        call.enqueue(new Callback<List<Property>>() {
            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                hideProgressBar();

                if (response.isSuccessful()) {
                    restaurants = response.body();
                    mAdapter = new PropertyListAdapter(PropertyListActivity.this, restaurants,restaurantSelectedListener);
                    mRecyclerView.setAdapter(mAdapter);
                    RecyclerView.LayoutManager layoutManager =
                            new LinearLayoutManager(PropertyListActivity.this);
                    mRecyclerView.setLayoutManager(layoutManager);
                    mRecyclerView.setHasFixedSize(true);

                    showRestaurants();
                } else {
                    showUnsuccessfulMessage();
                }
            }

            @Override
            public void onFailure(Call<List<Property>> call, Throwable t) {
                Toast.makeText(PropertyListActivity.this, t.getMessage(), Toast.LENGTH_SHORT).show();
                hideProgressBar();
                showFailureMessage();
            }
        });
    }
}