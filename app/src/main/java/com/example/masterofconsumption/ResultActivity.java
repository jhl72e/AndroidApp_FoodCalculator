package com.example.masterofconsumption;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class ResultActivity extends AppCompatActivity {

    BackButtonCloseHandler backButtonCloseHandler;

    TextView priceText;
    Button applyButton;
    ListView listView;

    ArrayList<ListViewResult> arrayList;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_result);

        backButtonCloseHandler = new BackButtonCloseHandler(this);

        priceText = findViewById(R.id.priceText);
        applyButton = findViewById(R.id.applyButton);
        listView = findViewById(R.id.listView);

        priceText.setText(MainActivity.answerText.getText().toString());

        arrayList = new ArrayList<>();

        for(int i = 0; i < SetItemActivity.checkList.size(); i++){
            String name = SetItemActivity.checkList.get(i).name;
            int price = Integer.parseInt(SetItemActivity.checkList.get(i).price);
            int value = Integer.parseInt(MainActivity.answerText.getText().toString())/Integer.parseInt(SetItemActivity.checkList.get(i).price);

            ListViewResult temp = new ListViewResult(name, price, value);
            arrayList.add(temp);
        }

        ListViewResultAdapter adapter = new ListViewResultAdapter(this, R.layout.result_item, arrayList);
        listView.setAdapter(adapter);

        applyButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ResultActivity.this, MainActivity.class);
                startActivity(intent);

                finish();
            }
        });
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backButtonCloseHandler.OnPressedBackButton();
    }
}
