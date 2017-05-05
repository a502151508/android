package com.example.eatwhat;

import java.util.ArrayList;
import java.util.List;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.util.Log;

public class DBManager {
	private DBHelper dbHelper;
	private String TBNAME;
	public DBManager(Context context)
	{
		dbHelper =new DBHelper(context);
		TBNAME=DBHelper.TB_NAME;
	}
	public void add(FoodItem item){
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		ContentValues values =new ContentValues();
		values.put("FOODNAME", item.getFoodName());
		values.put("FOODTYPE", item.getFoodType());
		db.insert(TBNAME,null,values);
		Log.i("add", "Ìí¼Ó³É¹¦ name="+item.getFoodName()+"  "+item.getRealType());
		db.close();
		
	}
	public void deleteAll(){
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		db.delete(TBNAME,null,null);
		db.close();
	}
	public void delete(int id){
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		db.delete(TBNAME,"ID=?",new String[]{String.valueOf(id)});
		db.close();
	}
	public void deleteByItem(String name){
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		db.delete(TBNAME,"FOODNAME=?",new String[]{String.valueOf(name)});
		db.close();
	}
	public void update(FoodItem item){
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		ContentValues values =new ContentValues();
		values.put("FOODNAME", item.getFoodName());
		values.put("FOODTYPE", item.getFoodType());
		db.update(TBNAME,values,"ID=?", new String[]{String.valueOf(item.getId())});
		db.close();
	}
	public List<FoodItem> listAll(){
		List<FoodItem> foodList=null;
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		Cursor cursor=db.query(TBNAME, null, null,null, null, null, null);
		if(cursor!=null){
			foodList = new ArrayList<FoodItem>();
			while(cursor.moveToNext()){
				FoodItem item=new FoodItem();
				item.setId(cursor.getInt(cursor.getColumnIndex("ID")));
				item.setFoodName(cursor.getString(cursor.getColumnIndex("FOODNAME")));
				item.setFoodType(cursor.getInt(cursor.getColumnIndex("FOODTYPE")));
				foodList.add(item);
			
			
			}
			cursor.close();
		}
		db.close();
		return foodList;
	}
	public FoodItem findById(int id){
		SQLiteDatabase db=dbHelper.getWritableDatabase();
		Cursor cursor=db.query(TBNAME,null, "ID=?", 
				new String[]{String.valueOf(id)}, null, null,null);
		FoodItem foodItem=null;
		if(cursor!=null&&cursor.moveToFirst()){
			foodItem =new FoodItem();
			foodItem.setId(cursor.getInt(cursor.getColumnIndex("ID")));
			foodItem.setFoodName(cursor.getString(cursor.getColumnIndex("FOODNAME")));
			foodItem.setFoodType(cursor.getInt(cursor.getColumnIndex("FOODTYPE")));
			cursor.close();
		}
		db.close();
		return foodItem;
	}
	public FoodItem Random(int code)
	{
		if(code==0)
		{
			SQLiteDatabase db=dbHelper.getWritableDatabase();
			Cursor cursor = db.query(TBNAME, new String[] { "*" }, null, null,
				null, null, "RANDOM()");
			if(!cursor.moveToNext()){
				cursor.close();
				db.close();
				return null;
			
			}
			else{
				cursor.moveToFirst();
				FoodItem foodItem=new FoodItem(cursor.getString(cursor.getColumnIndex("FOODNAME")),cursor.getInt(cursor.getColumnIndex("FOODTYPE")));
				cursor.close();
				db.close();
				return foodItem;
				}
		}
		else if(code ==1)
		{
			SQLiteDatabase db=dbHelper.getWritableDatabase();
			Cursor cursor = db.query(TBNAME, new String[] { "*" },"FOODTYPE =2 OR FOODTYPE = 3", null,
				null, null, "RANDOM()");
			if(!cursor.moveToNext()){
				cursor.close();
				db.close();
				return null;
			
			}
			else{
				cursor.moveToFirst();
				FoodItem foodItem=new FoodItem(cursor.getString(cursor.getColumnIndex("FOODNAME")),cursor.getInt(cursor.getColumnIndex("FOODTYPE")));
				cursor.close();
				db.close();
				return foodItem;
				}
		}
		else if(code ==2)
		{
			SQLiteDatabase db=dbHelper.getWritableDatabase();
			Cursor cursor = db.query(TBNAME, new String[] { "*" },"FOODTYPE = 1 OR FOODTYPE = 3", null,
				null, null, "RANDOM()");
			if(!cursor.moveToNext()){
				cursor.close();
				db.close();
				return null;
			
			}
			else{
				cursor.moveToFirst();
				FoodItem foodItem=new FoodItem(cursor.getString(cursor.getColumnIndex("FOODNAME")),cursor.getInt(cursor.getColumnIndex("FOODTYPE")));
				cursor.close();
				db.close();
				return foodItem;
				}
		}
		else if(code ==3)
		{
			SQLiteDatabase db=dbHelper.getWritableDatabase();
			Cursor cursor = db.query(TBNAME, new String[] { "*" },"FOODTYPE =2 OR FOODTYPE = 1", null,
				null, null, "RANDOM()");
			if(!cursor.moveToNext()){
				cursor.close();
				db.close();
				return null;
			
			}
			else{
				cursor.moveToFirst();
				FoodItem foodItem=new FoodItem(cursor.getString(cursor.getColumnIndex("FOODNAME")),cursor.getInt(cursor.getColumnIndex("FOODTYPE")));
				cursor.close();
				db.close();
				return foodItem;
				}
		}
		else if(code ==4)
		{
			SQLiteDatabase db=dbHelper.getWritableDatabase();
			Cursor cursor = db.query(TBNAME, new String[] { "*" },"FOODTYPE = 3", null,
				null, null, "RANDOM()");
			if(!cursor.moveToNext()){
				cursor.close();
				db.close();
				return null;
			
			}
			else{
				cursor.moveToFirst();
				FoodItem foodItem=new FoodItem(cursor.getString(cursor.getColumnIndex("FOODNAME")),cursor.getInt(cursor.getColumnIndex("FOODTYPE")));
				cursor.close();
				db.close();
				return foodItem;
				}
		}
		else if(code ==5)
		{
			SQLiteDatabase db=dbHelper.getWritableDatabase();
			Cursor cursor = db.query(TBNAME, new String[] { "*" },"FOODTYPE = 1", null,
				null, null, "RANDOM()");
			if(!cursor.moveToNext()){
				cursor.close();
				db.close();
				return null;
			
			}
			else{
				cursor.moveToFirst();
				FoodItem foodItem=new FoodItem(cursor.getString(cursor.getColumnIndex("FOODNAME")),cursor.getInt(cursor.getColumnIndex("FOODTYPE")));
				cursor.close();
				db.close();
				return foodItem;
				}
		}
		else if(code == 6)
		{
			SQLiteDatabase db=dbHelper.getWritableDatabase();
			Cursor cursor = db.query(TBNAME, new String[] { "*" },"FOODTYPE = 2", null,
				null, null, "RANDOM()");
			if(!cursor.moveToNext()){
				cursor.close();
				db.close();
				return null;
			
			}
			else{
				cursor.moveToFirst();
				FoodItem foodItem=new FoodItem(cursor.getString(cursor.getColumnIndex("FOODNAME")),cursor.getInt(cursor.getColumnIndex("FOODTYPE")));
				cursor.close();
				db.close();
				return foodItem;
				}
		}
			return null;
	}
}
