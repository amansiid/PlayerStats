package numberlist.primitivelist;

import java.io.Serializable;
import numberlist.IndexRangeException;

/**
 * This class provides a growable array for primitive integer values.
 *
 * @author [Paula Elsaeed]
 * @author [Deanna Siaterlis]
 * @author [Hilana Ibrahim]
 * @author [Aman Siid]
 * 
 * @version 3/2/20
 */
public class IntegerArrayList extends BigIntArrayList implements Serializable{

    /**
     * Adds to the end of the list and return the index it was added at.
     *
     * @param value the value to be stored
     * @return the index the value was added at
     */
    public int insert(long value) {
        try {
            insert(getCount(), value);
        } catch (IndexRangeException iie) {
            System.out.println(iie);
        }
        return getCount();
    }

    /**
     * Removes all instances of the value from the list.
     *
     * @param value the value to remove
     */
    public void deleteAll(long value) {
        int indxCount = getCount();
        for (int i = indxCount - 1; i >= 0; i--) {
            try {
                if (value == getValueAt(i)) {
                    deleteAt(i);
                }
            } catch (IndexRangeException iie) {
                System.out.println(iie);
            }
        }
    }

    /**
     * Returns the index of the last element in the list that contains the
     * value, or -1 if the value does not exist.
     *
     * @param value the value to find in the array
     * @return the index where the value was found, or -1 if not found
     */
    public int locateLast(long value) {
       int indxCount = getCount();
        for (int i = indxCount - 1; i >= 0; i--) {
            try {
                if (getValueAt(i) == value) {
                    return i;
                }
            } catch (IndexRangeException iie) {
                System.out.println(iie);
            }
        }
        return -1;
    }
}
