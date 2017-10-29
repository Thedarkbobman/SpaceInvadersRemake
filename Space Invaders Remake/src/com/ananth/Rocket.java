package com.ananth;

public class Rocket {
	int x,y,w,h,bW,bH;
	boolean shot;
	public Rocket(int x,int y){
		w=x;
		h=y;
		bW = 10;
		bH = 30;

	}
	
	
	public void move(){
		y-=30;
		
	}
	
	public int xLoc(){
		return x;
	}
	public int yLoc(){
		return y;
	}
	public void shot(){
		shot = true;
	}
	public boolean isShot(){
		return shot;
	}
	public void setVal(int x2, int y2){
		x = x2;
		y = y2;
	}
	public void notShot(){
		shot = false;
		System.out.println("false");
	}
}
