error id: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Viewer.java:java/lang/System#out.
file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Viewer.java
empty definition using pc, found symbol in pc: java/lang/System#out.
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 3929
uri: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Viewer.java
text:
```scala
import java.awt.Color;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.Iterator;

import javax.imageio.ImageIO;
import javax.swing.JPanel;
import javax.swing.Timer;

import util.GameObject;


/*
 * Created by Abraham Campbell on 15/01/2020.
 *   Copyright (c) 2020  Abraham Campbell

	Permission is hereby granted, free of charge, to any person obtaining a copy
	of this software and associated documentation files (the "Software"), to deal
	in the Software without restriction, including without limitation the rights
	to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
	copies of the Software, and to permit persons to whom the Software is
	furnished to do so, subject to the following conditions:

	The above copyright notice and this permission notice shall be included in all
	copies or substantial portions of the Software.

	THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
	IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
	FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
	AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
	LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
	OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN THE
	SOFTWARE.
		
		(MIT LICENSE ) e.g do what you want with this :-) 
	
	* Credits: Kelly Charles (2020)
 */ 
public class Viewer extends JPanel {
	private long CurrentAnimationTime= 0; 
	
	Model gameworld =new Model(); 
	 
	public Viewer(Model World) {
		this.gameworld=World;
	}

	public Viewer(LayoutManager layout) {
		super(layout);
	}

	public Viewer(boolean isDoubleBuffered) {
		super(isDoubleBuffered);
	}

	public Viewer(LayoutManager layout, boolean isDoubleBuffered) {
		super(layout, isDoubleBuffered);
	}

	public void updateview() {
		this.repaint();		
	}
	
	public void paintComponent(Graphics g) {
		
		super.paintComponent(g);
		CurrentAnimationTime++; // runs animation time step 
		
		//Draw player Game Object 
		int x = (int) gameworld.getPlayer().getCentre().getX();
		int y = (int) gameworld.getPlayer().getCentre().getY();
		int width = (int) gameworld.getPlayer().getWidth();
		int height = (int) gameworld.getPlayer().getHeight();
		String texture = gameworld.getPlayer().getTexture();

		drawUpperBackground(g);
		drawHouse(g);
		drawBackground(g);
		drawHealthPlayer(g);
		drawHealthHouse(g);
		drawPlayer(x, y, width, height, texture,g);

		if(gameworld.getShowBulletPopUp()){
			drawBulletPopUp(g);
		}

		if(gameworld.getShowHousePopUp()){
			drawHousePopUp(g);
		}
		  
		//change back 
		gameworld.getBullets().forEach((bullet) ->{ 
			drawBullet((int) bullet.getCentre().getX(), (int) bullet.getCentre().getY(), (int) bullet.getWidth(), (int) bullet.getHeight(), bullet.getTexture(),g);	 
		}); 
		 
		gameworld.getEnemies().forEach((enemy) ->{
			drawEnemies((int) enemy.getCentre().getX(), (int) enemy.getCentre().getY(), (int) enemy.getWidth(), (int) enemy.getHeight(), enemy.getTexture(),g);	 
	  }); 
	}
	
	private void drawHousePopUp(Graphics g) {
		int width = 400;
		int height = 200;
		int x = (1000 - 400) / 2;
		int y = (1000 - 200) / 2;

		try {
			g.create(x, y, width, height);
			g.fillRect(x, y, width, height);
			g.setColor(new Color(0,0,0,120));
			g.drawString("Want to update to a bigger bullet?", x+40, y+20);
			g.drawString("Chance will arise again after 20 more point.", x+40, y+40);

			g.drawString("[Y] Yes", x + 80, y + 120);
    	g.drawString("[N] No", x + 220, y + 120);
			
		} catch (Exception e) {
			System.@@out.println("Error drawing the bullet house up");
			e.printStackTrace();
		}
	}

	private void drawBulletPopUp(Graphics g) {
		
		try {
			
		} catch (Exception e) {
			System.out.println("Error drawing the bulltet pop up");
			e.printStackTrace();
		}
	}

	private void drawEnemies(int x, int y, int width, int height, String texture, Graphics g) {
		File TextureToLoad = new File(texture);
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			int currentPositionInAnimation= ((int) (CurrentAnimationTime%4)*32); //slows down animation so every 10 frames we get another frame so every 100ms 
			g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+31, 32, null); 
			
		} catch (IOException e) {
			System.out.println("Error drawing the enemies");
			e.printStackTrace();
		} 
		
	}

	private void drawBackground(Graphics g){
		File TextureToLoad = new File("res/dirt1.png");  
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			 g.drawImage(myImage, 0,250, 1000, 1000, 0 , 0, 200, 200, null); 
			
		} catch (IOException e) {
			System.out.println("Error drawing the dirt");
			e.printStackTrace();
		}
	}
	
	private void drawBullet(int x, int y, int width, int height, String texture,Graphics g){
		File TextureToLoad = new File(texture);
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			 g.drawImage(myImage, x,y, (int)((x+width*0.05)), (int)((y+height*0.05)), 0 , 0, 306, 813, null); 
			
		} catch (IOException e) {
			System.out.println("Error drawing the upper bullets");
			e.printStackTrace();
		}
	}

	private void drawPlayer(int x, int y, int width, int height, String texture,Graphics g) { 
		File TextureToLoad = new File(texture); 
		try {
			Image myImage = ImageIO.read(TextureToLoad);
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%30)/10))*32; //slows down animation so every 10 frames we get another frame so every 100ms 
			g.drawImage(myImage, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+31, 32, null); 
			
		} catch (IOException e) {
			System.out.println("Error drawing the house");
			e.printStackTrace();
		} 
		 
		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer));
		//Lighnting Png from https://opengameart.org/content/animated-spaceships  its 32x32 thats why I know to increament by 32 each time 
		// Bullets from https://opengameart.org/forumtopic/tatermands-art 
		// background image from https://www.needpix.com/photo/download/677346/space-stars-nebula-background-galaxy-universe-free-pictures-free-photos-free-images
		
	}

		private void drawUpperBackground(Graphics g){
		File TextureToLoad = new File("res/grass2.png");
		try{
			Image myImage = ImageIO.read(TextureToLoad); 
			 g.drawImage(myImage, 0,0, 1000, 250, 0 , 300, 300, 480, null); 
			
		} catch (IOException e) {
			System.out.println("Error drawing the grass");
			e.printStackTrace();
		}
	}

	private void drawHouse(Graphics g){
		File TextureToLoad = new File("res/house.png");
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			 g.drawImage(myImage, 45,75, 170, 260, 0 , 0, 64, 64, null); 
			
		} catch (IOException e) {
			System.out.println("Error drawing the house");
			e.printStackTrace();
		}
	}

	private void drawHealthHouse(Graphics g){
		int life = gameworld.getHouseLife();
		File TextureToLoad = new File("res/houseHealth/VIDA_" + life + ".png");
		try {
			Image myImage = ImageIO.read(TextureToLoad); 
			//shrink image
			 g.drawImage(myImage, 35, 15, (int)((35+378)*0.5), (int)((15+38)*0.5), 0, 0, 378, 38, null); 
			
		} catch (IOException e) {
			System.out.println("Error drawing the health house bar");
			e.printStackTrace();
		}
	}

	private void drawHealthPlayer(Graphics g){
		int life = gameworld.getHumanLife();
		File TextureToLoad = new File("res/playerHealth/" + life +".png");
		try {
			Image myImage = ImageIO.read(TextureToLoad); 

			 g.drawImage(myImage, 800,15, 800+54, 15+17, 0 , 0, 54, 17, null); 
			
		} catch (IOException e) {
			System.out.println("Error drawing the health player hearts + file path: " + TextureToLoad.getPath());
			e.printStackTrace();
		}
	}

}
```


#### Short summary: 

empty definition using pc, found symbol in pc: java/lang/System#out.