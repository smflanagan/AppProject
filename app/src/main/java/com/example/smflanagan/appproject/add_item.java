package com.example.smflanagan.appproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.storage.FirebaseStorage;
import com.google.firebase.storage.StorageReference;
import com.google.firebase.storage.UploadTask;

import com.squareup.picasso.Picasso;

import java.io.ByteArrayOutputStream;


public class add_item extends AppCompatActivity {

    private static int RESULT_LOAD_IMG = 1;
    String imgDecodableString;
    public String name;
    public String cost_string;
    public double cost;
    public String seller;
    public String location;
    private FirebaseDatabase database;
    private DatabaseReference myRef;
    private StorageReference storageRef;
    public ImageView imageView;
    public String string_download_url;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
        database = FirebaseDatabase.getInstance();
        //Will add user id instead of items once auth is in place
         myRef = database.getReference("Items");
        FirebaseStorage storage = FirebaseStorage.getInstance();
        storageRef = storage.getReference();

        
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
                //uploadImageToFirebase();

            } else {
                Toast.makeText(this, "You haven't picked Image",
                        Toast.LENGTH_LONG).show();
            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }


    }

    public void toViewItem(View view) {
        Intent intent = new Intent(this, created_item.class);
        intent.putExtra("name", name);
        intent.putExtra("cost_string", cost_string);
        intent.putExtra("seller", seller);
        intent.putExtra("location", location);
        startActivity(intent);
    }

    public void createItem(View view) {
        EditText item_name = (EditText) findViewById(R.id.ItemNameView);
        name = item_name.getText().toString();

        EditText item_cost = (EditText) findViewById(R.id.ItemCost);
        cost_string = item_cost.getText().toString();
        cost = Double.parseDouble(cost_string);

        EditText seller_name = (EditText) findViewById(R.id.EnterBundleName);
        seller = seller_name.getText().toString();

        EditText seller_location = (EditText) findViewById(R.id.Location);
        location = seller_location.getText().toString();

        //downloadStoredFirebaseImage();

        ItemData test = new ItemData(name, cost, seller, location);

        myRef.push().setValue(test);

        toViewItem(view);
    }

    private void uploadImageToFirebase() {
        // Get the data from an ImageView as bytes
        imageView.setDrawingCacheEnabled(true);
        imageView.buildDrawingCache();
        Bitmap bitmap = imageView.getDrawingCache();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] data = baos.toByteArray();

        StorageReference imgViewRef = storageRef.child("imgViews");
        UploadTask uploadTask = imgViewRef.putBytes(data);
        uploadTask.addOnFailureListener(new OnFailureListener() {
            @Override
            public void onFailure(@NonNull Exception exception) {
                // Handle unsuccessful uploads
            }
        }).addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
            @Override
            public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
            }
        });
    }

    private void downloadStoredFirebaseImage() {
        Uri url = storageRef.child("imgViews").getDownloadUrl().getResult();
        string_download_url = url.toString();
        ImageView image = (ImageView) findViewById(R.id.imgView2);
        Picasso.with(this).load(string_download_url).into(image);
    }

}



