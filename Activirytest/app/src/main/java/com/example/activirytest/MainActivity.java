package com.example.activirytest;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.MotionEvent;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class MainActivity extends AppCompatActivity {
    Button intent_button;
    Button call_button;
    ImageView intent_imageview;

    float xpos = 0;
    float ypos = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        intent_button = findViewById(R.id.intentButton);
        call_button = findViewById(R.id.callButton);
        intent_imageview = findViewById(R.id.intentImageView);

        intent_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//묵시적 인테트
                Intent intent = new Intent(Intent.ACTION_VIEW);//다른 액티비티를 호출
                Uri url = Uri.parse("https://www.naver.com");
                intent.setData(url);
                startActivity(intent);// naver 호출
                
            }
        });

        intent_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {//명시적 인테트
                Intent intent = new Intent(MainActivity.this, ShowMessageMainActivity.class);//다른 액티비티를 호출
                Bundle bundle = new Bundle();
                bundle.putFloat("X pos", xpos);
                bundle.putFloat("Y pos", ypos);
                intent.putExtras(bundle);
                startActivity(intent);
            }
        });

        intent_imageview.setOnTouchListener(new View.OnTouchListener() {
            @Override
            public boolean onTouch(View view, MotionEvent motionEvent) {
                xpos = motionEvent.getX();
                ypos = motionEvent.getY();

                System.out.println(xpos + "/" + ypos);
                return false;
            }
        });
        call_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String phoneNumber = "tel:" + "01045564206";
                Intent intent = new Intent(Intent.ACTION_CALL);
                intent.setData(Uri.parse(phoneNumber));
                startActivity(intent);
            }
        });
    }
}