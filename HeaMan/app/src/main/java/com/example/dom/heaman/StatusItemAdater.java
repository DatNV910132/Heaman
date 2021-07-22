package com.example.dom.heaman;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class StatusItemAdater extends BaseAdapter {
    private Context context;
    private int layout;
    private ArrayList<StatusItem> statusitem;

    public StatusItemAdater(Context context, int layout, ArrayList<StatusItem> statusitem) {
        this.context = context;
        this.layout = layout;
        this.statusitem = statusitem;
    }

    @Override
    public int getCount() {
        return statusitem.size();
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

        TextView Date;
        TextView Calo;
        TextView Percentcalo;
    }

    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        StatusItemAdater.ViewHolder holder;
        if (view == null) {
            holder = new StatusItemAdater.ViewHolder();
            LayoutInflater inflater = (LayoutInflater) context.getSystemService(context.LAYOUT_INFLATER_SERVICE);
            view = inflater.inflate(layout, null);
            holder.Date = view.findViewById(R.id.ListStatusDate);
            holder.Calo = view.findViewById(R.id.ListStatusCalo);
            holder.Percentcalo = view.findViewById(R.id.ListStatusPercent);
            view.setTag(holder);

        } else {
            holder = (StatusItemAdater.ViewHolder) view.getTag();
        }
        StatusItem st = statusitem.get(i);
        holder.Date.setText(st.getStDate());
        holder.Calo.setText(st.getStCalo());
        holder.Percentcalo.setText(st.getStPercentCalo());
        return view;

    }

    {
    }
}
