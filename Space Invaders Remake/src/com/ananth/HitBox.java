package com.ananth;

import java.awt.Rectangle;
import java.awt.geom.Rectangle2D;

public class HitBox extends Rectangle2D{
	int x,y,x2,y2;
	Rectangle ship,alienShot,shot,alien;
	public boolean collision(int a, int b, int c, int d){
		x=a;
		y=b;
		x2=c;
		y2=d;
		Rectangle ship = new Rectangle(x,y,75,40);
		Rectangle alienShot = new Rectangle(x2,y2,5,10);
		
		return ship.intersects(alienShot);
	}
	public boolean alienKill(int a,int b, int c, int d){
		x=a;
		y=b;
		x2=c;
		y2=d;
		Rectangle shot = new Rectangle(x,y,5,10);
		Rectangle alien = new Rectangle(x2,y2,70,50);
		
		return shot.intersects(alien);
	}
	public boolean boxDamage(int a,int b, int c, int d){
		x=a;
		y=b;
		x2=c;
		y2=d;
		Rectangle box = new Rectangle(x,y,100,100);
		Rectangle alienshot = new Rectangle(x2,y2,5,10);
		
		return box.intersects(alienshot);
	}
	public int test(){
		return x;
	}
	public int test2(){
		return y;
	}
	public int test3(){
		return x2;
	}
	public int test4(){
		return y2;
	}


	@Override
	public Rectangle2D createIntersection(Rectangle2D r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public Rectangle2D createUnion(Rectangle2D r) {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public int outcode(double x, double y) {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public void setRect(double x, double y, double w, double h) {
		// TODO Auto-generated method stub
		
	}

	@Override
	public double getHeight() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getWidth() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getX() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public double getY() {
		// TODO Auto-generated method stub
		return 0;
	}

	@Override
	public boolean isEmpty() {
		// TODO Auto-generated method stub
		return false;
	}
	
	
}
