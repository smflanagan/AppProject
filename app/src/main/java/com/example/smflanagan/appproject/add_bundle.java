package com.example.smflanagan.appproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

public class add_bundle extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_bundle);
    }

    public void toAddItem(View view)
    {
        Intent intent = new Intent(this, add_item.class);
        startActivity(intent);
    }

    public void toSaveBundle(View view)
    {
        Intent intent = new Intent(this, save_bundle.class);
        startActivity(intent);
    }

    public void toMyItemsFromAddBundle(View view)
    {
        Intent intent = new Intent(this, my_items.class);
        startActivity(intent);
    }

}
