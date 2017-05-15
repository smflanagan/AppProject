package com.example.smflanagan.appproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;



/**
 * add_bundle class: Java class that enables user to add and save a bundle
 * Also used to edit and save previously created bundles
 */
public class add_bundle extends AppCompatActivity {

    /**
     * Sets the layout when add_bundle screen is loaded
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bundle);
    }

    /**
     * Method brings user to add_item class when "Add Item" button is pressed
     * @param view
     */
    public void toAddItem(View view)
    {
        Intent intent = new Intent(this, add_item.class);
        startActivity(intent);
    }

    /**
     * Method brings user to save_bundle class when "Save Bundle" button is pressed
     * In save_bundle class, user is able to save a bundle they have created or edited
     * @param view
     */
    public void toSaveBundle(View view)
    {
        Intent intent = new Intent(this, save_bundle.class);
        startActivity(intent);
    }

    /**
     * Method brings user to my_items class when "My Items" button is pressed
     * @param view
     */
    public void toMyItemsFromAddBundle(View view)
    {
        Intent intent = new Intent(this, my_items.class);
        startActivity(intent);
    }

}
