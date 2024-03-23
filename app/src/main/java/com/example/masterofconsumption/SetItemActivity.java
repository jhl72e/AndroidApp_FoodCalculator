package com.example.masterofconsumption;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.Drawable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.ExpandableListView;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class SetItemActivity extends AppCompatActivity implements View.OnClickListener {

    BackButtonCloseHandler backButtonCloseHandler;

    ImageButton backButton;
    Button applyButton;
    ExpandableListView listView;

    ArrayList<ListViewSetItemKind> kindArrayList;
    ArrayList<ArrayList<ListViewSetItem>> itemArrayList;

    static ArrayList<ListViewSetItem> checkList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_set_item);

        backButtonCloseHandler = new BackButtonCloseHandler(this);

        backButton = findViewById(R.id.backButton);
        applyButton = findViewById(R.id.applyButton);
        listView = findViewById(R.id.listView);

        backButton.setOnClickListener(this);
        applyButton.setOnClickListener(this);

        kindArrayList = new ArrayList<>();
        itemArrayList = new ArrayList<>();
        checkList = new ArrayList<>();

        itemArrayList.add(new ArrayList<ListViewSetItem>());
        itemArrayList.add(new ArrayList<ListViewSetItem>());
        itemArrayList.add(new ArrayList<ListViewSetItem>());
        itemArrayList.add(new ArrayList<ListViewSetItem>());

        DBHelper helper = new DBHelper(this);
        SQLiteDatabase db = helper.getReadableDatabase();
        String sql = "select * from uploaditem";

        Cursor cursor = db.rawQuery(sql, null);
        while(cursor.moveToNext()){
            String name = cursor.getString(1);
            String price = cursor.getString(2);
            ListViewSetItem item = new ListViewSetItem(name, price);
            if(cursor.getString(0).equals("음식")){
                itemArrayList.get(0).add(item);
            }
            else if(cursor.getString(0).equals("생활용품")){
                itemArrayList.get(1).add(item);
            }
            else if(cursor.getString(0).equals("문화")){
                itemArrayList.get(2).add(item);
            }
            else if(cursor.getString(0).equals("기타")){
                itemArrayList.get(3).add(item);
            }
        }
        db.close();

        initKindArrayList();

        ListViewSetItemAdapter adapter = new ListViewSetItemAdapter(this, R.layout.listview_setitem_kind, R.layout.listview_setitem, kindArrayList);
        listView.setAdapter(adapter);
    }

    @Override
    public void onClick(View v) {
        if(v == backButton){
            Intent intent = new Intent(SetItemActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
        }
        else if(v == applyButton){
            for(int i = 0; i < kindArrayList.size(); i++){
                for(int j = 0; j < itemArrayList.get(i).size(); j++){
                    if(itemArrayList.get(i).get(j).isChecked){
                        checkList.add(itemArrayList.get(i).get(j));
                    }
                }
            }

            Intent intent = new Intent(SetItemActivity.this, ResultActivity.class);
            startActivity(intent);

            finish();
        }
    }

    private void initKindArrayList(){
        kindArrayList.add(new ListViewSetItemKind("음식", itemArrayList.get(0)));
        kindArrayList.add(new ListViewSetItemKind("생활용품", itemArrayList.get(1)));
        kindArrayList.add(new ListViewSetItemKind("문화", itemArrayList.get(2)));
        kindArrayList.add(new ListViewSetItemKind("기타", itemArrayList.get(3)));
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backButtonCloseHandler.OnPressedBackButton();
    }
}
