package me.paheratbeat.helloworld;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class MainActivity extends AppCompatActivity {
	private final String TAG = "Hello World";

	/** Called when the activity is first created. */
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Log.d(TAG, "The onCreate() event");
	}

	/** Called when the activity is about to become visible. */
	@Override
	protected void onStart() {
		super.onStart();
		Log.d(TAG, "The onStart() event");
	}

	/** Called when the activity has become visible. */
	@Override
	protected void onResume() {
		super.onResume();
		Log.d(TAG, "The onResume() event");
	}

	/** Called when another activity is taking focus. */
	@Override
	protected void onPause() {
		super.onPause();
		Log.d(TAG, "The onPause() event");
	}

	/** Called when the activity is no longer visible. */
	@Override
	protected void onStop() {
		super.onStop();
		Log.d(TAG, "The onStop() event");
	}

	/** Called just before the activity is destroyed. */
	@Override
	public void onDestroy() {
		super.onDestroy();
		Log.d(TAG, "The onDestroy() event");
	}
}
