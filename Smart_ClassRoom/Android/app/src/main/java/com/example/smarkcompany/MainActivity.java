package com.example.smarkcompany;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;
public class MainActivity extends AppCompatActivity {
    private static final String LOG_TAG="ActivityLifeCycle";
    String admister="xiecheng",adpass="123456";
    EditText et_user, et_password;
    Button bt_login, bt_fgp,bt_reset,bt_cancel,bt_register;
    TextView tv_xinxi;
    public void validpwad(String user,String password){
        if ((et_user.getText().toString().equals("")) && (et_password.getText().toString().equals(""))) {
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            startActivity(intent);
        } else if ((et_user.getText().toString().equals(admister)) && (et_password.getText().toString().equals(adpass))){
            Intent intent = new Intent(MainActivity.this, UserActivity.class);
            startActivity(intent);
        }
        else {
            Toast.makeText(MainActivity.this, "用户名或密码错误！", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        Log.i(LOG_TAG,"onCreate方法被调用");
        et_user = findViewById(R.id.et_user);

        et_password = findViewById(R.id.et_password);
        bt_fgp = findViewById(R.id.bt_fgp);//忘记密码
        bt_fgp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, Foreget.class);
                startActivityForResult(intent,2);
            }
        });
        bt_login = findViewById(R.id.bt_login);
        bt_login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {//登录按钮
                Intent i=getIntent();
                String user=i.getStringExtra("user");
                String password=i.getStringExtra("password");
                validpwad(user,password);
            }
        });
        bt_reset=findViewById(R.id.bt_reset);//重置
        bt_reset.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_user.setText("");
                et_password.setText("");
            }
        });
        bt_cancel=findViewById(R.id.bt_cancel);//取消
        bt_cancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                et_user.setText("");
                et_password.setText("");
            }
        });
        bt_register=findViewById(R.id.bt_register);
        bt_register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, RegisterActivity.class);
                startActivityForResult(intent,1);
            }
        });
    }
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data){
        super.onActivityResult(requestCode,resultCode,data);
        if(requestCode==1){
            if(resultCode==1){
                if(data!=null){
                    et_user.setText(data.getStringExtra("username"));
                    et_password.setText(data.getStringExtra("passwd"));
                }
            }
        }
        if(resultCode==2){
            String fg_user=data.getStringExtra("user");
            String fg_password=data.getStringExtra("password");
            et_user.setText(fg_user);
            et_password.setText(fg_password);
            tv_xinxi=findViewById(R.id.tv_xinxi);
            tv_xinxi.setText("重置密码后登录");
        }
        if(resultCode==3){
            tv_xinxi=findViewById(R.id.tv_xinxi);
            tv_xinxi.setText("取消重置后返回");
        }
    }
}