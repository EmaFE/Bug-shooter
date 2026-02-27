import java.awt.Color;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Graphics2D;
import java.awt.Image;
import java.awt.LayoutManager;
import java.awt.Point;
import java.awt.Rectangle;
import java.awt.TexturePaint;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
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
	private Image playerImg;
	private Image houseImg;
	private Image upperBgImg;
	private Image lowerBgImg;

	private Image bulletImg;
	private Image bigBulletImg;

	private Image spiderImg;
	private Image antImg;
	private Image uglyBug1Img;
	private Image uglyBug2Img;
	private Image flyImg;

	private ArrayList<Image> healthPlayerImages;
	private ArrayList<Image> healthHouseImages;

	private Image gameWonImg;
	BufferedImage[] gameOverFrames = new BufferedImage[4];
	int imgCount = 0;
	boolean showingGameOver = true;
	
	Model gameworld = new Model(); 
	 
	public Viewer(Model World) {
		this.gameworld=World;
		File playerTexture = new File(gameworld.getPlayer().getTexture());
		File houseTexture = new File(gameworld.getHouse().getTexture());
		File upperBgTexture = new File("res/bgs/grass2.png");
		File lowerBgTexture = new File("res/bgs/dirt1.png");

		File bulletTexture = new File("res/bullets/bullet2.png");
		File bigBulletTexture = new File("res/bullets/bullet.png");

		File gameWonTexture = new File("res/gameOver/Firework.png");

		try {
			playerImg = ImageIO.read(playerTexture);
			houseImg = ImageIO.read(houseTexture);
			upperBgImg = ImageIO.read(upperBgTexture);
			lowerBgImg = ImageIO.read(lowerBgTexture);
			bulletImg = ImageIO.read(bulletTexture);
			bigBulletImg = ImageIO.read(bigBulletTexture);
			gameWonImg = ImageIO.read(gameWonTexture);
		} catch (Exception e) {
			System.out.println("Coulnd not load an image");
			e.printStackTrace();
		}
		
		loadHealthPlayerImages();
		loadHealthHouseImages();

		loadEnemiesImages();
		loadGameOverImages();
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
		if(gameworld.isGameOver()){
			drawGameOverBg(g);
			drawGameOver(g);
		} else if(gameworld.isGameWon()){
			drawGameOverBg(g);
			drawGameWon(g);
		} else{

		//Draw player Game Object 
		int x = (int) gameworld.getPlayer().getCentre().getX();
		int y = (int) gameworld.getPlayer().getCentre().getY();
		int width = (int) gameworld.getPlayer().getWidth();
		int height = (int) gameworld.getPlayer().getHeight();
		String texture = gameworld.getPlayer().getTexture();


		//draw house object
		int xh = (int) gameworld.getHouse().getCentre().getX();
		int yh = (int) gameworld.getHouse().getCentre().getY();
		int widthh = (int) gameworld.getHouse().getWidth();
		int heighth = (int) gameworld.getHouse().getHeight();
		String textureh = gameworld.getHouse().getTexture();

		drawUpperBackground(g);
		if(gameworld.getHouse().getLives() > 0){
			drawHouse(xh, yh, widthh, heighth, textureh, g);
		}
		drawBackground(g);
		drawHealthPlayer(g);
		drawHealthHouse(g);
		drawPlayer(x, y, width, height, texture,g);

		if(gameworld.isAcceptedHouse()){
			gameworld.getHouse().setLives(10);
		};
		//change back 
		gameworld.getBullets().forEach((bullet) ->{ 
			if(gameworld.isAcceptedBigBullet()){
				drawBigBullet((int) bullet.getCentre().getX(), (int) bullet.getCentre().getY(), (int) bullet.getWidth(), (int) bullet.getHeight(), bullet.getTexture(),g);
			} else{
				drawBullet((int) bullet.getCentre().getX(), (int) bullet.getCentre().getY(), (int) bullet.getWidth(), (int) bullet.getHeight(), bullet.getTexture(),g);
			}	 
		}); 
		 
		gameworld.getEnemies().forEach((enemy) ->{
			drawEnemies((int) enemy.getCentre().getX(), (int) enemy.getCentre().getY(), (int) enemy.getWidth(), (int) enemy.getHeight(), enemy.getTexture(),g);	 
	  }); 
	}}

	private void drawEnemies(int x, int y, int width, int height, String texture, Graphics g) {
		gameworld.getEnemies().forEach((enemy) ->{
			int currentPositionInAnimation= ((int) (CurrentAnimationTime%4)*32); //slows down animation so every 10 frames we get another frame so every 100ms 
			switch (enemy.getName()) {
				case "spider": g.drawImage(spiderImg, x, y, x+width, y+height, currentPositionInAnimation, 0, currentPositionInAnimation+31, 32, null); break;
				case "fly": g.drawImage(flyImg, x, y, x+width, y+height, currentPositionInAnimation, 0, currentPositionInAnimation+31, 32, null); break;
				case "ant": g.drawImage(antImg, x, y, x+width, y+height, currentPositionInAnimation, 0, currentPositionInAnimation+31, 32, null); break;
				case "ugly1": g.drawImage(uglyBug1Img, x, y, x+width, y+height, currentPositionInAnimation, 0, currentPositionInAnimation+31, 32, null); break;
				case "ugly2": g.drawImage(uglyBug2Img, x, y, x+width, y+height, currentPositionInAnimation, 0, currentPositionInAnimation+31, 32, null); break;
				default: break;
			}
		});				
	}

	private void drawBackground(Graphics g){
		g.drawImage(lowerBgImg, 0,250, 1000, 1000, 0 , 0, 200, 200, null); 
	}
	
	private void drawBigBullet(int x, int y, int width, int height, String texture,Graphics g){
		g.drawImage(bigBulletImg, x,y, (int)((x+width*0.09)), (int)((y+height*0.09)), 0 , 0, 306, 813, null); 
	}

	private void drawBullet(int x, int y, int width, int height, String texture,Graphics g){
		g.drawImage(bulletImg, x,y, (int)((x+width*0.03)), (int)((y+height*0.03)), 0 , 0, 306, 600, null); 
	}

	private void drawPlayer(int x, int y, int width, int height, String texture,Graphics g) { 
			//The spirte is 32x32 pixel wide and 4 of them are placed together so we need to grab a different one each time 
			//remember your training :-) computer science everything starts at 0 so 32 pixels gets us to 31  
			int currentPositionInAnimation= ((int) ((CurrentAnimationTime%30)/10))*32; //slows down animation so every 10 frames we get another frame so every 100ms 
			g.drawImage(playerImg, x,y, x+width, y+height, currentPositionInAnimation  , 0, currentPositionInAnimation+31, 32, null); 
		} 
		 
		//g.drawImage(img, dx1, dy1, dx2, dy2, sx1, sy1, sx2, sy2, observer));
		//Lighnting Png from https://opengameart.org/content/animated-spaceships  its 32x32 thats why I know to increament by 32 each time 
	

	private void drawUpperBackground(Graphics g){
		g.drawImage(upperBgImg, 0,0, 1000, 250, 0 , 300, 300, 480, null); 
	}

	private void drawHouse(int x, int y, int width, int height, String texture,Graphics g){
		g.drawImage(houseImg, x, y , x+width, y+height, 0,0, 64, 64, null); 
	}

	private void drawHealthHouse(Graphics g){
		healthHouseImages.forEach((image) ->{
			int index = healthHouseImages.indexOf(image);
			if( index == gameworld.getHouse().getLives()){
				g.drawImage(image, 35, 15, (int)((35+378)*0.5), (int)((15+38)*0.5), 0, 0, 378, 38, null); 
			}
		});
	}

	private void drawHealthPlayer(Graphics g){
		healthPlayerImages.forEach((image) ->{
			int index = healthPlayerImages.indexOf(image);
			if( index == gameworld.getPlayer().getLives()){
				g.drawImage(image, 800,15, 800+54, 15+17, 0 , 0, 54, 17, null); 
			}
		});
	}

	private void drawGameOver(Graphics g) {
		//slow it down
		g.drawImage(gameOverFrames[(gameworld.getGameOverFrameCounter() / 30) % 4],250, 150,250 + 500, 150 + 500,0, 0, 16, 16,null);
		imgCount++;

		g.create(250, 850, 300, 100);
		g.setColor(Color.BLACK);

		g.setFont(new Font("Arial", Font.BOLD, 25));
		g.setColor(Color.WHITE);
		g.drawString("GAME OVER", 150, 750);
		g.drawString("You've been eaten by by bugs and", 150, 790);
		g.drawString("your house has been infested.", 150, 820);
	}

	public void drawGameOverBg(Graphics g){
		g.setColor(Color.BLACK);
		g.fillRect(0,0,1000,1000);
	}

	private void drawGameWon(Graphics g) {
			int fwidth = 256;
			int fheight = 256;

		 	int cols = 1536 / fwidth; //=6 cols
			int ftotal = cols * (1280 / fheight); //=30 frames

			int findex = (int) ((CurrentAnimationTime) % ftotal);

			int srcX = (findex % cols) * fwidth;
			int srcY = (findex / cols) * fheight;

			g.drawImage(gameWonImg,150, 150, 150 + 300, 150 + 300, srcX, srcY, srcX + fwidth, srcY + fheight,null);
			g.drawImage(gameWonImg,320, 260, 320 + 300, 260 + 300, srcX, srcY, srcX + fwidth, srcY + fheight,null);
			g.drawImage(gameWonImg,150, 450, 150 + 300, 450 + 300, srcX, srcY, srcX + fwidth, srcY + fheight,null);			
			g.drawImage(gameWonImg,450, 150, 450 + 300, 150 + 300, srcX, srcY, srcX + fwidth, srcY + fheight,null);
			g.drawImage(gameWonImg,650, 250, 650 + 300, 250 + 300, srcX, srcY, srcX + fwidth, srcY + fheight,null);
			g.drawImage(gameWonImg,450, 450, 450 + 300, 450 + 300, srcX, srcY, srcX + fwidth, srcY + fheight,null);						


			g.create(250, 850, 300, 100);
			g.setColor(Color.BLACK);

			g.setFont(new Font("Arial", Font.BOLD, 25));
			g.setColor(Color.WHITE);
			g.drawString("YOU WON!", 150, 750);
			g.drawString("You protected yourself and your house!", 150, 790);
			g.drawString("You are now bug-free!", 150, 820);
	}

	public void loadGameOverImages() {
    try {
      for (int i = 0; i < 4; i++) {
        gameOverFrames[i] = ImageIO.read(new File("res/gameOver/SpiderWalking" + i + ".png"));
      }
    } catch (IOException e) {
			System.out.println("Could not load game over image");
      e.printStackTrace();
    }
}

	public void loadEnemiesImages(){
		gameworld.getEnemies().forEach((enemy) ->{
			String enemyName = enemy.getName();
			try {
				switch (enemyName){
				case "spider": ImageIO.read(new File("res/enemies/spider.png"));break;
				case "fly": ImageIO.read(new File("res/enemies/fly.png"));break;
				case "ant": ImageIO.read(new File("res/enemies/ant.png"));break;
				case "ugly1": ImageIO.read(new File("res/enemies/spider.png"));break;
				case "ugly2": ImageIO.read(new File("res/enemies/spider.png"));break;
				default: System.out.println("No picture to load for this enemy");break;
			}
				
			} catch (Exception e) {
				System.out.println("Error retrieving an enemy");
				e.printStackTrace();
			}
			
		});
	}

	public void loadHealthHouseImages(){
		int houseLife = gameworld.getHouse().getLives();
		healthHouseImages.forEach((health) ->{
			File healthHouseTexture = new File("res/houseHealth/VIDA_" + houseLife +".png");
			try {
				health = ImageIO.read(healthHouseTexture);
			} catch (Exception e) {
				System.out.println("House health picture not found");
				e.printStackTrace();
			}
		});
	}

	public void loadHealthPlayerImages(){
		int playerLife = gameworld.getPlayer().getLives();
		healthPlayerImages.forEach((health) ->{
			File healthPlayerTexture = new File("res/playerHealth/" + playerLife +".png");
			try {
				health = ImageIO.read(healthPlayerTexture);
			} catch (Exception e) {
				System.out.println("Player health picture not found");
				e.printStackTrace();
			}
		});
	}
}