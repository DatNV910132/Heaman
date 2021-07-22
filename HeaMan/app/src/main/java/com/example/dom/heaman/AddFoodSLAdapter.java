package com.example.dom.heaman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class AddFoodSLAdapter extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<SanPham> listsp;

    public AddFoodSLAdapter(Context context, int layout, ArrayList<SanPham> listsp) {
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

        TextView slNameSp;
        TextView slCalorie;
        TextView sl;
        ImageButton btntang;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        AddFoodSLAdapter.ViewHolder holder;
        if (view == null) {
            holder = new AddFoodSLAdapter.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.slNameSp = view.findViewById(R.id.slnamefood);
            holder.slCalorie = view.findViewById(R.id.slcaloriefood);
            holder.sl = view.findViewById(R.id.slfood);
            holder.btntang = view.findViewById(R.id.btntang);
            view.setTag(holder);

        } else {
            holder = (AddFoodSLAdapter.ViewHolder) view.getTag();
        }
        SanPham sp = listsp.get(i);
        holder.slNameSp.setText(sp.getTenSP());
        holder.slCalorie.setText(String.valueOf(sp.getCalorie()));
        holder.sl.setText(" ");
        return view;

    }

}
