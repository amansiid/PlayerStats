package numberlist.primitivelist;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import numberlist.IndexRangeException;

/**
 * This class provides a growable array for primitive double values.
 *
 * @author [Aman Siid]
 *
 * @version 3/2/20
 *
 */
class BigFloatArrayList implements Serializable {

    //fields
    private BigIntArrayList longArrayList;

    /**
     * Constructor.
     */
    public BigFloatArrayList() {
        longArrayList = new BigIntArrayList();
    }

    /**
     * Adds the given double value at the given index. The index is assumed to
     * be a value between 0 and count. Existing elements are moved up as needed
     * to make room for the new value.
     *
     * @param index the index where the new value should be stored
     * @param value the value to be stored
     * @throws InvalidPositionException
     */
    public void insert(int index, double value) throws IndexRangeException {
        long longValue = Double.doubleToRawLongBits(value);
        longArrayList.insert(index, longValue);
    }

    /**
     * This method sets a value at a specific index and returns the value that
     * was removed.
     *
     * @param index The index to add the value to.
     * @param value The value to add to the given index
     * @return valueRemoved The value that was removed from the given index.
     * @throws InvalidPositionException
     */
    public double replace(int index, double value) throws IndexRangeException {
        if (longArrayList.getCount() == 0) {
            throw new IndexRangeException(-1, -1, index);
        }
        if (index < 0 || index >= longArrayList.getCount()) {
            throw new IndexRangeException(0, getCount() - 1, index);
        }
        long longValue = Double.doubleToRawLongBits(value);
        double valueRemoved
                = Double.longBitsToDouble(longArrayList.replace(index, longValue));
        return valueRemoved;
    }

    /**
     * Removes and returns the value at the given index. The index is assumed to
     * be a value between 0 and count - 1. Existing elements are moved down as
     * needed to keep all values contiguous, without any empty spaces in the
     * array.
     *
     * @param index the index of the element that should be removed
     * @return the value that was removed
     * @throws InvalidPositionException
     */
    public double deleteAt(int index) throws IndexRangeException{
        long longBits = longArrayList.deleteAt(index);
        return Double.longBitsToDouble(longBits);
    }

    /**
     * Removes the first instance of the given value. Existing elements are
     * moved down as needed to keep all values contiguous, without any empty
     * spaces in the array. If the value does not exist, this method returns
     * without doing anything.
     *
     * @param value the value to remove
     */
    public void delete(double value) {
        long longValue = Double.doubleToRawLongBits(value);
        longArrayList.delete(longValue);
    }

    /**
     * Returns the value at the given index without removing it. The index is
     * assumed to be a value between 0 and count - 1.
     *
     * @param index the index of the element
     * @return the value at that index
     */
    public double getValueAt(int index)throws IndexRangeException{
        long longBits = longArrayList.getValueAt(index);
        return Double.longBitsToDouble(longBits);
    }

    /**
     * Returns the index of the first instance of the given value in the array.
     * If the value doesn't exist, -1 is returned.
     *
     * @param value the value to find in the array
     * @return the index where the value was found, or -1 if not found
     */
    public int locate(double value) {
        long longValue = Double.doubleToRawLongBits(value);
        return longArrayList.locate(longValue);
    }

    /**
     * Provides access to the number of values currently in the array.
     *
     * @return the number of values in the array
     */
    public int getCount() {
        return longArrayList.getCount();
    }

    /**
     * Provides a string representation of the growable array, displaying all
     * values currently in the array using the format [ value1, value2, ... ].
     *
     * @return the string representation of the array
     */
    @Override
    public String toString() {
        String output = "[ ";
        for (int i = 0; i < longArrayList.getCount(); i++) {
            try {
                output += Double.longBitsToDouble(longArrayList.getValueAt(i)) + ", ";
            } catch (IndexRangeException ex) {
                Logger.getLogger(BigFloatArrayList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        if (longArrayList.getCount() > 0) {
            output = output.substring(0, output.length() - 2);
        } else {
            output = output.substring(0, output.length() - 1);
        }
        output += " ]";
        return output;
    }

}
