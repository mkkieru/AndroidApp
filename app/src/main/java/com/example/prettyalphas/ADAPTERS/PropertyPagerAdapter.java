package com.example.prettyalphas.ADAPTERS;


import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import com.example.prettyalphas.UI.PropertyDetailFragment;
import com.example.prettyalphas.models.Property;

import java.util.List;

public class PropertyPagerAdapter extends FragmentPagerAdapter {
    private List<Property> mProperties;

    public PropertyPagerAdapter(@NonNull FragmentManager fm, int behavior, List<Property> properties) {
        super(fm, behavior);
        mProperties = properties;
    }

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