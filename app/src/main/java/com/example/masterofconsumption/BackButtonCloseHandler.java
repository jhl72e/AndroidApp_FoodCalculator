package com.example.masterofconsumption;

import android.app.Activity;
import android.widget.Toast;

public class BackButtonCloseHandler {

    long tempTime = 0;

    Activity activity;

    public BackButtonCloseHandler(Activity context){
        this.activity = context;
    }

    public void OnPressedBackButton(){
        if(System.currentTimeMillis() - tempTime > 1500){
            tempTime = System.currentTimeMillis();
            Toast.makeText(activity, "한번 더 누르면 종료됩니다.", Toast.LENGTH_SHORT).show();
            return;
        }
        if(System.currentTimeMillis() - tempTime <= 1500){
            activity.finish();
        }
    }



}
