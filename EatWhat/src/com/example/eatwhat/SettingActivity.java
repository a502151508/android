package com.example.eatwhat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.SharedPreferences.Editor;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class SettingActivity extends Activity implements OnClickListener {
	EditText et1;
	EditText et2;
	EditText et3;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_setting);
		
		et1 = (EditText)findViewById(R.id.text_t1);
		et2= (EditText)findViewById(R.id.text_t2);
		et3= (EditText)findViewById(R.id.text_t3);
		SharedPreferences sp = getSharedPreferences("type", Context.MODE_PRIVATE);
		et1.setText(sp.getString("type1", "中餐"));
		et2.setText(sp.getString("type2", "西餐"));
		et3.setText(sp.getString("type3", "甜品"));
		Button btnSave = (Button)findViewById(R.id.btn_sav);
		btnSave.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.setting, menu);
		return true;
	}

	public void onClick(View btn) {
		// TODO Auto-generated method stub
		Intent intent = getIntent();
		SharedPreferences sp = getSharedPreferences("type", Context.MODE_PRIVATE);
		Editor edit = sp.edit();
		String t1=et1.getText().toString();
		String t2=et2.getText().toString();
		String t3=et3.getText().toString();
		edit.putString("type1", t1);
		edit.putString("type2", t2);
		edit.putString("type3", t3);
		edit.commit();
		DBManager dbm=new DBManager(SettingActivity.this);
		dbm.deleteAll();
		Toast.makeText(this,"修改成功,列表已重置",Toast.LENGTH_SHORT).show();
		Log.i("sav type",t1+" "+t2+" "+t3);
		setResult(2,intent);
		finish();
		
		
	}

}
