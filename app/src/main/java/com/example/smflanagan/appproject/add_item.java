package com.example.smflanagan.appproject;

import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


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


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_item);
       //Firebase.setAndroidContext(this);
      // firebase = new Firebase("https://appproject-af9a4.firebaseio.com/");
        database = FirebaseDatabase.getInstance();
//Will add user id instead of items once auth is in place
         myRef = database.getReference("Items");


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

        // Image example = new Image();

        ItemData test = new ItemData(name, cost, seller, location);

    myRef.push().setValue(test);

        toViewItem(view);
    }
/*
    private void storeImageToFirebase() {
        BitmapFactory.Options options = new BitmapFactory.Options();
        options.inSampleSize = 8; // shrink it down otherwise we will use stupid amounts of memory
        Bitmap bitmap = BitmapFactory.decodeFile(mCurrentPhotoUri.getPath(), options);
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG, 100, baos);
        byte[] bytes = baos.toByteArray();
        String base64Image = Base64.encodeToString(bytes, Base64.DEFAULT);

        // we finally have our base64 string version of the image, save it.
        firebase.child("pic").setValue(base64Image);
        System.out.println("Stored image with length: " + bytes.length);
    }

    private void previewStoredFirebaseImage() {
        firebase.child("pic").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                String base64Image = (String) snapshot.getValue();
                byte[] imageAsBytes = Base64.decode(base64Image.getBytes(), Base64.DEFAULT);
                mThumbnailPreview.setImageBitmap(
                        BitmapFactory.decodeByteArray(imageAsBytes, 0, imageAsBytes.length)
                );
                System.out.println("Downloaded image with length: " + imageAsBytes.length);
            }

            @Override
            public void onCancelled(FirebaseError error) {}
        });
    }
*/
}



