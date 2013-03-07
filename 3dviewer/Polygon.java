//package linearAlgebra;

import java.util.Vector;
import java.util.Enumeration; 

public class Polygon {
    private Vector collection_;
    //private Color color
    public Polygon() {
	collection_ = new Vector();
    }

    public Polygon(int[] indexes) {
      collection_ = new Vector();
      for (int i = 0; i < indexes.length; i++) {
	collection_.addElement(new Integer(indexes[i]));
      }
    }
  
    public void addIndex(int p) {
	collection_.addElement(new Integer(p));
    }

    public int getIndex(int index) {
	return ((Integer) collection_.elementAt(index)).intValue();
    }

  public int size() {
    return collection_.size();
  }

    //public void setColor() {
    //	color = color;
    //}
    //public Color getColor() {
    //	return color;
    //}
    public double getAverageZ(Vector points) {
	Enumeration e = collection_.elements();
	double average = 0;
	while (e.hasMoreElements()) {
	    Integer i = (Integer) e.nextElement();
	    average += ((Point)points.elementAt(i.intValue())).z();
	}
	return average/(double)points.size();
    }

    public Enumeration elements() {
	return collection_.elements();
    }
}







