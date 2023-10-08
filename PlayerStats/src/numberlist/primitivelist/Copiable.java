package numberlist.primitivelist;

/**
 * This interface provides a deep copy of all the fields of an object, resulting
 * in a new object of the same class
 *
 * @author [Aman Siid]
 * 
 * @version 3/2/20
 */
public interface Copiable {

    /**
     * This method creates a deep copy of an object.
     *
     * @return a Copiable object
     */
    public Copiable deepCopy();
}
