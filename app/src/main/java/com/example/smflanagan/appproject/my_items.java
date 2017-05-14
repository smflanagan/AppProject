package com.example.smflanagan.appproject;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

// my_items class: Java class collects all of a user's items and displays them in a list view
// From this class, users are able to identify all of their items and add any item to a specific bundle
public class my_items extends AppCompatActivity {

    // Class variables required for Firebase implementation
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    // Class variables allow for item data to be transferred into a ListView containing strings that display item contents
    private String itemName;
    private double itemCost;
    private String itemCostString;
    private String itemSeller;
    private String itemLocation;
    private String allItemData;
    private ListView itemList;
    private String uid;
    private ArrayList<ItemData> MyItems;
    private ArrayList<String> array;

    // Method sets layout and references Firebase to create ListView
    // Commented out section displays alternate method to obtaining values for the array shown in ListView using putExtra methods
    // The issue with this is that the class can only be loaded after creating a new item
    // Using Firebase allows the data to be accessed everywhere without restrictions
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);


        database = FirebaseDatabase.getInstance();
        MyItems = new ArrayList<ItemData>();
        //Will add user id instead of items once auth is in place

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // Name, email address, and profile photo Url
            String name = user.getDisplayName();
            String email = user.getEmail();
            Uri photoUrl = user.getPhotoUrl();

            // Check if user's email is verified
            //boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            uid = user.getUid();
        }

        myRef = database.getReference("Items");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot)
            {
                updateItems(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            itemName = bundle.getString("name");
            itemCost = bundle.getString("cost");
            itemSeller = bundle.getString("seller");
            itemLocation = bundle.getString("location");
            allItemData = "Name: " + itemName + "\nCost: $" + itemCost + "\nSeller: " + itemSeller + "\nLocation: " + itemLocation;

            itemList = (ListView) findViewById(R.id.ItemList);

            // Instantiates the array list of items
            array = new ArrayList<String>();
            array.add(allItemData);

            // This is the array adapter, it takes the context of the activity as a
            // first parameter, the type of list view as a second parameter and your
            // array as a third parameter.
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);

            itemList.setAdapter(arrayAdapter);
        } else
            allItemData = "";
            */

        myRef.addChildEventListener(new ChildEventListener() {
            // When an item is added, ListView updates to incorporate new item
            @Override
            public void onChildAdded(DataSnapshot ds, String s) {

                updateItems(ds);

            }

            // When an item is changed, ListView updates to incorporate change in item
            @Override
            public void onChildChanged(DataSnapshot ds, String s) {

                updateItems(ds);
            }

            @Override
            public void onChildRemoved(DataSnapshot dataSnapshot) {

            }

            @Override
            public void onChildMoved(DataSnapshot dataSnapshot, String s) {

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    // Method brings user to add_item class when "Add New Item" button is pressed
    public void toAddItemFromMyItems(View view) {
        Intent intent = new Intent(this, add_item.class);
        startActivity(intent);
    }

    // Updates ListView of items
    public void updateItems(DataSnapshot ds) {
        ItemData item;
        for (DataSnapshot data : ds.getChildren()) {
            item = ds.getValue(ItemData.class);
            if (item == null) {

                continue;
            }
            MyItems.add(item);

            // Instantiates the array list of items
            array = new ArrayList<String>();

            // Iteration loop creates item data for each item in ListView display
            for (int i = 0; i < MyItems.size(); i = i + 4) {

                itemName = MyItems.get(i).getItemName();
                itemCost = MyItems.get(i).getItemCost();
                itemCostString = Double.toString(itemCost);
                itemSeller = MyItems.get(i).getSeller();
                itemLocation = MyItems.get(i).getItemLocation();
                allItemData = "Name: " + itemName + "\nCost: $" + itemCost + "\nSeller: " + itemSeller + "\nLocation: " + itemLocation;

                if (itemCost != 0)
                    array.add(allItemData);
            }

            itemList = (ListView) findViewById(R.id.ItemList);

            // This is the array adapter, it takes the context of the activity as a
            // first parameter, the type of list view as a second parameter and your
            // array as a third parameter.
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);

            itemList.setAdapter(arrayAdapter);

        }
    }
}

