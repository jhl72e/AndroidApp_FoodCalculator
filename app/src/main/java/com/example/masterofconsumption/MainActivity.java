package com.example.masterofconsumption;

import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.TextView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    BackButtonCloseHandler backButtonCloseHandler;

    ImageButton uploadButton;
    Button setItemButton, button_CE, button_C, button_backspace, button_result, button_plus, button_minus, button_multiply, button_divide;
    Button button_0, button_1, button_2, button_3, button_4, button_5, button_6, button_7, button_8, button_9, button_00, button_dot;
    static TextView answerText, savedAnswerText;
    String answer, savedAnswer;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        backButtonCloseHandler = new BackButtonCloseHandler(this);

        answer = "";
        savedAnswer = "";

        uploadButton = findViewById(R.id.uploadButton);
        setItemButton = findViewById(R.id.setItemButton);

        button_CE = findViewById(R.id.button_CE);
        button_C = findViewById(R.id.button_C);
        button_backspace = findViewById(R.id.button_backspace);
        button_result = findViewById(R.id.button_result);
        button_plus = findViewById(R.id.button_plus);
        button_minus = findViewById(R.id.button_minus);
        button_multiply = findViewById(R.id.button_multiply);
        button_divide = findViewById(R.id.button_divide);

        button_0 = findViewById(R.id.button_0);
        button_1 = findViewById(R.id.button_1);
        button_2 = findViewById(R.id.button_2);
        button_3 = findViewById(R.id.button_3);
        button_4 = findViewById(R.id.button_4);
        button_5 = findViewById(R.id.button_5);
        button_6 = findViewById(R.id.button_6);
        button_7 = findViewById(R.id.button_7);
        button_8 = findViewById(R.id.button_8);
        button_9 = findViewById(R.id.button_9);
        button_00 = findViewById(R.id.button_00);
        button_dot = findViewById(R.id.button_dot);

        answerText = findViewById(R.id.answerText);
        savedAnswerText = findViewById(R.id.savedAnswerText);

        uploadButton.setOnClickListener(this);
        setItemButton.setOnClickListener(this);

        button_CE.setOnClickListener(this);
        button_C.setOnClickListener(this);
        button_backspace.setOnClickListener(this);
        button_result.setOnClickListener(this);
        button_plus.setOnClickListener(this);
        button_minus.setOnClickListener(this);
        button_multiply.setOnClickListener(this);
        button_divide.setOnClickListener(this);

        button_0.setOnClickListener(this);
        button_1.setOnClickListener(this);
        button_2.setOnClickListener(this);
        button_3.setOnClickListener(this);
        button_4.setOnClickListener(this);
        button_5.setOnClickListener(this);
        button_6.setOnClickListener(this);
        button_7.setOnClickListener(this);
        button_8.setOnClickListener(this);
        button_9.setOnClickListener(this);
        button_00.setOnClickListener(this);
        button_dot.setOnClickListener(this);

        UpdateAnswerText();
    }

    @Override
    public void onClick(View v) {
        if(v == uploadButton){
            Intent intent = new Intent(MainActivity.this, UploadItemActivity.class);
            startActivity(intent);

            finish();
        }
        else if(v == setItemButton){
            Intent intent = new Intent(MainActivity.this, SetItemActivity.class);
            startActivity(intent);

            finish();
        }
        else if(v == button_1){
            answer += "1";
            UpdateAnswerText();
        }
        else if(v == button_2){
            answer += "2";
            UpdateAnswerText();
        }
        else if(v == button_3){
            answer += "3";
            UpdateAnswerText();
        }
        else if(v == button_4){
            answer += "4";
            UpdateAnswerText();
        }
        else if(v == button_5){
            answer += "5";
            UpdateAnswerText();
        }
        else if(v == button_6){
            answer += "6";
            UpdateAnswerText();
        }
        else if(v == button_7){
            answer += "7";
            UpdateAnswerText();
        }
        else if(v == button_8){
            answer += "8";
            UpdateAnswerText();
        }
        else if(v == button_9){
            answer += "9";
            UpdateAnswerText();
        }
        else if(v == button_0){
            if(answer != "") {
                answer += "0";
            }
            UpdateAnswerText();
        }
        else if(v == button_00){
            if(answer != ""){
                answer += "00";
            }
            UpdateAnswerText();
        }
        else if(v == button_dot){
            answer += ".";
            UpdateAnswerText();
        }
        else if(v == button_CE){
            answer = "";
            UpdateAnswerText();
        }
        else if(v == button_C){
            answer = "";
            savedAnswer = "";
            UpdateAnswerText();
        }
        else if(v == button_backspace){
            if(answer.length() > 1){
                answer = answer.substring(0, answer.length() - 1);
            }
            else {
                answer = "";
            }
            UpdateAnswerText();
        }
        else if(v == button_plus){
            if(answer != "") {
                savedAnswer += answer + "+";
                answer = "";
            }
            else {
                if(savedAnswer == ""){
                    savedAnswer = "0+";
                }
                else {
                    savedAnswer = savedAnswer.substring(0, savedAnswer.length() - 1);
                    savedAnswer += "+";
                }
            }
            UpdateAnswerText();
        }
        else if(v == button_minus){
            if(answer != "") {
                savedAnswer += answer + "-";
                answer = "";
            }
            else {
                if(savedAnswer == ""){
                    savedAnswer = "0-";
                }
                else {
                    savedAnswer = savedAnswer.substring(0, savedAnswer.length() - 1);
                    savedAnswer += "-";
                }
            }
            UpdateAnswerText();
        }
        else if(v == button_multiply){
            if(answer != "") {
                savedAnswer += answer + "*";
                answer = "";
            }
            else {
                if(savedAnswer == ""){
                    savedAnswer = "0*";
                }
                else {
                    savedAnswer = savedAnswer.substring(0, savedAnswer.length() - 1);
                    savedAnswer += "*";
                }
            }
            UpdateAnswerText();
        }
        else if(v == button_divide){
            if(answer != "") {
                savedAnswer += answer + "/";
                answer = "";
            }
            else {
                if(savedAnswer == ""){
                    savedAnswer = "0/";
                }
                else {
                    savedAnswer = savedAnswer.substring(0, savedAnswer.length() - 1);
                    savedAnswer += "/";
                }
            }
            UpdateAnswerText();
        }
        else if(v == button_result){
            if(answer == ""){
                if(savedAnswer != ""){
                    savedAnswer = savedAnswer.substring(0, savedAnswer.length() - 1);
                }
            }
            else {
                savedAnswer += answer;
            }
            answer = "";
            answerText.setText(Calculate(savedAnswer));
            answerText.setTextColor(Color.BLUE);
            savedAnswer = "";
            savedAnswerText.setText("");
        }
    }

    private void UpdateAnswerText(){
        if(answer == ""){
            answerText.setText("0");
        }
        else {
            if(answer.length() > 10){
                answer = answer.substring(0, 11);
            }
            answerText.setText(answer);
        }
        answerText.setTextColor(Color.BLACK);
        savedAnswerText.setText(savedAnswer);
    }

    private String Calculate(String string){
        String[] split = string.split("(?<=[*/+-])|(?=[*/+-])");
        ArrayList<String> splitArray = new ArrayList<>();

        for(int i = 0; i < split.length; i++){
            splitArray.add(split[i]);
        }

        for(int i = 0; i < splitArray.size(); i++){
            int temp = 0;

            if (splitArray.get(i).equals("*")) {
                temp = Integer.parseInt(splitArray.get(i - 1)) * Integer.parseInt(splitArray.get(i + 1));
                splitArray.set(i - 1, temp + "");
                splitArray.remove(i + 1);
                splitArray.remove(i);
                i--;
            } else if (splitArray.get(i).equals("/")) {
                temp = Integer.parseInt(splitArray.get(i - 1)) / Integer.parseInt(splitArray.get(i + 1));
                splitArray.set(i - 1, temp + "");
                splitArray.remove(i + 1);
                splitArray.remove(i);
                i--;
            }
        }
        for(int i = 0; i < splitArray.size(); i++){
            int temp = 0;

            if (splitArray.get(i).equals("+")) {
                temp = Integer.parseInt(splitArray.get(i - 1)) + Integer.parseInt(splitArray.get(i + 1));
                splitArray.set(i - 1, temp + "");
                splitArray.remove(i + 1);
                splitArray.remove(i);
                i--;
            } else if (splitArray.get(i).equals("-")) {
                temp = Integer.parseInt(splitArray.get(i - 1)) - Integer.parseInt(splitArray.get(i + 1));
                splitArray.set(i - 1, temp + "");
                splitArray.remove(i + 1);
                splitArray.remove(i);
                i--;
            }
        }
        return splitArray.get(0);
    }

    @Override
    public void onBackPressed() {
        //super.onBackPressed();
        backButtonCloseHandler.OnPressedBackButton();
    }
}
