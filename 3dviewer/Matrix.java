
//package linearAlgebra;

/**
 * A basic Matrix class for computer graphics.
 * <P>
 * This class supports all the common operations on Matrices
 * necessary for basic computer graphics work.  Instances of 
 * this class should be immutable, i.e., once you've created
 * a Matrix you should <EM>not</EM> be able to change its
 * values in any way.
 * </P>
 * <P>
 * It is the responsibility of the caller to verify that any 
 * listed pre-conditions are satisfied before any of these
 * methods are called.  These methods will not (and 
 * should not) check those conditions.
 * </P>
 * @author Nic McPhee (design)
 * @author Erik Hadden (implementation)
 * @date September 1999
 * @course 3D Modelling, University of Minnesota, Morris
 * @see linearAlgebra.Vector
 */
public class Matrix {
    private double values_[][];
    
    /**
     * Construct a Matrix out of a 2D array of doubles.
     * <P>
     * This requires as a pre-condition (i.e., we don't
     * have to check this) that the array is non-empty
     * and rectangular.  This means, for example, that
     * the length of <CODE>values[0]</CODE> must be the same as the
     * length for <CODE>values[1]</CODE>, etc.
     */
    public Matrix(double[][] values) {
	//values_ = (double[][]) values.clone();
	values_ = values;
    }
    /**
     * Return the number of rows in this Matrix.
     */

    public double[][] getArrays() {
	//return (double[][]) values_.clone();
	return values_;
    }

    public int numColumns() {
	return values_[0].length;
    }
    /**
     * Return the number of columns in this Matrix.
     */
    public int numRows() {
	return values_.length;
    }

    /**
     * Return the specified element of the Matrix.
     * <P>
     * This requires as a pre-condition that both
     * <CODE>row</CODE> and <CODE>column</CODE> are
     * legal values, i.e.,
     * <UL>
     *		<LI>0&lt;=row&lt;numRows()
     *		<LI>0&lt;=column&lt;numColumns()
     * </UL>
     * </P>
     */
    public double getElement(int row, int column) {
	return values_[row][column];
    }
    
    /**
     * Add two Matrices.
     * <P>
     * This requires as a pre-condition that the sizes
     * of the two Matrices be the same.
     * </P>
     * <P>
     * By the rule of immutability for this class, this
     * must return a <EM>new</EM> Matrix, not simply
     * modify an existing Matrix.
     * </P>
     */
    public static Matrix add(Matrix a, Matrix b) {
	double matrix[][] = new double[a.numRows()][a.numColumns()];
	for(int i = 0; i < a.numRows(); i++) {
	    for(int j = 0; j < a.numColumns(); j++) {
		matrix[i][j] = a.getElement(i, j) + b.getElement(i, j);
	    }
	}
	return new Matrix(matrix);
    }

    /**
     * Add this Matrix to another.
     * <P>
     * This requires as a pre-condition that the sizes
     * of the two Matrices be the same.
     * </P>
     * <P>
     * By the rule of immutability for this class, this
     * must return a <EM>new</EM> Matrix, not simply
     * modify an existing Matrix.
     * </P>
     */
    public Matrix add(Matrix other) {
	double matrix[][] = values_;
	for(int i = 0; i < this.numRows(); i++) {
	    for(int j = 0; j < this.numColumns(); j++) {
		matrix[i][j] += other.getElement(i, j);
	    }
	}
	return new Matrix(matrix);
    }
    
    /**
     * Subtract two Matrices.
     * <P>
     * This requires as a pre-condition that the sizes
     * of the two Matrices be the same.
     * </P>
     */
    public static Matrix subtract(Matrix a, Matrix b) {
	double matrix[][] = new double[a.numRows()][a.numColumns()];
	for(int i = 0; i < a.numRows(); i++) {
	    for(int j = 0; j < a.numColumns(); j++) {
		matrix[i][j] = a.getElement(i, j) - b.getElement(i, j);
	    }
	}
	return new Matrix(matrix);
    }
   
    /**
     * Subtract another Matrix from this one..
     * <P>
     * This requires as a pre-condition that the sizes
     * of the two Matrices be the same.
     * </P>
     * <P>
     * By the rule of immutability for this class, this
     * must return a <EM>new</EM> Matrix, not simply
     * modify an existing Matrix.
     * </P>
     * @return <CODE>(this - other)</CODE>
     */
    public Matrix subtract(Matrix other) {
	double matrix[][] = values_;
	for(int i = 0; i < this.numRows(); i++) {
	    for(int j = 0; j < this.numColumns(); j++) {
		matrix[i][j] -= other.getElement(i, j);
	    }
	}
	return new Matrix(matrix);
    }
    
