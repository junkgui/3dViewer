import java.awt.event.*;

public class MyMouseAdapter extends MouseAdapter { 
    private int x, y;
    public int px, py;
    public void mousePressed(MouseEvent e) {
	x = e.getX();
	y = e.getY();
    }
    public void mouseReleased(MouseEvent e) {
	px = e.getX()-x; 
	py = e.getY()-y;
    }
}
