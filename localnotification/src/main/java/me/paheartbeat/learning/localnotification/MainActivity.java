package me.paheartbeat.learning.localnotification;

import android.app.Activity;
import android.nfc.Tag;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;


public class MainActivity extends Activity {
	Button btnNotify,getBtnNotifyUngrouped, btnNotifyNowG1, btnNotifyNowG2, btnClearNoti;
	int i, j;
	private static final String Tag = "NotificationExample";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		
		AndroidNotificationManager.Me().setContext(this);
		
		
		bindControl();
		bindEvents();
		
	}
	
	private void bindControl() {
		btnNotifyNowG1 = (Button) findViewById(R.id.btnNotifyNowG1);
		btnNotifyNowG2 = (Button) findViewById(R.id.btnNotifyNowG2);
		btnNotify = (Button) findViewById(R.id.btnNotify);
		getBtnNotifyUngrouped= (Button) findViewById(R.id.btnNotifyUnGroup);
		btnClearNoti = (Button) findViewById(R.id.btnClearNotification);
	}
	
	private void bindEvents() {
		btnNotifyNowG1.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(Tag,"Grouped Notification with default Group One");
				AndroidNotificationManager.Me().scheduleNotification(
						new AndroidNotificationFields() {{
							title = "Group One";
							message = "Group One Test Notification " + i;
							duration = 0;
							groupKey = "Group One";
						}}
				);
				i++;
			}
		});
		btnNotifyNowG2.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(Tag,"Grouped Notification with default Group Two");
				AndroidNotificationManager.Me().scheduleNotification(
						new AndroidNotificationFields() {{
							title = "Group Two";
							message = "Group Two Test Notification " + i;
							duration = 0;
							groupKey = "Group Two";
						}}
				);
				i++;
			}
		});
		btnNotify.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(Tag,"Grouped Notification with default key");
				AndroidNotificationManager.Me().scheduleNotification(
						new AndroidNotificationFields() {{
							title = "Group " + j;
							message = "Notification " + i;
							duration = 0;
						}}
				);
				i++;
				j++;
			}
		});
		getBtnNotifyUngrouped.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				Log.i(Tag,"Un-grouped Notification with default key");
				AndroidNotificationManager.Me().scheduleNotification(
						new AndroidNotificationFields() {{
							title = "Group " + j;
							message = "Notification " + i;
							duration = 0;
							isGroup = false;
						}}
				);
				i++;
				j++;
			}
		});
		
		btnClearNoti.setOnClickListener(new View.OnClickListener() {
			@Override
			public void onClick(View v) {
				AndroidNotificationManager.Me().clearNotifications();
			}
		});
	}
}
