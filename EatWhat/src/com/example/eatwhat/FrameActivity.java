package com.example.eatwhat;

import android.os.Bundle;
import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.util.Log;
import android.view.Menu;
import android.view.Window;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;

public class FrameActivity extends FragmentActivity {
	private Fragment mFragments[];
	private RadioGroup radioGroup;
	private FragmentManager fragmentManager;
	private FragmentTransaction fragmentTransaction;
	private RadioButton rbtHome,rbtMain,rbtSetting;
	CheckBox cB1;  
	CheckBox cB2;  
	CheckBox cB3; 
	TextView tvmain;
	String type1="中餐";
	String type2="西餐";
	String type3="甜品";
	String type1_s;
	String type2_s;
	String type3_s;
	@Override
	
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_frame);
		mFragments=new Fragment[3];
		rbtHome=(RadioButton)findViewById(R.id.radioHome);
		rbtMain=(RadioButton)findViewById(R.id.radioMain);
		rbtSetting=(RadioButton)findViewById(R.id.radioSetting);
		rbtHome.setBackgroundResource(R.drawable.shape2);
		
		fragmentManager =getSupportFragmentManager();
		mFragments[0]=fragmentManager.findFragmentById(R.id.fragment_home);
		mFragments[1]=fragmentManager.findFragmentById(R.id.fragment_main);
		mFragments[2]=fragmentManager.findFragmentById(R.id.fragment_setting);
		fragmentTransaction=fragmentManager.beginTransaction().hide(mFragments[0]).
				hide(mFragments[1]).hide(mFragments[2]);
		fragmentTransaction.show(mFragments[0]).commit();
		radioGroup=(RadioGroup)findViewById(R.id.bottomGroup);
		radioGroup.setOnCheckedChangeListener(new OnCheckedChangeListener(){
			public void onCheckedChanged(RadioGroup group,int checkedId){
				Log.i("radioGroup","checkId="+checkedId);
				fragmentTransaction=fragmentManager.beginTransaction().hide(mFragments[0]).
						hide(mFragments[1]).hide(mFragments[2]);
				rbtHome.setBackgroundResource(R.drawable.shape1);
				rbtMain.setBackgroundResource(R.drawable.shape1);
				rbtSetting.setBackgroundResource(R.drawable.shape1);
				switch(checkedId){
				case R.id.radioHome:
					cB1=(CheckBox)findViewById(R.id.cB1);
					cB2=(CheckBox)findViewById(R.id.cB2);
					cB3=(CheckBox)findViewById(R.id.cB3);
					SharedPreferences sp = getSharedPreferences("type",Context.MODE_PRIVATE);
					type1_s=sp.getString("type1", type1);
					type2_s=sp.getString("type2", type2);
					type3_s=sp.getString("type3", type3);
					Log.i("spsp", type1_s+" "+type2_s+" "+type3_s);
					cB1.setText(type1_s);
					cB2.setText(type2_s);
					cB3.setText(type3_s);
					tvmain=(TextView)findViewById(R.id.outText);
					tvmain.setText("点击下面按钮，看看今天吃什么。(可以通过勾选来选择自己想吃的类别，不选则视为全选)");
					fragmentTransaction.show(mFragments[0]).commit();
					rbtHome.setBackgroundResource(R.drawable.shape2);
					
					break;
				case R.id.radioMain:
					fragmentTransaction.show(mFragments[1]).commit();
					rbtMain.setBackgroundResource(R.drawable.shape2);
					break;
				case R.id.radioSetting:
					fragmentTransaction.show(mFragments[2]).commit();
					rbtSetting.setBackgroundResource(R.drawable.shape2);
					break;
				default:
					break;
				}
			}
		});
	}
	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.frame, menu);
		return true;
	}

}
