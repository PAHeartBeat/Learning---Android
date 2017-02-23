package me.paheratbeat.intentfilterexample;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.MenuItem;
import android.widget.TextView;

public class CustomBrowserActivity extends Activity {

	TextView addressBar;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_custom_browser);

		activeButtonInActionBar();

		bindControls();
		bindEvntes();

		getIntentDataNUpdateControl();
	}

	private void activeButtonInActionBar() {
		getActionBar().setHomeButtonEnabled(true);
		getActionBar().setDisplayHomeAsUpEnabled(true);
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
			case android.R.id.home:
				// app icon in action bar clicked; goto parent activity.
				this.finish();
				return true;
			default:
				return super.onOptionsItemSelected(item);
		}
	}

	private void getIntentDataNUpdateControl() {
		Uri uri = getIntent().getData();
		addressBar.setText(uri.toString());
	}

	private void bindControls() {
		addressBar = (TextView) findViewById(R.id.tvAddressBar);
	}

	private void bindEvntes() {

	}
}
