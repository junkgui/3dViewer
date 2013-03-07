//package linearAlgebra;

public class TransformationFactory {
    /**
     * Builds a TransformationFactory
     */
    public TransformationFactory(int size) {
    }
    public TransformationFactory() {
    }
    /**
     * Builds an Identity Matrix
     */
    public Matrix buildIdentity() {
	double[][] matrix = {{ 1, 0, 0, 0},
			     { 0, 1, 0, 0},
			     { 0, 0, 1, 0},
			     { 0, 0, 0, 1}};
	return new Matrix(matrix);
    }

  public Matrix buildPerspective(double distance) {
	double[][] matrix = {{ 1, 0, 0, 0},
			     { 0, 1, 0, 0},
			     { 0, 0, 1, 1/distance},
			     { 0, 0, 0, 0}};
	return new Matrix(matrix);
    }
    /**
     * Builds a Matrix to do uniform scaling
     */

    public  Matrix buildUniformScaling(double factor) {
	double[][] matrix = {{ factor, 0, 0, 0},
			     { 0, factor, 0, 0},
			     { 0, 0, factor, 0},
			     { 0, 0, 0, 1}};   
	return new Matrix(matrix);  
    }
    /**
     * Builds a nonuniform scaling matrix
     */
    public Matrix buildNonUniformScaling(double x, double y, double z) {
	double[][] matrix = {{ x, 0, 0, 0},
			     { 0, y, 0, 0},
			     { 0, 0, z, 0},
			     { 0, 0, 0, 1}};
	return new Matrix(matrix);   
    }
    /**
     * Builds a translation matrix
     */
    public Matrix buildTranslation(double x, double y, double z) {
	double[][] matrix = {{ 1, 0, 0, 0},
			     { 0, 1, 0, 0},
			     { 0, 0, 1, 0},
			     { x, y, z, 1}};
	return new Matrix(matrix);
    }                          
    /** 
     * Builds a rotation around the z-axis matrix given an angle
     */

    public Matrix buildZRotation(double angle) {
	double cos = Math.cos(angle);
	double sin = Math.sin(angle);
	double[][] matrix = {{ cos, sin, 0, 0},
			     { -sin, cos, 0, 0},
			     { 0, 0, 1, 0},
			     { 0, 0, 0, 1}};
	return new Matrix(matrix);
    }           

    /** 
     * Builds a rotation around the x-axis matrix given an angle
     */

    public Matrix buildXRotation(double angle) {
	double cos = Math.cos(angle);
	double sin = Math.sin(angle);
	double[][] matrix = {{ 1, 0, 0, 0},
			     { 0, cos, sin, 0},
			     { 0, -sin, cos, 0},
			     { 0, 0, 0, 1}};
	return new Matrix(matrix);
    }           

    /** 
     * Builds a rotation around the y-axis matrix given an angle
     */

    public Matrix buildYRotation(double angle) {
	double cos = Math.cos(angle);
	double sin = Math.sin(angle);
	double[][] matrix = {{ cos, 0, -sin, 0},
			     { 0, 1, 0, 0},
			     { sin, 0, cos, 0},
			     { 0, 0, 0, 1}};
	return new Matrix(matrix);
    }           

}
