import java.awt.event.*;
import java.awt.*;
import java.io.*;
import java.util.Vector;
import java.util.Enumeration;
import java.util.Iterator;

public class View extends Frame {
  private static RotateThread rt;
  private static MouseAdapter ma_;
  private RenderPanel pict_;
  private TransformationFactory tf_  = new TransformationFactory();
  public View(Scene s, Renderer r) {
    pict_ = (RenderPanel) r.render(s);
    add(pict_);
    addWindowListener(new WindowAdapter() {
      public void windowClosing(WindowEvent evt) {
        System.exit(0);
      }
    });
    setTitle("View");
    setSize(200, 200);
    show();
  }
  private static Scene getDefaultCube() {
    GObject gob = new GObject();
  Point p0 = new Point(new double[] {-25, 25, -25, 1});
    int p0index = gob.addPoint(p0);
  Point p1 = new Point(new double[] {-25, -25, -25, 1});
    int p1index = gob.addPoint(p1);
  Point p2 = new Point(new double[] {25, -25, -25, 1});
    int p2index = gob.addPoint(p2);
  Point p3 = new Point(new double[] {25, 25, -25, 1});
    int p3index = gob.addPoint(p3);
  Point p4 = new Point(new double[] {-25, 25, 25, 1});
    int p4index = gob.addPoint(p4);
  Point p5 = new Point(new double[] {-25, -25, 25, 1});
    int p5index = gob.addPoint(p5);
  Point p6 = new Point(new double[] {25, -25, 25, 1});
    int p6index = gob.addPoint(p6);
  Point p7 = new Point(new double[] {25, 25, 25, 1});
    int p7index = gob.addPoint(p7);
    Surface s0 = new Surface();
  Polygon poly0 = new Polygon(new int[] {p0index, p1index, p2index, p3index});
    s0.addPolygon(poly0);
    s0.setColor(Color.blue);
    gob.addSurface(s0);
    Surface s1 = new Surface();
  Polygon poly1 = new Polygon(new int[] {p0index, p3index, p7index, p4index});
    s1.addPolygon(poly1);
    s1.setColor(Color.red);
    gob.addSurface(s1);
    Surface s2 = new Surface();
  Polygon poly2 = new Polygon(new int[] {p4index, p5index, p6index, p7index});
    s2.addPolygon(poly2);
    s2.setColor(Color.orange);
    gob.addSurface(s2);
    Surface s3 = new Surface();
  Polygon poly3 = new Polygon(new int[] {p1index, p2index, p6index, p5index});
    s3.addPolygon(poly3);
    s3.setColor(Color.magenta);
    gob.addSurface(s3);
    Surface s4 = new Surface();
  Polygon poly4 = new Polygon(new int[] {p0index, p1index, p5index, p4index});
    s4.addPolygon(poly4);
    s4.setColor(Color.yellow);
    gob.addSurface(s4);
    Surface s5 = new Surface();
  Polygon poly5 = new Polygon(new int[] {p3index, p2index, p6index, p7index});
    s5.addPolygon(poly5);
    gob.addSurface(s5);
    Scene s = new Scene();
    s.add(gob);
    return s;
  }
  public static void main(String [] args) {
    //args = new String[] {"C:/java/teapot.tri"};
    TransformationFactory tf = new TransformationFactory();
    PointFlyWeight pfw = new PointFlyWeight();
    Scene s = new Scene();
    if (args.length == 0) {
      s = View.getDefaultCube();
    }
    int file = 0;
    try {
      Matrix m;
      int counter = 0;
      //Surface sur = new Surface();
      for (int i = 0; i < args.length; i++) {
        GObject gob = new GObject();
        file = i;
        StreamTokenizer st = new StreamTokenizer(new InputStreamReader(ClassLoader.getSystemResourceAsStream("teapot.tri")));
        st.parseNumbers();
        try {
          if (st.nextToken()==StreamTokenizer.TT_WORD) {
            while(st.sval.equals("triangle") && st.nextToken() != StreamTokenizer.TT_EOF) {
              Surface sur = new Surface();
              Polygon pol = new Polygon();
              double[] point = new double[4];
              for (int k = 0; k < 3; k++) {
                st.nextToken();
                for(int j = 0; j < 3; j++) {
                  st.nextToken();
                  st.nextToken();
                  point[j] = st.nval;
                }
                point[3] = 1;
                Point p = new Point(point);
                int index = pfw.getIndex(p);
                //System.out.println("Index of " + p + " is " + counter+ " or " + index);
                //if (counter > index) {
                //  pol.addIndex(index);
                //}else if (counter == index) {
                  //   pol.addIndex(gob.addPoint(p));
                  //gob.addPoint(p);
                  pol.addIndex(index);
                //  counter++;
                //}
                //System.out.print(index + " " + p.toString() + " ");
                st.nextToken();
              }
              //System.out.print("\n");
              st.nextToken();
              sur.addPolygon(pol);
              //System.out.println("--------------------Poly------------------");
              sur.setColor(new Color((float) Math.random(), (float) Math.random(),(float) Math.random()));
              //sur.setColor(Color.white);
              gob.addSurface(sur);
            }
          }
        } catch (Exception f) {
          System.out.println("Parse error at line: " + st.lineno() + " because " + f+ " was thrown.");
        }
        Iterator pit = pfw.points();
        while(pit.hasNext()){
          Point x = (Point) pit.next();
          //System.out.println(x);
          gob.addPoint(x);
        }
        m = tf.buildTranslation(0, 0, -1.5);
        gob = gob.globalTransform(m);
        m = tf.buildUniformScaling(30);
        gob = gob.globalTransform(m);
        s.add(gob);
      }
    } catch (Exception e) {
      System.out.println("The file " + args[file] + " was not found.");
      //System.exit(0);
    }
    pfw = null;
    double distance = 450;
    SimpleRenderer r = new SimpleRenderer(distance);
    View v = new View(s, r);
    ma_ = new MyMouseAdapter();
    r.getPanel().addMouseListener(ma_);
    rt = new RotateThread(s, r.getPanel(), ma_, v);
    rt.start();
    rt.yield();
  }
}



