    /**
     * Multiply a scalar and a Matrix.
     * <P>
     * By the rule of immutability for this class, this
     * must return a <EM>new</EM> Matrix, not simply
     * modify an existing Matrix.
     * </P>
     */
    public static Matrix multiply(double scalar, Matrix a) {
	double matrix[][] = new double[a.numRows()][a.numColumns()];
	for(int i = 0; i < a.numRows(); i++) {
	    for(int j = 0; j < a.numColumns(); j++) {
		matrix[i][j] = a.getElement(i, j) * scalar;
	    }
	}
	return new Matrix(matrix);
    }
    /**
     * Multiply this Matrix by a scalar.
     * <P>
     * By the rule of immutability for this class, this
     * must return a <EM>new</EM> Matrix, not simply
     * modify an existing Matrix.
     * </P>
     */
    public Matrix multiply(double scalar) {
	double matrix[][] = values_;
	for(int i = 0; i < this.numRows(); i++) {
	    for(int j = 0; j < this.numColumns(); j++) {
		matrix[i][j] *= scalar;
	    }
	}
	return new Matrix(matrix);
    }	
  /**
   * Multiply two Matrices.
   * <P>
   * This requires as a pre-condition that the number of
   * columns in Matrix <CODE>a</CODE> be the same as the
   * number of rows in Matrix <CODE>b</CODE>.
   * </P>
   * <P>
   * By the rule of immutability for this class, this
   * must return a <EM>new</EM> Matrix, not simply
   * modify an existing Matrix.
   * </P>
   */
    public static Matrix multiply(Matrix a, Matrix b) {
	double matrix[][] = new double[a.numRows()][b.numColumns()];
	for (int i=0; i < a.numRows(); i++) {
	    for (int j=0; j < b.numColumns(); j++) {
		matrix[i][j]=0;
		for (int k=0; k < a.numColumns(); k++) {
		    matrix[i][j] += a.getElement(i, k) * b.getElement(k,j);
		}
	    }
	}
	return new Matrix(matrix);
    }
    
		    
    /**
     * Multiply this Matrix by another.
	 * <P>
	 * This requires as a pre-condition that the number of
	 * columns in Matrix <CODE>this</CODE> be the same as the
	 * number of rows in the <CODE>other</CODE> Matrix.
	 * </P>
	 * <P>
	 * By the rule of immutability for this class, this
	 * must return a <EM>new</EM> Matrix, not simply
	 * modify an existing Matrix.
	 * </P>
	 * @return <CODE>(this * other)</CODE>
	 */
    public Matrix multiply(Matrix other) {
	Matrix a = this;
	double matrix[][] = new double[a.numRows()][other.numColumns()];
	for (int i=0; i < a.numRows(); i++) {
	    for (int j=0; j < other.numColumns(); j++) {
		matrix[i][j]=0;
		for (int k=0; k < a.numColumns(); k++) {
		    matrix[i][j] += a.getElement(i, k) * other.getElement(k,j);
		}
	    }
	}
	return new Matrix(matrix);
    }
	
    //public Object clone() {
    //how can the clone from Object tell that this member variable
    //goes in this constructor... or does it do something compleatly different
    //return new Matrix(values_);
    //}
    /**
     * Transpose a Matrix.
     * <P>
     * By the rule of immutability for this class, this
     * must return a <EM>new</EM> Matrix, not simply
     * modify an existing Matrix.
     * </P>
     */
    public static Matrix transpose(Matrix a) {
	double matrix[][]= new double[a.numColumns()][a.numRows()];
	for (int i = 0; i < a.numRows(); i++) {
	    for (int j = 0; j < a.numColumns(); j++) {
		matrix[j][i] = a.getElement(i, j);
	    }
	}
	return new Matrix(matrix);
    }
    /**
     * Transpose this Matrix.
     * <P>
     * By the rule of immutability for this class, this
     * must return a <EM>new</EM> Matrix, not simply
     * modify an existing Matrix.
     * </P>
     */
    public Matrix transpose() { 
	double matrix[][]= new double[this.numColumns()][this.numRows()];
	for (int i = 0; i < this.numRows(); i++) {
	    for (int j = 0; j < this.numColumns(); j++) {
		matrix[j][i] = values_[i][j];
	    }
	}
	return new Matrix(matrix);
    }
    
