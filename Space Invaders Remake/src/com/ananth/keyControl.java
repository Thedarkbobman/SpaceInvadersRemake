package com.ananth;

import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.util.ArrayList;

import javax.swing.JPanel;

public class keyControl extends JPanel implements KeyListener {
	private static final long serialVersionUID = 1L;
	private int width, height, leet;
	private Frog bob;
	private Rocket rob;
	private boolean rockCheck,loseCheck;
	private boolean[] keys = new boolean[65536];


	public keyControl(int w, int h, Frog frog,Rocket r) {
		width = w;
		height = h;
		bob = frog;
		rob = r;
		rockCheck=true;
		loseCheck = false;
	}

	@Override
	public void keyPressed(KeyEvent e) {
		keys[e.getKeyCode()] = true;
	}
		
	

	@Override
	public void keyReleased(KeyEvent e) {
		keys[e.getKeyCode()] = false;
	}

	@Override
	public void keyTyped(KeyEvent e) {
		
	}
	
	public void tick() {
		if (keys[KeyEvent.VK_A]
				|| keys[KeyEvent.VK_LEFT]) {
			bob.moveLeft(false);
		}

		if (keys[KeyEvent.VK_D]
				|| keys[KeyEvent.VK_RIGHT]) {
			bob.moveRight(false);
			
		}
		if (keys[KeyEvent.VK_SPACE]
				&&rockCheck) {
			rob.shot();
			rob.setVal(bob.locX()+35, bob.locY());
			rockCheck=false;
			
		}
		if (keys[KeyEvent.VK_R] && loseCheck) {
			bob.setHealth();
			loseCheck = false;
		}

	}
	
	public int xLoc() {
		System.out.println( bob.locX());
		return bob.locX();
	}

	public int yLoc() {
		System.out.println(bob.locY());
		return bob.locY();
	}
	public void rockLeave(){
		rockCheck=true;
	}
	public void lost(){
		loseCheck=true;
	}
	public boolean isLost(){
		return loseCheck;
	}



}
