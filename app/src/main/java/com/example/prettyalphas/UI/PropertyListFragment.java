package com.example.prettyalphas.UI;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.core.view.MenuItemCompat;
import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.preference.PreferenceManager;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.SearchView;
import android.widget.Toast;

import com.example.prettyalphas.Constants;
import com.example.prettyalphas.R;
import com.example.prettyalphas.adapters.PropertyListAdapter;
import com.example.prettyalphas.models.Property;
import com.example.prettyalphas.network.API;
import com.example.prettyalphas.util.OnPropertySelectedListener;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PropertyListFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PropertyListFragment extends Fragment {
    @BindView(R.id.recyclerView)
    RecyclerView mRecyclerView;

    private SharedPreferences mSharedPreferences;
    private SharedPreferences.Editor mEditor;
    private String mRecentAddress;

    private PropertyListAdapter mAdapter;
    public List<Property> restaurants;

    private OnPropertySelectedListener mOnRestaurantSelectedListener;

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnRestaurantSelectedListener = (OnPropertySelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + e.getMessage());
        }
    }


    public PropertyListFragment() {
        // Required empty public constructor
    }

    public static PropertyListFragment newInstance(String param1, String param2) {
        PropertyListFragment fragment = new PropertyListFragment();
        Bundle args = new Bundle();
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mSharedPreferences = PreferenceManager.getDefaultSharedPreferences(getActivity());
        mEditor = mSharedPreferences.edit();

        // Instructs fragment to include menu options:
        setHasOptionsMenu(true);
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_property_list, container, false);
        ButterKnife.bind(this, view);

        mRecentAddress = mSharedPreferences.getString("location", null);

        if (mRecentAddress != null) {
            fetchProperties(mRecentAddress);
        }

        return view;
    }

    public void fetchProperties(String location) {
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl(Constants.BASE_URL)
                .addConverterFactory(GsonConverterFactory.create())
                .build();
        API api = retrofit.create(API.class);

        Call<List<Property>> call = api.getProperties();

        call.enqueue(new Callback<List<Property>>() {
            @Override
            public void onResponse(Call<List<Property>> call, Response<List<Property>> response) {
                restaurants = response.body();

                getActivity().runOnUiThread(new Runnable() {
                    // Line above states 'getActivity()' instead of previous 'RestaurantListActivity.this'
                    // because fragments do not have own context, and must inherit from corresponding activity.

                    @Override
                    public void run() {
                        mAdapter = new PropertyListAdapter(getActivity(), restaurants, mOnRestaurantSelectedListener);
                        // Line above states `getActivity()` instead of previous
                        // 'getApplicationContext()' because fragments do not have own context,
                        // must instead inherit it from corresponding activity.

                        mRecyclerView.setAdapter(mAdapter);
                        RecyclerView.LayoutManager layoutManager = new LinearLayoutManager(getActivity());
                        // Line above states 'new LinearLayoutManager(getActivity());' instead of previous
                        // 'new LinearLayoutManager(RestaurantListActivity.this);' when method resided
                        // in RestaurantListActivity because Fragments do not have context
                        // and must instead inherit from corresponding activity.

                        mRecyclerView.setLayoutManager(layoutManager);
                        mRecyclerView.setHasFixedSize(true);
                    }
                });
            }

            @Override
            public void onFailure(Call<List<Property>> call, Throwable t) {

            }
        });
    }

    @Override
    // Method is now void, menu inflater is now passed in as argument:
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {

        // Call super to inherit method from parent:
        super.onCreateOptionsMenu(menu, inflater);

        inflater.inflate(R.menu.menu_search, menu);

        MenuItem menuItem = menu.findItem(R.id.action_search);
        SearchView searchView = (SearchView) MenuItemCompat.getActionView(menuItem);

        searchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {

            @Override
            public boolean onQueryTextSubmit(String query) {
                addToSharedPreferences(query);
                fetchProperties(query);
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                return false;
            }
        });
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        return super.onOptionsItemSelected(item);
    }

    private void addToSharedPreferences(String location) {
        //mEditor.putString(Constants.PREFERENCES_LOCATION_KEY, location).apply();
    }
}