    /**
     * Compute the cofactor of a Matrix.
     * <P>
     * The cofactor of a Matrix is obtained by
     * eliminating the specified row and column.
     * </P>
     * <P>
     * We assume as a pre-condition that <CODE>row</CODE> and
     * <CODE>column</CODE> are legal row/column indices for 
     * Matrix <CODE>a</CODE>.  We also assume the initial Matrix
     * has at least two rows and at least two columns.
     * </P>
     */
    public static Matrix cofactor(Matrix a, int row, int column) {
	double[][] matrix = new double[a.numRows()-1][a.numColumns()-1];
	for (int i = 0; i < row; i++) {
	    for (int j = 0; j < column; j++) {
		matrix[i][j] = a.getElement(i, j);
	    }
	}
	for (int i = row+1; i < a.numRows(); i++) {
	    for (int j = column+1; i < a.numColumns(); j++) {
		matrix[i-1][j-1] = a.getElement(i, j);
	    } 
	}
	return new Matrix(matrix);
    }
    /**
     * Compute the cofactor of this Matrix.
     * <P>
     * The cofactor of a Matrix is obtained by
     * eliminating the specified row and column.
     * </P>
     * <P>
     * We assume as a pre-condition that <CODE>row</CODE> and
     * <CODE>column</CODE> are legal row/column indices for this
     * Matrix.  We also assume that this Matrix
     * has at least two rows and at least two columns.
     * </P>
     */
    public Matrix cofactor(int row, int column) {
	double[][] matrix = new double[this.numRows()-1][this.numColumns()-1];
	for (int i = 0; i < row; i++) {
	    for (int j = 0; j < column; j++) {
		matrix[i][j] = this.getElement(i, j);
	    }
	}
	for (int i = row+1; i < this.numRows(); i++) {
	    for (int j = column+1; i < this.numColumns(); j++) {
		matrix[i-1][j-1] = this.getElement(i, j);
	    } 
	}
	return new Matrix(matrix);
    }
    
    /**
     * Compute the determinant of a Matrix.
     * <P>
     * We assume as a pre-condition that the specified
     * Matrix is square.
     * </P>
     */
    public static double determinant(Matrix a) {
	double det = 0;
	for (int i = 0; i < a.numRows(); i++) {
	    det += Math.pow(-1, i) * a.getElement(0, i) * Matrix.determinant(Matrix.cofactor(a, 0, i));
	}
	return det;
    }
    /**
     * Compute the determinant of this Matrix.
     * <P>
     * We assume as a pre-condition that this
     * Matrix is square.
     * </P>
     */
    public double determinant() { 
	return determinant(this);
    }
    /**
     * Compute the multiplicative inverse of a Matrix.
     * <P>
     * We assume as a pre-condition that the specified
	 * Matrix is square and has non-zero determinant.
	 * </P>
	 */
    public static Matrix inverse(Matrix a) {
	return null;
    } 
	/**
	 * Compute the multiplicative inverse of this Matrix.
	 * <P>
	 * We assume as a pre-condition that this
	 * Matrix is square and has non-zero determinant.
	 * </P>
	 */
    public Matrix inverse() {
	return null;
    }
    
    /**
     * Generate a printed representation of this Matrix.
     */
    public String toString() {
	String str ="";
	for (int i=0; i < this.numRows(); i++) {
	    for (int j=0; j < this.numColumns(); j++) {
		str += new String(this.getElement(i, j) + " ");
	    }
	    str += "\n";
	}
	return str;
    }
    
    /**
     * Is this Matrix equal to another Object?
     * <P>
     * The Matrix is equal to <CODE>other</CODE> if the
     * <CODE>other</CODE> is also a Matrix, the two
     * Matrices have the same size, and are equal
     * element by element.
     * </P>
     */
    public boolean equals(Object other) {
	if (other instanceof Matrix) {
	    return this.equals((Matrix) other);
	} 
	return false;
    }
    /**
     * Is this Matrix equal to another Matrix?
     * <P>
     * The Matrix is equal to <CODE>other</CODE> if the two
     * Matrices have the same size, and are equal
     * element by element.
     * </P>
     */
    public boolean equals(Matrix other) {
	if (hashCode() == (other.hashCode())) {
	    if (this.numRows() == other.numRows() 
		&& this.numColumns() == other.numColumns()) {
		for (int i=0; i<this.numRows(); i++) {
		    for (int j=0; j<this.numColumns(); j++) {
			if (this.getElement(i, j) != other.getElement(i, j)) {
			    return false;
			}
		    }
		}
		return true;
	    } 
	}
	return false;
	//return false;
	//if (this.hashCode() == other.hashCode()) {
	//    return true;
	//}
	//return false;
    }
    /**
     * Return a hashCode for this Matrix.
     * <P>
     * It's crucial here that two <CODE>equals()</CODE>
     * Matrices have the same <CODE>hashCode</CODE>.
     * </P>
     */
    public int hashCode() {
	return this.toString().hashCode();
    }
}
















