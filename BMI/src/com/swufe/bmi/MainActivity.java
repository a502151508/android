package com.swufe.bmi;

import com.swufe.bmi.R;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends Activity implements OnClickListener{
	EditText ET1;
	EditText ET2;
	TextView out;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		ET1=(EditText)findViewById(R.id.ET1);
		ET2=(EditText)findViewById(R.id.ET2);
		out = (TextView)findViewById(R.id.viewOut);
		Button btn=(Button)findViewById(R.id.button1);
		btn.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String str="";double h,g,bmi;
		h=Double.parseDouble(ET1.getText().toString());
		g=Double.parseDouble(ET2.getText().toString());
		bmi=g/h/h;
		str=String.valueOf(bmi);
		out.setText(str);
	}

}
