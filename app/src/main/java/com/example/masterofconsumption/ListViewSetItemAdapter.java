package com.example.masterofconsumption;

import android.content.Context;
import android.graphics.Color;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseExpandableListAdapter;
import android.widget.CheckBox;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

public class ListViewSetItemAdapter extends BaseExpandableListAdapter {

    Context context;
    int groupLayout, childLayout;
    ArrayList<ListViewSetItemKind> kindList;
    LayoutInflater inflater;

    public ListViewSetItemAdapter(Context _context, int _groupLayout, int _childLayout, ArrayList<ListViewSetItemKind> arrayList){
        context = _context;
        groupLayout = _groupLayout;
        childLayout = _childLayout;
        kindList = arrayList;
        inflater = (LayoutInflater)context.getSystemService(Context.LAYOUT_INFLATER_SERVICE);
    }

    @Override
    public int getGroupCount() {
        return kindList.size();
    }

    @Override
    public int getChildrenCount(int groupPosition) {
        return kindList.get(groupPosition).itemList.size();
    }

    @Override
    public Object getGroup(int groupPosition) {
        return kindList.get(groupPosition);
    }

    @Override
    public Object getChild(int groupPosition, int childPosition) {
        return kindList.get(groupPosition).itemList.get(childPosition);
    }

    @Override
    public long getGroupId(int groupPosition) {
        return groupPosition;
    }

    @Override
    public long getChildId(int groupPosition, int childPosition) {
        return childPosition;
    }

    @Override
    public boolean hasStableIds() {
        return true;
    }

    @Override
    public View getGroupView(int groupPosition, boolean isExpanded, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(groupLayout, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.setItemKindText);
        ImageView imageView = convertView.findViewById(R.id.setItemKindImage);
        textView.setText(kindList.get(groupPosition).name);
        kindList.get(groupPosition).imageView = imageView;
        if(isExpanded){
            kindList.get(groupPosition).unfold();
        }
        else {
            kindList.get(groupPosition).fold();
        }

        return convertView;
    }

    @Override
    public View getChildView(final int groupPosition, final int childPosition, boolean isLastChild, View convertView, ViewGroup parent) {
        if(convertView == null){
            convertView = inflater.inflate(childLayout, parent, false);
        }

        TextView textView = convertView.findViewById(R.id.setItemName);
        TextView textViewPrice = convertView.findViewById(R.id.setItemPrice);
        CheckBox checkBox = convertView.findViewById(R.id.setItemCheckbox);
        textView.setText(kindList.get(groupPosition).itemList.get(childPosition).name);
        textViewPrice.setText(kindList.get(groupPosition).itemList.get(childPosition).price);
        checkBox.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                kindList.get(groupPosition).itemList.get(childPosition).check();
            }
        });

        return convertView;
    }

    @Override
    public boolean isChildSelectable(int groupPosition, int childPosition) {
        return true;
    }
}
