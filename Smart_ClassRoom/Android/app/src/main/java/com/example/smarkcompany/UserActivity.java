package com.example.smarkcompany;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;

import java.util.AbstractList;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class UserActivity extends AppCompatActivity implements AdapterView.OnItemClickListener {
    GridView gridview;
    private List<Map<String, Object>> data_list;
    private SimpleAdapter simpleAdapter;
    private int[] pic = {R.drawable.check};
    private String[] picName = {"签到处"};

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        gridview = (GridView) findViewById(R.id.gview);
        data_list = new ArrayList<Map<String, Object>>();
        getData();
        String[] from = {"image", "text"};
        int[] to = {R.id.ic_mark, R.id.ic_text};
        simpleAdapter = new SimpleAdapter(this, data_list, R.layout.item, from, to);
        gridview.setAdapter(simpleAdapter);
        gridview.setOnItemClickListener(this);
    }

    public List<Map<String, Object>> getData() {
        for (int k = 0; k < pic.length; k++) {
            Map<String, Object> map = new HashMap<String, Object>();
            map.put("image", pic[k]);
            map.put("text", picName[k]);
            data_list.add(map);
        }
        return data_list;
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        switch (position) {
            case 0:
                Intent intent2 = new Intent(UserActivity.this, CompanyActivity.class);
                startActivity(intent2);
                break;
        }
    }
}