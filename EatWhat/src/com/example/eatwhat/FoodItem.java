package com.example.eatwhat;

public class FoodItem {
	private int id;
	private String foodName;
	private int foodType;
	
	String type1="ÖÐ²Í",type2="Î÷²Í",type3="ÌðÆ·";
	public FoodItem(){
		super();
		foodName="";
		foodType=0;
	}
	public FoodItem(String foodName,int foodType){
		this.foodName=foodName;
		this.foodType=foodType;
	}
	public void setType(String type,int i)
	{
		if(i==1)
			this.type1=type;
		if(i==2)
			this.type2=type;
		if(i==3)
			this.type3=type;
	}
	public String getRealType(){
		if(this.foodType==1)
			return type1;
		else if(this.foodType==2)
			return type2;
		else if(this.foodType==3)
			return type3;
		else
			return " ";
	}
	public int getId(){
		return id;
	}
	public void setId(int id){
		this.id=id;
	}
	public String getFoodName(){
		return foodName;
	}
	public int getFoodType(){
		return foodType;
	}
	public void setFoodName(String foodName){
		this.foodName=foodName;
	}
	public void setFoodType(int foodType){
		this.foodType=foodType;
	}
}
