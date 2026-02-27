error id: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Model.java:_empty_/GameObject#
file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Model.java
empty definition using pc, found symbol in pc: _empty_/GameObject#
found definition using semanticdb; symbol Model#enemiesList.
empty definition using fallback
non-local guesses:

offset: 9030
uri: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Model.java
text:
```scala
import java.util.ArrayList;
import java.util.Random;
import java.util.concurrent.CopyOnWriteArrayList;

import util.GameObject;
import util.Point3f;
import util.Vector3f; 
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
 */ 

public class Model {
	
	private GameObject player;
	private Controller controller = Controller.getInstance();
	//some bugs have 2 lives, so they need to be shot twice + some are faster
	private CopyOnWriteArrayList<GameObject> enemiesList  = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> bulletList  = new CopyOnWriteArrayList<GameObject>();
	private int humanLife = 6;
	private int houseLife = 10;
	//you get +1 for each bug you kill, so even if you lose your house, you can buy it back if you kill enough bugs while trying to protect your house
	private int money = 0;
	private boolean showHousePopUp = false;
	private boolean showBulletPopUp = false;

	private boolean acceptedBigBullet = false;
	private boolean acceptedFasterBullet = false;
	private boolean acceptedHouse = false;

	private boolean gameOver = false;

	public GameObject generateEnemy(){
		//make them bigger nad idffrenet sizes
		ArrayList<GameObject> enemies = new ArrayList<>();
		enemies.add(new GameObject("res/spider.png",70,70,new Point3f(((float)Math.random()*1000 ),900,0)));
		enemies.add(new GameObject("res/fly.png",50,50,new Point3f(((float)Math.random()*1000 ),900,0)));
		enemies.add(new GameObject("res/fly.png",50,50,new Point3f(((float)Math.random()*1000 ),900,0)));
		enemies.add(new GameObject("res/spider.png",50,50,new Point3f(((float)Math.random()*1000 ),900,0)));

		int index = new Random().nextInt(enemies.size());
		return enemies.get(index);
	}

	public Model() {
		//setup game world 
		//Player 
		player= new GameObject("res/npcmaleidle.png",32,86,new Point3f(500,165,0));
		//Enemies  starting with four ''

		while(enemiesList.size() < 6){
			enemiesList.add(generateEnemy());
		}
	}
	
	// This is the heart of the game , where the model takes in all the inputs ,decides the outcomes and then changes the model accordingly. 
	public void gamelogic(){
		playerLogic(); 
		enemyLogic();
		bulletLogic();
		gameLogic(); 
	}

	private void buyBullet(){
		if (!isAcceptedBigBullet()){
			setAcceptedBigBullet(true);
		}
	}

	private void buyHouse(){
		if (!isAcceptedHouse()){
			setAcceptedHouse(true);
		}
	}

	private void gameLogic() { 
		// this is a way to increment across the array list data structure 

		//check if they hit anything 
		// using enhanced for-loop style as it makes it alot easier both code wise and reading wise too 
		for (GameObject enemy : enemiesList){
			for (GameObject bullet : bulletList){
				if ( Math.abs(enemy.getCentre().getX() - bullet.getCentre().getX()) < enemy.getWidth() 
					&& Math.abs(enemy.getCentre().getY() - bullet.getCentre().getY()) < enemy.getHeight()){
						enemiesList.remove(enemy);
						bulletList.remove(bullet);
						setMoney(getMoney() + 1);
				}  
			}
		}

		//modify this to 2 adn remove > 0 for testing purposes
		if(money > 0 && money % 2 == 0 && !getShowBulletPopUp()){
			setShowBulletPopUp(true);
			setMoney(money - 20);
		}

		if(money > 0 && money % 45 == 0 && !getShowHousePopUp()){
			setShowHousePopUp(true);
			setMoney(money - 45);
		}

		//implement logic for when the game is over (human life == 0) -> big bug crawls from bottom screen + "GAME OVER" middle screen
		if (humanLife == 0){
			gameOver = true;
		}

	}

		// COME BACK TO THIS
	private boolean collision(GameObject enemy){
		float xCenter = enemy.getCentre().getX() + enemy.getWidth()/2.0f;
		float yCenter = enemy.getCentre().getY() + enemy.getHeight()/2.0f;

		float enemyTopLeft = enemy.getCentre().getX();
		float enemyTopRight = enemy.getCentre().getX() + enemy.getWidth();
		//float enemyTopLeft = enemy.getCentre().getY();
		float enemyBottomLeft = enemy.getCentre().getY() + enemy.getHeight();
		//float enemyBottomRight = enemy.getCentre().getY() + enemy.getHeight();

		float houseTopLeft = 45;
		float houseTopRight = 45 + 125;
		float houseBottomLeft = 75;
		float houseBottomRight = 75 + 185; 


		//collision if it's underneath the bottom line of the house
		//doesnt work if enemies come from the side
		if(xCenter >= 45 && xCenter <= 45 + 125 && yCenter <= 75 + 185){
			houseLife--;
			return true;
		}
		

		//collission with the player if it's underneath the player
		if(xCenter >= player.getCentre().getX() && xCenter <= player.getCentre().getX() + player.getWidth() && yCenter <= player.getCentre().getY() + player.getHeight()){
			humanLife--;
			return true;
		} 

		return false;
	}

	private void enemyLogic() {

		for (GameObject enemy : enemiesList){  
			//have 1 in 3 enemies traget the house, the rest target the player
			if(enemiesList.indexOf(enemy) % 2 == 0){

			float targetLocationX = 45 + 135/2;
			float targetLocationY = 75 + 185/2;

			float dx = targetLocationX - enemy.getCentre().getX();
			float dy = targetLocationY - enemy.getCentre().getY();

			//calculate distance between enemy and target location
			float length = (float) Math.sqrt(dx * dx + dy * dy);
			if (length != 0) {
					dx /= length;
			}
			enemy.getCentre().ApplyVector(new Vector3f(dx , -1, 0));

			} else{
				float targetLocationX = player.getCentre().getX();
				float targetLocationY = player.getCentre().getY();

				float dx = targetLocationX - enemy.getCentre().getX();
				float dy = targetLocationY - enemy.getCentre().getY();

				//calculate distance between enemy and target location
				float length = (float) Math.sqrt(dx * dx + dy * dy);
				if (length != 0) {
						dx /= length;
						//not needed but for completion purposes
						//dy /= length;
				}
				enemy.getCentre().ApplyVector(new Vector3f(dx , -1, 0));

				//remove if collion happens or it's at the top of the screen
				if (collision(enemy) || enemy.getCentre().getY() <= 1.0f){
					enemiesList.remove(enemy);
				}
			}
			//with while it adds more enemies into one frame, as oppsed to adding one each frame if there was only an if statment
			if (enemiesList.size() < 4)
			{
				while (enemiesList.size() < 4)
				{
					enemiesList.add(generateEnemy()); 
				}
			}
		}
	}

	private void bulletLogic() {	  

		for (GameObject bullet : bulletList){
			if(acceptedFasterBullet){
				bullet.getCentre().ApplyVector(new Vector3f(0,3,0));
			} else{
				bullet.getCentre().ApplyVector(new Vector3f(0,1,0));
			}
			//see if they get to the top of the screen ( remember 0 is the top )
			//anything more that aprox 900, the bullet gets stuck at the bottom
			if (bullet.getCentre().getY()==900){
			 	bulletList.remove(bullet);
			} 
		} 
	}

	private void playerLogic() {
		
		// smoother animation is possible if we make a target position  // done but may try to change things for students  
		//check for movement and if you fired a bullet 
		if(Controller.getInstance().isKeyAPressed()){player.getCentre().ApplyVector( new Vector3f(-2,0,0)); }
		if(Controller.getInstance().isKeyDPressed()){
			player.getCentre().ApplyVector( new Vector3f(2,0,0));
		}
		if(Controller.getInstance().isKeySpacePressed() || Mouse.getInstance().isMouseClicked()){
			createBullet();
			Controller.getInstance().setKeySpacePressed(false);
			Mouse.getInstance().setMouseClicked(false);
		} 	
	}

	private void createBullet() {
		if (acceptedBigBullet){
			bulletList.add(new GameObject("res/bullet2.png", 306, 600, new Point3f(player.getCentre().getX(), player.getCentre().getY(), 0.0f)));
		} else{
			bulletList.add(new GameObject("res/bullet.png",306,813,new Point3f(player.getCentre().getX(),player.getCentre().getY(),0.0f)));
		}
	}

	public Gam@@eObject getPlayer() {
		return player;
	}
	public CopyOnWriteArrayList<GameObject> getEnemies() {
		return enemiesList;
	}
	public CopyOnWriteArrayList<GameObject> getBullets() {
		return bulletList;
	}
	public int getMoney() { 
		return money;
	}
	public void setMoney(int newMoney){
		money = newMoney;
	}
	public int getHumanLife(){
		return humanLife;
	}
	public void setHumanLife(int newHumanLife){
		humanLife = newHumanLife;
	}
	public int getHouseLife(){
		return houseLife;
	}
	public void setHouseLife(int newHouseLife){
		houseLife = newHouseLife;
	}
	public boolean getShowHousePopUp(){
		return showHousePopUp;
	}
	public void setShowHousePopUp( boolean newPopUp){
		showHousePopUp = newPopUp;
	}
	public boolean getShowBulletPopUp(){
		return showBulletPopUp;
	}
	public void setShowBulletPopUp( boolean newPopUp){
		showBulletPopUp = newPopUp;
	}
  public boolean isAcceptedBigBullet() {
		return acceptedBigBullet;
	}
	public void setAcceptedBigBullet(boolean acceptedBigBullet) {
		this.acceptedBigBullet = acceptedBigBullet;
	}

	public boolean isAcceptedFasterBullet() {
		return acceptedFasterBullet;
	}
	public void setAcceptedFasterBullet(boolean acceptedFasterBullet) {
		this.acceptedFasterBullet = acceptedFasterBullet;
	}

	public boolean isAcceptedHouse() {
		return acceptedHouse;
	}
	public void setAcceptedHouse(boolean acceptedHouse) {
		this.acceptedHouse = acceptedHouse;
	}
	public boolean isGameOver(){
		return gameOver;
	}
	public void setGameOver(boolean gameOver){
		this.gameOver = gameOver;
	}
	public Controller getController(){
		return controller;
	}

}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/GameObject#