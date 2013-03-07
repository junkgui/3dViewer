import java.awt.*;
import java.applet.*;

public class AnimationThread extends Thread {
    public Applet applet;
    public AnimationThread(Applet a) {
	applet = a;
    }
    public void run() {
	applet.repaint(0);
	try {
	    this.sleep(1000);
	} catch (Exception e) {
	}
    }
}
    
