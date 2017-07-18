package me.paheartbeat.learning.dragdropexample;

import android.app.Activity;
import android.content.ClipData;
import android.content.ClipDescription;
import android.graphics.Point;
import android.os.Bundle;
import android.util.Log;
import android.view.DragEvent;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageButton;
import android.widget.RelativeLayout;


public class MainActivity extends Activity {

	public static final String TAG = "DragDrop";
	public static String IMAGE_DRAG_DROP_TAG = "imgDragDrop";

	RelativeLayout.LayoutParams layoutParams;
	ImageButton imgLogo;
	ImageEventsHandler imageEventsHandler = new ImageEventsHandler();

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bindControls();
		bindEvents();
	}

	void bindControls() {
		imgLogo = (ImageButton) findViewById(R.id.ibLogo);
		imgLogo.setTag(IMAGE_DRAG_DROP_TAG);
	}

	void bindEvents() {
		imgLogo.setOnLongClickListener(imageEventsHandler);
		imgLogo.setOnDragListener(imageEventsHandler);
		imgLogo.setOnTouchListener(imageEventsHandler);
	}

	private class ImageEventsHandler implements
			View.OnTouchListener,
			View.OnLongClickListener,
			View.OnDragListener {

		int x_cord, y_cord, x, y;
		Point offset = new Point(0, 0);

		@Override
		public boolean onTouch(View v, MotionEvent me) {
			if (me.getAction() == MotionEvent.ACTION_DOWN) {
				Log.i(TAG, "Motion Action Down Called");
				layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
				x_cord = (int) me.getX();
				y_cord = (int) me.getY();

				x = x_cord;
				y = y_cord;
				//x = x_cord - layoutParams.leftMargin;
				//y = y_cord - layoutParams.topMargin;
				//x = Math.abs(x);
				//y = Math.abs(y);

				offset = new Point(x, y);
				Log.i(TAG, "On Touch: " + x_cord + ", " + y_cord + " - " + layoutParams.leftMargin
						+ ", " + layoutParams.topMargin + " - " + offset.toString());
				//return true;
				ClipData data = ClipData.newPlainText("", "");
				View.DragShadowBuilder shadowBuilder = new CustomDragShadowBuilder(v);

				v.startDrag(data, shadowBuilder, v, 0);
				//v.setVisibility(View.INVISIBLE);
				v.invalidate();
				return true;
			} else {
				return false;
			}
		}

		@Override
		public boolean onLongClick(View v) {
			Log.i(TAG, "On Long Click");
			ClipData.Item item = new ClipData.Item((CharSequence) v.getTag());
			String[] mimeTypes = {ClipDescription.MIMETYPE_TEXT_PLAIN};

			ClipData dragData = new ClipData(v.getTag().toString(), mimeTypes, item);
			View.DragShadowBuilder myShadow = new CustomDragShadowBuilder(v, offset);
			v.startDrag(dragData, myShadow, null, 0);
			v.invalidate();
			return true;
		}

		@Override
		public boolean onDrag(View v, DragEvent event) {
			switch (event.getAction()) {
				case DragEvent.ACTION_DRAG_STARTED:
					layoutParams = (RelativeLayout.LayoutParams) v.getLayoutParams();
					Log.i(TAG, "On Start: " + x_cord + ", " + y_cord + " - " + layoutParams.leftMargin
							+ ", " + layoutParams.topMargin + " - " + offset.toString());
					Log.i(TAG, "Action is DragEvent.ACTION_DRAG_STARTED");
					break;

				case DragEvent.ACTION_DRAG_ENTERED:
					Log.i(TAG, "Action is DragEvent.ACTION_DRAG_ENTERED");
					break;

				case DragEvent.ACTION_DRAG_EXITED:
					Log.d(TAG, "Action is DragEvent.ACTION_DRAG_EXITED");
					break;

				case DragEvent.ACTION_DRAG_LOCATION:
					//Log.i(TAG, "Action is DragEvent.ACTION_DRAG_LOCATION");
					break;

				case DragEvent.ACTION_DRAG_ENDED:
					Log.i(TAG, "Action is DragEvent.ACTION_DRAG_ENDED");
					// Do nothing
					break;

				case DragEvent.ACTION_DROP:
					Log.i(TAG, "Action is DragEvent.ACTION_DROP");
					x_cord = (int) event.getX();
					y_cord = (int) event.getY();

					layoutParams.leftMargin = x_cord - offset.x;
					layoutParams.topMargin = y_cord - offset.y;
					Log.i(TAG, "On Drop: " + x_cord + ", " + y_cord + " - " + layoutParams.leftMargin
							+ ", " + layoutParams.topMargin + " - " + offset.toString());

					v.setLayoutParams(layoutParams);
					v.invalidate();
					v.setVisibility(View.VISIBLE);
					break;
				default:
					break;
			}
			return true;
		}

	}

	public class CustomDragShadowBuilder extends View.DragShadowBuilder {
		// Private attributes:
		private Point _offset, t;
		private int x, y;
		//private Drawable shadow;

		// ------------------------------------------------------------------------------------------


		// Constructor :
		public CustomDragShadowBuilder(View view) {
			this(view, new Point(0, 0));
		}

		public CustomDragShadowBuilder(View view, Point offset) {
			// Stores the View parameter passed to myDragShadowBuilder.
			super(view);
			// Save the offset :
			_offset = offset;
			// Creates a draggable image that will fill the Canvas provided by the system.
			//shadow = new ColorDrawable(Color.LTGRAY);
		}
		// ------------------------------------------------------------------------------------------

		// Defines a callback that sends the drag shadow dimensions and touch point back to the system.
		@Override
		public void onProvideShadowMetrics(Point size, Point touch) {
			// Set the shadow size :
			Log.i(TAG, "Setting up Size");
			size.set(getView().getWidth(), getView().getHeight());

			// Sets the touch point's update with offset of touch down and view position
			x = touch.x;
			y = touch.y;
			t = new Point(x + _offset.x, y + _offset.y);
			Log.i(TAG, "Setting up Touch: (" + x + ", " + y + ") os: " + _offset.toString() +
					" np: " + t.toString());
			touch.set(_offset.x, _offset.y);
		}
		// ------------------------------------------------------------------------------------------
	}
}
