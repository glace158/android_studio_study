package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import java.util.Random;
import java.util.Arrays;
public class MainActivity extends AppCompatActivity {
    TextView first_person_textview;
    TextView second_person_textview;

    TextView result_textview;

    Button start_button;
    Button winner_button;

    Random random = new Random();

    String[] arr = {"가위", "바위", "보"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        first_person_textview = findViewById(R.id.FirstPersonTextView);
        second_person_textview = findViewById(R.id.SecondPersonTextView);

        result_textview =findViewById(R.id.ResultTextView);

        start_button =findViewById(R.id.StartButton);
        winner_button = findViewById(R.id.WinnerButton);

        start_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int num_p1 = random.nextInt(3);
                int num_p2 = random.nextInt(3);
                first_person_textview.setText(arr[num_p1]);
                second_person_textview.setText(arr[num_p2]);
            }
        });

        winner_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                int p1_num = Arrays.asList(arr).indexOf(first_person_textview.getText().toString());
                int p2_num = Arrays.asList(arr).indexOf(second_person_textview.getText().toString());

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