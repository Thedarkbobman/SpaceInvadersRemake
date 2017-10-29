package com.ananth;

import java.awt.*;
import java.awt.geom.AffineTransform;
//import java.awt.Toolkit;
//import java.awt.event.KeyEvent;
//import java.awt.event.KeyListener;
import java.io.*;
import java.util.ArrayList;
import java.util.Arrays;

import javax.imageio.ImageIO;
import javax.sound.sampled.*;
import javax.swing.JButton;
//import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;


@SuppressWarnings("serial")
public class Main extends JPanel {
	JFrame frame;
	 static keyControl keyControl;
	 int x,y,aX,aY,count,score,x2,y2,damage,level,score2,level2,start,end,start2,end2;
	 Frog bob;
	 Rocket rob;
	 Boolean rockShot,alienShot;
	 Image ship,alien;
	 Image[][] aliens;
	 double[][] locationsX,locationsY;
	 int numCol,numRow;
	 boolean down,changeDir,box1,box2,box3,box4,first,reset;
	 boolean[][] alive;
	 HitBox box;
	 AlienShot[][] alienShots;
	 long count2 = 0;
	 double box1Health,box2Health,box3Health,box4Health,speed;
		String[] good;
		String user;
	
	public Main(JFrame jf) {
		frame = jf;
	}

	public void init(int x, int y) throws IOException {
		
			frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		
		 bob = new Frog(x,y);
		 rob = new Rocket(x,y);
		 keyControl = new keyControl(x,y,bob,rob);
		 rockShot = false;
		 ship = ImageIO.read(Main.class.getResource("/Ship2.png"));
		 alien = ImageIO.read(Main.class.getResource("/Alien.jpg"));
		 int numRow = 5;
		 int numCol = 11;
		 aliens = new Image[numRow][numCol];
		 locationsX = new double[numRow][numCol];
		 locationsY = new double[numRow][numCol];
		 alive = new boolean[numRow][numCol];
		 down = false;
		 box = new HitBox();
		 alienShot = false;
		 count = 0;
		 rob.setVal(bob.locX()+35, bob.locY());
		 alienShots = new AlienShot[numRow][numCol];
		 box1 = true;
		 box2 = true;
		 box3 = true;
		 box4 = true;
		 box1Health = 80;
		 box2Health = 80;
		 box3Health = 80;
		 box4Health = 80;
		 score = 0;
		 x2=0;
		 y2=0;
		 damage = 5;
		 speed = 0.25;
		 first = true;
		 level = 1;
		 reset = false;
		 for(int i = 0; i < alive.length; i++){
			 for(int i2 = 0; i2 < alive[i].length; i2++){
				 alive[i][i2] = true;
			 }
		 }
		 for(int i = 0; i < aliens.length; i++){
			 for(int i2 = 0; i2 < aliens[i].length; i2++){
				 aliens[i][i2] = alien;
			 }
		 }
		 int count = 0;
		 for(int i = 0; i < locationsX.length; i++){
			 for(int i2 = 0; i2 < locationsX[i].length; i2++){
				 locationsX[i][i2] = i2+count;
				
				 count+=80;
			 }
			 count = 0;
			 
		 }
		 count = 0;
		 for(int i = 0; i < locationsY.length; i++){
			 for(int i2 = 0; i2 < locationsY[i].length; i2++){
				 locationsY[i][i2] = i+count;
				 
				//System.out.println(locationsY[i][i2]);
			 }
			 count+=60;
		 }
		 for(int i = 0; i < alienShots.length; i++){
			 for(int i2 = 0; i2 < alienShots[i].length; i2++){
				 alienShots[i][i2] = new AlienShot((int)(locationsX[i][i2])+10,(int)(locationsY[i][i2])+10, frame.getWidth());
				 
			 }
		 }
	}

