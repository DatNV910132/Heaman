package com.example.dom.heaman;

import android.content.Context;
import android.content.Intent;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class UserFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private TextView name;
    private TextView age;
    private TextView chieucao;
    private TextView cannang;
    private TextView calorie;
    public int muctieu = 1;
    private ImageView genuser;
    private ImageButton btnedit;
    private ListView listStatusItem;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public void onResume() {
        Loaddata();
        super.onResume();
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_user, container, false);
        name = view.findViewById(R.id.nameuser);
        age = view.findViewById(R.id.ageuser);
        chieucao = view.findViewById(R.id.highuser);
        cannang = view.findViewById(R.id.weiuser);
        calorie = view.findViewById(R.id.calouser);
        genuser = view.findViewById(R.id.usergen);
        btnedit = view.findViewById(R.id.btnedit);
        listStatusItem = view.findViewById(R.id.listStatusitem);
        Loaddata();

        btnedit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getContext(), EditUserActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    public void Loaddata() {
        MyDatabaseHelper data = new MyDatabaseHelper(getContext());
        User newuser = new User(1,"Nguyễn Văn Đạt","Nam",165,65,22,"0969991097",3300);
        data.addUser(newuser);
        ArrayList<StatusItem> ListItemStatus = new ArrayList<>();
        Cursor itemStatus = data.getItemStatus();
        Cursor user = data.getUser();
        if (user.moveToFirst()) {
            do {
                if (user.getString(2).equals("Nam")) {
                    genuser.setImageResource(R.drawable.nam);
                } else genuser.setImageResource(R.drawable.nu);
                name.setText(user.getString(1));
                age.setText(String.valueOf(user.getInt(3)));
                String high = String.valueOf(user.getInt(4)).concat(" Cm");
                chieucao.setText(high);
                String wei = String.valueOf(user.getInt(5)).concat(" Kg");
                cannang.setText(wei);
                calorie.setText(String.valueOf(user.getInt(7)));
                muctieu = user.getInt(7);
            } while (user.moveToNext());
        }

        if (itemStatus.moveToFirst()) {
            do {
                String Date = itemStatus.getString(0);
                int allcalo = itemStatus.getInt(1);
                String CaloItemStatus = String.valueOf(itemStatus.getInt(1));
                int per = (allcalo * 100 )/muctieu;
                String ItemStatusPercent = String.valueOf(per);
                ListItemStatus.add(new StatusItem(Date,CaloItemStatus,ItemStatusPercent));
                StatusItem st = new StatusItem(Date,CaloItemStatus,ItemStatusPercent);
                Log.i("AddListItemStatus",st.toString());
            } while (itemStatus.moveToNext());
        }

        StatusItemAdater adaterst = new StatusItemAdater(getContext(), R.layout.status_item, ListItemStatus);
        listStatusItem.setAdapter(adaterst);
    }
}
