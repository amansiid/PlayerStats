package numberlist.objectlist;

import java.io.Serializable;

/**
 * This class represents a single monetary value in U.S. dollars and cents.
 *
 * @author [Aman Siid]
 * @version 3/2/20
 */
public class Money implements Copiable, Comparable<Money>, Serializable {

    //fields
    private long dollars;
    private byte cents;

    /**
     * Default constructor. Creates a new Money object. Sets both dollars and
     * cents to zero.
     */
    public Money() {
        dollars = 0;
        cents = 0;
    }

    /**
     * Full constructor. Creates a new Money object. Dollars and cents are
     * normalized. If one is negative and the other is positive, the resulting
     * object will be the natural addition of the two values. (Example: 2
     * dollars and -5 cents is converted to 1 dollar and 95 cents.) If the
     * absolute value of cents is greater than 99, the resulting object will
     * carry the extra cents into the dollars. (Example: 1 dollar and 105 cents
     * is converted to 2 dollars and 5 cents.) A negative value is represented
     * with both the dollars and cents negative.
     *
     * @param dollars the number of dollars in the amount
     * @param cents the number of cents in the amount
     */
    public Money(long dollars, byte cents) {
        long value = dollars * 100;
        value += cents;
        this.dollars = value / 100;
        this.cents = (byte) (value % 100);
    }

    /**
     * Provides access to the dollars portion of the monetary value.
     *
     * @return the dollars portion of this Money object
     */
    public long getDollars() {
        return dollars;
    }

    /**
     * Provides access to the cents portion of the monetary value.
     *
     * @return the cents portion of this Money object
     */
    public byte getCents() {
        return cents;
    }

    /**
     * Inserts the current and the given Money objects together, and stores the
     * sum in a new Money object. The current and given Money objects are not
     * altered in the process. The resulting Money object's dollars and cents
     * are normalized as defined in the full constructor comments.
     *
     * @param other the other Money object to add to this one
     * @return the Money object containing the results of this addition
     */
    public Money insert(Money other) {
        long value = dollars * 100;
        value += cents;
        long oValue = other.dollars * 100;
        oValue += other.cents;
        long result = value + oValue;
        return new Money(result / 100, (byte) (result % 100));
    }

    /**
     * Subtracts the given Money object from this one, and stores the result in
     * a new Money object. The current and given Money objects are not altered
     * in the process. The resulting Money object's dollars and cents are
     * normalized as defined in the full constructor comments.
     *
     * @param other the other Money object to subtract from this one
     * @return the Money object containing the results of this subtraction
     */
    public Money subtract(Money other) {
        long value = dollars * 100;
        value += cents;
        long oValue = other.dollars * 100;
        oValue += other.cents;
        long result = value - oValue;
        return new Money(result / 100, (byte) (result % 100));
    }

    /**
     * Creates a unique identifier for the name.
     *
     * @return the unique identifier
     */
    @Override
    public int hashCode() {
        int hash = 5;
        hash = 17 * hash + (int) (this.dollars ^ (this.dollars >>> 32));
        hash = 17 * hash + this.cents;
        return hash;
    }

    /**
     * This method compares if two Money objects are equal or not.
     *
     * @param other the Money object being compared
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
        final Money obj = (Money) other;
        if (this.dollars != obj.dollars) {
            return false;
        }
        if (this.cents != obj.cents) {
            return false;
        }
        return true;
    }

    /**
     * Provides a string representation of the current Money object, in the form
     * $mm.mm. Negative values have a minus sign before the dollar sign. Zero
     * dollar amounts are represented with one zero between the dollar sign and
     * the decimal point. Cents that are multiples of 10 still show two decimal
     * places. (Examples: $12.34, -$6.50, $0.99, -$0.99, $0.00)
     *
     * @return the string representation
     */
    @Override
    public String toString() {
        if (dollars >= 0) {
            return "$" + dollars + "." + String.format("%02d", cents);
        } else {
            return "-$" + (-dollars) + "." + String.format("%02d", (-cents));

        }
    }

    /**
     * This method creates a new object of the Money class.
     *
     * @return a Money object
     */
    @Override
    public Money makeDeepCopy() {
        return new Money(dollars, cents);
    }

    /**
     * Compares two distinct Money objects.
     *
     * @param other the object to be compared with
     * @return a numeric representation determining how different the two
     * objects are
     */
    @Override
    public int compareTo(Money other) {
        return Long.compare(this.dollars, other.dollars) + Byte.compare(this.cents, other.cents);
    }

}
