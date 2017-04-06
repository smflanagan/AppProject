package com.example.smflanagan.appproject;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    Button addItem = (Button) findViewById(R.id.AddItem);
    addItem.setOnClickListener(new View.OnCLickListener)
    public void onClick(View view)
    {
        startActivity(new Intent(MainActivity.this, add_item.class));

        //jiji
    }
}
