package com.example.eatwhat;

import android.content.Context;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.RadioGroup;
import android.widget.RadioGroup.OnCheckedChangeListener;
import android.widget.TextView;
import android.widget.Toast;

public class homeFragment extends Fragment  implements OnClickListener {

	/**
	 * @param args
	 */
	TextView outText =null;
	Button btnRan;
	CheckBox cB1;  
	CheckBox cB2;  
	CheckBox cB3; 
	String type1="中餐";
	String type2="西餐";
	String type3="甜品";
	String type1_s;
	String type2_s;
	String type3_s;
	public  String getRealType1(FoodItem foodItem,SharedPreferences sp){
		sp = getActivity().getSharedPreferences("type", Context.MODE_PRIVATE);
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
	public View onCreateView(LayoutInflater inflater,ViewGroup container,Bundle savedInstanceState)
	{
		return inflater.inflate(R.layout.frame_homepage,container);
	}
	public void onActivityCreated(Bundle savedInstanceState){
		super.onActivityCreated(savedInstanceState);
		btnRan=(Button)getView().findViewById(R.id.btn_ran);
		outText=(TextView)getView().findViewById(R.id.outText);
		btnRan.setOnClickListener(this);
		cB1=(CheckBox)getView().findViewById(R.id.cB1);
		cB2=(CheckBox)getView().findViewById(R.id.cB2);
		cB3=(CheckBox)getView().findViewById(R.id.cB3);
		SharedPreferences sp = getActivity().getSharedPreferences("type",Context.MODE_PRIVATE);
		type1_s=sp.getString("type1", type1);
		type2_s=sp.getString("type2", type2);
		type3_s=sp.getString("type3", type3);
		cB1.setText(type1_s);
		cB2.setText(type2_s);
		cB3.setText(type3_s);
	}
	public static void main(String[] args) {
		// TODO Auto-generated method stub

	}
	public void onClick(View arg0) {
		// 引入code代表下列的情况
		if(cB1.isChecked()&&cB2.isChecked()&&cB3.isChecked())//0
		{
			DBManager dbm=new DBManager(getActivity());
			if(dbm.Random(0)==null){
				Toast.makeText(getActivity(), "没有找到合适的餐厅，点击功能添加你喜爱的餐厅。", Toast.LENGTH_SHORT).show();
			outText.setText("点击下面按钮，看看今天吃什么。(可以通过勾选来选择自己想吃的类别，不选则视为全选)");}
			else
			{
				
				FoodItem foodItem=dbm.Random(0);
				foodItem.setType(type1_s, 1);
				foodItem.setType(type2_s, 2);
				foodItem.setType(type3_s, 3);
				SharedPreferences sp = getActivity().getSharedPreferences("type",Context.MODE_PRIVATE);
				outText.setText("餐厅名： "+foodItem.getFoodName()+"  类型： "+getRealType1(foodItem, sp));
			}
		}
		else if(!cB1.isChecked()&&cB2.isChecked()&&cB3.isChecked())//1
		{
			DBManager dbm=new DBManager(getActivity());
			if(dbm.Random(1)==null){
				Toast.makeText(getActivity(), "没有找到合适的餐厅，点击功能添加你喜爱的餐厅。", Toast.LENGTH_SHORT).show();
			outText.setText("点击下面按钮，看看今天吃什么。(可以通过勾选来选择自己想吃的类别，不选则视为全选)");}
			else
			{
				FoodItem foodItem=dbm.Random(1);
				foodItem.setType(type1_s, 1);
				foodItem.setType(type2_s, 2);
				foodItem.setType(type3_s, 3);
				SharedPreferences sp = getActivity().getSharedPreferences("type",Context.MODE_PRIVATE);
				outText.setText("餐厅名： "+foodItem.getFoodName()+"  类型： "+getRealType1(foodItem, sp));
			}
		}else if(cB1.isChecked()&&!cB2.isChecked()&&cB3.isChecked())//2
		{
			DBManager dbm=new DBManager(getActivity());
			if(dbm.Random(2)==null){
				Toast.makeText(getActivity(), "没有找到合适的餐厅，点击功能添加你喜爱的餐厅。", Toast.LENGTH_SHORT).show();
			outText.setText("点击下面按钮，看看今天吃什么。(可以通过勾选来选择自己想吃的类别，不选则视为全选)");}
			else
			{
				FoodItem foodItem=dbm.Random(2);
				foodItem.setType(type1_s, 1);
				foodItem.setType(type2_s, 2);
				foodItem.setType(type3_s, 3);
				SharedPreferences sp = getActivity().getSharedPreferences("type",Context.MODE_PRIVATE);
				outText.setText("餐厅名： "+foodItem.getFoodName()+"  类型： "+getRealType1(foodItem, sp));
			}
		}else if(cB1.isChecked()&&cB2.isChecked()&&!cB3.isChecked())//3
		{
			DBManager dbm=new DBManager(getActivity());
			if(dbm.Random(3)==null){
				Toast.makeText(getActivity(), "没有找到合适的餐厅，点击功能添加你喜爱的餐厅。", Toast.LENGTH_SHORT).show();
			outText.setText("点击下面按钮，看看今天吃什么。(可以通过勾选来选择自己想吃的类别，不选则视为全选)");}
			else
			{
				FoodItem foodItem=dbm.Random(3);
				foodItem.setType(type1_s, 1);
				foodItem.setType(type2_s, 2);
				foodItem.setType(type3_s, 3);
				SharedPreferences sp = getActivity().getSharedPreferences("type",Context.MODE_PRIVATE);
				outText.setText("餐厅名： "+foodItem.getFoodName()+"  类型： "+getRealType1(foodItem, sp));
			}
		}else if(!cB1.isChecked()&&!cB2.isChecked()&&cB3.isChecked())//4
		{
			DBManager dbm=new DBManager(getActivity());
			if(dbm.Random(4)==null){
				Toast.makeText(getActivity(), "没有找到合适的餐厅，点击功能添加你喜爱的餐厅。", Toast.LENGTH_SHORT).show();
			outText.setText("点击下面按钮，看看今天吃什么。(可以通过勾选来选择自己想吃的类别，不选则视为全选)");}
			else
			{
				FoodItem foodItem=dbm.Random(4);
				foodItem.setType(type1_s, 1);
				foodItem.setType(type2_s, 2);
				foodItem.setType(type3_s, 3);
				SharedPreferences sp = getActivity().getSharedPreferences("type",Context.MODE_PRIVATE);
				outText.setText("餐厅名： "+foodItem.getFoodName()+"  类型： "+getRealType1(foodItem, sp));
			}
		}else if(cB1.isChecked()&&!cB2.isChecked()&&!cB3.isChecked())//5
		{
			DBManager dbm=new DBManager(getActivity());
			if(dbm.Random(5)==null){
				Toast.makeText(getActivity(), "没有找到合适的餐厅，点击功能添加你喜爱的餐厅。", Toast.LENGTH_SHORT).show();
			outText.setText("点击下面按钮，看看今天吃什么。(可以通过勾选来选择自己想吃的类别，不选则视为全选)");}
			else
			{
				FoodItem foodItem=dbm.Random(5);
				foodItem.setType(type1_s, 1);
				foodItem.setType(type2_s, 2);
				foodItem.setType(type3_s, 3);
				SharedPreferences sp = getActivity().getSharedPreferences("type",Context.MODE_PRIVATE);
				outText.setText("餐厅名： "+foodItem.getFoodName()+"  类型： "+getRealType1(foodItem, sp));
			}
		}
		else if(!cB1.isChecked()&&cB2.isChecked()&&!cB3.isChecked())//6
		{
			DBManager dbm=new DBManager(getActivity());
			if(dbm.Random(6)==null){
				Toast.makeText(getActivity(), "没有找到合适的餐厅，点击功能添加你喜爱的餐厅。", Toast.LENGTH_SHORT).show();
			outText.setText("点击下面按钮，看看今天吃什么。(可以通过勾选来选择自己想吃的类别，不选则视为全选)");}
			else
			{
				FoodItem foodItem=dbm.Random(6);
				foodItem.setType(type1_s, 1);
				foodItem.setType(type2_s, 2);
				foodItem.setType(type3_s, 3);
				SharedPreferences sp = getActivity().getSharedPreferences("type",Context.MODE_PRIVATE);
				outText.setText("餐厅名： "+foodItem.getFoodName()+"  类型： "+getRealType1(foodItem, sp));
			}
		}
		else if(!cB1.isChecked()&&!cB2.isChecked()&&!cB3.isChecked())//0
		{
			
			DBManager dbm=new DBManager(getActivity());
			if(dbm.Random(0)==null){
				Toast.makeText(getActivity(), "没有找到合适的餐厅，点击功能添加你喜爱的餐厅。", Toast.LENGTH_SHORT).show();
				outText.setText("点击下面按钮，看看今天吃什么。(可以通过勾选来选择自己想吃的类别，不选则视为全选)");}
			else
			{
				FoodItem foodItem=dbm.Random(0);
				SharedPreferences sp = getActivity().getSharedPreferences("type",Context.MODE_PRIVATE);
				outText.setText("餐厅名： "+foodItem.getFoodName()+"  类型： "+getRealType1(foodItem, sp));
			}
		}
	}

}
