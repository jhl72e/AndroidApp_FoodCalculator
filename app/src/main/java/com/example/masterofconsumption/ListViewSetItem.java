package com.example.masterofconsumption;

import android.widget.CheckBox;

public class ListViewSetItem {

    String name;
    String price;
    CheckBox checkBox;
    boolean isChecked;

    public ListViewSetItem(String _name, String _price) {
        name = _name;
        price = _price;
        isChecked = false;
    }

    public void check(){
        if(isChecked){
            isChecked = false;
        }
        else {
            isChecked = true;
        }
    }

}
