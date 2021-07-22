package com.example.dom.heaman;

import android.database.Cursor;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.view.WindowManager;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemSelectedListener;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import java.util.ArrayList;


public class ListfoodActivity extends AppCompatActivity {

    String arr[] = {
            "Tên",
            "Loại",
            "Hãng sản xuất",
            "Calo từ cao đến thấp",
            "Calo từ thấp đến cao"};
    ListView listfood;
    EditText listsearch;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_listfood);
        //Lấy đối tượng Spinner ra
        Spinner spin = findViewById(R.id.spinnerbar);
        listfood = findViewById(R.id.listfood);
        listsearch = findViewById(R.id.listedsearch);
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
        this.getWindow().setSoftInputMode(WindowManager.LayoutParams.SOFT_INPUT_STATE_ALWAYS_HIDDEN);
        listsearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                String s1 = listsearch.getText().toString();
                MyDatabaseHelper data = new MyDatabaseHelper(ListfoodActivity.this);
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

    //Class tạo sự kiện
    private class MyProcessEvent implements
            OnItemSelectedListener {
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
//            selection.setText("");
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
        ListFoodItemAdapter listap = new ListFoodItemAdapter(this, R.layout.activity_list_food_item, arrayListSp);
        listfood.setAdapter(listap);
    }
}
