package com.example.dom.heaman;

import android.app.Activity;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.NotificationCompat;
import android.support.v4.app.NotificationManagerCompat;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;


import com.google.zxing.integration.android.IntentIntegrator;
import com.google.zxing.integration.android.IntentResult;

import java.util.ArrayList;
import java.util.Calendar;


public class StatusFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";
    Button btnscan;
    Button btnadd;
    TextView txtstatus;
    TextView txtpercentstattus;
    ListView listStatus;
    String arr[];
    int AllCalo = 0;
    int muctieu = 1;
    public static final int REQUEST_CODE = 100;


    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    public StatusFragment() {
        // Required empty public constructor
    }

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
        View view = inflater.inflate(R.layout.fragment_status, container, false);
        btnscan = view.findViewById(R.id.btnScan);
        btnadd = view.findViewById(R.id.btnAdd);
        txtstatus = view.findViewById(R.id.Numstatus);
        listStatus = view.findViewById(R.id.listitem);
        txtpercentstattus = view.findViewById(R.id.percentstatus);


        MyDatabaseHelper data = new MyDatabaseHelper(getContext());
        BasicData databasic = new BasicData();
        SanPham[] sps = databasic.getbasicSP();
        Cursor dataSP = data.getAllSanPham();
        if (dataSP.moveToFirst()) {
            do {
            } while (dataSP.moveToNext());
        } else {
            for (int i = 0; i < sps.length; i++) {
                data.addSanpham(sps[i]);
            }
        }

        Cursor user = data.getUser();
        if (user.moveToFirst()) {
            do {
                muctieu = user.getInt(7);
            } while (user.moveToNext());
        }
        Log.i("muctieu", String.valueOf(muctieu));
        Loaddata();

        btnadd.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), AddfoodActivity.class);
                startActivity(i);
            }
        });
        btnscan.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(getActivity(), ScanActivity.class);
                startActivity(i);
            }
        });
        return view;
    }

    public void Loaddata() {
        MyDatabaseHelper loaddata = new MyDatabaseHelper(getContext());
        ArrayList<Status> arrayListSt = new ArrayList<>();
        AllCalo = 0;
        Calendar calendar = Calendar.getInstance();
        String year = String.valueOf(calendar.get(Calendar.YEAR));
        String month = String.valueOf(calendar.get(Calendar.MONTH) + 1);
        String day = String.valueOf(calendar.get(Calendar.DAY_OF_MONTH));
        String hour = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
        String minute = String.valueOf(calendar.get(Calendar.MINUTE));
        String second = String.valueOf(calendar.get(Calendar.SECOND));
        String date = day + "/" + month + "/" + year;
        String time = hour + ":" + minute + ":" + second;
        Cursor datast = loaddata.getStatus(date);

        // Duyệt trên con trỏ, và thêm vào danh sách.
        if (datast.moveToFirst()) {
            do {

                String uid = datast.getString(0);
                Integer calo = (datast.getInt(5));
                AllCalo += calo;
                String datadate = datast.getString(1);
                String datatime = datast.getString(2);
                String sid = datast.getString(3);
                String namesp = datast.getString(4);
                arrayListSt.add(new Status(uid, datadate, datatime, sid, namesp, calo));


            } while (datast.moveToNext());
        }
        Cursor user = loaddata.getUser();
        if (user.moveToFirst()) {
            do {
                muctieu = user.getInt(7);
            } while (user.moveToNext());
        }
        ItemStatusAdapter adapter = new ItemStatusAdapter(getContext(), R.layout.activity_item_status, arrayListSt);
        listStatus.setAdapter(adapter);
        txtstatus.setText(String.valueOf(AllCalo));
        int percent = ((AllCalo * 100) / muctieu);
        String p = String.valueOf(percent).concat(" %");
        txtpercentstattus.setText(p);
    }

}
