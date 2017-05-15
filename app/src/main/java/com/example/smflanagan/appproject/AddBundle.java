package com.example.smflanagan.appproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * AddBundle class: Java class that enables user to add and save a bundle
 * Also used to edit and save previously created bundles
 */
public class AddBundle extends AppCompatActivity {

    /**
     * Sets the layout when AddBundle screen is loaded
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bundle);
    }

    /**
     * Method brings user to AddItem class when "Add Item" button is pressed
     *
     * @param view
     */
    public void toAddItem(View view) {
        Intent intent = new Intent(this, AddItem.class);
        startActivity(intent);
    }

    /**
     * Method brings user to SaveBundle class when "Save Bundle" button is pressed
     * In SaveBundle class, user is able to save a bundle they have created or edited
     *
     * @param view
     */
    public void toSaveBundle(View view) {
        Intent intent = new Intent(this, SaveBundle.class);
        startActivity(intent);
    }

    /**
     * Method brings user to MyItems class when "My Items" button is pressed
     *
     * @param view
     */
    public void toMyItemsFromAddBundle(View view) {
        Intent intent = new Intent(this, MyItems.class);
        startActivity(intent);
    }

}
