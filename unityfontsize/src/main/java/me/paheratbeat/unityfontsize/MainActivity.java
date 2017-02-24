package me.paheratbeat.unityfontsize;

import android.app.Activity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends Activity {
	private final static int POINT_SIZE = 72;

	Button btnCalculate;
	EditText txtDesignDP, txtDesignFontPT,txtUnityCamSize,txtBGHeight;
	TextView tvUnityFontSizePX, tvPxlPUnityUnit;

	public static boolean tryParseInt(String value) {
		try {
			Integer.parseInt(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}
	public static boolean tryParseFloat(String value) {
		try {
			Float.parseFloat(value);
			return true;
		} catch (NumberFormatException e) {
			return false;
		}
	}

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);

		bindControl();
		bindEvents();
	}

	private void bindControl() {
		txtDesignDP = (EditText) findViewById(R.id.txtDesignDP);
		txtDesignFontPT = (EditText) findViewById(R.id.txtFontSizPT);
		txtUnityCamSize = (EditText) findViewById(R.id.txtUnityCamSize);
		txtBGHeight = (EditText) findViewById(R.id.txtDesignBGSize);

		btnCalculate = (Button) findViewById(R.id.btnCalculate);

		tvUnityFontSizePX = (TextView) findViewById(R.id.tvFontSizeUnityResult);
		tvPxlPUnityUnit = (TextView) findViewById(R.id.tvPxlPUnitResult);
	}

	private void bindEvents() {
		btnCalculate.setOnClickListener(new btnCalculateEventListner());
	}

	private void showErrorTost(String message) {
		Toast.makeText(this, message, Toast.LENGTH_SHORT).show();
	}

	private class btnCalculateEventListner implements View.OnClickListener {
		@Override
		public void onClick(View v) {
			String strDPI = txtDesignDP.getText().toString();
			String strFontPT = txtDesignFontPT.getText().toString();
			String strCamSize = txtUnityCamSize.getText().toString();
			String strBGSize = txtBGHeight.getText().toString();

			int dpi = 0, fontPT = 0;
			int fontPX = 0;
			float camSize,bgSize, pxlPUnit;
			if (tryParseInt(strDPI)) {
				dpi = Integer.parseInt(strDPI);
			} else {
				showErrorTost("Design DPI value is invalid");
				return;
			}

			if (tryParseInt(strFontPT)) {
				fontPT = Integer.parseInt(strFontPT);
			} else {
				showErrorTost("Design Font Size is invalid");
				return;
			}

			fontPX = (fontPT * dpi) / POINT_SIZE;
			tvUnityFontSizePX.setText(String.valueOf(fontPX));


			if (tryParseFloat(strCamSize)) {
				camSize = Float.parseFloat(strCamSize);
			} else {
				showErrorTost("Camera size is invalid");
				return;
			}

			if (tryParseInt(strBGSize)) {
				bgSize = (float)Integer.parseInt(strBGSize);
			} else {
				showErrorTost("Design background height is invalid");
				return;
			}
			pxlPUnit = bgSize/(camSize*2f);
			tvPxlPUnityUnit.setText(String.valueOf(pxlPUnit));
		}
	}
}
