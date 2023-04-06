package com.example.myapplication;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

import java.sql.Array;
import java.util.Arrays;
import java.util.Random;

public class MainActivity extends AppCompatActivity {

    ImageView p1_1imageview;
    ImageView p1_2imageview;
    ImageView p2_1imageview;
    ImageView p2_2imageview;

    ImageView start_imageview;

    Button game_button;

    int[] imageArray = {R.drawable.gawi, R.drawable.bawi, R.drawable.bo,
            R.drawable.gawi, R.drawable.bawi, R.drawable.bo};

    boolean is_game_click = false;

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
        game_button = findViewById(R.id.GameButton);

        start_imageview.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view){
                    is_game_click = false;
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

                if(is_game_click){
                    return;
                }

                is_game_click = true;

                imageViews[new Random().nextInt(2)].setVisibility(View.INVISIBLE);

                imageViews[new Random().nextInt(2) + 2].setVisibility(View.INVISIBLE);

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


}