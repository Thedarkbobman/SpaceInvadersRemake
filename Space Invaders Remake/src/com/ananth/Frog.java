package com.ananth;

public class Frog {
private int xLoc,yLoc,frogWidth,xMov,yMov,w,h,health;
private boolean lost;

public Frog(int width, int height){
	frogWidth = 50;
	xLoc = width/2-frogWidth/2;
	yLoc = height-75;
	xMov = 15;
	yMov = 15;
	w=width;
	h=height;
	health = 100;
	lost = false;
}

public int locX(){
	return xLoc;
}
public int locY(){
	return yLoc;
}
public void moveLeft(Boolean a){
	if(xLoc>0){
		if(!a){
			
			xLoc-=xMov;
			
		}
		else{
			xLoc-=xMov*2;
		}
	}
}
public void moveRight(Boolean a){
	if(xLoc < w-100){

		if(!a){
			xLoc+=xMov;
		}
		else{
			xLoc+=xMov*2;
		}
	}
	}


public void reset(){
	xLoc = w/2-frogWidth/2;
	yLoc = h-50;
}
public void loseHealth(int x){
	health-= x;
}
public int getHealth(){
	return health;
}
public void setHealth(){
	health = 100;
}
public void kill(){
	health = 0;
}

}
