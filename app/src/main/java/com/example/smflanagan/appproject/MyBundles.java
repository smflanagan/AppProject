package com.example.smflanagan.appproject;

import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;

public class MyBundles extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_my_bundles);
    }

    public void toHomeFromMyBundles(View view) {
        Intent intent = new Intent(this, MainActivity.class);
        startActivity(intent);
    }

}
