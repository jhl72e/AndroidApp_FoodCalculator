package com.example.masterofconsumption;

import android.content.DialogInterface;
import android.database.sqlite.SQLiteOpenHelper;
import android.database.sqlite.SQLiteDatabase;
import android.content.Intent;
import android.database.sqlite.SQLiteStatement;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.InputStream;

public class UploadItemActivity extends AppCompatActivity implements View.OnClickListener {

    BackButtonCloseHandler backButtonCloseHandler;

    ImageButton backButton;
    Button selectKindButton, applyButton;
    EditText itemKind, itemName, itemPrice;

    AlertDialog itemKindList;

    int index;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_upload_item);

        backButtonCloseHandler = new BackButtonCloseHandler(this);

        backButton = findViewById(R.id.backButton);
        itemKind = findViewById(R.id.itemKind);
        selectKindButton = findViewById(R.id.selectKindButton);
        itemName = findViewById(R.id.itemName);
        itemPrice = findViewById(R.id.itemPrice);
        applyButton = findViewById(R.id.applyButton);

        backButton.setOnClickListener(this);
        selectKindButton.setOnClickListener(this);
        applyButton.setOnClickListener(this);

        index = 0;
    }

    @Override
    public void onClick(View v) {
        if(v == backButton){
            //뒤로가기 버튼 눌렀을때
            Intent intent = new Intent(UploadItemActivity.this, MainActivity.class);
            startActivity(intent);

            finish();
        }
        else if(v == selectKindButton){
            //종류 선택 버튼 눌렀을때
            AlertDialog.Builder builder = new AlertDialog.Builder(this);
            builder.setTitle("물건 종류 선택");
            builder.setSingleChoiceItems(R.array.dialog_itemKind, index, new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    index = which;
                }
            });

            builder.setPositiveButton("확인", new DialogInterface.OnClickListener() {
                @Override
                public void onClick(DialogInterface dialog, int which) {
                    String[] datas = getResources().getStringArray(R.array.dialog_itemKind);
                    String s = datas[index];
                    itemKind.setText(s);
                }
            });
            builder.setNegativeButton("취소", null);
            itemKindList = builder.create();
            itemKindList.show();
        }
        else if(v == applyButton){
            //물건 업로드 버튼 눌렀을때
            DBHelper helper = new DBHelper(this);
            SQLiteDatabase db = helper.getWritableDatabase();

            String item = itemKind.getText().toString();
            String name = itemName.getText().toString();
            String price = itemPrice.getText().toString();

            String sql ="insert into uploaditem values(?,?,?)";
            SQLiteStatement insertStmt = db.compileStatement(sql);
            insertStmt.clearBindings();
            insertStmt.bindString(1, item);
            insertStmt.bindString(2, name);
            insertStmt.bindString(3, price);
            try {
                insertStmt.executeInsert();
                Toast.makeText(this, "물품이 입력되었습니다.",
                        Toast.LENGTH_SHORT).show();
            } catch (Exception e) {
                Toast.makeText(this,"입력에 실패하였습니다.\n이미 입력되어 있는 물품인지 확인해주세요.",
                        Toast.LENGTH_SHORT).show();
                Log.e("SQL", sql);
            }
            db.close();
        }
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backButtonCloseHandler.OnPressedBackButton();
    }

}
