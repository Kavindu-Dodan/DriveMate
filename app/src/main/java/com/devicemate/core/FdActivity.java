package com.devicemate.core;

import android.app.Activity;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

import com.devicemate.R;


public class FdActivity extends Activity {

	Button button;
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_mate_core);

		TextView textView = (TextView) findViewById(R.id.textView1);

		SharedPreferences sharedPref = getSharedPreferences("com.DriveMate", MODE_PRIVATE);
		float message = sharedPref.getFloat(getString(R.string.speedValues), 20.0f);

		textView.setText(Float.toString(message));
	}

}