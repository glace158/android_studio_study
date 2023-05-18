package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    int[] lotto_ids = {R.id.lottoView1, R.id.lottoView2, R.id.lottoView3,R.id.lottoView4};
    LotoView[] lottoViews = new LotoView[lotto_ids.length];
    TextView scoreTextView, resultTextView, titleTextView;

    int lotto_win_count = 0;
    int prize_money = 0;
    boolean is_result = false;
    Button suffle_button, result_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        for(int i = 0; i < lotto_ids.length; i++){
            lottoViews[i] = findViewById(lotto_ids[i]);
        }

        suffle_button = findViewById(R.id.suffleButton);
        result_button = findViewById(R.id.resultButton);
        scoreTextView = findViewById(R.id.scoreTextView);
        resultTextView = findViewById(R.id.rusultTextView);
        titleTextView = findViewById(R.id.titleTextView);

        //유저 이름 가져오기
        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();
        String username = bundle.getString("name");
        titleTextView.setText("Welcome!!\n" + username);

        suffleLotto();//처음 실행시 로또 섞어주기

        suffle_button.setOnClickListener(new View.OnClickListener() {//로또 섞기 버튼
            @Override
            public void onClick(View view) {
                suffleLotto();
            }
        });

        result_button.setOnClickListener(new View.OnClickListener() {//결과 버튼
            @Override
            public void onClick(View view) {
                boolean all_opened =true;
                for(int i = 0; i < lottoViews.length; i++){//모든 항목이 열렸는지 확인
                    if(!lottoViews[i].isState()){
                        Toast.makeText(getApplicationContext(), "Not open all", Toast.LENGTH_SHORT).show();
                        all_opened =false;
                        break;
                    }
                }

                if(all_opened && !is_result){//모든 항목이 열렸고 결화버튼이 안눌렸을 때
                    is_result = true;
                    prize_money += lotto_win_count * 100;
                    scoreTextView.setText("Score: " + String.valueOf(prize_money));// 총 상금 표시
                    resultTextView.setText(lotto_win_count == 0 ? "Try again..\nYou loss.." : "Congratulations!!\nYou are "+ String.valueOf(lotto_win_count) + " Win!!" );// 당첨 개수 표시
                }
            }
        });
    }

    public void suffleLotto(){// 로또 섞기
        lotto_win_count = 0;
        is_result = false;
        for(int i = 0; i < lottoViews.length; i++){
            lotto_win_count = lottoViews[i].sufflelotto() ? lotto_win_count+=1 : lotto_win_count;
        }
    }
}