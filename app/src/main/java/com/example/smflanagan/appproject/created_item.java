package com.example.smflanagan.appproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;


public class created_item extends AppCompatActivity {

    public String name;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String cost;
        String seller;
        String location;
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

    public void displayItemName(String itemNameInput) {
        final TextView item_name = (TextView) findViewById(R.id.ItemNameView);
        item_name.setText(itemNameInput);
    }

    public void displayItemCost(String itemCostInput) {
        final TextView item_cost = (TextView) findViewById(R.id.ItemCost);
        item_cost.setText("$" + itemCostInput);
    }

    public void displaySeller(String sellerInput) {
        final TextView username = (TextView) findViewById(R.id.SellerName);
        username.setText(sellerInput);
    }

    public void displayItemLocation(String locationInput) {
        final TextView item_location = (TextView) findViewById(R.id.SellerLocation);
        item_location.setText(locationInput);
    }

    public void toMyItemsFromCreated(View view)
    {
        Intent intent = new Intent(this, my_items.class);
        intent.putExtra("name", name);
        startActivity(intent);
    }

}
