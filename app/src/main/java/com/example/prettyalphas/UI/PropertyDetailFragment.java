package com.example.prettyalphas.UI;

import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.example.prettyalphas.R;
import com.example.prettyalphas.models.Property;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;


public class PropertyDetailFragment extends Fragment {

    @BindView(R.id.restaurantImageView) ImageView mImageLabel;
    @BindView(R.id.restaurantNameTextView) TextView mNameLabel;
    @BindView(R.id.cuisineTextView) TextView mCategoriesLabel;
    @BindView(R.id.ratingTextView) TextView mRatingLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    @BindView(R.id.saveRestaurantButton) TextView mSaveRestaurantButton;

    private Property mProperty;



    public PropertyDetailFragment() {
        // Required empty public constructor
    }

    public static PropertyDetailFragment newInstance(Property property) {
        PropertyDetailFragment propertyDetailFragment = new PropertyDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("restaurant", Parcels.wrap(property));
        propertyDetailFragment.setArguments(args);
        return propertyDetailFragment;
    }

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;
        mProperty = Parcels.unwrap(getArguments().getParcelable("property"));
    }
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_property_detail, container, false);
        ButterKnife.bind(this, view);

        Picasso.get().load(mProperty.getPropertyImage()).into(mImageLabel);
        mNameLabel.setText(mProperty.getType());
        mRatingLabel.setText(mProperty.getLocation());
        mPhoneLabel.setText(mProperty.getValue());
        mAddressLabel.setText(mProperty.getLocation());

        return view;
    }
}