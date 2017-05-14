package com.example.smflanagan.appproject;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

// MainActivity class: Java class for the main screen that the user sees when the app initially builds
// Display of main activity is unaffected by any action user performs in app

public class MainActivity extends AppCompatActivity {

    // Method creates layout when screen is built
    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    // Method brings user to add_item class when button "Add Item" is pushed
    public void toAddItem(View view)
    {
        Intent intent = new Intent(this, add_item.class);
        startActivity(intent);
    }

    // Method brings user to add_bundle class when button "Add Bundle" is pushed
    public void toAddBundle(View view)
    {
        Intent intent = new Intent(this, add_bundle.class);
        startActivity(intent);
    }

    // Method brings user to login class when button "Login" is pushed
    public void toLoginScreen(View view)
    {
        Intent intent = new Intent(this, login_screen.class);
        startActivity(intent);
    }

    // Method brings user to my_items class when button "My Items" is pushed
    public void toMyItems(View view)
    {
        Intent intent = new Intent(this, my_items.class);
        startActivity(intent);
    }
}