	@Override
	public void paint(Graphics g) {

		super.paint(g);
		Graphics2D g2d = (Graphics2D) g;
		g2d.setRenderingHint(RenderingHints.KEY_ANTIALIASING,
				RenderingHints.VALUE_ANTIALIAS_ON);
		setBackground(Color.black);
		if(bob.getHealth()>0){
		g.setColor(Color.green);
		 x = bob.locX();
		 y = bob.locY();
		 if(box1Health <= 0){
			 box1 = false;
		 }
		 if(box2Health <= 0){
			 box2 = false;
		 }
		 if(box3Health <= 0){
			 box3 = false;
		 }
		 if(box4Health <= 0){
			 box4 = false;
		 }
		 if(box1){
		g.setColor(Color.green);
		 g.fillRect(70, 580, 100, 100);
		 g.setColor(Color.red);
		 g.drawRect(80,610, 80, 40);
		 g.fillRect(80, 610, (int)box1Health, 40);
		 }
		 if(box2){
			 g.setColor(Color.green);
			 g.fillRect(370, 580, 100, 100);
			 g.setColor(Color.red);
			 g.drawRect(380,610, 80, 40);
			 g.fillRect(380, 610, (int)box2Health, 40);
			 }
		 if(box3){
			 g.setColor(Color.green);
			 g.fillRect(670, 580, 100, 100);
			 g.setColor(Color.red);
			 g.drawRect(680,610, 80, 40);
			 g.fillRect(680, 610, (int)box3Health, 40);
			 }
		 if(box4){
			 g.setColor(Color.green);
			 g.fillRect(970, 580, 100, 100);
			 g.setColor(Color.red);
			 g.drawRect(980,610, 80, 40);
			 g.fillRect(980, 610, (int)box4Health, 40);
			 }
		 
		g.drawImage(ship, x, y, 75, 40, null);
		
		score2 =score;
		level2 = level;
		g.setColor(Color.white);
		g.drawString("Current Health: " + bob.getHealth(), getWidth()-150, 30);
		g.drawString("Score: " + score, 50, 50);
		g.drawString("Level: "+level, 500, 50);
		rockShot = rob.isShot();
		if(rob.yLoc()<-30){
			keyControl.rockLeave();
		}
		if(rockShot){
			g.setColor(Color.white);
			g.fillRect(rob.xLoc(), rob.yLoc(), 5, 10);
			rob.move();
		}
		for(int i = 0; i < aliens.length; i++){
			 for(int i2 = 0; i2 < aliens[i].length; i2++){
				if(alive[i][i2]){
				 g.drawImage(aliens[i][i2], (int)locationsX[i][i2], (int)locationsY[i][i2], 70, 50, null);
				// g.drawRect(locationsX[i][i2], locationsY[i][i2], 70, 50);
				 System.out.println("hi");
				}
			 }
		 }
		 hi: for(int i = 0; i < alive.length; i++){
			 for(int i2 = 0; i2 < alive[i].length; i2++){
				if(alive[i][i2]){
					 start = i;
					 start2 = i2;
					 break hi;
					 
				}
				
			 }
		 }
	  
	 hi2:for(int i = alive.length-1; i >=0; i--){
			 for(int i2 = alive[i].length-1; i2 >= 0; i2--){
				if(alive[i][i2]){
					 end = i;
					 end2 = i2;
					 break hi2;
				}
				
			 }
		 }
		 for(int i = 0; i < locationsX.length; i++){
			 for(int i2 = 0; i2 < locationsX[i].length; i2++){
				
				 if(alive[i][i2]){
				 if(box.alienKill(rob.xLoc(), rob.yLoc(), (int)locationsX[i][i2], (int)locationsY[i][i2])){
					System.out.println("hit");
					keyControl.rockLeave();
					rob.notShot();
					alive[i][i2]=false;
					rob.setVal(bob.locX()+35, bob.locY());
					score += 30;
					if(locationsY[i][i2] > getHeight()-75){
					bob.kill();
					}

				}
				 }
				 if(!changeDir)
				 locationsX[i][i2] +=speed;
				 if(changeDir)
					 locationsX[i][i2] -=speed;
				
			 }
		 }
		 
		 if(locationsX[end][end2] >= 1100){
			 down = true;
			 changeDir = true;
		 }
		 if(locationsX[start][start2] == 0 & changeDir == true){
			 down = true;
			 changeDir = false;
		 }
		 
		 if(down){
		 for(int i = 0; i < locationsY.length; i++){
			 for(int i2 = 0; i2 < locationsY[i].length; i2++){
				 locationsY[i][i2] +=100;
				 
			 }
		 }
		 down = false;
		 }

		 
		 for(int i = 0; i < aliens.length; i++){
			 for(int i2 = 0; i2 < aliens[i].length; i2++){
				 int bob2 = (int)(Math.random()*(4))+1;
				 int bob3 = (int)(Math.random()*(10))+1;
				
				 if(alive[bob2][bob3] && !alienShots[bob2][bob3].isActive() && count2 % 55 == 0){
					 alienShots[bob2][bob3].active();
				 	
				}
				 if(alienShots[i][i2].isActive()){
						g.setColor(Color.white);
						g.fillRect(alienShots[i][i2].xloc(), alienShots[i][i2].yloc(), 5, 10);
						//System.out.println("active");
						 if(box.collision(x, y, alienShots[i][i2].xloc(), alienShots[i][i2].yloc())){
							 bob.loseHealth(damage);
							 alienShots[i][i2].notActive();
							 alienShots[i][i2].setVal((int)locationsX[i][i2], (int)locationsY[i][i2]);
						 }
						 if(box.boxDamage(70, 580, alienShots[i][i2].xloc(), alienShots[i][i2].yloc())&& box1){
							 box1Health -= 1;
							 alienShots[i][i2].notActive();
							 alienShots[i][i2].setVal((int)locationsX[i][i2], (int)locationsY[i][i2]);
						 }
						 if(box.boxDamage(370, 580, alienShots[i][i2].xloc(), alienShots[i][i2].yloc())&& box2){
							 box2Health -= 1;
							 alienShots[i][i2].notActive();
							 alienShots[i][i2].setVal((int)locationsX[i][i2], (int)locationsY[i][i2]);
						 }
						 if(box.boxDamage(670, 580, alienShots[i][i2].xloc(), alienShots[i][i2].yloc())&& box3){
							 box3Health -= 1;
							 alienShots[i][i2].notActive();
							 alienShots[i][i2].setVal((int)locationsX[i][i2], (int)locationsY[i][i2]);
						 }
						 if(box.boxDamage(970, 580, alienShots[i][i2].xloc(), alienShots[i][i2].yloc())&& box4){
							 box4Health -= 1;
							 alienShots[i][i2].notActive();
							 alienShots[i][i2].setVal((int)locationsX[i][i2], (int)locationsY[i][i2]);
						 }

						
						 
						 alienShots[i][i2].move();
						 if(alienShots[i][i2].yloc() > getWidth()){
							 alienShots[i][i2].setVal((int)locationsX[i][i2], (int)locationsY[i][i2]);
							 alienShots[i][i2].notActive();
							// System.out.println("notActive");
						 }
					 }
				 
				 
			 }
		 }
		 if(box.boxDamage(70, 580, rob.xLoc(), rob.yLoc())&& rockShot && box1){
			  box1Health -= .25;
			 rob.notShot();
			 rob.setVal(bob.locX()+35, bob.locY());
			 System.out.println("hi");
			 keyControl.rockLeave();
		 }
		 if(box.boxDamage(370, 580, rob.xLoc(), rob.yLoc())&& rockShot && box2){
			 box2Health -= .25;
			 rob.setVal(bob.locX()+35, bob.locY());
			 rob.notShot();
			 keyControl.rockLeave();
		 }
		 if(box.boxDamage(670, 580, rob.xLoc(), rob.yLoc())&& rockShot && box3){
			 box3Health -= .25;
			 rob.setVal(bob.locX()+35, bob.locY());
			 rob.notShot();
			 keyControl.rockLeave();
			 
		 }
		 if(box.boxDamage(970, 580, rob.xLoc(), rob.yLoc())&& rockShot && box4){
			 box4Health -= .25;
			 rob.setVal(bob.locX()+35, bob.locY());
			 rob.notShot();
			 keyControl.rockLeave();
			 
		 }

		 if(count2 % 1000 == 0){
		 for(int i = 0; i < alienShots.length; i++){
			 for(int i2 = 0; i2 < alienShots[i].length; i2++){
				if(alive[i][i2]){
				 alienShots[i][i2].active();
				 
				// System.out.println(alienShots[i][i2].isActive());
				}
			 }
		 }
		 }
		 
		// System.out.println(count2);
		 for(int i = 0; i < alienShots.length; i++){
			 for(int i2 = alienShots[i].length-1; i2 >= 0; i2--){
				if(alive[i][i2]){
				 x2 = i;
				 y2= i2;
				 first = false;
				 System.out.println(locationsY[x2][y2]);
				}
				
			 }
		 }
		  for(int i = 0; i < alive.length; i++){
			 for(int i2 = 0; i2 < alive[i].length; i2++){
				if(alive[i][i2]){
					 reset = true;
					 
				}
				
			 }
		 }
		 
			
		  if(!reset){
			  
			  reset();
			  
			  
		  }
		  reset = false;
		 if(locationsY[x2][y2]> 800){
			 System.out.println("hi");
		 }
		 count2++;
		}
		else{
			keyControl.lost();
			
		g.setColor(Color.green);
		//AffineTransform transform = ((Graphics2D)g).getTransform();
		//((Graphics2D)g).scale(5,5);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 90)); 
		g.drawString(" U d3d", getWidth()/2-250, getHeight()/2-50);
		g.setFont(new Font("TimesRoman", Font.PLAIN, 40)); 
		g.drawString("You got to level " + level2 + " with a score of "+score2, getWidth()/2-250, getHeight()/2);
		g.drawString("Press r to try again.", getWidth()/2-250, getHeight()/2+50);
		//((Graphics2D)g).setTransform(transform);
		for(int i = 0; i < alive.length; i++){
			 for(int i2 = 0; i2 < alive[i].length; i2++){
				 alive[i][i2] = true;
			 }
		 }
		 int count = 0;
		 for(int i = 0; i < locationsX.length; i++){
			 for(int i2 = 0; i2 < locationsX[i].length; i2++){
				 locationsX[i][i2] = i2+count;
				
				 count+=80;
			 }
			 count = 0;
			 
		 }
		 count = 0;
		 for(int i = 0; i < locationsY.length; i++){
			 for(int i2 = 0; i2 < locationsY[i].length; i2++){
				 locationsY[i][i2] = i+count;
				 
				//System.out.println(locationsY[i][i2]);
			 }
			 count+=60;
		 }
		 for(int i = 0; i < alienShots.length; i++){
			 for(int i2 = 0; i2 < alienShots[i].length; i2++){
				 alienShots[i][i2] = new AlienShot((int)(locationsX[i][i2])+10,(int)(locationsY[i][i2])+10, frame.getWidth());
				 
			 }
		 }
		 box1Health = 80;
		 box2Health = 80;
		 box3Health = 80;
		 box4Health = 80;
		 level = 1;
		 score = 0;
		 
		}
		 
		 
		
		 
	}
		 

