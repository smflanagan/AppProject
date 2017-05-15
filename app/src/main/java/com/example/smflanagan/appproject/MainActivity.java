package com.example.smflanagan.appproject;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;

/**
 * MainActivity class: Java class for the main screen that the user sees when the app initially builds
 * Display of main activity is unaffected by any action user performs in app
 */
public class MainActivity extends AppCompatActivity {

    /**
     * Method creates layout when screen is built
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    /**
     * Method brings user to AddItem class when button "Add Item" is pushed
     *
     * @param view
     */
    public void toAddItem(View view) {
        Intent intent = new Intent(this, AddItem.class);
        startActivity(intent);
    }

    /**
     * Method brings user to AddBundle class when button "Add Bundle" is pushed
     *
     * @param view
     */
    public void toAddBundle(View view) {
        Intent intent = new Intent(this, AddBundle.class);
        startActivity(intent);
    }

    /**
     * Method brings user to login class when button "Login" is pushed
     *
     * @param view
     */
    public void toLoginScreen(View view) {
        Intent intent = new Intent(this, LoginScreen.class);
        startActivity(intent);
    }

    /**
     * Method brings user to MyItems class when button "My Items" is pushed
     *
     * @param view
     */
    public void toMyItems(View view) {
        Intent intent = new Intent(this, MyItems.class);
        startActivity(intent);
    }
}