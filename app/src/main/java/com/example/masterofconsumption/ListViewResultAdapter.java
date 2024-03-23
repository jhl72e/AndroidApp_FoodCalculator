package com.example.masterofconsumption;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewResultAdapter extends BaseAdapter {

    Context context;
    int resId;
    ArrayList<ListViewResult> datas;

    public ListViewResultAdapter(Context _context, int _resId, ArrayList<ListViewResult> _datas){
        context = _context;
        resId = _resId;
        datas = _datas;
    }

    @Override
    public int getCount() {
        return datas.size();
    }

    @Override
    public Object getItem(int position) {
        return datas.get(position);
    }

    @Override
    public long getItemId(int position) {
        return position;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        if(convertView == null){
            LayoutInflater inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
            convertView = inflater.inflate(resId, null);
        }

        TextView name = convertView.findViewById(R.id.name);
        TextView price = convertView.findViewById(R.id.price);
        TextView value = convertView.findViewById(R.id.value);

        name.setText(datas.get(position).name);
        price.setText(datas.get(position).price + "원");
        value.setText("X " + datas.get(position).value);

        return convertView;
    }
}
