package com.example.prettyalphas.adapters;

import android.hardware.lights.LightState;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentStatePagerAdapter;
import androidx.lifecycle.Lifecycle;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import com.example.prettyalphas.UI.PropertyDetailFragment;
import com.example.prettyalphas.models.Property;

import java.util.List;

public class PropertyPagerAdapter extends FragmentStatePagerAdapter /*FragmentStateAdapter*/ {

    private List<Property> mProperties;

    public PropertyPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Property> properties) {
        super(fm, behavior);
        mProperties = properties;
    }

    /*
    public PropertyPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Property> restaurants) {
        super(fm, behavior);
        mRestaurants = restaurants;
    }

    @Override
    public Fragment createFragment(int position) {
        return null;
    }

    @Override
    public int getItemCount() {
        return 0;
    } */

    @NonNull
    @Override
    public Fragment getItem(int position) {
        return PropertyDetailFragment.newInstance(mProperties.get(position));
    }

    @Override
    public int getCount() {
        return mProperties.size();
    }
    @Override
    public CharSequence getPageTitle(int position) {
        return mProperties.get(position).getType();
    }
}
