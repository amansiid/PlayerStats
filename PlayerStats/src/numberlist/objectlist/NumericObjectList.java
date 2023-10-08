package numberlist.objectlist;

import numberlist.IndexRangeException;

/**
 * This class represents the List ADT with all its basic methods.
 *
 * @author [Aman Siid]
 * @version 3/2/20 
 */
abstract class NumericObjectList {

    int count;

    abstract void insert(int index, Copiable obj);

    /**
     * Inserts to the end of the list and return the index it was inserted at.
     *
     * @param value the value to be stored
     * @return the index the value was inserted at
     */
    public int insert(Copiable obj) {
        insert(count, obj);
        count++;
        return count - 1;
    }

    /**
     * Deletes and returns the value at the given index. The index is assumed to
     * be a value between 0 and count - 1. Existing elements are moved down as
     * needed to keep all values contiguous, without any empty spaces in the
     * array.
     *
     * @param index the index of the element that should be deleted
     * @return the value that was deleted
     */
    abstract Copiable deleteAt(int index) throws IndexRangeException;

    ;

    /**
     * Deletes the first instance of the given value. Existing elements are
     * moved down as needed to keep all values contiguous, without any empty
     * spaces in the array. If the value does not exist, this method returns
     * without doing anything.
     *
     * @param obj the value to delete
     */
    abstract void delete(Copiable obj) throws IndexRangeException;

    ;

    /**
     * Returns the value at the given index without deleting it. The index is
     * assumed to be a value between 0 and count - 1.
     *
     * @param index the index of the element
     * @return the value at that index
     */
    abstract Copiable getValueAt(int index) throws IndexRangeException;

    ;

    /**
     * Returns the index of the first instance of the given value in the array.
     * If the value doesn't exist, -1 is returned.
     *
     * @param obj the value to find in the array
     * @return the index where the value was found, or -1 if not found
     */
    abstract int locate(Copiable obj);

    /**
     * Provides access to the number of values currently in the array.
     *
     * @return the number of values in the array
     */
    public int getCount() {
        return count;
    }
}
