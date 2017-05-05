package com.example.eatwhat;



import android.content.Intent;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class mainFragment extends android.app.Fragment implements OnClickListener {

	/**
	 * @param args
	 */
	Button btnList,btnAdd,btnClr;
	
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.frame_main,container);
	}
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		TextView tv=(TextView)getView().findViewById(R.id.mainTextView1);
		tv.setText("功能界面，你可以在这儿查看你的美食。在列表中点击单项可单项删除。");
		btnList=(Button)getView().findViewById(R.id.btn_list);
		btnList.setOnClickListener(this);
		btnAdd=(Button)getView().findViewById(R.id.btn_ran);
		btnAdd.setOnClickListener(this);
		btnClr=(Button)getView().findViewById(R.id.btn_clr);
		btnClr.setOnClickListener(this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void onClick(View btn) {
		// TODO Auto-generated method stub
		if(btn.getId()==R.id.btn_list){
		Intent listIntent=new Intent(getActivity(),FoodListActivity.class);
		startActivity(listIntent);}
		else if(btn.getId()==R.id.btn_ran){
			Intent configIntent = new Intent(getActivity(),AddActivity.class);
			startActivity(configIntent);
		}
		else if(btn.getId()==R.id.btn_clr){
			DBManager dbManager=new DBManager(getActivity());
			dbManager.deleteAll();
			Toast.makeText(getActivity(),"列表已清空", Toast.LENGTH_SHORT).show();
		}
	}

}
