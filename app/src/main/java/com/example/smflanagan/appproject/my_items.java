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

public class my_items extends AppCompatActivity {

    private ListView itemList;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String itemName;
    private double itemCost;
    private String itemCostString;
    private String itemSeller;
    private String itemLocation;
    private String allItemData;
    private ArrayList<ItemData> MyItems;
    private String uid;



    private ArrayList<String> array;

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
            boolean emailVerified = user.isEmailVerified();

            // The user's ID, unique to the Firebase project. Do NOT use this value to
            // authenticate with your backend server, if you have one. Use
            // FirebaseUser.getToken() instead.
            uid = user.getUid();
        }

        myRef = database.getReference(uid);

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
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
            @Override
            public void onChildAdded(DataSnapshot ds, String s) {

                updateItems(ds);

            }

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

    public void toAddItemFromMyItems(View view) {
        Intent intent = new Intent(this, add_item.class);
        startActivity(intent);
    }

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

            for (int i = 0; i < MyItems.size(); i++) {

                itemName = MyItems.get(i).getItemName();
                itemCost = MyItems.get(i).getItemCost();
                itemCostString = Double.toString(itemCost);
                itemSeller = MyItems.get(i).getSeller();
                itemLocation = MyItems.get(i).getItemLocation();
                allItemData = "Name: " + itemName + "\nCost: $" + itemCost + "\nSeller: " + itemSeller + "\nLocation: " + itemLocation;

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

