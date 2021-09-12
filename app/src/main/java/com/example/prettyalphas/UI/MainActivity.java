package com.example.prettyalphas.UI;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.example.prettyalphas.R;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends AppCompatActivity {

    @BindView (R.id.viewAllButton) Button mViewAllProperties;
    @BindView (R.id.searchItemEditText) EditText mItemToSearch;
    @BindView (R.id.searchButton) Button mSearchItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);

        mViewAllProperties.setOnClickListener(this::onClick);
        mSearchItem.setOnClickListener(this::onClick);
    }

    public void onClick(View view) {
        if (view == mSearchItem){
            String area = mItemToSearch.getText().toString();
            if (area.equals("")) {
                Toast.makeText(MainActivity.this, "Please Enter An Area ... ", Toast.LENGTH_SHORT).show();
            }
            else{
                Toast.makeText(MainActivity.this, "Searching ... ", Toast.LENGTH_SHORT).show();
                    /*Intent intent = new Intent(MainActivity.this, AreaActivity.class);
                    intent.putExtra("area", area);
                    startActivity(intent);*/
            }
        }
        if (view == mViewAllProperties){
            Intent intent = new Intent(MainActivity.this, All_Properties.class);
            startActivity(intent);
        }
    }
}