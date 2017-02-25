package me.paheartbeat.learning.explicitintentexample;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

public class ContactInfoActivity extends Activity {

	Button openSocialInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_contact_info);
		activeButtonsInActionBar();

		bindControls();
		bindEvents();
	}

	private void bindControls() {
		openSocialInfo = (Button) findViewById(R.id.btnOpenSocialInfo);
	}

	private void bindEvents() {
		openSocialInfo.setOnClickListener(new socialButtonEventListener());
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

	private class socialButtonEventListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent next = new Intent(ContactInfoActivity.this, SocialInfoActivity.class);
			startActivity(next);
		}
	}

}
