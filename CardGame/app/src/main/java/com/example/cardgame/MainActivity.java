package com.example.cardgame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.sql.Array;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Locale;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    TextView[] win_rate_textview = new TextView[2];
    TextView[] sum_textview = new TextView[2];
    ImageView[] p1_imageViews = new ImageView[2];
    ImageView[] p2_imageViews = new ImageView[2];

    Button game_button;

    int[] spade_cards = {R.drawable.sa, R.drawable.s2, R.drawable.s3, R.drawable.s4, R.drawable.s5,
            R.drawable.s6, R.drawable.s7, R.drawable.s8, R.drawable.s9, R.drawable.s10,
            R.drawable.sj, R.drawable.sq, R.drawable.sk};

    int[] diamond_cards = {R.drawable.da, R.drawable.d2, R.drawable.d3, R.drawable.d4, R.drawable.d5,
            R.drawable.d6, R.drawable.d7, R.drawable.d8, R.drawable.d9, R.drawable.d10,
            R.drawable.dj, R.drawable.dq, R.drawable.dk};


    int[] win_count = {0 , 0};// 각 플레이어 승수
    int[] loss_count = {0 , 0};// 각 플레이어 패수

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        win_rate_textview[0] = findViewById(R.id.p1_textView);
        win_rate_textview[1] = findViewById(R.id.p2_textView);
        sum_textview[0] = findViewById(R.id.p1SumTextView);
        sum_textview[1] = findViewById(R.id.p2SumTextView);

        for(TextView textView : sum_textview){
            textView.setVisibility(View.INVISIBLE);
        }

        p1_imageViews[0] = findViewById(R.id.p1_1imageView);
        p1_imageViews[1] = findViewById(R.id.p1_2imageView);
        for(ImageView imageView : p1_imageViews){
            imageView.setImageResource(R.drawable.back);
        }

        p2_imageViews[0] = findViewById(R.id.p2_1imageView);
        p2_imageViews[1] = findViewById(R.id.p2_2imageView);
        for(ImageView imageView : p2_imageViews){
            imageView.setImageResource(R.drawable.back);
        }

        game_button = findViewById(R.id.gameButton);

        ArrayList<Integer> spade_card_list = new ArrayList<>();
        set_card_list(spade_card_list, spade_cards);//스페이드 카드 리스트 생성

        ArrayList<Integer> diamond_card_list = new ArrayList<>();
        set_card_list(diamond_card_list, diamond_cards);//다이아몬드 카드 리스트 생성


        game_button.setOnClickListener(new View.OnClickListener(){
            @Override
            public void onClick(View view){
                shuffleArray(spade_cards);
                shuffleArray(diamond_cards);

                set_player_card(p1_imageViews, spade_cards);
                set_player_card(p2_imageViews, diamond_cards);

                int p1_sum = sum_cards(spade_card_list,spade_cards);
                int p2_sum = sum_cards(diamond_card_list,diamond_cards);

                check_winner(p1_sum, p2_sum);

                calculate_win_rate(1);
                calculate_win_rate(2);

                sum_textview[0].setVisibility(View.VISIBLE);
                sum_textview[0].setText(String.valueOf(p1_sum));
                sum_textview[1].setVisibility(View.VISIBLE);
                sum_textview[1].setText(String.valueOf(p2_sum));
            }
        });
    }

    void set_player_card(ImageView[] imageViews, int[] cards){//player imageView 설정
        for(int i = 0; i < imageViews.length; i++){
            imageViews[i].setImageResource(cards[i]);
        }
    }

    void set_card_list(ArrayList<Integer> card_list, int[] cards){//카드 리스트 생성
        for(int i = 0; i < cards.length ; i++){
            card_list.add(cards[i]);
        }
    }

    void shuffleArray(int[] imageArray){// 카드 섞기
        Random rand = new Random();
        for (int i = 0; i < imageArray.length; i++) {
            int random_index = rand.nextInt(imageArray.length);
            int temp = imageArray[random_index];
            imageArray[random_index] = imageArray[i];
            imageArray[i] = temp;
        }
        System.out.println(Arrays.toString(imageArray));
    }

    void check_winner(int p1_num, int p2_num){

        if(p1_num == p2_num){// 무승부
            Toast.makeText(getApplicationContext(), "Draw!", Toast.LENGTH_SHORT).show();
        }
        else if(p1_num < p2_num){// p2 가 승리
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

    int sum_cards(ArrayList<Integer> card_list, int[] cards){//승자 확인
        int[] states = new int[2];
        for(int i = 0 ; i < states.length; i++){//섞인 카드를 card_list 로 뽑힌 카드 확인
            states[i] =  card_list.indexOf(cards[i]) + 1;
        }

        return states[0] + states[1];
    }

    void calculate_win_rate(int player_num){//승률 계산
        int index = player_num - 1;

        float win_rate = ((float) win_count[index] / (win_count[index] + loss_count[index])) * 100;// 승수 / (승수 + 패수)

        win_rate_textview[index].setText(String.format(Locale.ENGLISH,"Player%d\n%.2f", player_num, win_rate));
    }
}