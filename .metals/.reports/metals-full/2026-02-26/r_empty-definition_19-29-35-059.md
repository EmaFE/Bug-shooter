error id: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Controller.java:Controller#
file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Controller.java
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol Controller#
empty definition using fallback
non-local guesses:

offset: 1739
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
public class Controller implements KeyListener {
			
	private static boolean KeyAPressed= false;
	private static boolean KeyDPressed= false;
	private static boolean keyYpressed = false;
	private static boolean keyNpressed = false;
	private static boolean KeySpacePressed= false;
	
	private static Controller@@ instance;
	
	private Controller() {}
	
	public static Controller getInstance(){
		if (instance == null) instance = new Controller();
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
			case 'y': setKeyYpressed(true); break;
			case 'Y': setKeyYpressed(true); break;
			case 'n': setKeyNpressed(true); break;
			case 'N': setKeyNpressed(true); break;
			case ' ': setKeySpacePressed(true);break;   
		    default:
		        break;
		}
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
	public boolean isKeyYpressed(){
		
		return keyYpressed;
	}
	public void setKeyYpressed(boolean isPressed){
		System.out.println("py in contoller: " + isKeyYpressed());
		keyYpressed = isPressed;
	}
	public boolean isKeyNpressed(){
		return keyNpressed;
	}
	public void setKeyNpressed(boolean isPressed){
		keyNpressed = isPressed;
	}

}
```


#### Short summary: 

empty definition using pc, found symbol in pc: 