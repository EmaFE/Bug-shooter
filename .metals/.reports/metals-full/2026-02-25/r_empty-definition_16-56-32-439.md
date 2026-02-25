error id: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Controller.java:java/awt/event/KeyEvent#getKeyChar().
file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Controller.java
empty definition using pc, found symbol in pc: java/awt/event/KeyEvent#getKeyChar().
found definition using semanticdb; symbol Controller#setKeyYpressed().
empty definition using fallback
non-local guesses:

offset: 2508
uri: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Controller.java
text:
```scala
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.util.ArrayList;
import java.util.HashMap;

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

//Singeton pattern
public class Controller implements KeyListener, MouseListener {
			
	private static boolean KeyAPressed= false;
	private static boolean KeyDPressed= false;
	private static boolean keyYpressed = false;
	private static boolean keyNpressed = false;
	private static boolean KeySpacePressed= false;
	private static boolean mouseClickedVar = false;

	private Model model;
	
	private static final Controller instance = new Controller();
	
	public Controller() {}
	// public Controller(Model model){
	// 	this.model = model;
	//}
	
	public static Controller getInstance(){
		return instance;
  }
	   
	@Override
	// Key pressed , will keep triggering 
	public void keyTyped(KeyEvent e) { 
		 
	}

	@Override
	public void keyPressed(KeyEvent e){ 
		switch (e.getKeyChar()) 
		{
			case 'a': setKeyAPressed(true);break;  
			case 'A': setKeyAPressed(true);break;
			case 'd': setKeyDPressed(true);break;
			case 'D': setKeyAPressed(true);break;
			case 'y': 
			case ' ': setKeySpacePressed(true);break;   
		    default:
		        break;
		}

		if(e.g@@etKeyChar() == 'y' || e.getKeyChar() == 'Y'){
			setKeyYpressed(true);
		} else if(e.getKeyChar() == 'n' || e.getKeyChar() == 'N'){
			setKeyNpressed(true);
		}

		if(e.getKeyChar() == 'y' || e.getKeyChar() == 'Y'){
			setKeyYpressed(true);
		} else if(e.getKeyChar() == 'n' || e.getKeyChar() == 'N'){
			setKeyNpressed(true);
		}

		// if (model.getShowBulletPopUp()){
		// 	if(e.getKeyChar() == 'y' || e.getKeyChar() == 'Y'){
		// 		setKeyYpressed(true);
		// 		model.setAcceptedBullet(true);
		// 		model.setShowBulletPopUp(false);
		// 	} else if(e.getKeyChar() == 'n' || e.getKeyChar() == 'N'){
		// 		setKeyNpressed(true);
		// 		model.setAcceptedBullet(false);
		// 		model.setShowBulletPopUp(false);
		// 	}
		// }

		// if (model.getShowHousePopUp()){
		// 	if(e.getKeyChar() == 'y' || e.getKeyChar() == 'Y'){
		// 		setKeyYpressed(true);
		// 		model.setAcceptedHouse(true);
		// 		model.setShowHousePopUp(false);
		// 	} else if(e.getKeyChar() == 'n' || e.getKeyChar() == 'N'){
		// 		setKeyNpressed(true);
		// 		model.setAcceptedHouse(false);
		// 		model.setShowHousePopUp(false);
		// 	}
		// }
	 // You can implement to keep moving while pressing the key here . 
	}

	@Override
	public void keyReleased(KeyEvent e) 
	{ 
		switch (e.getKeyChar()) 
		{
			case 'a': setKeyAPressed(false);break;  
			case 'A': setKeyAPressed(false); break;
			case 'd': setKeyDPressed(false);break;
			case 'D': setKeyAPressed(false); break;
			case 'y': setKeyYpressed(false); break;
			case 'Y': setKeyYpressed(false);break;
			case 'n': setKeyNpressed(false); break;
			case 'N': setKeyNpressed(false);break;
			case ' ': setKeySpacePressed(false);break;   
		    default:
		        break;
		} 
	}

	@Override
	public void mouseClicked(MouseEvent e) {
		try {
			setMouseClicked(true);
		} catch (Exception ex) {
			throw new UnsupportedOperationException("Unimplemented method 'mouseClicked'");
		}		
	}

	@Override
	public void mouseReleased(MouseEvent e) {
		try {
			setMouseClicked(false);
		} catch (Exception ex) {
			throw new UnsupportedOperationException("Unimplemented method 'mouseReleased'" + ex);
		}		
	}


	public boolean isKeyAPressed() {
		return KeyAPressed;
	}
	public void setKeyAPressed(boolean keyAPressed) {
		KeyAPressed = keyAPressed;
	}
	public boolean isKeyDPressed() {
		return KeyDPressed;
	}
	public void setKeyDPressed(boolean keyDPressed) {
		KeyDPressed = keyDPressed;
	}
	public boolean isKeySpacePressed() {
		return KeySpacePressed;
	}
	public void setKeySpacePressed(boolean keySpacePressed) {
		KeySpacePressed = keySpacePressed;
	}
	public boolean isMouseClicked(){
		return mouseClickedVar;
	}
	public void setMouseClicked(boolean newMouseClickedVar){
		mouseClickedVar = newMouseClickedVar;
	}
	public boolean isKeyYpressed(){
		return keyYpressed;
	}
	public void setKeyYpressed(boolean isPressed){
		keyYpressed = isPressed;
	}
	public boolean isKeyNpressed(){
		return keyYpressed;
	}
	public void setKeyNpressed(boolean isPressed){
		keyYpressed = isPressed;
	}

	@Override
	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
	}
	@Override
	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
	}

	@Override
	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
	} 	 
}
```


#### Short summary: 

empty definition using pc, found symbol in pc: java/awt/event/KeyEvent#getKeyChar().