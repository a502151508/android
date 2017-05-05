package com.zhouf.hello;


import java.io.IOException;
import java.io.InputStream;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;

//import org.apache.commons.io.IOUtils;

import android.app.Activity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class RateActivity extends Activity implements OnClickListener,Runnable{

	EditText editTextRMB;
	EditText editTextOther;
	
	float dollarRate = 0f;
	float euroRate = 0f;
	float wonRate = 0f;
	Handler handler;
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_rate);
		SharedPreferences shared= getSharedPreferences("rate", MODE_PRIVATE);
		dollarRate=shared.getFloat("dollar-rate",10f);
		euroRate=shared.getFloat("euro-rate",100f);
		wonRate=shared.getFloat("won-rate",1000f);
		editTextRMB = (EditText)findViewById(R.id.editRMB);
		editTextOther = (EditText)findViewById(R.id.editOther);
		Button btnDollar = (Button)findViewById(R.id.btnDollar);
		Button btnEuro = (Button)findViewById(R.id.btnEuro);
		Button btnWon = (Button)findViewById(R.id.btnWon);
		Button btnOpenCfg = (Button)findViewById(R.id.btnOpenConfig);
		
		btnDollar.setOnClickListener(this);
		btnEuro.setOnClickListener(this);
		btnWon.setOnClickListener(this);
		btnOpenCfg.setOnClickListener(this);
		Thread t=new Thread(this);
		t.start();
		handler =new Handler(){
			public void handleMessage(Message msg)
			{
				if(msg.what==3)
				{
					Log.i("handler","get message");
					String getStr=(String)msg.obj;
					Log.i("handler","get Str    "+getStr);
					
				}
				super.handleMessage(msg);
				
			}};
	
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.rate, menu);
		return true;
	}
	
	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		if(item.getItemId()==R.id.rate_settings){
			Intent configIntent = new Intent(RateActivity.this,RateConfigActivity.class);
			configIntent.putExtra("key_dollar_rate", dollarRate);
			configIntent.putExtra("key_euro_rate", euroRate);
			configIntent.putExtra("key_won_rate", wonRate);
			//startActivity(configIntent);
			startActivityForResult(configIntent,1);
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View btn) {
		String strRmb = editTextRMB.getText().toString();
		float rmb = 0.0f;
		float other = 0.0f;
		
		if(strRmb!=null && strRmb.length()>0){
			rmb = Float.parseFloat(strRmb);
		}
		
		if(btn.getId()==R.id.btnDollar){
			other = rmb * dollarRate;
			editTextOther.setText(other + "美元");
		}else if(btn.getId()==R.id.btnEuro){
			other = rmb * euroRate;
			editTextOther.setText(other + "欧元");
		}else if(btn.getId()==R.id.btnWon){
			other = rmb * wonRate;
			editTextOther.setText(other + "韩元");
		}else if(btn.getId()==R.id.btnOpenConfig){
			Intent configIntent = new Intent(RateActivity.this,RateConfigActivity.class);
			configIntent.putExtra("key_dollar_rate", dollarRate);
			configIntent.putExtra("key_euro_rate", euroRate);
			configIntent.putExtra("key_won_rate", wonRate);

			startActivityForResult(configIntent,1);
		}
		
	}
	protected void onActivityResult(int requestCode,int resultCode,Intent data)
	{
		if(requestCode==1&&resultCode==2)
		{
			Bundle bdl= new Bundle();
			bdl=data.getExtras();
			dollarRate = bdl.getFloat("key_ret_dollar");
			euroRate = bdl.getFloat("key_ret_euro");
			wonRate = bdl.getFloat("key_ret_won");
		}
		super.onActivityResult(requestCode, resultCode, data);
	}

	@Override
	public void run() {
		try {
			URL url=new URL("http://www.usd-cny.com/bankofchina.htm");
			HttpURLConnection http=(HttpURLConnection)url.openConnection();
			InputStream is=http.getInputStream();
			Log.i("run", "get http data is="+is);
//			String getWebStr=IOUtils.toString(is,"gb2312");
//			Log.i("run", "get WWW Str  "+getWebStr);
		} catch (MalformedURLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		Message msg=handler.obtainMessage();
		msg.what=3;
		msg.obj="str from run()";
		handler.sendMessage(msg);
	}
}
