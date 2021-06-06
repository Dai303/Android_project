package com.example.smarkcompany;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.Socket;
import java.net.URL;
import java.net.URLEncoder;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimeZone;

public class CompanyActivity extends AppCompatActivity {
    private Socket socket;
    EditText et_time,et_cardnum;
    Button bt_checkin,bt_checkout,bt_enquire;
    String card;
    class ReceiveCard implements Runnable{
        @Override
        public void run() {
            try {
                //if (socket==null)
                socket = new Socket("192.168.4.2", 8080);
                InputStream inputStream=socket.getInputStream();
                byte[] bb=new byte[8];
                //inputStream.read(bb);
                int len=-1;
                while((len=inputStream.read(bb))!=-1){
                    card=new String(bb,0,len);

                    new Handler(CompanyActivity.this.getMainLooper()).post(new Runnable() {
                        @Override
                        public void run() {
                            changecard(card);
                            // 在这里执行你要想的操作 比如直接在这里更新ui或者调用回调在 在回调中更新ui
                        }
                    });
                }

            } catch (IOException e) {
                e.printStackTrace();
            }

        }
    }

    public void changecard(String card){
        et_cardnum.setText(card);
        Toast.makeText(CompanyActivity.this,"有新员工刷卡",Toast.LENGTH_SHORT).show();
    }

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

        bt_enquire = findViewById(R.id.bt_enquire);//查询记录
        bt_enquire.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(CompanyActivity.this, EnquireActivity.class);
                startActivity(intent);
            }
        });
        bt_checkin = findViewById(R.id.bt_checkin);//员工签到
        bt_checkin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个线程来连接数据库并获取数据库中对应表的数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String body = null;
                        try {
                            body = "name=" + URLEncoder.encode("223","utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        URL url = null;
                        HttpURLConnection httpURLConnection = null;
                        InputStreamReader inputStreamReader = null;
                        try {
                            url = new URL("http://192.168.4.2:8099/record/add");
                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                            conn.setRequestProperty("Content-Length", String.valueOf(body.length()));
                            conn.setRequestMethod("POST");
                            conn.setDoOutput(true);
                            conn.getOutputStream().write(body.getBytes());
                            inputStreamReader = new InputStreamReader(conn.getInputStream());
                            BufferedReader bf = new BufferedReader(inputStreamReader);
                            String readLine = null;
                            int code = conn.getResponseCode();
                            Looper.prepare();
                            Toast.makeText(CompanyActivity.this, String.valueOf(code) , Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            inputStreamReader.close();
                            httpURLConnection.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });

        bt_checkout = findViewById(R.id.bt_checkout);//签退
        bt_checkout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // 创建一个线程来连接数据库并获取数据库中对应表的数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {
                        String body = null;
                        try {
                            body = "name=" + URLEncoder.encode("223","utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        URL url = null;
                        HttpURLConnection httpURLConnection = null;
                        InputStreamReader inputStreamReader = null;
                        try {
                            url = new URL("http://192.168.4.2:8099/record/add");
                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                            conn.setRequestProperty("Content-Length", String.valueOf(body.length()));
                            conn.setRequestMethod("POST");
                            conn.setDoOutput(true);
                            conn.getOutputStream().write(body.getBytes());
                            inputStreamReader = new InputStreamReader(conn.getInputStream());
                            BufferedReader bf = new BufferedReader(inputStreamReader);
                            String readLine = null;
                            int code = conn.getResponseCode();
                            Looper.prepare();
                            Toast.makeText(CompanyActivity.this, String.valueOf(code) , Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            inputStreamReader.close();
                            httpURLConnection.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                    }
                }).start();
            }
        });
    }
}