package me.paheartbeat.learning.localnotification;

import android.app.Activity;
import android.app.Notification;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.service.notification.StatusBarNotification;
import android.util.Log;


import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.File;
//import java.lang.reflect.Field;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;

/**
 * Created by PAHeartBeat on 02/03/17.
 *
 */

public class AndroidNotificationManager {
	public static final long SECOND = 1000;
	public static final long MINUTE = 1000 * 60;
	public static final long HOUR = 1000 * 60 * 60;
	public static final long DAY = 1000 * 60 * 60 * 24;
	public static final long WEEK = 1000 * 60 * 60 * 24 * 7;
	
	private static AndroidNotificationManager _me;
	
	private final String TAG = "ANM";
	private String notificationTAG = "TAG";
	private int notId;
	private NotificationManager manager;
	private HashMap<String, NotificationInfo> notificationStack;
	/*
	private Class<?> _unityPlayerClass;
	private Field _unityPlayerActivityField;
	private Activity _parentActivity;
	*/
	private Activity _activity;
	private Context _context;
	
	public AndroidNotificationManager() {
		this._me = this;
		notificationStack = new HashMap<String, NotificationInfo>();
		/*
		try {
			this._unityPlayerClass = Class.forName("com.unity3d.player.UnityPlayer");
			this._unityPlayerActivityField = this._unityPlayerClass.getField("currentActivity");
			this._parentActivity = (Activity) this._unityPlayerActivityField.get(this._unityPlayerClass);
		} catch (ClassNotFoundException cnfe) {
			Log.e(TAG, "not able to find UnityPlayer class: " + cnfe.getMessage());
		} catch (NoSuchFieldException nsfe) {
			Log.e(TAG, "not able find currentActivity field: " + nsfe.getMessage());
		} catch (Exception e) {
			Log.e(TAG, "unknown exception during getting activity: " + e.getMessage());
		}
		*/
	}
	
	public static AndroidNotificationManager Me() {
		//Log.i(TAG,"Getting Me");
		if (_me == null) {
			//Log.i(TAG,"Me is not Available, creating new instance");
			_me = new AndroidNotificationManager();
		}
		//Log.i(TAG,"Returning instance");
		return _me;
	}
	
	public static boolean isNullOrEmpty(String s) {
		return ((s == null) || (s.isEmpty()) || (s == "") || (s.length() <= 0));
	}
	
	public void setContext(Context context) {
		Log.i(TAG, "Setting Context for later use");
		this._context = context;
		this._activity = (Activity) this._context;
		Log.i(TAG, "Context Sets");
		manager = (NotificationManager) _context.getSystemService(Context.NOTIFICATION_SERVICE);
	}
	
	protected void scheduleNotification(AndroidNotificationFields anf) {
		Gson gson = new Gson();
		Type t = new TypeToken<AndroidNotificationFields>() {
		}.getType();
		String gSon = gson.toJson(anf, t);
		scheduleNotification(gSon);
	}
	
	public int scheduleNotification(String jsonData) {
		JSONObject jsonObject = null;
		
		int scheduleRequestId;
		//
		long duration;
		//
		String title, message, ticker, smallIcon, largeIcon, category, groupTitle, groupKey,
				otherJSonData, soundFile;
		//
		boolean isGroup, isSound, isVibrate;
		long[] vibrateTone;
		
		try {
			Log.i(TAG, "Getting Data from JSon");
			jsonObject = new JSONObject(jsonData);
			scheduleRequestId = jsonObject.getInt("scheduleRequestId");
			duration = jsonObject.getLong("duration");
			//
			title = jsonObject.getString("title");
			message = jsonObject.getString("message");
			//
			ticker = jsonObject.getString("ticker");
			smallIcon = jsonObject.getString("smallIcon");
			largeIcon = jsonObject.getString("largeIcon");
			//
			category = jsonObject.getString("category");
			//
			isGroup = jsonObject.getBoolean("isGroup");
			groupKey = jsonObject.getString("groupKey");
			groupTitle = jsonObject.getString("groupTitle");
			//
			isSound = jsonObject.getBoolean("isSound");
			isVibrate = jsonObject.getBoolean("isVibrate");
			otherJSonData = jsonObject.getString("otherJSonData");
			soundFile = jsonObject.getString("soundFile");
			if (scheduleRequestId < 0) {
				notId++;
				scheduleRequestId = notId;
			}
			
			Log.i(TAG, "Updating data in Notification Intent");
			Intent intent = new Intent(_context, _activity.getClass());
			intent.putExtra("title", title);
			intent.putExtra("message", message);
			intent.putExtra("ticker", ticker);
			intent.putExtra("isGroup", isGroup);
			intent.putExtra("category", category);
			intent.putExtra("smallIcon", smallIcon);
			intent.putExtra("largeIcon", largeIcon);
			intent.putExtra("groupKey", groupKey);
			intent.putExtra("groupTitle", groupTitle);
			intent.putExtra("otherJSonData", otherJSonData);
			intent.putExtra("isVibrate", isVibrate);
			intent.putExtra("soundFile", soundFile);
			intent.putExtra("isSound", isSound);
			
			Log.i(TAG, "requested vibrate: " + isVibrate);
			Log.i(TAG, "requested sound: " + isSound);
			
			if (scheduleRequestId > 0) {
				intent.putExtra("scheduleRequestId", scheduleRequestId);
			}
			
			
			if (duration > 0) {
				// TODO: Feature is pending to Implements
				Log.i(TAG, "Scheduling Notification");
			} else {
				Log.i(TAG, "Creating Notification");
				createNotification(intent);
				scheduleRequestId = -1;
			}
		} catch (JSONException e) {
			scheduleRequestId = -1;
			e.printStackTrace();
		}
		return scheduleRequestId;
	}
	
