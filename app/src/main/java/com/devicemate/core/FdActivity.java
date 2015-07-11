package com.devicemate.core;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.devicemate.R;
import com.devicemate.setting.AppActivity;


public class FdActivity extends Activity {

	private Handler handler = new Handler();
	private Runnable runnable;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_mate_core);

		TextView textView = (TextView) findViewById(R.id.textView1);

		SharedPreferences sharedPref = getSharedPreferences("com.DriveMate", MODE_PRIVATE);
		final int message = sharedPref.getInt(getString(R.string.delayKey), 20);

		textView.setText(Integer.toString(message));

		runnable = new Runnable() {
			@Override
			public void run() {

				driveMateContextSwitch();
			}
		};

		handler.postDelayed(runnable, message * 1000);
	}

	public void driveMateContextSwitch(){

        /*
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(Integer.toString(temp))
            .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = alertDialog.create();
        alert.show();
        */

		Intent intent = new Intent(this,AppActivity.class);
		startActivity(intent);
		finish();

	}

	/*Accessing values from SharedPreferences*/
	public void showMessage(View view){
		SharedPreferences sharedPref = getSharedPreferences("com.DriveMate", MODE_PRIVATE);
		float message = sharedPref.getFloat(getString(R.string.speedValues), 50.0f);

		TextView texView = (TextView)findViewById(R.id.textView1);
		texView.setText(Float.toString(message));
	}

}