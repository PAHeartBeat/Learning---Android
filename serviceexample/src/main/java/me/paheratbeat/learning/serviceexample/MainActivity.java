package me.paheartbeat.learning.serviceexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import android.content.Intent;

import android.app.Activity;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
	private final String TAG = "Service Example";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.i(TAG,"on Activity Created");
	}

	public void startService(View view){
		Log.i(TAG,"Starting Service");
		Toast.makeText(this, "Starting Service",Toast.LENGTH_SHORT).show();
		startService(new Intent(getBaseContext(), MyService.class));

	}

	public void stopService(View view){
		Log.i(TAG,"Stopping Service");
		stopService(new Intent(getBaseContext(), MyService.class));
	}
}
