package me.paheratbeat.explicitintentexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class SocialInfoActivity extends Activity {

	Button openContactInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_social_info);

		activeButtonsInActionBar();

		bindControls();
		bindEvents();
	}

	private void bindControls() {
		openContactInfo = (Button) findViewById(R.id.btnOpenContactInfo);
	}

	private void bindEvents() {
		openContactInfo.setOnClickListener(new contactButtonEventListener());
	}

	private void activeButtonsInActionBar() {
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

	private class contactButtonEventListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent next = new Intent(SocialInfoActivity.this, ContactInfoActivity.class);
			startActivity(next);
		}
	}

}
