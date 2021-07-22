package com.example.dom.heaman;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class BMIActivity extends AppCompatActivity {

    EditText Bmichieucao;
    EditText Bmicannang;
    TextView Bmikq;
    TextView Bmidudoan;
    Button Bmical;
    Button Bmiclear;
    TextView Bmicanhbao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi);
        Bmichieucao = findViewById(R.id.bmichieucao);
        Bmicannang = findViewById(R.id.bmicannang);
        Bmikq = findViewById(R.id.bmiketqua);
        Bmidudoan = findViewById(R.id.bmidudoan);
        Bmical = findViewById(R.id.bmitinh);
        Bmiclear = findViewById(R.id.bmiclear);
        Bmicanhbao = findViewById(R.id.bmicanhbao);


        Bmical.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (Bmichieucao.getText().toString().equals("") ||
                        Bmicannang.getText().toString().equals("")) {
                    Bmicanhbao.setText("Bạn cần nhập đầy đủ thông tin chiều cao và cân nặng");
                } else {
                    float cc = Float.valueOf(Bmichieucao.getText().toString());
                    float cn = Float.valueOf(Bmicannang.getText().toString());
                    BMIHelper bmical = new BMIHelper();
                    float chiso = bmical.BMI(cc, cn);
                    Bmikq.setText(String.valueOf(chiso));
                    String dd = bmical.DuDoan(chiso);
                    Bmidudoan.setText(dd);
                }

            }
        });

        Bmiclear.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bmichieucao.setText("");
                Bmicannang.setText("");
                Bmikq.setText("");
                Bmidudoan.setText("");
            }
        });

    }
}
