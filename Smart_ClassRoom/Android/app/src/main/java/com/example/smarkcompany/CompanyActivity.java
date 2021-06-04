package com.example.smarkcompany;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CompanyActivity extends AppCompatActivity {
    EditText et_time;
    Button bt_checkin,bt_checkout,bt_enquire;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_company);
        SimpleDateFormat formatter = new SimpleDateFormat("YYYY-MM-dd HH:mm:ss"); //设置时间格式
        formatter.setTimeZone(TimeZone.getTimeZone("GMT+08")); //设置时区
        Date curDate = new Date(System.currentTimeMillis()); //获取当前时间
        String createDate = formatter.format(curDate);   //格式转换
        et_time=findViewById(R.id.et_time);
        et_time.setText(createDate);

        bt_enquire = findViewById(R.id.bt_enquire);//忘记密码
        bt_enquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanyActivity.this, EnquireActivity.class);
                startActivity(intent);
            }
        });
    }
}