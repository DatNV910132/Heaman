package com.example.dom.heaman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;
import java.util.zip.Inflater;

public class ItemStatusAdapter extends BaseAdapter {

    private Context context;
    private int layout;
    private ArrayList<Status> liststatus;

    public ItemStatusAdapter(Context context, int layout, ArrayList<Status> liststatus) {
        this.context = context;
        this.layout = layout;
        this.liststatus = liststatus;
    }

    @Override
    public int getCount() {
        return liststatus.size();
    }

    @Override
    public Object getItem(int position) {
        return null;
    }

    @Override
    public long getItemId(int position) {
        return 0;
    }

    private class ViewHolder{

        TextView NameSt;
        TextView TimeSt;
        TextView CaloSt;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder holder;
        if(view == null){
            holder = new ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout,null);
            holder.NameSt = view.findViewById(R.id.NameSPStatus);
            holder.TimeSt = view.findViewById(R.id.DateStatus);
            holder.CaloSt = view.findViewById(R.id.CaloStatus);
            view.setTag(holder);

        }else{
            holder = (ViewHolder) view.getTag();
        }

        Status st = liststatus.get(i);
        holder.NameSt.setText(st.getNamesp());
        holder.TimeSt.setText(st.getTime());
        holder.CaloSt.setText(String.valueOf(st.getCalorie()));

        return view;
    }
}
