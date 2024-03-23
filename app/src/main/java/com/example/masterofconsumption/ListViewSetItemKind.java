package com.example.masterofconsumption;

import android.widget.ImageView;

import java.util.ArrayList;

public class ListViewSetItemKind {

    String name;
    ArrayList<ListViewSetItem> itemList;
    ImageView imageView;

    public ListViewSetItemKind(String _name, ArrayList<ListViewSetItem> arrayList){
        name = _name;
        itemList = arrayList;
    }

    public void unfold(){
        imageView.setRotation(180);
    }

    public void fold(){
        imageView.setRotation(0);
    }

}
