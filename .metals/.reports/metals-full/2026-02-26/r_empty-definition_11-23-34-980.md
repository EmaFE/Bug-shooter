error id: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Mouse.java:local7
file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Mouse.java
empty definition using pc, found symbol in pc: 
found definition using semanticdb; symbol local7
empty definition using fallback
non-local guesses:

offset: 1599
uri: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Mouse.java
text:
```scala
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

  private static Mouse instance;

  private Mouse(){}

  public static Mouse getInstance(){
		if (instance == null) instance = new Mouse();
		return instance;
  }
	   

  private static boolean mouseClickedVar = false;
	private static int mouseX;
  private static int mouseY;

  @Override
  public void mouseClicked(MouseEvent e) {
    try {
			setMouseClicked(true);
			System.out.println("clicked from Controller: " + isMouseClicked());
			mouseX = e.getX();
			mouseY = e.getY();
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

  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'mousePressed'");
  }


  public boolean isMouseClicked() {
    return mouseClickedVar;
  }

  public void setMouseClicked(boolean mouseClickedVar) {
    Mouse.mouseClickedVar = mouseClickedVar;
  }

  public int getMouseX() {
    return mouseX;
  }

  public static+++ void setMouseX(int mouseX) {
    Mouse.mouseX = mouseX;
  }

  public static int getMouseY() {
    return mouseY;
  }

  public static void setMouseY(int@@ mouseY) {
    Mouse.mouseY = mouseY;
  }

  @Override
  public void mouseEntered(MouseEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'mouseEntered'");
  }

  @Override
  public void mouseExited(MouseEvent e) {
    // TODO Auto-generated method stub
    throw new UnsupportedOperationException("Unimplemented method 'mouseExited'");
  }
  
}

```


#### Short summary: 

empty definition using pc, found symbol in pc: 