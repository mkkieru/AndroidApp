package com.example.prettyalphas.adapters;

import android.content.Context;
import android.content.Intent;
import android.content.res.Configuration;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prettyalphas.R;
import com.example.prettyalphas.UI.PropertyDetailActivity;
import com.example.prettyalphas.models.Property;
import com.example.prettyalphas.util.OnPropertySelectedListener;
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyListAdapter.PropertyVieHolder> {
    private static final int MAX_WIDTH = 200;
    private static final int MAX_HEIGHT = 200;

    private List<Property> mProperties;
    private Context mContext;
    private OnPropertySelectedListener mOnRestaurantSelectedListener;


    public PropertyListAdapter(Context mContext,List<Property> mProperties, OnPropertySelectedListener restaurantSelectedListener) {
        this.mProperties = mProperties;
        this.mContext = mContext;
        mOnRestaurantSelectedListener = restaurantSelectedListener;

    }

    @Override
    public PropertyListAdapter.PropertyVieHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_list_item, parent, false);
        PropertyVieHolder viewHolder = new PropertyVieHolder(view, mProperties, mOnRestaurantSelectedListener);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PropertyListAdapter.PropertyVieHolder holder, int position) {
        holder.bindProperty(mProperties.get(position));
    }

    @Override
    public int getItemCount() {
        return mProperties.size();
    }



    public class PropertyVieHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.restaurantImageView)
        ImageView mRestaurantImageView;
        @BindView(R.id.restaurantNameTextView) TextView mNameTextView;
        @BindView(R.id.categoryTextView) TextView mCategoryTextView;


        private Context mContext;
        private int mOrientation;
        private List<Property> mRestaurants;
        private OnPropertySelectedListener mRestaurantSelectedListener;


        public PropertyVieHolder(View itemView, List<Property> mProperties, OnPropertySelectedListener restaurantSelectedListener) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            mOrientation = itemView.getResources().getConfiguration().orientation;
            mRestaurants = mProperties;
            mRestaurantSelectedListener = restaurantSelectedListener;

            /* if (mOrientation == Configuration.ORIENTATION_LANDSCAPE){
                createDetailFragment(0);
            }*/
            itemView.setOnClickListener(this);
        }

        public void bindProperty(Property property) {
            Picasso.get().load(property.getPropertyImage()).into(mRestaurantImageView);
            mNameTextView.setText(property.getType());
            mCategoryTextView.setText(property.getDescription());
        }
        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, PropertyDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("properties", Parcels.wrap(mProperties));
            mContext.startActivity(intent);
        }
    }
}
