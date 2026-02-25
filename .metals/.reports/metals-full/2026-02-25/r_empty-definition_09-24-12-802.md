error id: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Model.java:_empty_/GameObject#getCentre#getY#
file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Model.java
empty definition using pc, found symbol in pc: _empty_/GameObject#getCentre#getY#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 5423
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
	private int score=0; 
	private int humanLife = 5;
	private int houseLife = 10;
	//you get +1 for each bug you kill, so even if you lose your house, you can buy it back if you kill enough bugs while trying to protect your house
	private int money = 0;

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
		// Player Logic first 
		playerLogic(); 
		// Enemy Logic next
		enemyLogic();
		// Bullets move next 
		bulletLogic();
		// interactions between objects 
		gameLogic(); 
	}

	private boolean collision(GameObject enemy){
		float xCenter = enemy.getCentre().getX() + enemy.getWidth()/2.0f;
		float yCenter = enemy.getCentre().getY() + enemy.getHeight()/2.0f;

		//collision if it's both in center with the house AND underneath the bottom line of the house
		if(xCenter >= 45 && xCenter <= 45 + 135 && yCenter <= 75 + 185){
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


	private void gameLogic() { 
		// this is a way to increment across the array list data structure 

		//see if they hit anything 
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
	}

	private void enemyLogic() {

		for (GameObject enemy : enemiesList){ 
			//see if they get to the top of the screen  
		 	if (enemy.getCentre().getY() <= 1.0f){ 
			 	enemiesList.remove(enemy);
			} else {
				float targetLocationX = player.getCentre().getX();
				float targetLocationY = player.getCentre().getY();

				float dx = targetLocationX - enemy.getCentre().getX();
				float dy = targetLocationY - enemy.getCentre().getY();

				//if the enemy has not reached the player, move enemy up by one towards target
				//dont let the enemy reach ABOVE the player and follow arounf (wont be able to shoot)
				float length = (float) Math.sqrt(dx * dx + dy * dy);
				if (length != 0) {
						dx /= length;
						dy /= length;
				}

				if(enemy.getCentre().getY() - player.getCentre().ge@@tY() + player.getHeight() > 1.0f){
					enemy.getCentre().ApplyVector(new Vector3f(dx * 1 , dy * 1, 0));
				}

				if (collision(enemy)){
					enemiesList.remove(enemy);
				}

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

	private void bulletLogic() {
		// move bullets 
	  
		for (GameObject bullet : bulletList){
		    //check to move them
			  
			bullet.getCentre().ApplyVector(new Vector3f(0,-1,0));
			//see if they hit anything 
			
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
				
				if(Controller.getInstance().isKeySpacePressed()){
					CreateBullet();
					Controller.getInstance().setKeySpacePressed(false);
				} 	
	}

	private void CreateBullet() {
		bulletList.add(new GameObject("res/Bullet.png",32,64,new Point3f(player.getCentre().getX(),player.getCentre().getY(),0.0f)));
	}

	public GameObject getPlayer() {
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
		return humanLife;
	}
	public void setHouseLife(int newHumanLife){
		humanLife = newHumanLife;
	}
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: _empty_/GameObject#getCentre#getY#