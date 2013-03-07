import java.util.Hashtable;
import java.util.LinkedList;
import java.util.Iterator;
import java.util.Enumeration;
import java.util.Vector;

public class PointFlyWeight {
    public Hashtable fly;
    public Vector pointList;
    public Point p;
    private int counter;
    public PointFlyWeight() {
	fly = new Hashtable();
	counter = 0;
        pointList = new Vector();
    }
    public int getIndex(Point p) {
        try {
          this.p = (Point) p.clone();
        } catch (Exception e) {
          e.printStackTrace();
        }
	Integer index = (Integer) fly.get(p.toString());
	//System.out.println("Flyweight index of " + p + " is " + index);
	if (index == null) {
            //System.out.println(p);
            //System.out.println((GVector) p);
            //System.out.println((Object) p);
            pointList.addElement(this.p);
	    index = new Integer(counter++);
	    fly.put(p.toString(), index);
	}
	//System.out.println("Returning " + index.intValue());
	return index.intValue();
    }
    public Enumeration keys() {
	return fly.keys();
    }
      
    public Iterator points() {
        //System.out.println("--------------------------------------------------------------------------------");
        return pointList.iterator();
    }
    
    public int size() {
	return fly.size();
    }

    public static void main(String[] args)
    {
	PointFlyWeight pfw = new PointFlyWeight();
	Point a = new Point(new double[] { 0, 0, 0, 1 });
	Point b = new Point(new double[] { 0, 0, 0, 1 });
	Point c = new Point(new double[] { 1, 0, 0, 1 });
	System.out.println("Index of a = " + pfw.getIndex(a));
	System.out.println("Index of c = " + pfw.getIndex(c));
	System.out.println("Index of b = " + pfw.getIndex(b));
    }
}
