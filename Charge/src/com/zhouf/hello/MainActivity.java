package com.zhouf.hello;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{

	EditText inputName = null;
	TextView outText = null;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		inputName = (EditText)findViewById(R.id.editName);
		outText = (TextView)findViewById(R.id.textHello);
		Button btn = (Button)findViewById(R.id.btnSay);
		btn.setOnClickListener(this);
		Button btnBye = (Button)findViewById(R.id.btnBye);
		btnBye.setOnClickListener(this);
		Button btnOpen = (Button)findViewById(R.id.btnOpenRate);
		btnOpen.setOnClickListener(this);
	}

	@Override
	public void onClick(View btn) {
		String strName = inputName.getText().toString();
		if(btn.getId()==R.id.btnSay){
			outText.setText("Hello " + strName);
		}else if(btn.getId()==R.id.btnBye){
			outText.setText("Bye " + strName);
		}else if(btn.getId()==R.id.btnOpenRate){
			Intent rateIntent = new Intent(MainActivity.this,RateActivity.class);
			startActivity(rateIntent);
		}
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}


}
