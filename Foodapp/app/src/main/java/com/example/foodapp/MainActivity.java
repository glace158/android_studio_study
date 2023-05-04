package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    ImageView festival_noodle_ImageView, raman_ImageView, tteokbokki_ImageView;
    TextView festival_noodle_TextView, raman_TextView, tteokbokki_TextView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

    }

    void menuInit(){
        festival_noodle_ImageView = findViewById(R.id.festivalNodleImageView);
        raman_ImageView = findViewById(R.id.ramanImageView);
        tteokbokki_ImageView =findViewById(R.id.tteokbokkiImageView);

        festival_noodle_TextView = findViewById(R.id.festivalNoodleTextView);
        raman_TextView = findViewById(R.id.ramanTextView);
        tteokbokki_TextView = findViewById(R.id.tteokbokkiTextView);

        raman_ImageView.setOnClickListener(imageViewSelectListerner);
        tteokbokki_ImageView.setOnClickListener(imageViewSelectListerner);
    }

    private View.OnClickListener imageViewSelectListerner = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            switch (view.getId()){
                case R.id.ramanImageView:
                    raman_TextView.append("\nprice 500");
                    break;
                case R.id.tteokbokkiImageView:
                    tteokbokki_TextView.append("\nprice 1000");
                    break;
            }
        }
    };
}