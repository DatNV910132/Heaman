package com.example.dom.heaman;

import android.content.Context;
import android.graphics.Bitmap;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.zxing.BarcodeFormat;
import com.google.zxing.WriterException;
import com.journeyapps.barcodescanner.BarcodeEncoder;

import java.util.ArrayList;

public class BarExItemAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<String > name;
    private ArrayList<String > code;

//    public BarExItemAdapter(Context context, int layout, ArrayList<String> name) {
//        this.context = context;
//        this.layout = layout;
//        this.name = name;
//    }

    public BarExItemAdapter(Context context, int layout, ArrayList<String> name, ArrayList<String> code) {
        this.context = context;
        this.layout = layout;
        this.name = name;
        this.code = code;
    }

    public int getCount() {
        return name.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder {

        ImageView bar;
        TextView name;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        Bitmap bt = null;
        if (view == null) {
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.bar = view.findViewById(R.id.barim);
            holder.name = view.findViewById(R.id.namrus);
            view.setTag(holder);

        } else {
            holder = (ViewHolder) view.getTag();
        }
        String st = name.get(i);
        String stringbar = code.get(i);
        BarcodeEncoder bar = new BarcodeEncoder();
        try {
            bt = bar.encodeBitmap(stringbar, BarcodeFormat.CODE_128, 100, 50);
        } catch (WriterException e) {
            e.printStackTrace();
        }

        holder.bar.setImageBitmap(bt);
        holder.name.setText(st);

        return view;
    }
}

