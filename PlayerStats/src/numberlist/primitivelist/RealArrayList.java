package numberlist.primitivelist;

import java.io.Serializable;
import numberlist.IndexRangeException;

/**
 * This class provides a growable array for real numbers. It is a child of
 * BigFloatArrayList
 *
 * @author [Aman Siid]
 * 
 * @version 3/2/20
 */
public class RealArrayList extends BigFloatArrayList implements Serializable{

    /**
     * inserts to the end of the list and return the index it was added
     *
     * @param value the value to be added
     * @return the index where it was added
     */
    public int insert(double value) {
        try {
            insert(getCount(), value);
        } catch (IndexRangeException iie) {
            System.out.println(iie);
        }
        return getCount();

    }

    /**
     * deletes all instances of the value from the list
     *
     * @param value the values to be removed
     */
    public void deleteAll(double value) {
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
     * value, or -1 if the value does not exist
     *
     * @param value value to look for
     * @return The index where the value was found.
     */
    public int locateLast(double value) {
        int indxCount = getCount();

        for (int i = getCount() - 1; i > 0; i--) {
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
