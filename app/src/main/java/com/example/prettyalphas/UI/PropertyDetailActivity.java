package com.example.prettyalphas.UI;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;

import com.example.prettyalphas.R;
import com.example.prettyalphas.adapters.PropertyPagerAdapter;
import com.example.prettyalphas.models.Property;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;

public class PropertyDetailActivity extends AppCompatActivity {


    @BindView(R.id.viewPager) ViewPager mViewPager;
    private PropertyPagerAdapter adapterViewPager;
    List<Property> mProperties;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_property_detail);

        mProperties = Parcels.unwrap(getIntent().getParcelableExtra("restaurants"));
        int startingPosition = getIntent().getIntExtra("position", 0);

        adapterViewPager = new PropertyPagerAdapter(getSupportFragmentManager(), FragmentPagerAdapter.BEHAVIOR_RESUME_ONLY_CURRENT_FRAGMENT, mProperties);
        mViewPager.setAdapter(adapterViewPager);
        mViewPager.setCurrentItem(startingPosition);
    }
}