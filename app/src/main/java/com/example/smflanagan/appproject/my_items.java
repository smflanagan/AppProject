package com.example.smflanagan.appproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;
import java.util.List;

public class my_items extends AppCompatActivity {

    private ListView itemList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_items);

        itemList = (ListView) findViewById(R.id.ItemList);

        // Instantiates the array list of items
        ArrayList<ItemData> array = new ArrayList<ItemData>();
        ItemData item = new ItemData("computer",200,"sean","douglas");
        array.add(item);
        //array.add("sean");

        // This is the array adapter, it takes the context of the activity as a
        // first parameter, the type of list view as a second parameter and your
        // array as a third parameter.
        ArrayAdapter<ItemData> arrayAdapter = new ArrayAdapter<ItemData>(this, android.R.layout.simple_list_item_1, array);

        itemList.setAdapter(arrayAdapter);

    }
}

