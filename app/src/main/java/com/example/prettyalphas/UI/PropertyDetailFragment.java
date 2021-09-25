package com.example.prettyalphas.UI;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.provider.MediaStore;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.prettyalphas.Constants;
import com.example.prettyalphas.R;
import com.example.prettyalphas.models.Property;
import com.example.prettyalphas.util.OnPropertySelectedListener;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link PropertyDetailFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class PropertyDetailFragment extends Fragment implements View.OnClickListener {

    @BindView(R.id.restaurantImageView) ImageView mImageLabel;
    @BindView(R.id.restaurantNameTextView) TextView mNameLabel;
    @BindView(R.id.cuisineTextView) TextView mCategoriesLabel;
    @BindView(R.id.ratingTextView) TextView mRatingLabel;
    @BindView(R.id.websiteTextView) TextView mWebsiteLabel;
    @BindView(R.id.phoneTextView) TextView mPhoneLabel;
    @BindView(R.id.addressTextView) TextView mAddressLabel;
    @BindView(R.id.saveRestaurantButton) TextView mSaveRestaurantButton;

    private Property mProperty;
    private ArrayList<Property> mProperties;
    private int mPosition;
    private String mSource;


    private static final int REQUEST_IMAGE_CAPTURE = 111;
    private static final int CAMERA_PERMISSION_REQUEST_CODE = 11;
    private String currentPhotoPath;
    private static final String TAG = "image creation value";

    private OnPropertySelectedListener mOnRestaurantSelectedListener;



    public PropertyDetailFragment() {
        // Required empty public constructor
    }


    public static PropertyDetailFragment newInstance(Property property) {
        PropertyDetailFragment propertyDetailFragment = new PropertyDetailFragment();
        Bundle args = new Bundle();
        args.putParcelable("properties", Parcels.wrap(property));
        propertyDetailFragment.setArguments(args);
        return propertyDetailFragment;
    }
    /*@Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnRestaurantSelectedListener = (OnPropertySelectedListener) context;
        } catch (ClassCastException e) {
            throw new ClassCastException(context.toString() + e.getMessage());
        }
    }*/

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        assert getArguments() != null;

        mProperty = Parcels.unwrap(getArguments().getParcelable("properties"));
        mPosition = getArguments().getInt("position");
        //mProperty = mProperties.get(mPosition);
        mSource = getArguments().getString("source");
        setHasOptionsMenu(true);
    }
    @Override
    public void onCreateOptionsMenu(Menu menu, MenuInflater inflater) {
        super.onCreateOptionsMenu(menu, inflater);
        /*if (mSource.equals("saved")) {
            inflater.inflate(R.menu.menu_photo, menu);
        } else {
            inflater.inflate(R.menu.menu_main, menu);
        }*/
        inflater.inflate(R.menu.menu_photo, menu);
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_photo:
                onLaunchCamera();
            default:
                break;
        }
        return false;
    }
    public void onLaunchCamera() {
        Intent takePictureIntent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
        if (takePictureIntent.resolveActivity(getActivity().getPackageManager()) != null) {
            startActivityForResult(takePictureIntent, REQUEST_IMAGE_CAPTURE);
        }
    }
    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_IMAGE_CAPTURE && resultCode == getActivity().RESULT_OK) {
            Bundle extras = data.getExtras();
            Bitmap imageBitmap = (Bitmap) extras.get("data");
            mImageLabel.setImageBitmap(imageBitmap);
            //      encodeBitmapAndSaveToFirebase(imageBitmap);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment

        View view =  inflater.inflate(R.layout.fragment_property_detail, container, false);
        ButterKnife.bind(this, view);
        //Picasso.get().load(mProperty.getPropertyImage()).into(mImageLabel);

        Picasso.get()
                .load(mProperty.getPropertyImage())
                .into(mImageLabel);

        mNameLabel.setText(mProperty.getType());
        mCategoriesLabel.setText(mProperty.getLocation());
        mPhoneLabel.setText(mProperty.getValue().toString());
        mAddressLabel.setText(mProperty.getLocation());



        mPhoneLabel.setOnClickListener(this);
        mSaveRestaurantButton.setOnClickListener(this);

        return view;
    }
    @Override
    public void onClick(View v) {
        if (v == mSaveRestaurantButton) {
            FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
            String uid = user.getUid();
            DatabaseReference restaurantRef = FirebaseDatabase
                    .getInstance()
                    .getReference(Constants.FIREBASE_CHILD_RESTAURANTS)
                    .child(uid);

            DatabaseReference pushRef = restaurantRef.push();
            String pushId = pushRef.getKey();
            mProperty.setPushId(pushId);
            pushRef.setValue(mProperty);

            Toast.makeText(getContext(), "Saved", Toast.LENGTH_SHORT).show();
        }
        if (v == mPhoneLabel){
            Intent phoneIntent = new Intent(Intent.ACTION_DIAL,Uri.parse("tel:" + mProperty.getValue()));
            startActivity(phoneIntent);
        }
    }
}