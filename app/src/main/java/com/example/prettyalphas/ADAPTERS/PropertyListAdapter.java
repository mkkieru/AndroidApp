package com.example.prettyalphas.ADAPTERS;

import android.content.Context;
import android.content.Intent;
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
import com.squareup.picasso.Picasso;

import org.parceler.Parcels;

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
        holder.bindProperty(mProperties.get(position));
    }

    @Override
    public int getItemCount() {
        return mProperties.size();
    }

    public class PropertyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        @BindView(R.id.propertyImageView)
        ImageView mpropertyImageView;
        @BindView(R.id.propertyTypeTextView)
        TextView mpropertyTypeTextView;
        @BindView(R.id.descriptionTextView)
        TextView mdescriptionTextView;

        private Context mContext;

        public PropertyViewHolder(View itemView) {
            super(itemView);
            ButterKnife.bind(this, itemView);
            mContext = itemView.getContext();
            itemView.setOnClickListener(this);
        }
        public void bindProperty(Property property) {
            Picasso.get().load(property.getPropertyImage()).into(mpropertyImageView);
            mpropertyTypeTextView.setText(property.getType());
            mdescriptionTextView.setText(property.getDescription());
        }
        @Override
        public void onClick(View v) {
            int itemPosition = getLayoutPosition();
            Intent intent = new Intent(mContext, PropertyDetailActivity.class);
            intent.putExtra("position", itemPosition);
            intent.putExtra("restaurants", Parcels.wrap(mProperties));
            mContext.startActivity(intent);
        }
    }
}
