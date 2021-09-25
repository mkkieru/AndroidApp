package com.example.prettyalphas.util;

import com.example.prettyalphas.models.Property;

import java.util.ArrayList;
import java.util.List;

public interface OnPropertySelectedListener {
    public void onRestaurantSelected(Integer position, List<Property> restaurants);

}
