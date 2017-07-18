package me.paheartbeat.learning.localnotification;

/**
 * Created by PAHeartBeat on 02/03/17.
 */

public class AndroidNotificationFields {
	public int scheduleRequestId;
	//
	public long duration;
	//
	public String title = "";
	public String message = "";
	//
	public String ticker = "";
	public String smallIcon = "";
	public String largeIcon = "";
	//
	public String category = "";
	//
	public boolean isGroup = true;
	public String groupTitle = "";
	public String groupKey = "";
	
	//
	public String otherJSonData= "";
	//
	public boolean isSound = true;
	public boolean isVibrate = true;
	public String soundFile="";
	public long[] vibrateTone;
}
