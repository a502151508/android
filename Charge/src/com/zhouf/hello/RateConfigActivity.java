package com.zhouf.hello;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RateConfigActivity extends Activity {

	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rate_config);
		float dollarRate = getIntent().getFloatExtra("key_dollar_rate", 10f);
		float euroRate = getIntent().getFloatExtra("key_euro_rate", 10f);
		float wonRate = getIntent().getFloatExtra("key_won_rate", 10f);
		//
		final EditText dollarEditText = (EditText)findViewById(R.id.editCfgDollar);
		final EditText euroEditText = (EditText)findViewById(R.id.editCfgEuro);
		final EditText wonEditText = (EditText)findViewById(R.id.editCfgWon);
		SharedPreferences sp=getSharedPreferences("rate", MODE_PRIVATE);
		Editor editor=sp.edit();
		editor.putFloat("dollar-rate",dollarRate);
		editor.putFloat("euro-rate",euroRate);
		editor.putFloat("won-rate",wonRate);
		editor.commit();
		dollarEditText.setText(String.valueOf(dollarRate));
		euroEditText.setText(String.valueOf(euroRate));
		wonEditText.setText(String.valueOf(wonRate));
		Button saveBtn = (Button)findViewById(R.id.btnSaveConfig);
		saveBtn.setOnClickListener(new OnClickListener(){
			@Override
			public void onClick(View arg0) {
				// TODO Auto-generated method stub
				float newDollarRate=Float.parseFloat(dollarEditText.getText().toString());
				float newEuroRate=Float.parseFloat(euroEditText.getText().toString());
				float newWonRate=Float.parseFloat(wonEditText.getText().toString());
				Log.i("1",String.valueOf(newDollarRate));
				Log.i("1",String.valueOf(newEuroRate));
				Log.i("1",String.valueOf(newWonRate));
				Bundle bdl= new Bundle();
				bdl.putFloat("key_ret_dollar",newDollarRate);
				bdl.putFloat("key_ret_euro",newEuroRate);
				bdl.putFloat("key_ret_won",newWonRate);
				Intent retIntent=getIntent();
				retIntent.putExtras(bdl);
				setResult(2,retIntent);
				finish();
			}});
		
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		//getMenuInflater().inflate(R.menu.rate_config, menu);
		return false;
	}

}
