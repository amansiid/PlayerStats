package numberlist.objectlist;

import java.io.Serializable;
import java.math.BigDecimal;
import java.math.MathContext;

/**
 * This class represents a single complex number of the form x + yi.
 *
 * @author [Aman Siid]
 * @version 3/2/20
 *
 */
public class Complex implements Copiable, Comparable<Complex>, Serializable {

    //fields
    private double real;
    private double imaginary;

    /**
     * Default constructor. Creates a new Complex object. Sets both real and
     * imaginary to zero.
     */
    public Complex() {
        real = 0;
        imaginary = 0;
    }

    /**
     * Full constructor. Creates a new Complex object.
     *
     * @param real the value of the real portion of the complex number
     * @param imaginary the value of the imaginary portion of the complex number
     */
    public Complex(double real, double imaginary) {
        this.real = real;
        this.imaginary = imaginary;
    }

    /**
     * Provides access to the real portion of the complex number
     *
     * @return the value of the real portion of the complex number
     */
    public double getReal() {
        return real;
    }

    /**
     * Provides access to the imaginary portion of the complex number
     *
     * @return the value of the imaginary portion of the complex number
     */
    public double getImaginary() {
        return imaginary;
    }

    /**
     * inserts the current and the given complex numbers together, and stores
     * the sum in a new Complex object. The current and given complex numbers
     * are not altered in the process.
     *
     * @param other the other complex number to insert to this one
     * @return the new Complex object that holds the result of the addition
     */
    public Complex insert(Complex other) {
        double realValue = real + other.real;
        double imaginaryValue = imaginary + other.imaginary;
        return new Complex(realValue, imaginaryValue);
    }

    /**
     * Subtracts the other complex number from this one, and stores the result
     * in a new Complex object. The current and given complex numbers are not
     * altered in the process.
     *
     * @param other the other complex number to subtract from this one
     * @return the new Complex object that holds the result of the subtraction
     */
    public Complex subtract(Complex other) {
        double realValue = real - other.real;
        double imaginaryValue = imaginary - other.imaginary;
        return new Complex(realValue, imaginaryValue);
    }

    /**
     * Provides a string representation of the current complex number, in the
     * form x + yi.
     *
     * @return the string representation of the current complex number
     */
    @Override
    public String toString() {
        BigDecimal bd = new BigDecimal(real);
        bd = bd.round(new MathContext(2));
        real = bd.doubleValue();
        bd = new BigDecimal(imaginary);
        bd = bd.round(new MathContext(2));
        imaginary = bd.doubleValue();
        if (imaginary == 0) {
            return real + "";
        }
        if (real == 0) {
            return imaginary + "i";
        }
        if (imaginary < 0) {
            return real + " - " + (-imaginary) + "i";
        }
        return real + " + " + imaginary + "i";
    }

    /**
     * Creates a unique identifier for the name.
     *
     * @return the unique identifier
     */
    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.real)
                ^ (Double.doubleToLongBits(this.real) >>> 32));
        hash = 59 * hash + (int) (Double.doubleToLongBits(this.imaginary)
                ^ (Double.doubleToLongBits(this.imaginary) >>> 32));
        return hash;
    }

    /**
     * This method compares if two Complex objects are equal or not.
     *
     * @param other the Complex object being compared
     * @return whether the object is equal or not
     */
    @Override
    public boolean equals(Object other) {
        if (this == other) {
            return true;
        }
        if (other == null) {
            return false;
        }
        if (getClass() != other.getClass()) {
            return false;
        }
        final Complex obj = (Complex) other;
        if (Double.doubleToLongBits(this.real)
                != Double.doubleToLongBits(obj.real)) {
            return false;
        }
        if (Double.doubleToLongBits(this.imaginary)
                != Double.doubleToLongBits(obj.imaginary)) {
            return false;
        }
        return true;
    }

    /**
     * This method creates a new object of the Complex class.
     *
     * @return a Complex object
     */
    @Override
    public Complex makeDeepCopy() {
        return new Complex(real, imaginary);
    }
    
    /**
     * Compares two distinct Complex objects.
     * 
     * @param other the object to be compared with
     * @return a numeric representation determining how different the two objects are
     */
    @Override
    public int compareTo(Complex other) {
        return Double.compare(this.real, other.real) + Double.compare(this.imaginary, other.imaginary);
    }
}
