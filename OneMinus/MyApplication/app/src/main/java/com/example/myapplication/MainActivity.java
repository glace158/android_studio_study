package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView p1_1imageview;
    ImageView p1_2imageview;
    ImageView p2_1imageview;
    ImageView p2_2imageview;

    ImageView start_imageview;

    TextView[] win_rate_textview = new TextView[2];

    Button game_button;
    int[] imageArray = {R.drawable.gawi, R.drawable.bawi, R.drawable.bo,
            R.drawable.gawi, R.drawable.bawi, R.drawable.bo};

    boolean is_game_click = false;

    int[] win_count = {0 , 0};// 각 플레이어 승수
    int[] loss_count = {0 , 0};// 각 플레이어 패수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        p1_1imageview = findViewById(R.id.p1_1imageView);
        p1_2imageview = findViewById(R.id.p1_2imageView);
        p2_1imageview = findViewById(R.id.p2_1imageView);
        p2_2imageview = findViewById(R.id.p2_2imageView);

        ImageView[] imageViews = {p1_1imageview, p1_2imageview, p2_1imageview, p2_2imageview};
        for(ImageView imageview : imageViews){
            imageview.setVisibility(View.INVISIBLE);
        }

        start_imageview = findViewById(R.id.StartImageView);
        win_rate_textview[0] = findViewById(R.id.p1_textView);
        win_rate_textview[1] = findViewById(R.id.p2_textView);
        game_button = findViewById(R.id.GameButton);

        start_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                    is_game_click = true;
                    shuffleArray();

                    for(int i = 0; i < imageViews.length; i++){
                        imageViews[i].setImageResource(imageArray[i]);
                        imageViews[i].setVisibility(View.VISIBLE);
                    }
                }
            });

        game_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                if(!is_game_click){
                    Toast.makeText(getApplicationContext(), "Click Start Image", Toast.LENGTH_SHORT).show();
                    return;
                }

                imageViews[new Random().nextInt(2)].setVisibility(View.INVISIBLE);

                imageViews[new Random().nextInt(2) + 2].setVisibility(View.INVISIBLE);

                check_winner(imageViews);

                calculate_win_rate(1);
                calculate_win_rate(2);
            }
        });
    }

    void shuffleArray(){
        Random rand = new Random();

        for (int i = 0; i < imageArray.length; i++) {
            int random_index = rand.nextInt(imageArray.length);
            int temp = imageArray[random_index];
            imageArray[random_index] = imageArray[i];
            imageArray[i] = temp;

        }
        System.out.println(Arrays.toString(imageArray));
    }

    void check_winner(ImageView[] imageViews){
        int[] states = new int[2];
        ArrayList<Integer> stateList = new ArrayList<>();
        stateList.add(R.drawable.gawi);
        stateList.add(R.drawable.bawi);
        stateList.add(R.drawable.bo);

        for(int i = 0 ; i < imageViews.length; i++){
            if(imageViews[i].getVisibility() == View.VISIBLE){
                states[ i <= 1 ? 0 : 1] = stateList.indexOf(imageArray[i]);
            }
        }

        if(states[0] == states[1]){// 무승부
            Toast.makeText(getApplicationContext(), "Draw!", Toast.LENGTH_SHORT).show();
        }
        else if(states[0] == (states[1] - 1 == -1 ? 2 : states[1] - 1)){// p2 가 승리
            Toast.makeText(getApplicationContext(), "P2 Win!", Toast.LENGTH_SHORT).show();
            win_count[1]++;
            loss_count[0]++;
        }
        else{
            Toast.makeText(getApplicationContext(), "P1 Win!", Toast.LENGTH_SHORT).show();
            win_count[0]++;
            loss_count[1]++;
        }
    }

    void calculate_win_rate(int player_num){//승률 계산
        int index = player_num - 1;

        float win_rate = ((float) win_count[index] / (win_count[index] + loss_count[index])) * 100;// 승수 / (승수 + 패수)

        win_rate_textview[index].setText(String.format(Locale.ENGLISH,"Player%d\n%.2f", player_num, win_rate));
    }
}