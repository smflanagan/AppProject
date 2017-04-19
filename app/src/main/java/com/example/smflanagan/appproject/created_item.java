package com.example.smflanagan.appproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.smflanagan.appproject.R.string.item_name;

public class created_item extends AppCompatActivity {

    public String name;
    public String cost_string;
    public double cost;
    public String seller;
    public String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Log.i("jack","sean");
        Bundle bundle = getIntent().getExtras();
        if (bundle==null)
            Log.i("jack","null");
        name = bundle.getString("name");
        Log.i("jack",name);
        cost_string = bundle.getString("cost_string");
        Log.i("jack",cost_string);
        cost = Double.parseDouble(cost_string);
        seller = bundle.getString("seller");
        Log.i("jack",seller);
        location = bundle.getString("location");
        Log.i("jack",location);
        setContentView(R.layout.activity_created_item);
    }

    public void displayItemName(ItemData test)
    {
        //final TextView item_name = (TextView) findViewById(R.id.ItemNameView);
        //item_name.setText(name);
    }

    public void displayItemCost(ItemData test)
    {
        double cost = test.getItemCost();
    }

    public void displaySeller(ItemData test)
    {
        String seller = test.getSeller();
    }
    public void displayItemLocation(ItemData test)
    {
        String location = test.getItemLocation();
    }
}
