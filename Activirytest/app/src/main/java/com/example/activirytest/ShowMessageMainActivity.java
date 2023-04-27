package com.example.activirytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.TextView;

public class ShowMessageMainActivity extends AppCompatActivity {
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_show_message_main);

        textView = findViewById(R.id.textView);
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        float posx = bundle.getFloat("X pos");
        float posy = bundle.getFloat("Y pos");
        textView.setText(String.valueOf(posx) + "/" + String.valueOf(posy));
    }
}