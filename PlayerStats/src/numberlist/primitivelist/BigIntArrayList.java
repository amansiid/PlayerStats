package numberlist.primitivelist;

import java.io.Serializable;
import numberlist.IndexRangeException;

/**
 * This class provides a growable array for primitive long values.
 *
 * @author [Aman Siid]
 * 
 * @version 3/2/20
 */
class BigIntArrayList implements Serializable {

    //fields
    private long[] list;
    private int count;

    /**
     * Constructor. Initializes the underlying array to 10 elements.
     */
    public BigIntArrayList() {
        list = new long[10];
        count = 0;
    }

    /**
     * Adds the given long value at the given index. The index is assumed to be
     * a value between 0 and count. Existing elements are moved up as needed to
     * make room for the new value.
     *
     * @param index the index where the new value should be stored
     * @param value the value to be stored
     * @throws InvalidPositionException
     */
    public void insert(int index, long value) throws IndexRangeException {
        if (index > count || index < 0) {
            throw new IndexRangeException(0, count, index);
        }
        if (list.length <= count) {
            long[] tmpList = new long[list.length * 2];
            for (int i = 0; i < count; i++) {
                tmpList[i] = list[i];
            }
            list = tmpList;
        }
        for (int i = count; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = value;
        count++;
    }

    /**
     * This method sets a value at a specific index and returns the value that
     * was removed.
     *
     * @param index The index to add the value to.
     * @param value The value to add to the given index
     * @return removed The value that was removed from the given index.
     * @throws InvalidPositionException
     */
    public long replace(int index, long value) throws IndexRangeException {
        if (count == 0) {
            throw new IndexRangeException(-1, -1, index);
        }
        if (index < 0 || index >= count) {
            throw new IndexRangeException(0, count - 1, index);
        }
        long removed = list[index];
        list[index] = value;
        return removed;
    }

    /**
     * Removes and returns the value at the given index. The index is assumed to
     * be a value between 0 and count - 1. Existing elements are moved down as
     * needed to keep all values contiguous, without any empty spaces in the
     * array.
     *
     * @param index the index of the element that should be removed
     * @return the value that was removed
     */
    public long deleteAt(int index) throws IndexRangeException {
        if (index > count || index < 0) {
            throw new IndexRangeException(0, count, index);
        }
        long value = list[index];
        for (int i = index; i < count - 1; i++) {
            list[i] = list[i + 1];
        }
        count--;
        return value;
    }

    /**
     * Removes the first instance of the given value. Existing elements are
     * moved down as needed to keep all values contiguous, without any empty
     * spaces in the array. If the value does not exist, this method returns
     * without doing anything.
     *
     * @param value the value to remove
     */
    public void delete(long value) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (list[i] == value) {
                index = i;
                break;
            }
        }
        if (index != -1) {
            try {
                deleteAt(index);
            } catch (IndexRangeException iie) {
                System.out.println(iie);
            }
        }
    }

    /**
     * Returns the value at the given index without removing it. The index is
     * assumed to be a value between 0 and count - 1.
     *
     * @param index the index of the element
     * @return the value at that index
     * @throws InvalidPositionException
     */
    public long getValueAt(int index) throws IndexRangeException{
        if (index > count  || index < 0) {
           throw new IndexRangeException(0, count, index);
        }
        return list[index];
    }

    /**
     * Returns the index of the first instance of the given value in the array.
     * If the value doesn't exist, -1 is returned.
     *
     * @param value the value to find in the array
     * @return the index where the value was found, or -1 if not found
     */
    public int locate(long value) {
        for (int i = 0; i < count; i++) {
            if (list[i] == value) {
                return i;
            }
        }
        return -1;
    }

    /**
     * Provides access to the number of values currently in the array.
     *
     * @return the number of values in the array
     */
    public int getCount() {
        return count;
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
        for (int i = 0; i < count; i++) {
            output += list[i] + ", ";
        }
        if (count > 0) {
            output = output.substring(0, output.length() - 2);
        } else {
            output = output.substring(0, output.length() - 1);
        }
        output += " ]";
        return output;
    }

}
