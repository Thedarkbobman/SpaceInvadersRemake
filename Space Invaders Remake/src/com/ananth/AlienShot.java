package com.ananth;

public class AlienShot {
private int xloc,yloc,width;
private boolean isActive;
public AlienShot(int x,int y, int w){
	xloc = x;
	yloc = y;
	isActive = false;
	width = w;
}
public void move(){
	yloc += 5;
}

public int xloc(){
	return xloc;
}
public int yloc(){
	return yloc;
}
public void active(){
	isActive = true;
}
public boolean isActive(){

	return isActive;
}
public void notActive(){
	isActive = false;
}
public void setVal(int x,int y){
	xloc = x;
	yloc = y;
}
}
