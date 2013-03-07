//import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.Enumeration;
import java.util.Vector;



public class RenderPanel extends Panel {
  private Scene scene_;
  private TransformationFactory tf_;
  private long time_;
  private double distance_;
  private int fpsCounter = 0;
  private double fpsAverager = 0;
  private boolean fpsOn_;
  private Dimension d_;
  private Image offscreen;
  private int oldsizex, oldsizey;
  private Vector lightSource;
  private Vector lightColor;
  private Vector lightOn;
  private Color ambient = new Color(10, 10, 10);
  public boolean shade = false;
  public boolean filled = false;
  public double I = 5;
  public double n = 10;
  public double k = .001;

  public RenderPanel(Scene s, double distance) {
    d_ = new Dimension(200, 200);
    time_ = System.currentTimeMillis();
    scene_ = s;
    tf_ = new TransformationFactory();
    distance_ = distance;
    fpsOn_ = false;
    addKeyListener(new KeyAdapter() {
      public void keyTyped(KeyEvent e) {
        char c = e.getKeyChar();
        if (c == 'r') {
          RenderPanel.this.toggleFPS();
        }
        if (c >= '0' && c <= '9') {
          RenderPanel.this.toggleLight(Integer.parseInt("" + c));
        }
        if (c == 'f') {
          RenderPanel.this.filled = !RenderPanel.this.filled;
        }
        if (c == 's') {
          RenderPanel.this.shade = !RenderPanel.this.shade;
        }
        if (c == 'p') {
          if (RenderPanel.this.n == 10) {
            RenderPanel.this.n = 0;
          } else {
            RenderPanel.this.n = 10;
          }
        }
        if (c== '+') {
          RenderPanel.this.I--;
          RenderPanel.this.I = Math.max(.01, RenderPanel.this.I);
        }
        if (c == '-') {
          RenderPanel.this.I++;
          RenderPanel.this.I = Math.min(100, RenderPanel.this.I);
        }
      }
    });
    offscreen = null;
    oldsizex = 200;
    oldsizey = 200;
    lightSource = new Vector();
    lightColor = new Vector();
    lightOn = new Vector();
  lightSource.addElement((new GVector(new double[] {-12, 45, -70,1})).getUnitVector());
    lightColor.addElement(new Color(150, 0, 25));
    lightOn.addElement(new Boolean(false));
  lightSource.addElement(new GVector(new double[] {1, 0, 0 ,1}));
    lightColor.addElement(new Color(255, 0, 0));
    lightOn.addElement(new Boolean(false));
  lightSource.addElement(new GVector(new double[] {1, 0, 0 ,1}));
    lightColor.addElement(new Color(0, 255, 0));
    lightOn.addElement(new Boolean(false));
  lightSource.addElement(new GVector(new double[] {1, 0, 0 ,1}));
    lightColor.addElement(new Color(0, 0, 255));
    lightOn.addElement(new Boolean(false));
  lightSource.addElement(new GVector(new double[] {0, 1, 0 ,1}));
    lightColor.addElement(new Color(255, 0, 0));
    lightOn.addElement(new Boolean(false));
  lightSource.addElement(new GVector(new double[] {0, 1, 0 ,1}));
    lightColor.addElement(new Color(0, 255, 0));
    lightOn.addElement(new Boolean(false));
  lightSource.addElement(new GVector(new double[] {0, 1, 0 ,1}));
    lightColor.addElement(new Color(0, 0, 255));
    lightOn.addElement(new Boolean(false));
  lightSource.addElement(new GVector(new double[] {0, 0, -1,1}));
    lightColor.addElement(new Color(127, 0, 0));
    lightOn.addElement(new Boolean(false));
  lightSource.addElement(new GVector(new double[] {0, 0, -1,1}));
    lightColor.addElement(new Color(0, 255, 0));
    lightOn.addElement(new Boolean(false));
  lightSource.addElement(new GVector(new double[] {0, 0, -1,1}));
    lightColor.addElement(new Color(0, 0, 255));
    lightOn.addElement(new Boolean(false));
    //  lightSource.addElement(new GVector(new double[] {-20, 0, 0 ,1}));
    //      	lightColor.addElement(new Color(0, 0, 127));
    //  	lightSource.addElement(new GVector(new double[] {0, -20, 0 ,1}));
    //   	lightColor.addElement(new Color(255, 0, 0));
    //    	lightSource.addElement(new GVector(new double[] {0, 0, -20,1}));
    //    	lightColor.addElement(new Color(0, 0, 255));
  }

  public void update(Graphics g) {
     paint(g);
  }



