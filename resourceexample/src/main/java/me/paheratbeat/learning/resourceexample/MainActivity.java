package me.paheartbeat.learning.resourceexample;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.*;

public class MainActivity extends AppCompatActivity {

	TextView msgTextView;
	Button btnMain;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bindControl();
		msgTextView.setText(R.string.hello);
	}

	private void bindControl() {
		msgTextView = (TextView) findViewById(R.id.text);
		btnMain = (Button) findViewById(R.id.button);
	}
}
