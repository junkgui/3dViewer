import java.awt.*;
import java.awt.event.*;
import java.applet.*;
import java.util.Random;
import java.net.*;

public class Breakout extends Applet {
    private int x = 250;
    private int y = 250;
    private int height = 1;
    private int width = 1;
    private int theta = 0;
    private double slopeX = .30;
    private double slopeY = -.22;
    private int speed = 100;
    private int lastx = 0;
    private int lasty = 0;
    private int paddlex = 0;
    private int paddley = 670;
    private int lives = 5;
    private int sleepTime = 10;
    private int paddleHeight = 25;
    private int paddleWidth = 150;
    private int ballDiameter = 30;
    private Image bg, ball, paddle, brick1, brick2, brick3; 
    private Image offscreen;
    
    private MediaTracker mt;
	
    public void init() {
	try {
	    mt = new MediaTracker(this);
	    bg = getImage(getDocumentBase(), "images/bg.gif");
	    ball = getImage(getDocumentBase(), "images/ball.gif");
	    paddle = getImage(getDocumentBase(), "images/paddle.gif");
	    brick1 = getImage(getDocumentBase(), "images/brick1.gif");
	    brick2 = getImage(getDocumentBase(), "images/brick2.gif");
	    brick3 = getImage(getDocumentBase(), "images/brick3.gif");
	    mt.addImage(ball, 0);
	    mt.addImage(bg, 1);
	    mt.addImage(paddle, 2);
	    mt.addImage(brick1, 3);
	    mt.addImage(brick2, 4);
	    mt.addImage(brick3, 5);	  
	} catch (Exception e) {
	    e.printStackTrace();
	    System.exit(0);
	}
	addMouseListener(new MouseAdapter() {
		public void mousePressed(MouseEvent evt) {
		    Breakout.this.setLastX(evt.getX());
		    Breakout.this.setLastY(evt.getY());
		}
	    }
			 );
 	addMouseMotionListener (new MouseMotionAdapter () {
 		public void mouseMoved (MouseEvent evt) {
 		    Breakout.this.setPaddleX(evt.getX());
 		    Breakout.this.setPaddleY(evt.getY());
    		}
 	    }
 				);
	Thread t = new Thread() {
		public void run() {
		    try {
			Breakout.this.loadImages();
		    } catch (InterruptedException ie) {
			ie.printStackTrace();
		    }
		    while (true) {
			Breakout.this.repaint(0);
			try {
			    sleep(Breakout.this.getSleepTime());
			} catch (Exception e) {
			}
		    }
		}
	    };
	t.start();
    }

    public void setPaddleX(int value) {
	paddlex = value;
    }

    public void setPaddleY(int value) {
	paddley = value;
    }

    public void setLastX(int value) {
	lastx = value;
    }

    public void setLastY(int value) {
	lasty = value;
    }
    
    public void loadImages() throws InterruptedException {
	mt.waitForAll();
	bg = bg.getScaledInstance(getWidth(), getHeight(), Image.SCALE_SMOOTH);
    }

    public int getSleepTime() {
	return sleepTime;
    }

    public void update(Graphics g) {
        paint(g);

    }
    
    public void paint(Graphics gx) {
	//Graphics2D g2 = (Graphics2D) g;
	if (offscreen == null) {
	    offscreen = createImage(getWidth(), getHeight()); 
	}
	Graphics g = offscreen.getGraphics();
	g.drawImage(bg, 0, 0, null);

	Random r = new Random();

	int xMe = x;
	int yMe = y;

	g.drawImage(ball, xMe, yMe, null);
	
	x += slopeX*speed;
	y += slopeY*speed;

	if (x<0 || x > getWidth()-ballDiameter) {
	    slopeX = -slopeX;
	}
	if (y<0) {
	    slopeY = -slopeY;
	}
	if (paddlex < x+(ballDiameter/2)
	    && paddlex + paddleWidth > x
	    && y > getHeight()-(ballDiameter+paddleHeight)) {
	    //slopeX = ;
	    slopeY = -slopeY;
	}   
	if (y>getHeight()) {
	    if (lives > 0) {
		x=0;
		y=0;
		lives--;
	    } else {
		g.setFont(new Font("SansSerif", Font.BOLD, 30));
		g.drawString("Game Over", r.nextInt(700), r.nextInt(700));
		y= 1000;
	    }   
	}
		      
	height = 1;
	width = 1;
	g.drawImage(paddle, 
		    (int) Math.min((double) paddlex, (double) (getWidth() - paddleWidth)), 
		    getHeight()-paddleHeight, 
		    null);

	super.paint(g);
	gx.drawImage(offscreen, 0, 0, null);
	g.dispose();
    }
}

    
