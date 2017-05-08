package com.example.smflanagan.appproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import java.util.ArrayList;
import java.util.List;

public class my_items extends AppCompatActivity {

    private ListView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);

        String itemName;
        String itemCost;
        String itemSeller;
        String itemLocation;
        Bundle bundle = getIntent().getExtras();
        if(bundle!=null)
        {
            itemName = bundle.getString("name");
            itemCost = bundle.getString("cost_string");
            itemSeller = bundle.getString("seller");
            itemLocation = bundle.getString("location");

            itemList = (ListView) findViewById(R.id.ItemList);

            // Instantiates the array list of items
            ArrayList<String> array = new ArrayList<String>();
            array.add(itemName+" "+itemCost+" "+itemSeller+" "+itemLocation);
            //array.add("sean");

            // This is the array adapter, it takes the context of the activity as a
            // first parameter, the type of list view as a second parameter and your
            // array as a third parameter.
            ArrayAdapter<String> arrayAdapter = new ArrayAdapter<String>(this, android.R.layout.simple_list_item_1, array);

            itemList.setAdapter(arrayAdapter);
        }

    }

    public void toAddItemFromMyItems(View view)
    {
        Intent intent = new Intent(this, add_item.class);
        startActivity(intent);
    }
}

