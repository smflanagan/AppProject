package com.example.smflanagan.appproject;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.view.View;
import android.widget.ImageView;
import android.widget.Toast;

/**
 * Created by melissafarraher on 3/29/17.
 */

public class PickGalleryImage extends Activity {
    private static int RESULT_LOAD_IMG = 1;
    String imgString;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void loadImagefromGallery(View view) {
        Intent galleryIntent = new Intent(Intent.ACTION_PICK,
                android.provider.MediaStore.Images.Media.EXTERNAL_CONTENT_URI);
        startActivityForResult(galleryIntent, RESULT_LOAD_IMG);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        try {
            // When an Image is selected
            if (requestCode == RESULT_LOAD_IMG && resultCode == RESULT_OK
                    && null != data) {
                // Get the Image from gallery

                Uri selectedImage = data.getData();
                String[] filePathColumn = { MediaStore.Images.Media.DATA };

                Cursor cursor = getContentResolver().query(selectedImage,
                        filePathColumn, null, null, null);

                cursor.moveToFirst();

                int columnIndex = cursor.getColumnIndex(filePathColumn[0]);
                imgString = cursor.getString(columnIndex);
                cursor.close();
                ImageView imgView = (ImageView) findViewById(R.id.imgView);

                imgView.setImageBitmap(BitmapFactory.decodeFile(imgString));

            }
        } catch (Exception e) {
            Toast.makeText(this, "Something went wrong", Toast.LENGTH_LONG)
                    .show();
        }

    }
}

