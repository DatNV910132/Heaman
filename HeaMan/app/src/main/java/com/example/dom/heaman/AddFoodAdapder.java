package com.example.dom.heaman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.BaseAdapter;
import android.widget.CheckBox;
import android.widget.TextView;

import java.util.ArrayList;

public class AddFoodAdapder extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<SanPham> listsp;

    public AddFoodAdapder(Context context, int layout, ArrayList<SanPham> listsp) {
        this.context = context;
        this.layout = layout;
        this.listsp = listsp;
    }

    @Override
    public int getCount() {
        return listsp.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    public static class ViewHolder {

        TextView NameSp;
        TextView Hangsx;
        TextView Loai;
        TextView Calorie;
//        CheckBox check;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AddFoodAdapder.ViewHolder holder;
        if (view == null) {
            holder = new AddFoodAdapder.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.NameSp = view.findViewById(R.id.addnamefood);
            holder.Hangsx = view.findViewById(R.id.addhangfood);
            holder.Loai = view.findViewById(R.id.addloaifood);
            holder.Calorie = view.findViewById(R.id.addcaloriefood);
//            holder.check = view.findViewById(R.id.addfoodcheck);
            view.setTag(holder);

        } else {
            holder = (AddFoodAdapder.ViewHolder) view.getTag();
        }
        SanPham sp = listsp.get(i);
        holder.NameSp.setText(sp.getTenSP());
        holder.Hangsx.setText(sp.getHangsx());
        holder.Loai.setText(sp.getLoai());
        holder.Calorie.setText(String.valueOf(sp.getCalorie()));
//        holder.check.setSelected(false);
        return view;

    }

}
