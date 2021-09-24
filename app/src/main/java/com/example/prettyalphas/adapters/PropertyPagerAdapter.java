package com.example.prettyalphas.adapters;

import android.util.Log;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import com.example.prettyalphas.UI.PropertyDetailFragment;
import com.example.prettyalphas.models.Property;

import java.util.ArrayList;
import java.util.List;

public class PropertyPagerAdapter extends FragmentStatePagerAdapter /*FragmentStateAdapter*/ {

    private List<Property> mProperties;

    public PropertyPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Property> properties) {
        super(fm, behavior);
        mProperties = properties;
    }

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return PropertyDetailFragment.newInstance(mProperties.get(position));
    }

    @Override
    public int getCount() {
        //return mProperties == null ? 0 : mProperties.size();
        Log.d("properties", mProperties.get(0).getType());
        return mProperties.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mProperties.get(position).getType();
    }
}
