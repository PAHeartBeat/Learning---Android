package me.paheartbeat.learning.serviceexample;

import android.app.Service;
import android.content.Intent;
import android.os.IBinder;
import android.support.annotation.Nullable;
import android.util.Log;
import android.widget.Toast;

public class MyService extends Service {
	private final String TAG = "MyService";

	@Override
	public void onCreate() {
		super.onCreate();
		Toast.makeText(this, "Creating Service", Toast.LENGTH_SHORT).show();
	}

	//@Nullable
	@Override
	public IBinder onBind(Intent intent) {
		Log.i(TAG,"Service Binds with null");
		return null;
	}

	@Override
	public int onStartCommand(Intent intent, int flags, int startId) {
		// Let it continue running until it is stopped.
		Log.i(TAG,"Service Started");
		Toast.makeText(this, "Service Started", Toast.LENGTH_SHORT).show();
		return START_STICKY;
	}

	@Override
	public void onDestroy() {
		super.onDestroy();
		Toast.makeText(this, "Service Destroyed", Toast.LENGTH_SHORT).show();
		Log.i(TAG,"Service stopped");
	}
}
