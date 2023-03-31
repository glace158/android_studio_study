package com.example.mygame;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Locale;
import java.util.Random;
public class MainActivity extends AppCompatActivity {

    ImageView p1_image_view;
    ImageView p2_image_view;

    TextView[] win_rate_textview = new TextView[3];

    TextView result_textview;
    TextView game_count_textview;

    Button start_button;
    Button winner_button;

    Random random = new Random();
    int[] state_arr = {R.drawable.gawi, R.drawable.bawi, R.drawable.bo};
    int[] current_player_state = {-1, -1};//각 플레이어 현재 가위, 바위, 보 상태

    int game_count = 0; // 총 게임 횟수
    int[] win_count = {0 , 0};// 각 플레이어 승수
    int[] loss_count = {0 , 0};// 각 플레이어 패수
    float[] win_rate = {0.0f , 0.0f};// 각 플레이어 승률 저장
    float target_p1_win_rate = 20.0f;// player1 목표 승률 지정
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        p1_image_view = findViewById(R.id.first_person_image_view);
        p2_image_view = findViewById(R.id.second_person_image_view);
        p1_image_view.setImageResource(R.drawable.p1);
        p2_image_view.setImageResource(R.drawable.p2);

        win_rate_textview[0] = findViewById(R.id.P1WinRateTextView);
        win_rate_textview[1] = findViewById(R.id.P2WinRateTextView);


        result_textview = findViewById(R.id.ResultTextView);
        game_count_textview = findViewById(R.id.GameCountTextView);

        start_button = findViewById(R.id.StartButton);
        winner_button = findViewById(R.id.WinnerButton);

        start_button.setOnClickListener(new View.OnClickListener() {//게임 시작 버튼 클릭
            @Override
            public void onClick(View view) {

                int[] num_arr = player_num_control();

                p1_image_view.setImageResource(state_arr[num_arr[0]]);
                p2_image_view.setImageResource(state_arr[num_arr[1]]);

                current_player_state[0] = num_arr[0];
                current_player_state[1] = num_arr[1];
            }
        });

        winner_button.setOnClickListener(new View.OnClickListener() {//승자 표시
            @Override
            public void onClick(View view) {
                int p1_num = current_player_state[0];
                int p2_num = current_player_state[1];

                if(p1_num == -1 || p2_num == -1){// 처음 StartButton을 누르지 않았을 때
                    Toast.makeText(getApplicationContext(), "Press Start Button", Toast.LENGTH_SHORT).show();
                    return;
                }

                if(p1_num == p2_num){// 무승부
                    result_textview.setText("Draw!");
                }
                else if(p1_num == (p2_num - 1 == -1 ? 2 : p2_num - 1)){// p2 가 승리
                    result_textview.setText("P2 Win!");
                    win_count[1] += 1;
                    loss_count[0] += 1;
                }
                else{
                    result_textview.setText("P1 Win!");// p1 이 승리
                    win_count[0] += 1;
                    loss_count[1] += 1;
                }

                game_count++;
                game_count_textview.setText("GameCount: " + game_count);

                //승률 계산
                calculate_win_rate(1);
                calculate_win_rate(2);
            }
        });
    }

    void calculate_win_rate(int player_num){//승률 계산
        int index = player_num - 1;
        if(game_count >= 10){
            win_rate[index] = ((float) win_count[index] / (win_count[index] + loss_count[index])) * 100;// 승수 / (승수 + 패수)

            win_rate_textview[index].setText(String.format(Locale.ENGLISH,"Player%d\n%.2f", player_num, win_rate[index]));
        }
    }

    int[] player_num_control(){// 플레이어 가위, 바위, 보 설정

        while (true){
            int p1_num = random.nextInt(3);
            int p2_num = random.nextInt(3);

            int[] num_arr = {p1_num, p2_num};

            if(game_count < 15){//게임이 15회 전이면
                return num_arr;// 바로 반환
            }

            //승부 조작
            if(win_rate[0] >= target_p1_win_rate && p1_num == (p2_num - 1 == -1 ? 2 : p2_num - 1)){//p1 승률이 목표 값보다 높고 p2이 이기면
                return num_arr;
            }
            else if(win_rate[0] <= target_p1_win_rate && p2_num == (p1_num - 1 == -1 ? 2 : p1_num - 1)){//p1 승률이 목표 값보다 낮고 p1이 이기면
                return num_arr;
            }
        }
    }
}