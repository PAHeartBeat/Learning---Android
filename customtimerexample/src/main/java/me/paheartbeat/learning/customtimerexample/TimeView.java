package me.paheartbeat.learning.customtimerexample;

import android.content.Context;
import android.content.res.TypedArray;
import android.icu.text.SimpleDateFormat;
import android.support.annotation.Nullable;
import android.util.AttributeSet;
import android.widget.TextView;

import java.util.Calendar;

/**
 * Created by PAHeartBeat on 06/03/17.
 */

public class TimeView extends TextView {
	String title;
	String format;
	
	public TimeView(Context context) {
		this(context, null);
	}
	
	public TimeView(Context context, @Nullable AttributeSet attrs) {
		//-301177 = com.android.internal.R.attr.textViewStyle
		this(context, attrs, -301177);
	}
	
	public TimeView(Context context, @Nullable AttributeSet attrs, int defStlye) {
		super(context, attrs, defStlye);
		readAttributes(context, attrs);
		setTime();
	}
	
	private void readAttributes(Context context, @Nullable AttributeSet attrs) {
		TypedArray typedArray = context.obtainStyledAttributes(attrs, R.styleable.TimeView);
		int counts = typedArray.getIndexCount();
		try {
			for (int i = 0; i < counts; ++i) {
				int attr = typedArray.getIndex(i);
				// the attr corresponds to the title attribute
				if (attr == R.styleable.TimeView_title) {
					// set the text from the layout
					title = typedArray.getString(attr);
				} else if (attr == R.styleable.TimeView_format) {
					// set the color of the attr "setColor"
					format = typedArray.getString(attr);
				}
			}
		}
		// the recycle() will be executed obligatorily
		finally {
			// for reuse
			typedArray.recycle();
		}
	}
	
	private void setTime() {
		String text="";
		String time="";
		
		if (format == null || format.trim() == "" || format.length() <= 0) {
			format = "hh:mm";
		}
		time = Calendar.getInstance().getTime().toString();
		if(title.trim().length()>0){
			text = title + " " + time;
		}else{
			text = time;
		}
		setText(text);
	}
}
