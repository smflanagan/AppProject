package com.example.smflanagan.appproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

// save_bundle class: Java class that allows user to save a new or edited bundle
public class save_bundle extends AppCompatActivity {

    // Sets layout when save_bundle screen is loaded
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_bundle);
    }

    // Method that saves user inputted data as bundle name when button "Save" is pressed
    // Method also brings user back to add_bundle class
    public void saveBundleName(View view)
    {
        Intent intent = new Intent(this, add_bundle.class);
        startActivity(intent);
    }

}
