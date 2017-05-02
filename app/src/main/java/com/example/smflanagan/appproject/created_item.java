package com.example.smflanagan.appproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


public class created_item extends AppCompatActivity {

    private static final String TAG = "MainActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        String name;
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

        Log.i("jack","aqui");

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();

        Log.i("jack","here");

        DatabaseReference myRef = database.getReference("message");

        myRef.setValue("Hello, World!");

        // Read from the database
        myRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                // This method is called once with the initial value and again
                // whenever data at this location is updated.
                String value = dataSnapshot.getValue(String.class);
                Log.d(TAG, "Value is: " + value);
            }

            @Override
            public void onCancelled(DatabaseError error) {
                // Failed to read value
                Log.w(TAG, "Failed to read value.", error.toException());
            }
        });
    }

    public void displayItemName(String itemNameInput)
    {
        final TextView item_name = (TextView) findViewById(R.id.ItemNameView);
        item_name.setText(itemNameInput);
    }

    public void displayItemCost(String itemCostInput)
    {
        final TextView item_cost = (TextView) findViewById(R.id.ItemCost);
        item_cost.setText("$" + itemCostInput);
    }

    public void displaySeller(String sellerInput)
    {
        final TextView username = (TextView) findViewById(R.id.SellerName);
        username.setText(sellerInput);
    }
    public void displayItemLocation(String locationInput)
    {
        final TextView item_location = (TextView) findViewById(R.id.SellerLocation);
        item_location.setText(locationInput);
    }
}
