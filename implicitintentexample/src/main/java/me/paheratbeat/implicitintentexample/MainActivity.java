package me.paheratbeat.implicitintentexample;

import android.app.Activity;
import android.app.SearchManager;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends Activity {

	Button browserButton;
	Button callButton;
	Button searchButton;
	TextView searchContentTextView;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bindControls();
		bindEvents();
	}

	private void bindControls() {
		browserButton = (Button) findViewById(R.id.btnStartBrowser);
		callButton = (Button) findViewById(R.id.btnShowPhone);
		searchButton = (Button) findViewById(R.id.btnSearch);
		searchContentTextView = (TextView) findViewById(R.id.txtSearch);
	}

	private void bindEvents() {
		browserButton.setOnClickListener(new browserButtonEventListener());
		callButton.setOnClickListener(new callButtonEventListener());
		searchButton.setOnClickListener(new callSearchEventListener());
	}


	private class browserButtonEventListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("http://www.google.com"));
			startActivity(i);
		}
	}

	private class callButtonEventListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(android.content.Intent.ACTION_VIEW, Uri.parse("tel:+91851168400"));
			startActivity(i);
		}
	}

	private class callSearchEventListener implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			Intent i = new Intent(Intent.ACTION_WEB_SEARCH);
			i.putExtra(SearchManager.QUERY, searchContentTextView.getText().toString());
			startActivity(i);
		}
	}

}
