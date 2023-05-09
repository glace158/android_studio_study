package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class OrderActivity extends AppCompatActivity {
    TextView selectMenuTextView, priceTextView;
    Button cancel_button, pay_button;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);
        cancel_button = findViewById(R.id.cancelButton);
        cancel_button.setOnClickListener(cancelButtonListener);
        pay_button = findViewById(R.id.payButton);
        pay_button.setOnClickListener(payButtonListener);

        selectMenuTextView = findViewById(R.id.selectMenuTextView);
        priceTextView = findViewById(R.id.priceTextView);

        Intent intent = getIntent();
        Bundle bundle = intent.getExtras();

        int price = 0;
        for(int i = 0; i < bundle.getInt("count"); i++){
            String menu_name = bundle.getString("menu"+String.valueOf(i));
            String menu_cont = bundle.getString(menu_name+"count");
            price += bundle.getInt(menu_name+"price") * Integer.parseInt(menu_cont);

            selectMenuTextView.append( menu_name +"\n");
            selectMenuTextView.append( "수량:" + menu_cont +"\n\n");

        }
        priceTextView.setText("총 가격: " + price);
    }

    private View.OnClickListener cancelButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(OrderActivity.this, MainActivity.class);//다른 액티비티를 호출
            startActivity(intent);
            finish();
        }
    };

    private View.OnClickListener payButtonListener = new View.OnClickListener() {
        @Override
        public void onClick(View view) {
            Intent intent = new Intent(OrderActivity.this, InitActivity.class);//다른 액티비티를 호출
            startActivity(intent);
            finish();
        }
    };

}