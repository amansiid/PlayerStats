package numberlist.objectlist;

import java.io.Serializable;
import java.util.logging.Level;
import java.util.logging.Logger;
import numberlist.IndexRangeException;

/**
 * This class represents the List ADT with all its basic methods and has data
 * storage techniques that rely upon a linked series of objects.
 *
 * @author [Paula Elsaeed]
 * @author [Deanna Siaterlis]
 * @author [Hilana Ibrahim]
 * @author [Aman Siid]
 *
 * @version 3/2/2020 
 */
public class NumericObjectLinkedList extends NumericObjectList implements Copiable, Serializable {

    private Node firstNode = null;

    public NumericObjectLinkedList() {
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
        if (index > count || index < 0) {
            return;
        }
        Node newNode = new Node(obj);
        if (index == 0) {
            newNode.setNext(firstNode);
            firstNode = newNode;
        } else {
            Node current = firstNode;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            newNode.setNext(current.getNext());
            current.setNext(newNode);
        }
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
        Node current = firstNode;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        Copiable removed = current.getValue();
        current.setValue(obj);

        return removed;
    }

    /**
     * Deletes and returns the value at the given index. The index is assumed to
     * be a value between 0 and count - 1. Existing elements are moved down as
     * needed to keep all values contiguous, without any empty spaces in the
     * linked list.
     *
     * @param index the index of the element that should be deleted
     * @return the value that was deleted
     * @throws IndexRangeException
     */
    @Override
    public Copiable deleteAt(int index) throws IndexRangeException {
        if (index > count || index < 0) {
            throw new IndexRangeException(0, count, index);
        }
        if (index == 0) {
            Copiable temp = firstNode.getValue();
            firstNode = firstNode.getNext();
            count--;
            return temp;
        } else {
            Node current = firstNode;
            for (int i = 0; i < index - 1; i++) {
                current = current.getNext();
            }
            Copiable temp = current.getNext().getValue();
            current.setNext(current.getNext().getNext());
            count--;
            return temp;
        }
    }

    /**
     * Deletes the first instance of the given value. Existing elements are
     * moved down as needed to keep all values contiguous, without any empty
     * spaces in the linked list. If the value does not exist, this method
     * returns without doing anything.
     *
     * @param obj the value to delete
     */
    @Override
    public void delete(Copiable obj) {
        int index;
        index = locate(obj);
        if (index >= 0) {
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
        if (index < 0 || index > count - 1) {
            throw new IndexRangeException(0, count, index);
        }
        if (index == 0) {
            return firstNode.getValue();
        }
        Node current = firstNode;
        for (int i = 0; i < index; i++) {
            current = current.getNext();
        }
        return current.getValue();
    }

    /**
     * Returns the index of the first instance of the given value in the linked
     * list. If the value doesn't exist, -1 is returned.
     *
     * @param obj the value to find in the linked list
     * @return the index where the value was found, or -1 if not found
     */
    @Override
    public int locate(Copiable obj) {
        for (int i = 0; i < count; i++) {
            try {
                if (getValueAt(i).equals(obj)) {
                    return i;
                }
            } catch (IndexRangeException iie) {
                System.out.println(iie);
            }
        }
        return -1;
    }

    /**
     * Provides a string representation of the growable linked list, displaying
     * all values currently in the linked list using the format [ value1,
     * value2, ... ].
     *
     * @return the string representation of the linked list
     */
    @Override
    public String toString() {
        String output = "[ ";
        for (int i = 0; i < count; i++) {
            try {
                output += getValueAt(i) + ", ";

            } catch (IndexRangeException iie) {
                System.out.println(iie);
            }
            if (count > 0) {
                output = output.substring(0, output.length() - 2);
            } else {
                output = output.substring(0, output.length() - 1);
            }
        }
        output += " ]";
        return output;
    }

    /**
     * This method creates a new object of the NumericObjectLinkedList class.
     *
     * @return a NumericObjectLinkedList object
     */
    @Override
    public NumericObjectLinkedList makeDeepCopy() {
        NumericObjectLinkedList listCopy = new NumericObjectLinkedList();
        listCopy.firstNode = this.firstNode;
        Node current = firstNode;
        for (int i = 0; i < count; i++) {
            listCopy.insert(i, current.getValue().makeDeepCopy());
            current = current.getNext();
        }
        return listCopy;
    }

}
