package me.paheratbeat.explicitintentexample;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.content.Intent;

public class MainActivity extends Activity {

	Button contactInfo;
	Button socialInfo;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bindControls();
		bindEvents();
	}


	private void bindControls() {
		contactInfo = (Button) findViewById(R.id.btnOpenContactInfo);
		socialInfo = (Button) findViewById(R.id.btnOpenSocialInfo);
	}

	private void bindEvents() {
		contactInfo.setOnClickListener(new contactButtonEventListener());
		socialInfo.setOnClickListener(new socialsButtonEventListener());
	}


	private class contactButtonEventListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent next = new Intent(MainActivity.this, ContactInfoActivity.class);
			startActivity(next);
		}
	}

	private class socialsButtonEventListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent next = new Intent(MainActivity.this, SocialInfoActivity.class);
			startActivity(next);
		}
	}

}
