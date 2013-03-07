
//package linearAlgebra;

/**
 * A basic GVector class for computer graphics.
 * <P>
 * This class supports all the common operations on Matrices
 * necessary for basic computer graphics work.  Instances of 
 * this class should be immutable, i.e., once you've created
 * a GVector you should <EM>not</EM> be able to change its
 * values in any way.
 * </P>
 * <P>
 * It is the responsibility of the caller to verify that any 
 * listed pre-conditions are satisfied before any of these
 * methods are called.  These methods will not (and 
 * should not) check those conditions.
 * </P>
 * <P>
 * Instead of having GVector inherit from Matrix, we've decided that 
 * we might instead use the Delegate Pattern (_Patterns in Java_
 * by Mark Grand).  In this pattern we will have GVector contain
 * a (private) instance of Matrix, and methods that GVector will
 * pass through appropriate calls to Matrix.
 * </P>
 * <P>
 * The name "GVector" was chosen instead of "Vector" to minimize
 * potential confusion with <CODE>java.util.Vector</CODE> which
 * is in no way related to this class.
 * </P>
 * @author Nic McPhee (design)
 * @author ??? (implementation)
 * @date September 1999
 * @course 3D Modelling, University of Minnesota, Morris
 * @see linearAlgebra.Matrix
 */

import java.util.Vector;
//import linearAlgebra.Matrix;

public class GVector
{
    /**
     * The private Matrix that this GVector class wraps.  See the 
     * comments above regarding the Delegate Pattern.
     */
    //private Matrix matrix_;
    protected double[] vector_;
    /**
     * Construct a GVector out of an array of doubles.
     * <P>
     * This requires as a pre-condition (i.e., we don't
     * have to check this) that the array is non-empty.
     */
    public GVector(double[] values) {
	vector_ = values;
    }

    public GVector(double[][] values) {
	vector_ = values[0];
    }

    public double[][] getArrays() {
	double[][] array = new double[1][];
	array[0] = vector_;
	return array;
    }

    /**
     * Return the number of elements in this GVector.
     */
    public int numElements() {
	return vector_.length;
    }
    
    /**
     * Return the specified element of the GVector.
     * <P>
     * This requires as a pre-condition that both
     * <CODE>n</CODE> is a legal value, i.e.,
     * <UL>
     *		<LI>0&lt;=n&lt;numElements()
     * </UL>
     * </P>
     */
    public double getElement(int n) {
	return vector_[n];
    }

    /**
     * Return the magnitude of this GVector.
     */
    public double magnitude() {
	double d = 0;
	for (int i = 0; i < vector_.length; i++) {
	    d += Math.pow(vector_[i], 2);
	}
	return Math.sqrt(d);
    }

    /**
     * Add two GVectors.
     * <P>
     * This requires as a pre-condition that the sizes
     * of the two GVectors be the same.
     * </P>
     * <P>
     * By the rule of immutability for this class, this
     * must return a <EM>new</EM> GVector, not simply
     * modify an existing GVector.
     * </P>
     */
    public static GVector add(GVector a, GVector b) {
	double c[] = new double[a.numElements()];
	for(int i = 0; i < a.numElements(); i++) {
	    c[i] = a.getElement(i) + b.getElement(i);
	}
	return new GVector(c);
    }
	
    public GVector getUnitVector() {
	double mag = this.magnitude();
	double[] newvec = new double[4];
	newvec[0] = vector_[0]/mag;
	newvec[1] = vector_[1]/mag;
	newvec[2] = vector_[2]/mag;
	newvec[3] = 1;
	//System.out.println("divbyw: " + (new Point(newvec)).toString());
	return new GVector(newvec);
    }
    /**
     * Add this GVector to another.
     * <P>
     * This requires as a pre-condition that the sizes
     * of the two GVectors be the same.
     * </P>
     * <P>
     * By the rule of immutability for this class, this
     * must return a <EM>new</EM> GVector, not simply
     * modify an existing GVector.
     * </P>
     */
    public GVector add(GVector other) {
	double a[] = new double[vector_.length];
	for (int i = 0; i < vector_.length; i++ ){
	    a[i] = other.getElement(i) + vector_[i];
	}
	return new GVector(a);
    }

    /**
     * Subtract two GVectors.
     * <P>
     * This requires as a pre-condition that the sizes
     * of the two GVectors be the same.
     * </P>
     */
    public static GVector subtract(GVector a, GVector b) {
	double c[] = new double[a.numElements()];
	for(int i = 0; i < a.numElements(); i++) {
	    c[i] = a.getElement(i)- b.getElement(i);
	}
	return new GVector(c);
    }
    /**
     * Subtract another GVector from this one..
     * <P>
     * This requires as a pre-condition that the sizes
     * of the two GVectors be the same.
     * </P>
     * <P>
     * By the rule of immutability for this class, this
     * must return a <EM>new</EM> GVector, not simply
     * modify an existing GVector.
     * </P>
     * @return <CODE>(this - other)</CODE>
     */
    public GVector subtract(GVector other) {
	double a[] = new double[vector_.length];
	for (int i = 0; i < vector_.length; i++ ){
	    a[i] = vector_[i] - other.getElement(i);
	}
	return new GVector(a);
    }
	    
    
    /**
     * Multiply a scalar and a GVector.
     * <P>
     * By the rule of immutability for this class, this
     * must return a <EM>new</EM> GVector, not simply
     * modify an existing GVector.
     * </P>
     */
    public static GVector multiply(double scalar, GVector a) {
	double b[] = new double[a.numElements()];
	for (int i = 0; i < a.numElements(); i++ ){
	    b[i] = a.getElement(i) *scalar;
	}
	return new GVector(b);
    }
    /**
     * Multiply this GVector by a scalar.
     * <P>
     * By the rule of immutability for this class, this
     * must return a <EM>new</EM> GVector, not simply
     * modify an existing GVector.
     * </P>
     */
    public GVector multiply(double scalar) {
	return GVector.multiply(scalar, this);
    }
    
