package com.example.eatwhat;



import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.SharedPreferences;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.Toast;

public class AddActivity extends Activity implements OnClickListener  {
	public  String getRealType1(FoodItem foodItem,SharedPreferences sp){
		sp = getSharedPreferences("type", Context.MODE_PRIVATE);
		if(foodItem.getFoodType()==1)
		return sp.getString("type1","�в�").toString();
		if(foodItem.getFoodType()==2)
			return sp.getString("type2","����").toString();
		if(foodItem.getFoodType()==3)
			return sp.getString("type3","��Ʒ").toString();
		return "�������ͳ������顣";
		
}
public  String getRealType2(FoodItem foodItem,String t1,String t2,String t3){
	if(foodItem.getFoodType()==1)
	return t1;
	if(foodItem.getFoodType()==2)
		return t2;
	if(foodItem.getFoodType()==3)
		return t3;
	return "�������ͳ������顣";
}
public  String getRealType3(int i,SharedPreferences sp){
	
	if(i==1)
	return sp.getString("type1", "�в�");
	if(i==2)
		return sp.getString("type2", "����");
	if(i==3)
		return sp.getString("type3", "��Ʒ");
	return "�������ͳ������顣";
}
	private RadioGroup group; 
	private RadioButton rb1 ; 
	private RadioButton rb2 ;
	private RadioButton rb3 ;
	EditText foodName=null;
	int foodType=0;
	@Override
	protected void onCreate(Bundle savedInstanceState) {

		super.onCreate(savedInstanceState);
		this.requestWindowFeature(Window.FEATURE_NO_TITLE);
		setContentView(R.layout.activity_add);
		Button btnAdd =(Button)findViewById(R.id.btn_config_add);
		btnAdd.setOnClickListener(this);
		foodName=(EditText)findViewById(R.id.et_food_name);
		group = (RadioGroup)findViewById(R.id.bottomGroup); 
		this.rb1 = (RadioButton) this.findViewById(R.id.radioButton1) ; 
		this.rb2 = (RadioButton) this.findViewById(R.id.radioButton2) ;
		this.rb3 = (RadioButton) this.findViewById(R.id.radioButton3) ;
		SharedPreferences sp = getSharedPreferences("type",Context.MODE_PRIVATE);
		rb1.setText(getRealType3( 1, sp));
		rb2.setText(getRealType3( 2, sp));
		rb3.setText(getRealType3( 3, sp));
			 
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.add, menu);
		return true;
	}

	public void onClick(View arg0) {
		// TODO Auto-generated method stub
		String name=foodName.getText().toString();
		Log.i("add", "name "+name);
		DBManager dbm=new DBManager(AddActivity.this);
		
		if(rb1.isChecked())
			foodType=1;
		if(rb2.isChecked())
			foodType=2;
		if(rb3.isChecked())
			foodType=3;
		FoodItem foodItem=new FoodItem(name,foodType);
		if(foodName.length()==0){
		Log.i("add", "����Ϊ�գ����ʧ��");
		Toast.makeText(AddActivity.this,"����Ϊ�գ����ʧ��������" ,Toast.LENGTH_SHORT ).show();
		rb1.setChecked(false);
		rb2.setChecked(false);
		rb3.setChecked(false);
		}
		else{
		dbm.add(foodItem);
		Log.i("add", "foodname"+name+"  type"+foodType);
		new AlertDialog.Builder(this).setTitle("��ӳɹ���").setMessage("�Ƿ�鿴�����б�")
		.setPositiveButton("ȷ��", new DialogInterface.OnClickListener() {
		public void onClick(DialogInterface dialog, int which) {
			Intent configIntent = new Intent(AddActivity.this,FoodListActivity.class);
			startActivity(configIntent);
		}})
		.setNegativeButton("�������",new DialogInterface.OnClickListener() {
			public void onClick(DialogInterface dialog, int which) {
				foodName.setText("");
				rb1.setChecked(false);
				rb2.setChecked(false);
				rb3.setChecked(false);
			}})
		.show();
		
	}

}}
