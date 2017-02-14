package me.paheratbeat.broadcastreceiverexample;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.util.Log;
import android.content.Intent;

public class MainActivity extends AppCompatActivity {
	private final String TAG = "BRE:MainActivity";
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
	}

	// broadcast a custom intent.
	public void broadcastIntent(Intent intent, final String packageId, final String intentType){
		Log.i(TAG,"Broadcasting Intent");
		final String boradcastIntentType = packageId+"."+intentType;
		intent.setAction(boradcastIntentType);
		sendBroadcast(intent);
	}
	public void firstBroadcast(View view){
		Log.i(TAG,"Broadcasting First");
		Intent intent = new Intent();
		intent.putExtra("sender", "Self");
		intent.putExtra("isShow", true);
		intent.putExtra("msg", "This is first Test");
		broadcastIntent(intent,"me.paheratbeat.broadcastreceiverexample","FIRST_TEST");
		Log.i(TAG,"First Broadcast Completed");

	}
	public void secondBroadcast(View view){
		Log.i(TAG,"Broadcasting Second");
		Intent intent = new Intent();
		intent.putExtra("sender", "Self");
		intent.putExtra("isShow", true);
		intent.putExtra("msg", "This is Second Test");
		broadcastIntent(intent,"me.paheratbeat.broadcastreceiverexample","SECOND_TEST");
		Log.i(TAG,"Second Broadcast Completed");
	}
	public void thirdBroadcast(View view){
		Log.i(TAG,"Broadcasting Third");
		Intent intent = new Intent();
		intent.putExtra("sender", "Self");
		intent.putExtra("isShow", false);
		intent.putExtra("msg", "This is third Test");
		broadcastIntent(intent,"me.paheratbeat.broadcastreceiverexample","FIRST_TEST");
		Log.i(TAG,"Third Broadcast Completed");
	}
}