    /**
     * Multiply a GVector by a Matrix.
     * <P>
     * This requires as a pre-condition that the size of
     * the GVector is the same as the number rows of the 
     * Matrix.
     * </P>
     * <P>
     * By the rule of immutability for this class, this
     * must return a <EM>new</EM> GVector, not simply
     * modify an existing GVector.
     * </P>
     */
    public static GVector multiply(GVector v, Matrix m) {
	Matrix vec = new Matrix(v.getArrays());
	return new GVector(Matrix.multiply(vec, m).getArrays());
    }

    /**
     * Multiply this GVector by a Matrix.
     * <P>
     * This requires as a pre-condition that the size of
     * the GVector is the same as the number rows of the 
     * Matrix.
     * </P>
     * <P>
     * By the rule of immutability for this class, this
     * must return a <EM>new</EM> GVector, not simply
     * modify an existing GVector.
     * </P>
     */
    public GVector multiply(Matrix m) {
	Matrix vec = new Matrix(this.getArrays());
	return new GVector(Matrix.multiply(vec, m).getArrays());
    }

    /**
     * Compute the dot product of two GVectors.
     * <P>
     * This requires as a pre-condition that the size of
     * the two GVectors be the same.
     * </P>
     */
    public static double dotProduct(GVector a, GVector b) {
	double dotprod = 0;
	for (int i =0; i < a.numElements(); i++) {
	    dotprod += a.getElement(i) * b.getElement(i);
	}
	return dotprod;
    }

    /**
     * Compute the dot product of this and another GVector.
     * <P>
     * This requires as a pre-condition that the size of
     * the two GVectors be the same.
     * </P>
     */
    public double dotProduct(GVector other) {
	return dotProduct(this, other);
    }

    /**
     * Compute the cross product of two GVectors.
     * <P>
     * This requires as a pre-condition that the size of
     * the two GVectors be the same.
     * </P>
     */

    private static int mod(int n, int m) {
	int mod = n%m;
	if (mod < 0 ) {
	    mod = m + mod;
	}
	return mod;
    }

    public static GVector crossProduct(GVector a, GVector b) {
	int size = a.numElements()-1;
	double[] acrossb = new double[size+1];
	for(int i =0; i <size; i++) {
	    acrossb[i]=a.getElement(mod(i+1, size)) *b.getElement(mod(i-1, size)) - a.getElement(mod(i-1, size)) * b.getElement(mod(i+1,size));
	}
	acrossb[size] = 1;
	return new GVector(acrossb);
    }
    /**
     * Compute the cross product of this and another GVector.
     * <P>
     * This requires as a pre-condition that the size of
     * the two GVectors be the same.
     * </P>
     */
    public GVector crossProduct(GVector other) {
	return (crossProduct(this, other));
    }

    /**
     * Generate a printed representation of this GVector.
     */
    public String toString() {
	return (new Matrix(this.getArrays())).toString();
    }
    
    /**
     * Is this GVector equal to another Object?
     * <P>
     * The GVector is equal to <CODE>other</CODE> if the
     * <CODE>other</CODE> is also a GVector, the two
     * GVectors have the same size, and are equal
     * element by element.
     * </P>
     */
    public boolean equals(Object other) {
	if (other instanceof GVector) {
	    return this.equals((GVector) other);
	} else if (other instanceof Matrix) {
	    return ((Matrix) other).equals(new Matrix(this.getArrays()));
	} else return false;
    }

    /**
     * Is this GVector equal to another GVector?
     * <P>
     * The GVector is equal to <CODE>other</CODE> if the two
     * GVectors have the same size, and are equal
     * element by element.
     * </P>
     */
    public boolean equals(GVector other) {
	if (hashCode() == other.hashCode() && vector_.length == other.numElements()) {
	    for (int i = 0; i < vector_.length; i++) {
		if (vector_[i] != other.getElement(i)) {
		    return false;
		}
	    }
	    return true;
	} else return false;
    }
    
    /**
     * Return a hashCode for this GVector.
     * <P>
     * It's crucial here that two <CODE>equals()</CODE>
     * GVectors have the same <CODE>hashCode</CODE>.
     * </P>
     */
    public int hashCode() {
	return this.toString().hashCode();
    }
}












