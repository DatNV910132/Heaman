package com.example.dom.heaman;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ListView;
import android.widget.RelativeLayout;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;
import java.util.Calendar;


public class AddfoodActivity extends AppCompatActivity {

    String arr[] = {
            "Tên",
            "Loại",
            "Hãng sản xuất",
            "Calo từ cao đến thấp",
            "Calo từ thấp đến cao"};
    ListView addlistfood;
    //    RelativeLayout reladd;
    Button btnok;
    Spinner spin;
    EditText edSearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_addfood);
        spin = findViewById(R.id.spinnerbaradd);
        addlistfood = findViewById(R.id.addlistfood);
        btnok = findViewById(R.id.btnOK);
        edSearch = findViewById(R.id.addsearch);
//        reladd = findViewById(R.id.readd);
//        reladd.setVisibility(View.INVISIBLE);

        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        //Gán Data source (arr) vào Adapter
        ArrayAdapter<String> adapter = new ArrayAdapter<String>
                (
                        this,
                        android.R.layout.simple_spinner_item,
                        arr
                );
        //phải gọi lệnh này để hiển thị danh sách cho Spinner
        adapter.setDropDownViewResource
                (android.R.layout.simple_list_item_single_choice);
        //Thiết lập adapter cho Spinner
        spin.setAdapter(adapter);
        edSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = edSearch.getText().toString();
                MyDatabaseHelper data = new MyDatabaseHelper(AddfoodActivity.this);
                Cursor search = data.SearchSanPham(s1);
                addListView(search);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
        //thiết lập sự kiện chọn phần tử cho Spinner
        spin.setOnItemSelectedListener(new MyProcessEvent());
    }

    public void addStatus(SanPham sps) {
        MyDatabaseHelper data = new MyDatabaseHelper(AddfoodActivity.this);
        Status st = new Status();
        st.setCalorie(sps.getCalorie());
        st.setUID("1");
        st.setSID(sps.getCode());
        st.setNamesp(sps.getTenSP());
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH)+1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String second = String.valueOf(calendar.get(Calendar.SECOND));
        String date = day + "/" + month + "/" + year;
        String time = hour + ":" + minute + ":" + second;
        st.setTime(time);
        st.setDate(date);

        data.addStatus(st);
    }

    //Class tạo sự kiện
    private class MyProcessEvent implements
            AdapterView.OnItemSelectedListener {
        //Khi có chọn lựa thì vào hàm này
        public void onItemSelected(AdapterView<?> arg0,
                                   View arg1,
                                   int arg2,
                                   long arg3) {
            //arg2 là phần tử được chọn trong data source
//            selection.setText(arr[arg2]);
            if (arg2 == 0) {
                MyDatabaseHelper database = new MyDatabaseHelper(getApplicationContext());
                Cursor datast = database.getSanphamGroupbyName();
                addListView(datast);
            }
            if (arg2 == 1) {
                MyDatabaseHelper database = new MyDatabaseHelper(getApplicationContext());
                Cursor datast = database.getSanphamGroupbyLoai();
                addListView(datast);
            }
            if (arg2 == 2) {
                MyDatabaseHelper database = new MyDatabaseHelper(getApplicationContext());
                Cursor datast = database.getSanphamGroupbyHang();
                addListView(datast);
            }
            if (arg2 == 3) {
                MyDatabaseHelper database = new MyDatabaseHelper(getApplicationContext());
                Cursor datast = database.getSanphamGroupbyCaloct();
                addListView(datast);
            }
            if (arg2 == 4) {
                MyDatabaseHelper database = new MyDatabaseHelper(getApplicationContext());
                Cursor datast = database.getSanphamGroupbyCalotc();
                addListView(datast);
            }
        }

        //Nếu không chọn gì cả
        public void onNothingSelected(AdapterView<?> arg0) {

        }
    }

    public void addListView(Cursor cursor) {
        final ArrayList<SanPham> arrayListSp = new ArrayList<>();
//        Cursor datast = database.getSanphamGroupbyName();

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (cursor.moveToFirst()) {
            do {
                String code = cursor.getString(0);
                String Ten = cursor.getString(1);
                String hangsx = cursor.getString(2);
                String loai = cursor.getString(3);
                Integer calorie = cursor.getInt(4);
                arrayListSp.add(new SanPham(code, Ten, hangsx, loai, calorie));


            } while (cursor.moveToNext());
        }


        // Duyệt trên con trỏ, và thêm vào danh sách.
        AddFoodAdapder listap = new AddFoodAdapder(getApplicationContext(), R.layout.add_list_food, arrayListSp);
        addlistfood.setAdapter(listap);
        addlistfood.setOnItemLongClickListener(new AdapterView.OnItemLongClickListener() {
            @Override
            public boolean onItemLongClick(AdapterView<?> parent, View view, int position, long id) {
                SanPham sps = arrayListSp.get(position);
                addStatus(sps);
                Toast.makeText(AddfoodActivity.this, "Thêm thực phẩm " + sps.getTenSP() + " thành công", Toast.LENGTH_SHORT).show();
                return true;
            }
        });
        addlistfood.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Toast.makeText(AddfoodActivity.this, "Nhấn dữ để thêm thực phẩm", Toast.LENGTH_LONG).show();
            }
        });
        btnok.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onBackPressed();
            }
        });
    }
}
