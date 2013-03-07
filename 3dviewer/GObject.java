   import java.util.*;
import java.awt.Color;

public class GObject {
  Vector pointCollect;
  Vector surfaceCollect;
  private Comparator bp = new Comparator() {
    public int compare(Object a, Object b) {
      Surface x = (Surface) a;
      Surface y = (Surface) b;
      double averagex = x.getAverageZ(GObject.this.pointCollect);
      double averagey = y.getAverageZ(GObject.this.pointCollect);
      if(averagex > averagey) {
        return -1;
      }
      return 1;
    }
  };

  public GObject() {
    pointCollect = new Vector();
    surfaceCollect = new Vector();
  }


  public GObject(Vector pC, Vector sC) {

    pointCollect = pC;

    surfaceCollect = sC;

  }


  public int addPoint(Point p ) {
    pointCollect.addElement(p);
    return pointCollect.size()-1;
  }

  public void addSurface(Surface s) {
    surfaceCollect.addElement(s);
  }

  public Point getPoint(int index) {
    return (Point) pointCollect.elementAt(index);
  }

  public Color getColor(int surfaceIndex,
  Vector lightDirection,
  Vector lightColor,
  Vector lightOn,
  double intensity,
  Color ambient,
  double specint,
  double n) {
    //Find the normal of the polygon
    Surface s = (Surface) surfaceCollect.elementAt(surfaceIndex);
    Polygon p = (Polygon) s.getPolygon(0);
    Point a = (Point) pointCollect.elementAt(p.getIndex(0));
    Point b = (Point) pointCollect.elementAt(p.getIndex(1));
    Point c = (Point) pointCollect.elementAt(p.getIndex(2));
    GVector v1 = a.subtract(b);
    GVector v2 = c.subtract(b);
    GVector normal = GVector.crossProduct(v2, v1);
    if (normal.getArrays()[0][2] > 0.0) {
      normal = GVector.crossProduct(v1, v2);
    }
    //start with ambient light

    normal = normal.getUnitVector();
    double red = ((double) ambient.getRed())/255.0;
    double green = ((double) ambient.getGreen())/255.0;
    double blue = ((double) ambient.getBlue())/255.0;
    //Enumerate thought the light source
    Enumeration lightD = lightDirection.elements();
    Enumeration lightC = lightColor.elements();
    Enumeration lightO = lightOn.elements();
    while (lightD.hasMoreElements() && lightC.hasMoreElements() && lightO.hasMoreElements()) {
      if (((Boolean) lightO.nextElement()).booleanValue()) {
        GVector ld = (GVector) lightD.nextElement();
        Color lc = (Color) lightC.nextElement();
        //Compute H
      GVector V = new GVector(new double[] {0, 0, -1, 1});
        GVector H = (GVector.add(V, ld)).multiply(.5);
        double ndoth = Math.pow(GVector.dotProduct(H, normal), n)*specint;
        //find the dot product, adjust for the size
        double ldotn = (GVector.dotProduct(ld, normal))/intensity;
        red += Math.max(((double) lc.getRed())/255.0 *(ldotn+ndoth), 0);
        green += Math.max(((double) lc.getGreen())/255.0*(ldotn+ndoth), 0);
        blue += Math.max(((double) lc.getBlue())/255.0*(ldotn+ndoth), 0);
      } else {
        lightD.nextElement();
        lightC.nextElement();
      }
    }
    if (red > 1) red = 1;
    if (blue > 1) blue = 1;
    if (green > 1) green = 1;
    return new Color((float) red , (float) green, (float) blue);
  }
  public Surface getSurface(int index) {
    return (Surface) surfaceCollect.elementAt(index);
  }

  public int numSurface() {
    return surfaceCollect.size();
  }

  public Enumeration surfaceElements() {
    //System.out.println("Surface: " + surfaceCollect.size());
    return surfaceCollect.elements();
  }

  public Enumeration pointElements() {
    return pointCollect.elements();
  }

  public Enumeration orderElements() {
    //VectorArray va = new VectorArray(surfaceCollect);
    Collections.sort(surfaceCollect, bp);
    return surfaceCollect.elements();
  }
  public GObject globalTransform(Matrix m) {
    GObject gob = new GObject(new Vector(), this.surfaceCollect);
    Enumeration e = pointCollect.elements();
    while(e.hasMoreElements()) {
      Point p = (Point) e.nextElement();
      GVector gv = ((GVector) p).multiply(m);
      Point newP = new Point(gv.getArrays()[0]);
      gob.addPoint(newP);
    }
    //e = surfaceCollect.elements();
    //while(e.hasMoreElements()) {
    //  gob.addSurface((Surface) e.nextElement());
    //}
    return gob;
  }
}






