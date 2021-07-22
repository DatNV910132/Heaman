package com.example.dom.heaman;

import android.icu.text.UnicodeSetSpanner;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddNFoodToDBActivity extends AppCompatActivity {

    EditText ten;
    EditText hang;
    EditText loai;
    EditText calo;
    Button btnaddsp;
    int countsp;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add_nfood_to_db);
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        ten = findViewById(R.id.editnamesp);
        hang = findViewById(R.id.edithangsxsp);
        loai = findViewById(R.id.editloaisp);
        calo = findViewById(R.id.editcaloriesp);
        btnaddsp = findViewById(R.id.btnaddsp);

        final MyDatabaseHelper database = new MyDatabaseHelper(this);
        countsp = database.getSPCount();
        btnaddsp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SanPham sp = new SanPham();
                int intcode = 9999999 + countsp + 1;
                String code = String.valueOf(intcode);
                sp.setCode(code);
                sp.setTenSP(ten.getText().toString());
                sp.setHangsx(hang.getText().toString());
                sp.setLoai(loai.getText().toString());
                sp.setCalorie(Integer.parseInt(calo.getText().toString()));

                database.addSanpham(sp);

                Toast.makeText(getApplicationContext(),"Thêm thực phẩm thành công",Toast.LENGTH_SHORT).show();
                onBackPressed();

            }
        });
    }
}
