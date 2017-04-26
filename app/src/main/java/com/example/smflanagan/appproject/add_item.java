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

public class add_item extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    public String name;
    public String cost_string;
    public double cost;
    public String seller;
    public String location;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
    }
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
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

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
                imgView.setImageBitmap(BitmapFactory
                        .decodeFile(imgDecodableString));

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }

    public void toViewItem(View view)
    {
        Intent intent = new Intent(this, created_item.class);
        intent.putExtra("name", name);
        intent.putExtra("cost_string", cost_string);
        intent.putExtra("seller", seller);
        intent.putExtra("location", location);
        startActivity(intent);
    }

    public void createItem(View view)
    {
        EditText item_name = (EditText) findViewById(R.id.ItemNameView);
        name = item_name.getText().toString();

        EditText item_cost = (EditText) findViewById(R.id.ItemCost);
        cost_string = item_cost.getText().toString();
        cost = Double.parseDouble(cost_string);

        EditText seller_name = (EditText) findViewById(R.id.Username);
        seller = seller_name.getText().toString();

        EditText seller_location = (EditText) findViewById(R.id.Location);
        location = seller_location.getText().toString();

       // Image example = new Image();

        ItemData test = new ItemData(name, cost, seller, location);


        toViewItem(view);
    }
}



