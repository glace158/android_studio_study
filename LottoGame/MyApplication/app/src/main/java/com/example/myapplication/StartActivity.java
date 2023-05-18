package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class StartActivity extends AppCompatActivity {
    Button start_button;
    EditText editText;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_start);

        start_button = findViewById(R.id.startButton);
        editText = findViewById(R.id.editTextTextPersonName);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                String username = editText.getText().toString();
                if(TextUtils.isEmpty(username)){
                    Toast.makeText(getApplicationContext(), "Input UserName", Toast.LENGTH_SHORT).show();
                    return;
                }

                Intent intent = new Intent(StartActivity.this, MainActivity.class);//메인 액티비티를 호출
                Bundle bundle = new Bundle();
                bundle.putString("name", username);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });
    }
}