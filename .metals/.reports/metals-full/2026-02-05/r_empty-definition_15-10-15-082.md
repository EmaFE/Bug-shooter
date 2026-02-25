error id: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/Assignment1/src/Model.java:java/util/concurrent/CopyOnWriteArrayList#add().
file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/Assignment1/src/Model.java
empty definition using pc, found symbol in pc: java/util/concurrent/CopyOnWriteArrayList#add().
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 2813
uri: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/Assignment1/src/Model.java
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
	
	private GameObject Player;
	private Controller controller = Controller.getInstance();
	//some bugs have 2 lives, so they need to be shot twice + some are faster
	private CopyOnWriteArrayList<GameObject> EnemiesList  = new CopyOnWriteArrayList<GameObject>();
	private CopyOnWriteArrayList<GameObject> BulletList  = new CopyOnWriteArrayList<GameObject>();
	private int Score=0; 
	private int humanLife = 5;
	private int houseLife = 10;
	//you get +1 for each bug you kill, so even if you lose your house, you can buy it back if you kill enough bugs while trying to protect your house
	private int money = 0;

	public GameObject generateEnemy(){
		ArrayList<GameObject> enemies = new ArrayList<>();
		enemies.add(new GameObject("res/spider.png",70,70,new Point3f(((float)Math.random()*50+400 ),900,0)));
		enemies.add(new GameObject("res/fly.png",50,50,new Point3f(((float)Math.random()*50+500 ),900,0)));
		enemies.add(new GameObject("res/fly.png",50,50,new Point3f(((float)Math.random()*100+500 ),900,0)));
		enemies.add(new GameObject("res/spider.png",50,50,new Point3f(((float)Math.random()*100+400 ),900,0)));

		int index = (int)Math.random()*enemies.size();
		return enemies.get(index);
	}

	public Model() {
		//setup game world 
		//Player 
		Player= new GameObject("res/npcmaleidle.png",32,86,new Point3f(500,165,0));
		//Enemies  starting with four 
		
		EnemiesList.add@@(generateEnemy());
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

	private void gameLogic() { 
		// this is a way to increment across the array list data structure 

		//see if they hit anything 
		// using enhanced for-loop style as it makes it alot easier both code wise and reading wise too 
		for (GameObject enemy : EnemiesList){
			for (GameObject bullet : BulletList){
				if ( Math.abs(enemy.getCentre().getX()- bullet.getCentre().getX()) < enemy.getWidth() 
					&& Math.abs(enemy.getCentre().getY()- bullet.getCentre().getY()) < enemy.getHeight()){
						EnemiesList.remove(enemy);
						BulletList.remove(bullet);
						money++;
						Score++;
				}  
			}
		}
	}

	private void enemyLogic() {
		for (GameObject enemy : EnemiesList){ 
			  
			//move enemy up by one
			//DO - move them around
			enemy.getCentre().ApplyVector(new Vector3f(0,1,0));
			 
			//see if they get to the top of the screen ( remember 0 is the top 
			if (enemy.getCentre().getY()==125.0f){  // current boundary need to pass value to model 
				EnemiesList.remove(enemy);
				// enemies win so score decreased
				humanLife--;
				Score--;
			} 
		}
		/*
		 funtionality to be added: 
		 generate random number and based on that number, add a different bug to the enemies list (their size might differ)
		
		*/
		if (EnemiesList.size()<2)
		{
			while (EnemiesList.size()<6)
			{
				EnemiesList.add(new GameObject("res/UFO.png",50,50,new Point3f(((float)Math.random()*1000),900,0))); 
			}
		}
	}

	private void bulletLogic() {
		// move bullets 
	  
		for (GameObject bullet : BulletList){
		    //check to move them
			  
			bullet.getCentre().ApplyVector(new Vector3f(0,-1,0));
			//see if they hit anything 
			
			//see if they get to the top of the screen ( remember 0 is the top )
			//anything more that aprox 900, the bullet gets stuck at the bottom
			if (bullet.getCentre().getY()==900){
			 	BulletList.remove(bullet);
			} 
		} 
	}

	private void playerLogic() {
		
		// smoother animation is possible if we make a target position  // done but may try to change things for students  
		 
		//check for movement and if you fired a bullet 
		  
		if(Controller.getInstance().isKeyAPressed()){Player.getCentre().ApplyVector( new Vector3f(-2,0,0)); }
				
				if(Controller.getInstance().isKeyDPressed()){
					Player.getCentre().ApplyVector( new Vector3f(2,0,0));
				}
					
				// if(Controller.getInstance().isKeyWPressed()){
				// 	Player.getCentre().ApplyVector( new Vector3f(0,2,0));
				// }
				
				// if(Controller.getInstance().isKeySPressed()){Player.getCentre().ApplyVector( new Vector3f(0,-2,0));}
				
				if(Controller.getInstance().isKeySpacePressed()){
					CreateBullet();
					Controller.getInstance().setKeySpacePressed(false);
				} 	
	}

	private void CreateBullet() {
		BulletList.add(new GameObject("res/Bullet.png",32,64,new Point3f(Player.getCentre().getX(),Player.getCentre().getY(),0.0f)));
	}

	public GameObject getPlayer() {
		return Player;
	}

	public CopyOnWriteArrayList<GameObject> getEnemies() {
		return EnemiesList;
	}
	
	public CopyOnWriteArrayList<GameObject> getBullets() {
		return BulletList;
	}

	public int getScore() { 
		return Score;
	}
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: java/util/concurrent/CopyOnWriteArrayList#add().