	public void createNotification(Intent intent) {
		int smallIconID, largeIconID, nid;
		Uri soundUri;
		String iconLocation, title, message, ticker, smallIcon, largeIcon, groupKey, groupTitle,
				category, soundFile, otherJSonData;
		boolean isGroup, isSound, isVibrate;
		
		Notification notification;
		Notification.Builder builder;
		Notification.InboxStyle nibs;
		Bitmap largeIconBitmap;
		NotificationInfo ni = null;
		Bundle b;
		
		Log.i(TAG, "Reading data from Intent Extras");
		b = intent.getExtras();
		title = b.getString("title");
		message = b.getString("message");
		ticker = b.getString("ticker");
		smallIcon = b.getString("smallIcon");
		largeIcon = b.getString("largeIcon");
		isGroup = b.getBoolean("isGroup");
		groupKey = b.getString("groupKey");
		groupTitle = b.getString("groupTitle");
		category = b.getString("category");
		isVibrate = b.getBoolean("isVibrate");
		isSound = b.getBoolean("sound");
		soundFile = b.getString("soundFile");
		otherJSonData = b.getString("otherJSonData");
		
		if (isNullOrEmpty(groupKey)) {
			groupKey = notificationTAG;
		}
		
		// Icon Settings for notification
		Log.i(TAG, "Creating basic setting for Icon in notification");
		iconLocation = "drawable";
		if (isNullOrEmpty(smallIcon)) {
			smallIcon = "ic_launcher";
		}
		if (isNullOrEmpty(largeIcon)) {
			largeIcon = "ic_launcher";
		}
		smallIconID = _context.getResources().getIdentifier(smallIcon, iconLocation, _context.getPackageName());
		largeIconID = _context.getResources().getIdentifier(largeIcon, iconLocation, _context.getPackageName());
		// Basic icon setting END
		
		
		Log.i(TAG, "creating notification builder");
		builder = new Notification.Builder(_context);
		largeIconBitmap = BitmapFactory.decodeResource(_context.getResources(), largeIconID);
		
		Log.i(TAG, "Allow to Group: " + isGroup + ", with: " + groupKey);
		if (!isNullOrEmpty(groupKey))
			ni = GetStackInfo(groupKey);
		
		if (Build.VERSION.SDK_INT >= 20) {
			builder.setGroup(groupKey)
					.setGroupSummary(false);
		}
		
		if (ni != null && isGroup) {
			nid = ni.id;
			Log.i(TAG, "Grouping notification with old one");
			nibs = new Notification.InboxStyle();
			
			ni.messages.add(message);
			for (int i = 0; i < ni.messages.size(); i++) {
				nibs.addLine(ni.messages.get(i));
			}
			if (!isNullOrEmpty(groupTitle))
				nibs.setBigContentTitle(groupTitle);
			else
				nibs.setBigContentTitle(ni.getTotalMessages() + " " + category);
			
			builder.setStyle(nibs)
					.setNumber(ni.getTotalMessages());
			
			if (Build.VERSION.SDK_INT >= 20) {
				builder.setGroup(groupKey)
						.setGroupSummary(true);
			}
		} else {
			Log.i(TAG, "Creating grouping info for notification");
			nid = notId;
			ni = new NotificationInfo();
			ni.id = nid;
			ni.messages.add(message);
			notificationStack.put(groupKey, ni);
			notId++;
		}
		
		Log.i(TAG, "Setting icons and message in builder");
		builder.setSmallIcon(smallIconID)
				.setLargeIcon(largeIconBitmap)
				.setContentTitle(title)
				.setContentText(message);
		
		Log.i(TAG, "Setting Up ticker info if available");
		if (!isNullOrEmpty(ticker))
			builder.setTicker(ticker);
		
		// Setting to play different sound for Notification
		Log.i(TAG, "Setting up sound info");
		if (isSound && !isNullOrEmpty(soundFile)) {
			File f = new File(soundFile);
			if (f.exists())
				soundUri = Uri.parse(soundFile);
			else
				soundUri = null;
		} else {
			soundUri = null;
		}
		if (soundUri != null)
			builder.setSound(soundUri);
		// Sound Setting END
		
		Log.i(TAG, "Setting up category if available");
		if (Build.VERSION.SDK_INT >= 21 && !isNullOrEmpty(category)) {
			builder.setCategory(category);
		}
		//builder.setExtras(b);
		
		Log.i(TAG, "Creating pending Intent for notification");
		PendingIntent contentIntent = PendingIntent.getActivity(_context, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT);
		builder.setContentIntent(contentIntent);
		
		Log.i(TAG, "Building notification from notification builder");
		notification = builder.build();
		Log.i(TAG, "executing notification");
		manager.notify(notificationTAG, nid, notification);
		Log.i(TAG, "notification executed");
		
		StatusBarNotification[] sbn = null;
		if (Build.VERSION.SDK_INT >= 23) {
			sbn = manager.getActiveNotifications();
			Notification n = sbn[0].getNotification();
		}
	}
	
	public void clearNotifications() {
		manager.cancelAll();
		notificationStack.clear();
	}
	
	public NotificationInfo GetStackInfo(String groupKey) {
		if (notificationStack.containsKey(groupKey)) {
			return notificationStack.get(groupKey);
		}
		return null;
	}
	
	private class NotificationInfo {
		public int id;
		public List<String> messages = new ArrayList<String>();
		public int getTotalMessages() {
			return messages.size();
		}
	}
}
