package numberlist.objectlist;

import java.io.Serializable;

/**
 * This class represents objects of the Copiable class as its values.
 *
 * @author [Paula Elsaeed]
 * @author [Deanna Siaterlis]
 * @author [Hilana Ibrahim]
 * @author [Aman Siid]
 * @version 3/2/20
 */
class Node implements Serializable {

    private Copiable obj;
    private Node nextNode;

    /**
     * Full Constructor. Creates a new Node object.
     *
     * @param obj the value of the object
     */
    public Node(Copiable obj) {
        this.obj = obj;
        nextNode = null;
    }

    /**
     * Gets the value of the object.
     *
     * @return the value of the object
     */
    public Copiable getValue() {
        return obj;
    }

    /**
     * Sets the value of the object.
     *
     * @param obj the value of the object
     */
    public void setValue(Copiable obj) {
        this.obj = obj;
    }

    /**
     * Gets the value of the next object.
     *
     * @return the value of the next object
     */
    public Node getNext() {
        return nextNode;
    }

    /**
     * Sets the value of the next object.
     *
     * @param node the value of the next object
     */
    public void setNext(Node node) {
        this.nextNode = node;
    }
}
