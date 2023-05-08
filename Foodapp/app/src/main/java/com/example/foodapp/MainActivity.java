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
    int[] countText_id_arr = {R.id.ramanCountTextView,R.id.tteokbokkiCountTextView,R.id.festivalNoodleCountTextView};
    int[] button_id_arr = {R.id.ramanCancelButton, R.id.tteokbokkiCancelButton, R.id.festivalCancelButton};

    ImageView[] imageviews = new ImageView[image_id_arr.length];
    TextView[] textviews = new TextView[textview_id_arr.length];
    TextView[] count_texts = new TextView[countText_id_arr.length];
    Button[] buttons = new Button[button_id_arr.length];

    Menu[] menus = {
            new Menu("라면", "500", "맛있는 라면"),
            new Menu("떡볶이", "1000", "맛있는 떡볶이"),
            new Menu("잔치국수", "1500", "맛있는 국수")
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        menuInit();
    }

    void menuInit(){

        for(int i = 0; i < imageviews.length; i++){
            imageviews[i] = findViewById(image_id_arr[i]);
            imageviews[i].setOnClickListener(imageViewSelectListener);
        }
        for(int i = 0; i < textviews.length; i++){
            textviews[i] = findViewById(textview_id_arr[i]);
            textviews[i].setText(menus[i].getName()+ "\n" + menus[i].getDescription() + "\n가격" + menus[i].getPrice());
            count_texts[i] = findViewById(countText_id_arr[i]);
            count_texts[i].setText("수량: 0");
        }
        for(int i = 0; i < buttons.length; i++){
            buttons[i] = findViewById(button_id_arr[i]);
            buttons[i].setOnClickListener(cancelButtonListener);
        }

    }

    private View.OnClickListener imageViewSelectListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 0; i < imageviews.length ; i++){
                if(imageviews[i].getId() == view.getId()){
                    menus[i].addCount();
                    count_texts[i].setText("수량:" + Integer.toString(menus[i].getCount()));
                }
            }
        }
    };

    private View.OnClickListener cancelButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            for (int i = 0; i < buttons.length ; i++){
                if(buttons[i].getId() == view.getId()){
                    menus[i].removeCount();
                    count_texts[i].setText("수량: " + Integer.toString(menus[i].getCount()));
                }
            }
        }
    };
}