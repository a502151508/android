package com.example.eatwhat;


import android.app.Fragment;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.LinearLayout;
import android.widget.TextView;

public class settingFragment extends Fragment implements OnClickListener {
	Button btnSet,btnItr;
	CheckBox cb;
	TextView outtv;
	/**
	 * @param args
	 */
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.frame_setting,container);
	}
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		btnSet=(Button)getView().findViewById(R.id.btn_set);
		btnItr=(Button)getView().findViewById(R.id.btn_intro);
		btnSet.setOnClickListener(this);
		btnItr.setOnClickListener(this);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void onClick(View btn) {
		if(btn.getId()==R.id.btn_set)
		{
		Intent it = new Intent(getActivity(),SettingActivity.class);
		startActivityForResult(it,1);
		}
		if(btn.getId()==R.id.btn_intro){
			outtv=(TextView)getView().findViewById(R.id.TI);
			outtv.setText("本着为了解决每天的大事（到底吃什么）的初衷，就有了这个APP的诞生，基本介绍一下功能  首页可以选择你今天想吃的类别并点击按钮进行随机选择符合条件的餐厅，" +
			"在功能页面可以对餐厅情况进行编辑 而在设置页面则可以对餐厅的类别名称进行自定义。界面简陋（因本人没有设计感）功能凑合~");
		}

}
}
