package com.example.smflanagan.appproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.EditText;

/**
 * save_bundle class: Java class that allows user to save a new or edited bundle
 */

public class save_bundle extends AppCompatActivity {

    private String bundleName;

    /**
     * Sets layout when save_bundle screen is loaded
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_save_bundle);
    }

    /**
     * Method that saves user inputted data as bundle name when button "Save" is pressed
     * Method also brings user to my_bundle class
     *
     * @param view
     */
    public void saveBundleName(View view) {
        EditText bundle_name = (EditText) findViewById(R.id.EnterBundleName);
        bundleName = bundle_name.getText().toString();
        Intent intent = new Intent(this, add_bundle.class);
        //intent.putExtra("bundleName",bundleName);
        startActivity(intent);
    }

}
