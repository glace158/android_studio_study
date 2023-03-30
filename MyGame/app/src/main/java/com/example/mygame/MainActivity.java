package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.Random;
import java.util.Arrays;
public class MainActivity extends AppCompatActivity {

    ImageView first_person_image_view;
    ImageView second_person_image_view;

    TextView result_textview;


    Button start_button;
    Button winner_button;


    Random random = new Random();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first_person_image_view = findViewById(R.id.first_person_image_view);
        second_person_image_view = findViewById(R.id.second_person_image_view);

        result_textview =findViewById(R.id.ResultTextView);

        start_button =findViewById(R.id.StartButton);
        winner_button = findViewById(R.id.WinnerButton);

        String[] arr = {"가위", "바위", "보"};

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num_p1 = random.nextInt(3);
                int num_p2 = random.nextInt(3);
                System.out.println(R.drawable.bawi);

                first_person_image_view.setImageResource(R.drawable.bawi);
                second_person_image_view.setImageResource(R.drawable.bawi);
            }
        });

        winner_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int p1_num = 0;//Arrays.asList(arr).indexOf(first_person_textview.getText().toString());
                int p2_num = 0;//Arrays.asList(arr).indexOf(second_person_textview.getText().toString());

                if(p1_num == p2_num){
                    result_textview.setText("무승부");
                }
                else if(p1_num == (p2_num - 1 == -1 ? 2 : p2_num - 1)){
                    result_textview.setText("P2 승리");
                }
                else{
                    result_textview.setText("P1 승리");
                }
            }
        });
    }
}