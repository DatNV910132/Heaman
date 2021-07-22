package com.example.dom.heaman;

import android.content.Intent;
import android.os.Build;
import android.support.annotation.RequiresApi;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ListView;

import java.util.ArrayList;

public class ListBarExportActivity extends AppCompatActivity {

    ListView list;
    Button btn;
    Button btnxong;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_bar_export);
        list = findViewById(R.id.listdemo);
        btn = findViewById(R.id.btndemo);
        btnxong = findViewById(R.id.btnxong);
        Intent i = getIntent();
        String Date = i.getStringExtra("DateStatus");
        ArrayList<String> array = i.getStringArrayListExtra("user");
        ArrayList<String> array1 = i.getStringArrayListExtra("code");

        BarExItemAdapter adater = new BarExItemAdapter(this, R.layout.list_bar_export_item, array,array1);
        list.setAdapter(adater);
        btn.setOnClickListener(new View.OnClickListener() {
            @RequiresApi(api = Build.VERSION_CODES.KITKAT)
            @Override
            public void onClick(View v) {
            }
        });

        btnxong.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
//                Intent i = new Intent(ListBarExportActivity.this,StatusFragment.class);
//                startActivity(i);
            }
        });
    }
}
