package me.paheartbeat.learning.intentfilterexample;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Intent;

public class MainActivity extends Activity {
	Button openBrowser, openBrowserLaunchOption, exceptionCase;
	EditText siteAddress;


	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bindControls();
		bindEvents();
	}

	private void bindControls() {
		openBrowser = (Button) findViewById(R.id.btnOpenBrowser);
		openBrowserLaunchOption = (Button) findViewById(R.id.btnOpenBrowserWithLaunchOption);
		exceptionCase = (Button) findViewById(R.id.btnExceptionCase);

		siteAddress = (EditText) findViewById(R.id.txtDomain);
	}

	private void bindEvents() {
		openBrowser.setOnClickListener(new openBrowserEvetnListener());
		openBrowserLaunchOption.setOnClickListener(new openBrowserLaunchOptionEvetnListener());
		exceptionCase.setOnClickListener(new exceptionCaseEvetnListener());
	}

	private class openBrowserEvetnListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent next = new Intent(Intent.ACTION_VIEW);
			next.setData(Uri.parse("http://www.google.com"));
			//next.setData(Uri.parse(siteAddress.getText().toString()));
			startActivity(next);
		}
	}

	private class openBrowserLaunchOptionEvetnListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent next = new Intent("me.paheartbeat.learning.intentfilterexample.LAUNCH");
			next.setData(Uri.parse("http://www.google.com"));
			//next.setData(Uri.parse(siteAddress.getText().toString()));
			startActivity(next);

		}
	}

	private class exceptionCaseEvetnListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent next = new Intent("me.paheartbeat.learning.intentfilterexample.LAUNCH");
			next.setData(Uri.parse("https://www.google.com"));
			//next.setData(Uri.parse(siteAddress.getText().toString()));
			startActivity(next);
		}
	}


}
