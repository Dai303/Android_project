package com.example.smarkcompany;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Looper;
import android.os.Message;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import com.alibaba.fastjson.JSON;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

import java.io.BufferedReader;
import java.io.DataOutputStream;
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLConnection;
import java.net.URLEncoder;
import java.util.HashMap;

public class EnquireActivity extends AppCompatActivity {

    private Button btn_get_data;
    private TextView tv_data;

    @SuppressLint("HandlerLeak")
    private Handler handler = new Handler(){
        @Override
        public void handleMessage(Message msg) {

            switch (msg.what){
                case 0x11:

//                    tv_data.setText(s);
                    break;
                case 0x12:
                    String ss = (String) msg.obj;
                    tv_data.setText(ss);
                    break;
            }
        }
    };
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_enquire);

        // 控件的初始化
        btn_get_data = findViewById(R.id.btn_get_data);
        tv_data = findViewById(R.id.tv_data);
        setListener();
    }
    private void setListener() {

        // 按钮点击事件
        btn_get_data.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                // 创建一个线程来连接数据库并获取数据库中对应表的数据
                new Thread(new Runnable() {
                    @Override
                    public void run() {
//                        JSONObject body = new JSONObject();
//                        body.put("id", 3);
                        // 调用数据库工具类DBUtils的getInfoByName方法获取数据库表中数据
                        String body = null;
                        try {
                            body = "id=" + URLEncoder.encode("1","utf-8");
                        } catch (UnsupportedEncodingException e) {
                            e.printStackTrace();
                        }
                        URL url = null;
                        String result = "";
                        HttpURLConnection httpURLConnection = null;
                        InputStreamReader inputStreamReader = null;
                        try {
                            url = new URL("http://192.168.1.146:8099/record/delete");
                            HttpURLConnection conn = (HttpURLConnection)url.openConnection();
                            conn.setRequestProperty("Content-Length", String.valueOf(body.length()));
//                            conn.setRequestProperty("Content-Type","application/x-www-form-urlencoded");
                            conn.setRequestMethod("POST");
                            conn.setDoOutput(true);
                            conn.getOutputStream().write(body.getBytes());
                            inputStreamReader = new InputStreamReader(conn.getInputStream());
                            BufferedReader bf = new BufferedReader(inputStreamReader);
                            String readLine = null;
                            while ((readLine = bf.readLine()) != null) {
                                result += readLine;
                            }
                            JSONArray objects = JSON.parseArray(result);
                            JSONObject jsonObject = objects.getJSONObject(0);
                            String id = jsonObject.getString("id");
                            int code = conn.getResponseCode();
                            Looper.prepare();
                            Toast.makeText(EnquireActivity.this, id, Toast.LENGTH_SHORT).show();
                            Toast.makeText(EnquireActivity.this, String.valueOf(code) , Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            inputStreamReader.close();
                            httpURLConnection.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
                            Message message = handler.obtainMessage();
                            message.what = 0x11;
                            message.obj = "es";
                        // 发消息通知主线程更新UI
                        handler.sendMessage(message);
                    }
                }).start();

            }
        });

    }
}