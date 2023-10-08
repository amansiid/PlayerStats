package numberlist.objectlist;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import numberlist.IndexRangeException;

/**
 * This class provides a growable array for primitive Object values.
 *
 * @author [Aman Siid]
 * @version 3/2/20
 */
public class NumericObjectArrayList extends NumericObjectList implements Copiable, Serializable {

    private Copiable[] list;

    /**
     * Constructor. Initializes the underlying array to 10 elements.
     */
    public NumericObjectArrayList() {
        list = new Copiable[10];
        count = 0;
    }

    /**
     * Inserts the given object value at the given index. The index is assumed
     * to be a value between 0 and count. Existing elements are moved up as
     * needed to make room for the new value.
     *
     * @param index the index where the new value should be stored
     * @param obj the value to be stored
     * @throws numberlist.IndexRangeException
     */
    @Override
    public void insert(int index, Copiable obj) {
        if (index < 0 || index > count) {
            return;
        }
        if (count == list.length) {
            Copiable[] temp = new Copiable[2 * list.length];
            for (int i = 0; i < count; i++) {
                temp[i] = list[i];
            }
            list = new Copiable[2 * list.length];
            list = temp;
        }
        for (int i = count; i > index; i--) {
            list[i] = list[i - 1];
        }
        list[index] = obj;
        count++;
    }

    /**
     * This method sets a object value at a specific index and returns the value
     * that was removed.
     *
     * @param index The index to add the value to.
     * @param obj The value to add to the given index
     * @return removed The value that was removed from the given index.
     * @throws IndexRangeException
     */
    public Copiable replace(int index, Copiable obj)
            throws IndexRangeException {
        if (count == 0) {
            throw new IndexRangeException(-1, -1, index);
        }
        if (index < 0 || index >= count) {
            throw new IndexRangeException(0, count - 1, index);
        }
        Copiable removed = list[index];
        list[index] = obj;
        return removed;
    }

    /**
     * Deletes and returns the value at the given index. The index is assumed to
     * be a value between 0 and count - 1. Existing elements are moved down as
     * needed to keep all values contiguous, without any empty spaces in the
     * array.
     *
     * @param index the index of the element that should be deleted
     * @return the value that was removed
     * @throws numberlist.IndexRangeException
     */
    @Override
    public Copiable deleteAt(int index) throws IndexRangeException {
        if (index > count || index < 0) {
            throw new IndexRangeException(0, count - 1, index);
        }
        Copiable ret = list[index];
        for (int i = index; i < count - 1; i++) {
            list[i] = list[i + 1];
        }
        count--;
        return ret;
    }

    /**
     * Deletes the first instance of the given value. Existing elements are
     * moved down as needed to keep all values contiguous, without any empty
     * spaces in the array. If the value does not exist, this method returns
     * without doing anything.
     *
     * @param obj the value to delete
     */
    public void delete(Copiable obj) {
        int index = -1;
        for (int i = 0; i < count; i++) {
            if (list[i] == obj) {
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
     * Returns the value at the given index without deleting it. The index is
     * assumed to be a value between 0 and count - 1.
     *
     * @param index the index of the element
     * @return the value at that index
     * @throws numberlist.IndexRangeException
     */
    @Override
    public Copiable getValueAt(int index) throws IndexRangeException {
        if (index > count || index < 0) {
            throw new IndexRangeException(0, count, index);
        }
        return list[index];
    }

    /**
     * Returns the index of the first instance of the given value in the array.
     * If the value doesn't exist, -1 is returned.
     *
     * @param obj the value to find in the array
     * @return the index where the value was found, or -1 if not found
     */
    public int locate(Copiable obj) {
        for (int i = 0; i < count; i++) {
            if (list[i].equals(obj)) {
                return i;
            }
        }
        return -1;
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

    /**
     * This method creates a new object of the NumericObjectArrayList class.
     *
     * @return a NumericObjectArrayList object
     */
    @Override
    public NumericObjectArrayList makeDeepCopy() {
        NumericObjectArrayList listCopy = new NumericObjectArrayList();
        for (int i = 0; i < count; i++) {
            try {
                listCopy.insert(i, getValueAt(i).makeDeepCopy());
            } catch (IndexRangeException ex) {
                Logger.getLogger(NumericObjectArrayList.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
        return listCopy;
    }

}
