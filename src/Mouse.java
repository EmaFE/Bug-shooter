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



  public boolean isMouseClicked() {
    return mouseClickedVar;
  }
  @Override
  public void mousePressed(MouseEvent e) {
    // TODO Auto-generated method stub
  
  }
  public void setMouseClicked(boolean mouseClickedVar) {
    Mouse.mouseClickedVar = mouseClickedVar;
  }
  public int getMouseX() {
    return mouseX;
  }
  public void setMouseX(int mouseX) {
    Mouse.mouseX = mouseX;
  }
  public int getMouseY() {
    return mouseY;
  }
  public void setMouseY(int mouseY) {
    Mouse.mouseY = mouseY;
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
