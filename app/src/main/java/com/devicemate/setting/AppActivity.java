package com.devicemate.setting;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.widget.Button;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.EditText;
import android.widget.SeekBar;


import com.devicemate.R;
import com.devicemate.core.FdActivity;


public class AppActivity extends Activity {

	private Button button;
	private SeekBar seekBar ;
	private EditText editText ;

    private boolean saved;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.device_mate_setting);

        saved = false;

		seekBar = (SeekBar) findViewById(R.id.myseekBar);
		editText = (EditText) findViewById(R.id.progressValue);

		seekBar.setOnSeekBarChangeListener(new SeekBar.OnSeekBarChangeListener() {
			@Override
			public void onProgressChanged(SeekBar seekBar, int progress, boolean fromUser) {
				editText.setText(Integer.toString(progress) + " S");
			}

			@Override
			public void onStartTrackingTouch(SeekBar seekBar) {

			}

			@Override
			public void onStopTrackingTouch(SeekBar seekBar) {

			}
		});

		addListenerOnButton();
	}

	public void addListenerOnButton() {

		final Context context = this;

		button = (Button) findViewById(R.id.nextbutton);

		button.setOnClickListener(new OnClickListener() {

            @Override
            public void onClick(View arg0) {

                if (saved) {
                    Intent intent = new Intent(context, FdActivity.class);
                    startActivity(intent);
                    finish();
                } else {
                    showDialog("You need to set the time first.");
                }
            }

        });
	}

    public void showDialog(String message){
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setMessage(message)
                .setCancelable(false)
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    public void onClick(DialogInterface dialog, int id) {
                    }
                });
        AlertDialog alert = alertDialog.create();
        alert.show();
    }

    public void saveMessage(View view){

        EditText editText = (EditText)findViewById(R.id.speedField);
        String speedText = editText.getText().toString();


        if (speedText.equals("")){
            showDialog("You need to provide speed value");
            return;
        }

        int progressValue= seekBar.getProgress();

        if (progressValue < 5){
            progressValue = 5;
        }

        float metersPerSecond =( Float.parseFloat(speedText) * 5f) /18f;

        SharedPreferences sharedPreferences = getSharedPreferences("com.DriveMate",MODE_PRIVATE);
        SharedPreferences.Editor editor = sharedPreferences.edit();
        editor.putInt(getString(R.string.delayKey),progressValue);
        editor.commit();
        editor.putFloat(getString(R.string.speedValues), metersPerSecond);
        editor.commit();

        saved =true;

    }

}