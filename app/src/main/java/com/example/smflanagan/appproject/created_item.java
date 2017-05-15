package com.example.smflanagan.appproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;

/**
 * created_item class: Java class that displays the contents of a user created item
 * Values for item components is taken from user inputted data in add_item class
 */
public class created_item extends AppCompatActivity {

    /**
     * Class variables for each of 4 text components of an item
     */
    private String name;
    private String cost;
    private String seller;
    private String location;

    /**
     * Sets variables equal to input from add_item class and displays the values upon creation of the class
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = getIntent().getExtras();
        name = bundle.getString("name");
        cost = bundle.getString("cost_string");
        seller = bundle.getString("seller");
        location = bundle.getString("location");
        setContentView(R.layout.activity_created_item);
        displayItemName(name);
        displayItemCost(cost);
        displaySeller(seller);
        displayItemLocation(location);
    }

    /**
     * Method displays the item name
     * @param itemNameInput
     */
    public void displayItemName(String itemNameInput) {
        final TextView item_name = (TextView) findViewById(R.id.EnterItemName);
        item_name.setText(itemNameInput);
    }

    /**
     * Method displays the item cost
     * @param itemCostInput
     */
    public void displayItemCost(String itemCostInput) {
        final TextView item_cost = (TextView) findViewById(R.id.EnterItemCost);
        item_cost.setText("$" + itemCostInput);
    }

    /**
     * Method displays the item seller
     * @param sellerInput
     */
    public void displaySeller(String sellerInput) {
        final TextView username = (TextView) findViewById(R.id.SellerName);
        username.setText(sellerInput);
    }

    /**
     * Method displas the item location
     * @param locationInput
     */
    public void displayItemLocation(String locationInput) {
        final TextView item_location = (TextView) findViewById(R.id.SellerLocation);
        item_location.setText(locationInput);
    }

    /**
     * Method brings user to my_items class when "My Items" button is pushed
     * Commented out putExtra methods are not necessary with the implementation of the Firebase API
     * Without Firebase, this information would be brought to the my_items class using putExtras methods
     * @param view
     */
    public void toMyItemsFromCreated(View view)
    {
        Intent intent = new Intent(this, my_items.class);
        /*
        intent.putExtra("name",name);
        intent.putExtra("cost",cost);
        intent.putExtra("seller",seller);
        intent.putExtra("location",location);
        */
        startActivity(intent);
    }

    /**
     * Method brings user to add_bundle class when "Add To Bundle" button is pushed
     * @param view
     */
    public void toAddBundleFromCreated(View view)
    {
        Intent intent = new Intent(this, add_bundle.class);
        startActivity(intent);
    }

}
