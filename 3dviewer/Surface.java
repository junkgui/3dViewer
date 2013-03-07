import java.util.*;
import java.awt.Color;

public class Surface {
    private Vector collection;
    private Color color_;
    private Vector pointCollect;
    private Comparator bp = new Comparator() {
	public int compare(Object a, Object b) {
	    Polygon x = (Polygon) a;
	    Polygon y = (Polygon) b;
	    double averagex = x.getAverageZ(Surface.this.pointCollect);
	    double averagey = y.getAverageZ(Surface.this.pointCollect);
	    if  (averagex > averagey) {
               return -1;
            }
            return 1;
	}
    };

    public Surface() {
	collection = new Vector();
	color_ = Color.green;
    }

    public int size() {
	return collection.size();
    }

    public void addPolygon(Polygon p) {
	collection.addElement(p);
    }

    public Polygon getPolygon(int index) {
	return (Polygon) collection.elementAt(index);
    }

    public void setColor(Color c) {
	color_ = c;
    }

    public Color getColor() {
	return color_;
    }
    
    public double getAverageZ(Vector points) {
	pointCollect = points;
	Enumeration e = collection.elements();
	double average = 0;
	while (e.hasMoreElements()) {
	    Polygon p = (Polygon) e.nextElement();
	    average += p.getAverageZ(points);
	}
	return average/(double)points.size();
    }
	
    public Enumeration elements() {
	//System.out.println(collection.size());
	return collection.elements();
    }

    public Enumeration orderElements() {
	//VectorArray va = new VectorArray(collection);
	Collections.sort(collection, bp);
	return collection.elements();
    }
}









