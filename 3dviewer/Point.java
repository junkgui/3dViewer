//package linearAlgebra;

public class Point extends GVector{
    // private double[] myvector;
    public Point(double[] values) {
	super(values);
	// myvector = values;
    }
    
    //this is a hack 
    public Point divideByW() {
	double[] newvec = new double[4];
	newvec[0] = vector_[0]/vector_[3];
	newvec[1] = vector_[1]/vector_[3];
	newvec[2] = vector_[2]/vector_[3];
	newvec[3] = 1;
	//System.out.println("divbyw: " + (new Point(newvec)).toString());
	return new Point(newvec);
    }

    public double x() {
	return vector_[0];
    }
    
    public double y() {
	return vector_[1];
    }
    
    public double z() {
	return vector_[2];
    }

    public Object clone() {
      return new Point(new double[] {vector_[0], vector_[1], vector_[2], vector_[3]});
    }

    public boolean equals(Object other) {
	//System.out.print("Comparing " + this + " with " + other);
	boolean result;
	if (other instanceof Point) {
	    // return(equals((Point) other));
	    result = equals((Point) other);
	} else {
	    // return(false);
	    result = false;
	}
	//System.out.println(" and answer was " + result);
	return(result);
    }
    
    private boolean nearlyEquals(double a, double b) {
	final double epsilon = 0.001;
	return(Math.abs(a-b) < epsilon);
    }

    public boolean equals(Point p) {
	// return ((vector_[0] == p.x()) && (vector_[1] == p.y()) && (vector_[2] == p.z()));
	return (nearlyEquals(vector_[0], p.x()) && nearlyEquals(vector_[1], p.y()) && nearlyEquals(vector_[2], p.z()));
    }
    
    public String toString() {
	return new String("(" + vector_[0] + ", " + vector_[1] + ", " + vector_[2] + ", "  + vector_[3] + ")");
    }

    public int hashCode() {
	int result = 0;
	for (int i=0; i<4; ++i) {
	    result = 1000*result + ((int) (499 * vector_[i]));
	}
	//System.out.println("HashCode of " + this + " is " + result);
	return result;
    }
}






