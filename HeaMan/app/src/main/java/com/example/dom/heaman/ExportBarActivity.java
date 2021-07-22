package com.example.dom.heaman;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.SparseBooleanArray;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.CompoundButton;
import android.widget.ListView;

import java.util.ArrayList;

public class ExportBarActivity extends AppCompatActivity {

    private CheckBox exbarSellectall;
    private ListView exbarListfood;
    private Button btnExBar;
    private Button btnexbarCan;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_export_bar);

        exbarSellectall = findViewById(R.id.exbarselectall);
        exbarListfood = findViewById(R.id.exbarlistfood);
        btnExBar = findViewById(R.id.btnexbar);
        btnexbarCan = findViewById(R.id.btnexbarcancel);

        exbarListfood.setChoiceMode(exbarListfood.CHOICE_MODE_MULTIPLE);

        MyDatabaseHelper database = new MyDatabaseHelper(this);
        ArrayList<SanPham> arrayListSp = new ArrayList<>();
        Cursor datast = database.getAllSanPham();

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (datast.moveToFirst()) {
            do {
                String code = datast.getString(0);
                  String Ten = datast.getString(1);
                String hangsx = datast.getString(2);
                String loai = datast.getString(3);
                Integer calorie = datast.getInt(4);
                arrayListSp.add(new SanPham(code,Ten, hangsx, loai, calorie));


            } while (datast.moveToNext());
        }

        final ArrayAdapter<SanPham> arrayAdapter
                = new ArrayAdapter<>(this, android.R.layout.simple_list_item_multiple_choice, arrayListSp);


        exbarListfood.setAdapter(arrayAdapter);

           exbarSellectall.setOnCheckedChangeListener(new CompoundButton.OnCheckedChangeListener() {
            @Override
            public void onCheckedChanged(CompoundButton buttonView, boolean isChecked) {
                if(isChecked == true){
                    for (int i = 0; i < arrayAdapter.getCount(); i++) {
                        exbarListfood.setItemChecked(i, true);
                    }
                } else {
                    for (int i = 0; i < arrayAdapter.getCount(); i++) {
                        exbarListfood.setItemChecked(i, false);
                    }
                }

            }
        });

        btnExBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SparseBooleanArray sp = exbarListfood.getCheckedItemPositions();

                ArrayList<String> arrayListSp = new ArrayList<>();
                ArrayList<String> arrayListSp1 = new ArrayList<>();
                String s = null;
                String s1 = null;
                for (int i = 0; i < sp.size(); i++) {
                    if (sp.valueAt(i) == true) {
                        SanPham user = (SanPham) exbarListfood.getItemAtPosition(i);
                        s = user.getTenSP();
                        s1 = user.getCode();
                        arrayListSp.add(s);
                        arrayListSp1.add(s1);
                        Intent intent = new Intent(ExportBarActivity.this, ListBarExportActivity.class);
                        intent.putStringArrayListExtra("user", arrayListSp);
                        intent.putStringArrayListExtra("code", arrayListSp1);
                        startActivity(intent);
                    }
                }

            }
        });

        btnexbarCan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                for (int i = 0; i < arrayAdapter.getCount(); i++) {
                    exbarListfood.setItemChecked(i, false);
                }
            }
        });
    }
}
