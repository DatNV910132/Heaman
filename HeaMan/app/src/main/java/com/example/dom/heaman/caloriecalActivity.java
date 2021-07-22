package com.example.dom.heaman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

public class caloriecalActivity extends AppCompatActivity {

    EditText Tuoi;
    EditText Chieucao;
    EditText Cannang;
    Spinner Gioitinh;
    Spinner Hoatdong;
    TextView Ketqua;
    Button Tinh;
    Button Clear;
    String arrgt[] = {
            "Nam",
            "Nữ"};
    String arrhd[] = {
            "Ít hoặc không vận động",
            "Vận động nhẹ: 1-3 lần/1 tuần",
            "Vận động vừa phải: 3-5 lần/ 1 tuần",
            "Vận động nhiều: 6-7 lần/1 tuần",
            "Vận động nặng: Trên 7 lần 1 tuần"
    };
    int gt, hd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_caloriecal);
        Gioitinh = findViewById(R.id.calspgioitinh);
        Hoatdong = findViewById(R.id.calsphd);
        Tuoi = findViewById(R.id.caltuoi);
        Chieucao = findViewById(R.id.calchieucao);
        Cannang = findViewById(R.id.calcannang);
        Ketqua = findViewById(R.id.calketqua);
        Tinh = findViewById(R.id.caltinh);
        Clear = findViewById(R.id.calclear);

        //Gán Data source (arr) vào Adapter
        ArrayAdapter<String> adaptergt = new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arrgt
                );
        ArrayAdapter<String> adapterhd = new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arrhd
                );
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adaptergt.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        Gioitinh.setAdapter(adaptergt);
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapterhd.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        Hoatdong.setAdapter(adapterhd);

        Gioitinh.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (Gioitinh.getSelectedItemPosition() == 0) {
                    gt = 0;
                } else gt = 1;
                Log.i("Gioi tinh", "gt= " + gt);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {
            }
        });

        Hoatdong.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                switch (Hoatdong.getSelectedItemPosition()) {
                    case 0:
                        hd = 0;
                        break;
                    case 1:
                        hd = 1;
                        break;
                    case 2:
                        hd = 2;
                        break;
                    case 3:
                        hd = 3;
                        break;
                    case 4:
                        hd = 4;
                        break;
                    default:
                        hd = 0;
                        break;
                }
                Log.i("Hoat dong", "hd= " + hd);
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        Tinh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                int tuoi = Integer.parseInt(Tuoi.getText().toString());
                float trongluong = Float.valueOf(Cannang.getText().toString());
                float chieucao = Float.valueOf(Chieucao.getText().toString());
                BMRHelper cal = new BMRHelper();
                float chiso = cal.BMRCal(tuoi, trongluong, chieucao, gt, hd);
                Ketqua.setText(String.valueOf(Math.round(chiso)));
            }
        });
        Clear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Tuoi.setText("");
                Cannang.setText("");
                Chieucao.setText("");
                Ketqua.setText("");
            }
        });

    }
}
