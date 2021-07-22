package com.example.dom.heaman;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;

public class MoreFragment extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;
    LinearLayout listfood;
    LinearLayout caloriecal;
    LinearLayout addfood;
    LinearLayout help;
    LinearLayout exportBar;
    LinearLayout BMI;


    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_more, container, false);
        listfood = view.findViewById(R.id.listfood);
        caloriecal = view.findViewById(R.id.Caloriecal);
        addfood = view.findViewById(R.id.AddFood);
        help = view.findViewById(R.id.help);
        exportBar = view.findViewById(R.id.CreateBar);
        BMI = view.findViewById(R.id.BMI);

        listfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreFragment.this.getActivity(), ListfoodActivity.class);
                startActivity(intent);
            }
        });

        caloriecal.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreFragment.this.getActivity(), caloriecalActivity.class);
                startActivity(intent);
            }
        });

        addfood.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreFragment.this.getActivity(), AddNFoodToDBActivity.class);
                startActivity(intent);
            }
        });

        help.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreFragment.this.getActivity(), HelpActivity.class);
                startActivity(intent);
            }
        });

        exportBar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreFragment.this.getActivity(), ExportBarActivity.class);
                startActivity(intent);
            }
        });
        BMI.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MoreFragment.this.getActivity(), BMIActivity.class);
                startActivity(intent);
            }
        });



        return view;
    }
}
