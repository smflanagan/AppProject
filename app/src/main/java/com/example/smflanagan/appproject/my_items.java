package com.example.smflanagan.appproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
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

import static com.example.smflanagan.appproject.R.layout.activity_my_items;

/**
 * my_items class: Java class collects all of a user's items and displays them in a list view
 * From this class, users are able to identify all of their items and add any item to a specific bundle
 */

public class my_items extends AppCompatActivity {

    /**
     * Class variables required for Firebase implementation
     */
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    /**
     * Class variables allow for item data to be transferred into a ListView containing strings that display item contents
     */
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
    ArrayAdapter<String> arrayAdapter;

    EditText search;

    /**
     * Method sets layout and references Firebase to create ListView
     * Commented out section displays alternate method to obtaining values for the array shown in ListView using putExtra methods
     * The issue with this is that the class can only be loaded after creating a new item
     * Using Firebase allows the data to be accessed everywhere without restrictions
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(activity_my_items);


        database = FirebaseDatabase.getInstance();
        MyItems = new ArrayList<ItemData>();
        //Will add user id instead of items once auth is in place

        FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
        if (user != null) {
            // User is signed in

        } else {
            // No user is signed in
        }

        myRef = database.getReference("Items");

        myRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                updateItems(dataSnapshot);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*
        Bundle bundle = getIntent().getExtras();
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

    /**
     * Method brings user to add_item class when "Add New Item" button is pressed
     *
     * @param view
     */
    public void toAddItemFromMyItems(View view) {
        Intent intent = new Intent(this, add_item.class);
        startActivity(intent);
    }

    /**
     * Method brings user to MainActivity class when "Home" button is pressed
     * @param view
     */
    public void toMainActivityFromMyItems(View view)
    {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

    /**
     * Updates ListView of items
     *
     * @param ds
     */
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
            search = (EditText) findViewById(R.id.Search);

            // This is the array adapter, it takes the context of the activity as a
            // first parameter, the type of list view as a second parameter and your
            // array as a third parameter.
            arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);

            itemList.setAdapter(arrayAdapter);

            // When input is entered into the search bar, only the strings in the array with that begin with
            // that exact sequence of characters appear
            // Search works for name, cost, seller, or location
            search.addTextChangedListener(new TextWatcher() {

                @Override
                public void onTextChanged(CharSequence cs, int arg1, int arg2, int arg3) {
                    // When user changed the Text
                    my_items.this.arrayAdapter.getFilter().filter(cs);
                }

                @Override
                public void beforeTextChanged(CharSequence arg0, int arg1, int arg2, int arg3) {

                }

                @Override
                public void afterTextChanged(Editable arg0) {

                }
            });

        }
    }
}

