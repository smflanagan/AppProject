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

    public void toAddItem(View view)
    {
        Intent intent = new Intent(this, add_item.class);
        startActivity(intent);
    }

    public void toAddBundle(View view)
    {
        Intent intent = new Intent(this, add_bundle.class);
        startActivity(intent);
    }

    public void toLoginScreen(View view)
    {
        Intent intent = new Intent(this, login_screen.class);
        startActivity(intent);
    }
}