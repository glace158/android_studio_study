package com.example.webcrawlingapplication;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.graphics.Color;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.jsoup.Jsoup;
import org.jsoup.nodes.Document;
import org.jsoup.nodes.Element;

import java.io.IOException;

public class MainActivity extends AppCompatActivity {
    private TextView textViewTitle;
    private Button buttonRunnable;
    private Button buttonThread;
    private Bundle bundle;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        textViewTitle = findViewById(R.id.textView);
        buttonRunnable = findViewById(R.id.runnablebutton);
        buttonThread = findViewById(R.id.threadbutton);

        buttonRunnable.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        Document document = null;
                        try {
                            document = Jsoup.connect("https://www.bing.com/?FORM=Z9FD1").get();
                        } catch (IOException e) {
                            e.printStackTrace();
                        }

                        Element titleElement = document.select("title").first();
                        final String title = titleElement.text();
                        runOnUiThread(new Runnable() {
                            @Override
                            public void run() {
                                textViewTitle.setTextColor(Color.BLUE); // for Runnable call
                                textViewTitle.setText(title);
                            }
                        });
                    }
                }).start();
            }
        });

        buttonThread.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                CrawlingThread crawlingThread = new CrawlingThread(handler);
                crawlingThread.start();// 쓰레드 실행
            }
        });

    }

    Handler handler =new Handler(Looper.getMainLooper()) {
        @Override
        public void handleMessage(@NonNull Message msg) {
            super.handleMessage(msg);
            bundle = msg.getData();
            textViewTitle.setTextColor(Color.RED);  // for Thread call
            textViewTitle.setText(bundle.getString("Title"));
        }
    };
}