package com.example.smflanagan.appproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

/**
 * add_item class: Java class adds a new item to a specific user's list of items
 * User inputs data for name, cost, and location of item
 */

public class add_item extends AppCompatActivity {

    /**
     * Class variables for image component of item
     */
    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;

    /**
     * Class variables for each of 4 text components of an item and variable for cost as a numerical value
     */
    private String name;
    private String cost_string;
    private double cost;
    private String seller;
    private String location;

    /**
     * Class variables necessary to interact with Firebase in class
     */
    private FirebaseDatabase database;
    private DatabaseReference myRef;

    /**
     * Method sets layout for screen and accesses Firebase
     *
     * @param savedInstanceState
     */
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        database = FirebaseDatabase.getInstance();
        myRef = database.getReference("Items");
    }

    /**
     * Method enables user to load an image from a separate photo app
     *
     * @param view
     */
    public void loadImagefromGallery(View view) {
        // Create intent to Open Image applications like Gallery, Google Photos
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        // Start the Intent
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is picked
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from data

                Uri selectedImage = data.getData();
                String[] filePathColumn = {MediaStore.Images.Media.DATA};

                // Get the cursor
                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);
                // Move to first row
                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgDecodableString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView);
                // Set the Image in ImageView after decoding the String
                imgView.setImageBitmap(BitmapFactory.decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

    /**
     * Method brings the user to created_item class when "Create Item" button is pushed (through method createItem)
     * putExtra methods take the user input and carry it to the created_item class where it is then displayed
     *
     * @param view
     */
    public void toViewItem(View view) {
        Intent intent = new Intent(this, created_item.class);
        intent.putExtra("name", name);
        intent.putExtra("cost_string", cost_string);
        intent.putExtra("seller", seller);
        intent.putExtra("location", location);
        startActivity(intent);
    }

    /**
     * Method assigns each variable to user input for each category
     * An item is created using the inputted data
     * Method then brings the user to created_item class when "Create Item" button is pushed by calling method toViewItem
     *
     * @param view
     */
    public void createItem(View view) {
        EditText item_name = (EditText) findViewById(R.id.EnterItemName);
        name = item_name.getText().toString();

        EditText item_cost = (EditText) findViewById(R.id.EnterItemCost);
        cost_string = item_cost.getText().toString();
        cost = Double.parseDouble(cost_string);

        EditText seller_name = (EditText) findViewById(R.id.EnterBundleName);
        seller = seller_name.getText().toString();

        EditText seller_location = (EditText) findViewById(R.id.EnterLocation);
        location = seller_location.getText().toString();

        ItemData test = new ItemData(name, cost, seller, location);

        myRef.push().setValue(test);

        toViewItem(view);
    }

}



