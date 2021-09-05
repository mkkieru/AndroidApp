package com.example.prettyalphas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
private Button mViewAllProperties;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mViewAllProperties = (Button) findViewById(R.id.viewall);
        mViewAllProperties.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            //Toast.makeText(MainActivity.this, "Hello World!", Toast.LENGTH_LONG).show();
            Intent intent = new Intent(MainActivity.this,ViewAll.class);
            startActivity(intent);
            }
        });
    }
}