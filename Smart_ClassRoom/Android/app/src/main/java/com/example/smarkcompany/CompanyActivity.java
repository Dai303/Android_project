package com.example.smarkcompany;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
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
import java.io.InputStreamReader;
import java.io.UnsupportedEncodingException;
import java.net.HttpURLConnection;
import java.net.URL;
import java.net.URLEncoder;
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
                            Toast.makeText(CompanyActivity.this, id, Toast.LENGTH_SHORT).show();
                            Toast.makeText(CompanyActivity.this, String.valueOf(code) , Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            inputStreamReader.close();
                            httpURLConnection.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        Message message = handler.obtainMessage();
//                        message.what = 0x11;
//                        message.obj = "es";
//                        // 发消息通知主线程更新UI
//                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
        bt_checkout = findViewById(R.id.bt_checkout);//忘记密码
        bt_checkout.setOnClickListener(new View.OnClickListener() {
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
                            Toast.makeText(CompanyActivity.this, id, Toast.LENGTH_SHORT).show();
                            Toast.makeText(CompanyActivity.this, String.valueOf(code) , Toast.LENGTH_SHORT).show();
                            Looper.loop();
                            inputStreamReader.close();
                            httpURLConnection.disconnect();
                        } catch (Exception e) {
                            e.printStackTrace();
                        }
//                        Message message = handler.obtainMessage();
//                        message.what = 0x11;
//                        message.obj = "es";
//                        // 发消息通知主线程更新UI
//                        handler.sendMessage(message);
                    }
                }).start();
            }
        });
    }
}