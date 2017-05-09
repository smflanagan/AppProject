package com.example.smflanagan.appproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

public class my_items extends AppCompatActivity {

    private ListView itemList;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private String itemName;
    private ArrayList<ItemData> MyItems;


    private ArrayList<String> array;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);

        //String itemCost;
        //String itemSeller;
        //String itemLocation;
        Bundle bundle = getIntent().getExtras();
        if (bundle != null) {
            itemName = bundle.getString("name");
            //itemCost = bundle.getString("cost_string");
            //Log.i("jack",itemCost);
            //itemSeller = bundle.getString("seller");
            //itemLocation = bundle.getString("location");

            itemList = (ListView) findViewById(R.id.ItemList);

            // Instantiates the array list of items
            array = new ArrayList<String>();
            array.add(itemName);    //+" "+itemCost+" "+itemSeller+" "+itemLocation);
            //array.add("sean");

            // This is the array adapter, it takes the context of the activity as a
            // first parameter, the type of list view as a second parameter and your
            // array as a third parameter.
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);

            itemList.setAdapter(arrayAdapter);
        } else
            itemName = "";

        database = FirebaseDatabase.getInstance();
        MyItems = new ArrayList<ItemData>();
        //Will add user id instead of items once auth is in place
        myRef = database.getReference("Items");
        myRef.addChildEventListener(new ChildEventListener() {
            @Override
            public void onChildAdded(DataSnapshot ds, String s) {
                ItemData item;
                for (DataSnapshot data : ds.getChildren()) {
                   item = ds.getValue(ItemData.class);
                    if (item == null) {
                        continue;
                    }
                    MyItems.add(item);

                }

            }

            @Override
            public void onChildChanged(DataSnapshot dataSnapshot, String s) {

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
}

