package com.example.prettyalphas.ADAPTERS;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.prettyalphas.R;
import com.example.prettyalphas.models.Property;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class PropertyListAdapter extends RecyclerView.Adapter<PropertyListAdapter.PropertyViewHolder> {
    private List<Property> mProperties;
    private Context mContext;

    public PropertyListAdapter(Context context,List<Property> properties) {
        this.mProperties = properties;
        this.mContext = context;
    }
    @Override
    public PropertyListAdapter.PropertyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.property_list_item, parent, false);
        PropertyViewHolder viewHolder = new PropertyViewHolder(view);
        return viewHolder;
    }

    @Override
    public void onBindViewHolder(PropertyListAdapter.PropertyViewHolder holder, int position) {
        holder.bindRestaurant(mProperties.get(position));
    }



    @Override
    public int getItemCount() {
        return mProperties.size();
    }

    public class PropertyViewHolder extends RecyclerView.ViewHolder {
        @BindView(R.id.restaurantImageView)
        ImageView mRestaurantImageView;
        @BindView(R.id.restaurantNameTextView)
        TextView mNameTextView;
        @BindView(R.id.categoryTextView)
        TextView mCategoryTextView;

        private Context mContext;

        public PropertyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
        }
        public void bindRestaurant(Property restaurant) {
            mNameTextView.setText(restaurant.getType());
            mCategoryTextView.setText(restaurant.getDescription());
        }
    }
}
