package com.example.eatwhat;

import android.app.Activity;
import android.content.Context;
import android.content.SharedPreferences;

public class   GetRealTypeHelper extends Activity {
	public GetRealTypeHelper(){
		
	}
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
}
