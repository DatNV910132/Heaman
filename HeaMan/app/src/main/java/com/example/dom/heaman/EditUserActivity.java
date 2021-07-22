package com.example.dom.heaman;

import android.content.Intent;
import android.database.Cursor;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

public class EditUserActivity extends AppCompatActivity {

    private EditText name;
    private EditText gen;
    private EditText age;
    private EditText chieucao;
    private EditText cannang;
    private EditText sdt;
    private EditText calorie;

    private Button update;
    private Button clear;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_user);

        name = findViewById(R.id.editnameuser);
        gen = findViewById(R.id.editgenuser);
        age = findViewById(R.id.editageuser);
        chieucao = findViewById(R.id.edithighuser);
        cannang = findViewById(R.id.editweiuser);
        sdt = findViewById(R.id.editphoneuser);
        calorie = findViewById(R.id.editcalouser);

        update = findViewById(R.id.btnedituser);

        final MyDatabaseHelper data = new MyDatabaseHelper(this);
        final Cursor user = data.getUser();
        if (user.moveToFirst()) {
            do {

                name.setText(user.getString(1));
                gen.setText(user.getString(2));
                age.setText(String.valueOf(user.getInt(3)));
                chieucao.setText(String.valueOf(user.getInt(4)));
                cannang.setText(String.valueOf(user.getInt(5)));
                sdt.setText(user.getString(6));
                calorie.setText(String.valueOf(user.getInt(7)));
            } while (user.moveToNext());
        }
        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String username = name.getText().toString();
                String usergen = gen.getText().toString() ;
                int userage = Integer.parseInt(age.getText().toString());
                int userhigh = Integer.parseInt(chieucao.getText().toString()) ;
                int userwei = Integer.parseInt(cannang.getText().toString()) ;
                String usersdt = sdt.getText().toString() ;
                int usercalo = Integer.parseInt(calorie.getText().toString()) ;
                User updateUser = new User(1,username,usergen,userhigh,userwei,userage,usersdt,usercalo);
                data.updateUser(updateUser);

                onBackPressed();
            }
        });
    }
}
