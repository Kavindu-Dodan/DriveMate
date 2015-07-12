package com.devicemate.game;

import android.content.Context;
import android.content.Intent;
import android.graphics.Color;
import android.support.v7.app.ActionBarActivity;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.CheckedTextView;


import com.devicemate.R;
import com.devicemate.setting.AppActivity;

import java.util.Random;

public class drive_mate_game extends ActionBarActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.drive_mate_game);

        final Context context = this;

        // Colour choosing
        Random rnd = new Random();

        int[] ColourChoices = {Color.RED,Color.YELLOW, Color.GREEN ,Color.BLUE};
        String[] ColourNames = {"Red","Yellow","Green","Blue"};

        // Original Answer
        int answerEntry = rnd.nextInt(4);
        int answer =  ColourChoices[answerEntry];
        String answerFlag= ColourNames[answerEntry];

        // Fake Answer
        int dummyEntry = rnd.nextInt(4);
        int dummy=-1;

        boolean flag = false;
        while (!flag){
            dummyEntry = rnd.nextInt(4);

            if (answerEntry !=dummyEntry){
                flag = true;
            }
        }

        dummy =  ColourChoices[dummyEntry];
        String dummyFlag = ColourNames[dummyEntry];

        // 0 or 1 place
        int OriginalPlace = rnd.nextInt(2);

        // Setting buttons
        if(OriginalPlace == 0){
            // Button1 set to Original
            Button btn1 = (Button)findViewById(R.id.button1);
            btn1.setBackgroundColor(answer);

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AppActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

            Button btn2 = (Button)findViewById(R.id.button2);
            btn2.setBackgroundColor(dummy);

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, drive_mate_game.class);
                    startActivity(intent);
                    finish();
                }
            });

        }else{

            Button btn1 = (Button)findViewById(R.id.button1);
            btn1.setBackgroundColor(dummy);

            btn1.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, drive_mate_game.class);
                    startActivity(intent);
                    finish();
                }
            });

            // Button2 set to Original
            Button btn2 = (Button)findViewById(R.id.button2);
            btn2.setBackgroundColor(answer);

            btn2.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(context, AppActivity.class);
                    startActivity(intent);
                    finish();
                }
            });

        }

        CheckedTextView chv= (CheckedTextView) findViewById(R.id.question);
        chv.setText(answerFlag);

    }
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }
}
