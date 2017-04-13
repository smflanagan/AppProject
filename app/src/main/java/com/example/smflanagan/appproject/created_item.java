package com.example.smflanagan.appproject;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.EditText;
import android.widget.TextView;

import static com.example.smflanagan.appproject.R.string.item_name;

public class created_item extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_created_item);
    }

    public void displayItemName(ItemData test)
    {
        String name = test.getItemName();
        TextView findViewById(R.id.ItemNameView)

                .setText(name);

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
