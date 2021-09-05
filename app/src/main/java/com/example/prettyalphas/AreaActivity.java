package com.example.prettyalphas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ListView;
import android.widget.TextView;

import butterknife.BindView;
import butterknife.ButterKnife;

public class AreaActivity extends AppCompatActivity {

    @BindView(R.id.lvProgram1) ListView mlvprograms;
    @BindView(R.id.areaTextView) TextView mAreaTextView;

    String[] programName = {"Rental","For Sale","Rental"," For Sale", "Quick Sale","For Sale","Rental"};
    String[] programDescription = {"Studio room for rent", "Luxurious house for sale", "Bedsitter for rent","3 Bedroom fer sale","Urban home for sale","Maisonette for rent ","Single rooms for rent"};
    int [] programImages = {R.drawable.home1,R.drawable.home2,R.drawable.home3,R.drawable.home4,R.drawable.home5,R.drawable.home6,R.drawable.home7};


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_area);
        ButterKnife.bind(this);
        ProgramAdapter programAdapter = new ProgramAdapter(this, programName, programImages,programDescription);
        mlvprograms.setAdapter(programAdapter);
        Intent intent = getIntent();
        String area = intent.getStringExtra("area");
        mAreaTextView.setText("Here are all the listings near " + area);
    }}