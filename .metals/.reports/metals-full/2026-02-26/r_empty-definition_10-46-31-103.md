error id: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Mouse.java:java/lang/Exception#
file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Mouse.java
empty definition using pc, found symbol in pc: java/lang/Exception#
empty definition using semanticdb
empty definition using fallback
non-local guesses:

offset: 464
uri: file:///C:/Users/flore/Uni/Y4/Sem2/GameDev/bugShooter/Bug-shooter/src/Mouse.java
text:
```scala
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class Mouse implements MouseListener {

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
		} catch (@@Exception ex) {
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


  public static boolean isMouseClicked() {
    return mouseClickedVar;
  }

  public static void setMouseClicked(boolean mouseClickedVar) {
    Mouse.mouseClickedVar = mouseClickedVar;
  }

  public static int getMouseX() {
    return mouseX;
  }

  public static void setMouseX(int mouseX) {
    Mouse.mouseX = mouseX;
  }

  public static int getMouseY() {
    return mouseY;
  }

  public static void setMouseY(int mouseY) {
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

empty definition using pc, found symbol in pc: java/lang/Exception#