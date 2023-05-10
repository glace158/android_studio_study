package com.example.foodapp;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Environment;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import org.apache.poi.hssf.usermodel.HSSFWorkbook;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;
import org.apache.poi.ss.usermodel.Sheet;
import org.apache.poi.ss.usermodel.Workbook;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;

public class OrderActivity extends AppCompatActivity {
    TextView selectMenuTextView, priceTextView;
    Button cancel_button, pay_button;

    String[][] menulist;
    int total_price;
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
        menulist = new String[bundle.getInt("count")+1][3];
        total_price = 0;
        for(int i = 0; i < bundle.getInt("count"); i++){
            String menu_name = bundle.getString("menu"+String.valueOf(i));
            String menu_count = bundle.getString(menu_name+"count");
            int price = bundle.getInt(menu_name+"price");
            total_price += price * Integer.parseInt(menu_count);

            menulist[i][0] = menu_name;
            menulist[i][1] = menu_count;
            menulist[i][2] = String.valueOf(price);

            selectMenuTextView.append( menu_name +"\n");
            selectMenuTextView.append( "수량:" + menu_count +"\n\n");
            System.out.println(menu_name);
            System.out.println(menulist[i]);
        }
        priceTextView.setText("총 가격: " + total_price);
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
            saveExcel();
            Intent intent = new Intent(OrderActivity.this, InitActivity.class);//다른 액티비티를 호출
            startActivity(intent);
            finish();
        }
    };

    private void saveExcel(){

        Workbook workbook = new HSSFWorkbook();

        Sheet sheet = workbook.createSheet(); // 새로운 시트 생성

        Row row = sheet.createRow(0); // 새로운 행 생성
        Cell cell;

        cell = row.createCell(0); // 1번 셀 생성
        cell.setCellValue("메뉴"); // 1번 셀 값 입력

        cell = row.createCell(1); // 2번 셀 생성
        cell.setCellValue("수량"); // 2번 셀 값 입력

        cell = row.createCell(2); // 2번 셀 생성
        cell.setCellValue("가격"); // 2번 셀 값 입력

        for(int i = 0; i < menulist.length ; i++){ // 데이터 엑셀에 입력
            row = sheet.createRow(i+1);
            cell = row.createCell(0);
            cell.setCellValue(menulist[i][0]);
            cell = row.createCell(1);
            cell.setCellValue(menulist[i][1]);
            cell = row.createCell(2);
            cell.setCellValue(menulist[i][2]);
        }
        row = sheet.createRow(menulist.length);
        cell = row.createCell(0);
        cell = row.createCell(1);
        cell.setCellValue("총합");
        cell = row.createCell(2);
        cell.setCellValue(total_price);

        File xlsFile = new File(Environment.getExternalStoragePublicDirectory(Environment.DIRECTORY_DOCUMENTS), "test.xls");

        try{
            FileOutputStream os = new FileOutputStream(xlsFile);
            workbook.write(os); // 외부 저장소에 엑셀 파일 생성
        }catch (IOException e){
            e.printStackTrace();
        }
        Toast.makeText(getApplicationContext(),xlsFile.getAbsolutePath()+"에 저장되었습니다",Toast.LENGTH_SHORT).show();
        System.out.println(xlsFile.getAbsolutePath()+"에 저장되었습니다");
    }

}