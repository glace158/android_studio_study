package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    int[] image_id_arr = {R.id.ramanImageView, R.id.tteokbokkiImageView, R.id.festivalNodleImageView};
    int[] textview_id_arr = {R.id.ramanTextView, R.id.tteokbokkiTextView, R.id.festivalNoodleTextView};
    int[] button_id_arr = {R.id.ramanCancelButton, R.id.tteokbokkiCancelButton, R.id.festivalCancelButton};

    ImageView[] imageviews = new ImageView[image_id_arr.length];
    TextView[] textviews = new TextView[textview_id_arr.length];
    Button[] buttons = new Button[button_id_arr.length];

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuInit();
    }

    void menuInit(){
        Menu raman = new Menu("라면", 500, "맛있는 라면");
        Menu tteokbokki = new Menu("떡볶이", 1000, "맛있는 떡볶이");

        for(int i = 0; i < imageviews.length; i++){
            imageviews[i] = findViewById(image_id_arr[i]);
            imageviews[i].setOnClickListener(imageViewSelectListerner);
        }
        for(int i = 0; i < textviews.length; i++){
            textviews[i] = findViewById(textview_id_arr[i]);
        }
        for(int i = 0; i < buttons.length; i++){
            buttons[i] = findViewById(button_id_arr[i]);
        }
    }

    private View.OnClickListener imageViewSelectListerner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.ramanImageView:
                    textviews[0].append("\nprice 500");
                    break;
                case R.id.tteokbokkiImageView:
                    textviews[1].append("\nprice 1000");
                    break;
            }
        }
    };
}