	public static void main(String[] args) throws InterruptedException,
			IOException {
		JFrame frame = new JFrame("MLG Space Invaders");
		final Main game = new Main(frame);
		game.init(1200, 800);

		keyControl.addKeyListener(keyControl);
		keyControl.setFocusable(true);
		frame.add(keyControl);
		frame.add(game);
		frame.setSize(1200, 800);
		frame.setResizable(false);
		frame.setVisible(true);

		frame.setBackground(Color.black);

		
		
		while (true) {
			
			keyControl.tick();
			game.repaint();
			Thread.sleep(30);
		}
		//There was originally music but I removed it because it was annoying.
		/*new Thread(new Runnable() {
			@Override
			public void run() {
				BufferedInputStream soundStream = new BufferedInputStream(Main.class.getResourceAsStream("/music.wav"));
				BufferedInputStream soundStream2 = new BufferedInputStream(Main.class.getResourceAsStream("/music.wav"));
				try {
					AudioInputStream sound = AudioSystem.getAudioInputStream(soundStream);
					AudioInputStream sound2 = AudioSystem.getAudioInputStream(soundStream2);

					// load the sound into memory (a Clip)
					DataLine.Info info = new DataLine.Info(Clip.class,
							sound.getFormat());
					DataLine.Info info2 = new DataLine.Info(Clip.class,
							sound2.getFormat());
					Clip clip = (Clip) AudioSystem.getLine(info);
					Clip clip2 = (Clip) AudioSystem.getLine(info2);
					clip.open(sound);
					clip2.open(sound2);
					while (true) {
						clip.loop(Clip.LOOP_CONTINUOUSLY);
						keyControl.tick();
						game.repaint();
						Thread.sleep(30);
					}
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		}).start();*/

	}
	public void reset(){
		for(int i = 0; i < alive.length; i++){
			 for(int i2 = 0; i2 < alive[i].length; i2++){
				 alive[i][i2] = true;
			 }
		 }
		 int count = 0;
		 for(int i = 0; i < locationsX.length; i++){
			 for(int i2 = 0; i2 < locationsX[i].length; i2++){
				 locationsX[i][i2] = i2+count;
				
				 count+=80;
			 }
			 count = 0;
			 
		 }
		 count = 0;
		 for(int i = 0; i < locationsY.length; i++){
			 for(int i2 = 0; i2 < locationsY[i].length; i2++){
				 locationsY[i][i2] = i+count;
				 
				//System.out.println(locationsY[i][i2]);
			 }
			 count+=60;
		 }
		 for(int i = 0; i < alienShots.length; i++){
			 for(int i2 = 0; i2 < alienShots[i].length; i2++){
				 alienShots[i][i2] = new AlienShot((int)(locationsX[i][i2])+10,(int)(locationsY[i][i2])+10, frame.getWidth());
				 
			 }
		 }
		 speed+=.25;
		 level++;
		 damage += 5;
		 changeDir = false;	
		 bob.setHealth();
		 
	}
	public void debug(){
		for(int i = 0; i < alive.length; i++){
			 for(int i2 = 0; i2 < alive[i].length-1; i2++){
				 alive[i][i2] = false;
				 
			 }
		 }
	}

}