  private GObject perspectiveizeGObject(GObject gob) {
    //System.out.println("About to perspectiveize GObject");
    //  Enumeration e0 =  gob.pointElements();
    //  	while(e0.hasMoreElements()) {
    //  	    Point point = (Point) e0.nextElement();
    //  	    //System.out.println("\tAdding point " + point);
    //  	    Point newPoint = point.divideByW();
    //  	    //System.out.println("\tNew version is " + newPoint);
    //  	}
    Matrix Tper = tf_.buildPerspective(distance_);
    Matrix Ttrans = tf_.buildTranslation(0, 0, distance_);
    // GObject transformedgob = gob.globalTransform(Ttrans.multiply(Tper));
    GObject transformedgob = gob.globalTransform(Ttrans.multiply(Tper));
    GObject finalgob = new GObject();
    Enumeration e =  transformedgob.pointElements();
    //System.out.println("Perspectiveizing GObject");
    while(e.hasMoreElements()) {
      Point point = (Point) e.nextElement();
      //System.out.println("\tAdding point " + point);
      Point newPoint = point.divideByW();
      //System.out.println("\tNew version is " + newPoint);
      finalgob.addPoint(newPoint);
    }
    e = transformedgob.surfaceElements();
    while(e.hasMoreElements()) {
      finalgob.addSurface((Surface) e.nextElement());
    }
    return finalgob;
  }

  public void setScene(Scene s, View v) {
    scene_ = s;
    d_ = v.getSize();
    if (oldsizex != d_.width || oldsizey != d_.height) {
      this.setSize(d_);
      oldsizex = d_.width;
      oldsizey = d_.height;
      offscreen = createImage(d_.width, d_.height);
    }
  }

  private void doFPS(Graphics g) {
    long time = System.currentTimeMillis() - time_;
    time_ = System.currentTimeMillis();
    double fps = 1000/((double) time);
    fpsCounter++;
    double fpsave = (fpsAverager / (double) fpsCounter);
    if (fpsCounter > 100) {
      fpsCounter = 10;
      fpsAverager = 10*fpsave;
    }
    fpsAverager += fps;
    g.setColor(Color.green);
    g.drawString("FPS: " + fpsave, 1, 10);
  }

  public void toggleFPS() {
    fpsOn_ = !fpsOn_;
  }

  public void toggleLight(int light) {
    lightOn.setElementAt(new Boolean(!((Boolean) lightOn.elementAt(light)).booleanValue()), light);
  }

  public void paint(Graphics og) {
    if (offscreen == null) {
      offscreen = createImage(d_.width, d_.height);
    }
    Graphics g = offscreen.getGraphics();
    g.setColor(Color.black);
    g.fillRect(0, 0, d_.width, d_.height);
    if (fpsOn_)  doFPS(g);
    g.translate(d_.width/2, d_.height/2);
    Enumeration gobs = scene_.elements();
    while(gobs.hasMoreElements()) {
      GObject gob = (GObject) gobs.nextElement();
      gob.orderElements();
      //gob.surfaceElements();
      GObject finalgob = perspectiveizeGObject(gob);
      Enumeration surfaces = finalgob.surfaceElements();
      int surCount  =0;
      while(surfaces.hasMoreElements()) {
        Surface s = (Surface) surfaces.nextElement();
        Enumeration polygons = s.elements();
        while(polygons.hasMoreElements()) {
          Polygon poly = (Polygon) polygons.nextElement();
          Enumeration indexes = poly.elements();
          int nPoints = poly.size();
          //System.out.println("Drawing polygon with " + nPoints + " points");
          int[] xPoints = new int[nPoints];
          int[] yPoints = new int[nPoints];
          int counter = 0;
          while(indexes.hasMoreElements()) {
            int index = ((Integer) indexes.nextElement()).intValue();
            Point p = finalgob.getPoint(index);
            xPoints[counter] = (int) p.x();
            yPoints[counter] = (int) p.y();
            //g.setColor(new Color((float) Math.random(), (float) Math.random(),(float) Math.random()));
            //g.drawString(""+index, (int) p.x(), (int) p.y());
            //System.out.println("\tAdded point " + p);
            counter++;
          }
          if (shade) {
            g.setColor(gob.getColor(surCount++, lightSource, lightColor, lightOn, I, ambient, k, n));
          } else {
            g.setColor(s.getColor());
          }
          if (filled) {
            g.fillPolygon(xPoints, yPoints, nPoints);
          } else {
            g.drawPolygon(xPoints, yPoints, nPoints);
          }
        }
      }
      //finalgob = null;
    }
    super.paint(g);
    og.drawImage(offscreen, 0, 0, null);
    g.dispose();
  }
}















