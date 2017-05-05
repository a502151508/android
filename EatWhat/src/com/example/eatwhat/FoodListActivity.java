package com.example.eatwhat;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;




import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.app.Activity;
import android.app.AlertDialog;
import android.app.ListActivity;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.Window;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.TextView;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class FoodListActivity extends ListActivity implements Runnable ,OnItemClickListener {
	private ArrayList<HashMap<String,String>> listItems;
	private SimpleAdapter listItemAdapter;
	private String[] data={"尚未添加任何美食，赶快添加吧"};
	Handler handler;
	ListView myListView;
	int msgWhat =3;
	List<String> nameList=new ArrayList<String>();
	List<Integer> typeList=new ArrayList<Integer>();
	public  String getRealType1(FoodItem foodItem,SharedPreferences sp){
		sp = getSharedPreferences("type", Context.MODE_PRIVATE);
		if(foodItem.getFoodType()==1)
		return sp.getString("type1","中餐").toString();
		if(foodItem.getFoodType()==2)
			return sp.getString("type2","西餐").toString();
		if(foodItem.getFoodType()==3)
			return sp.getString("type3","甜品").toString();
		return "设置类型出错，请检查。";
		
}
public  String getRealType2(FoodItem foodItem,String t1,String t2,String t3){
	if(foodItem.getFoodType()==1)
	return t1;
	if(foodItem.getFoodType()==2)
		return t2;
	if(foodItem.getFoodType()==3)
		return t3;
	return "设置类型出错，请检查。";
	}
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
//		setContentView(R.layout.activity_food_list);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		ListAdapter adapter=new ArrayAdapter<String>(FoodListActivity.this,android.R.layout.simple_list_item_1,data);
		setListAdapter(adapter);
		myListView=getListView();
		
//		Thread t =new Thread(this);
//		t.start();
		Log.i("run","full retList");
		List<String> retList=new ArrayList<String>();
		DBManager dbManager=new DBManager(FoodListActivity.this);
		for(FoodItem foodItem:dbManager.listAll()){
			SharedPreferences sp = getSharedPreferences("type",Context.MODE_PRIVATE);
			retList.add(foodItem.getFoodName()+" >>> "+getRealType1(foodItem, sp));
			nameList.add(foodItem.getFoodName());
		
		}
		
		ListAdapter adapter1=new ArrayAdapter<String>
		(FoodListActivity.this,android.R.layout.simple_list_item_1,retList);
		setListAdapter(adapter1);
		if(retList.isEmpty())
			setListAdapter(adapter);
		myListView.setOnItemClickListener(this);
		Log.i("handler","get list data");
				
	
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.food_list, menu);
		return true;
	}

	public void run() {
		// TODO Auto-generated method stub
		Log.i("run","Thread t start running.......");
		List<String> retList=new ArrayList<String>();
		Message msg=handler.obtainMessage();
		DBManager dbManager=new DBManager(FoodListActivity.this);
		for(FoodItem foodItem:dbManager.listAll()){
			SharedPreferences sp = getSharedPreferences("type",Context.MODE_PRIVATE);
			retList.add(foodItem.getFoodName()+" >>> "+getRealType1(foodItem, sp));
		msg.obj=retList;
		msg.what=msgWhat;
		handler.sendMessage(msg);
		}
		
	}
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		// TODO Auto-generated method stub
		TextView item=(TextView)arg1;
		if(item.getText().equals("尚未添加任何美食，赶快添加吧")){
			
		}
		else{
	Log.i("ontiem", " "+nameList.get(arg2));
		final String name=nameList.get(arg2);
		new AlertDialog.Builder(FoodListActivity.this).setTitle("Delete").setMessage("是否要删除该餐厅信息？")
		.setPositiveButton("是", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			DBManager dbm=new DBManager(FoodListActivity.this);
			dbm.deleteByItem(name);
			Intent configIntent = new Intent(FoodListActivity.this,FoodListActivity.class);
			startActivity(configIntent);
		}})
		.setNegativeButton("否",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				
			}})
		.show();
	}
}}
