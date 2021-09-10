package com.example.prettyalphas.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ListView;

import com.example.prettyalphas.ADAPTERS.ProgramAdapter;
import com.example.prettyalphas.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class ViewAll extends AppCompatActivity {
    @BindView(R.id.lvProgram) ListView mlvprograms;

    String[] programName = {"Rental","For Sale","Rental"," For Sale", "Quick Sale","For Sale","Rental"};
    String[] programDescription = {"Studio room for rent", "Luxurious house for sale", "Bedsitter for rent","3 Bedroom fer sale","Urban home for sale","Maisonette for rent ","Single rooms for rent"};
    int [] programImages = {R.drawable.home1,R.drawable.home2,R.drawable.home3,R.drawable.home4,R.drawable.home5,R.drawable.home6,R.drawable.home7};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_view_all);
        ButterKnife.bind(this);
        ProgramAdapter programAdapter = new ProgramAdapter(this, programName, programImages,programDescription);
        mlvprograms.setAdapter(programAdapter);
    }
  
}