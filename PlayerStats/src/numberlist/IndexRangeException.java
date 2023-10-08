package numberlist;

/**
 * Custom exception that is thrown when index is out of bounds.
 * 
 * @author [Paula Elsaeed]
 * @author [Deanna Siaterlis]
 * @author [Hilana Ibrahim]
 * @author [Aman Siid]
 * @version 03/2/2020
 */
public class IndexRangeException extends RuntimeException {

    private final int minAllowed;
    private final int maxAllowed;
    private final int valueUsed;

    /**
     * Constructor. This setups up the exception with the range attempted and the
     * value sent.
     *
     * @param minAllowed The low end of the array.
     * @param maxAllowed The max number of elements in an array.
     * @param valueUsed The index to have changed.
     */
    public IndexRangeException(int minAllowed, int maxAllowed, int valueUsed) {
        super("The index sent was out of range.");
        this.minAllowed = minAllowed;
        this.maxAllowed = maxAllowed;
        this.valueUsed = valueUsed;
    }

    /**
     * This method retrieves the minimum index allowed in an array.
     *
     * @return min The value of the minimum target.
     */
    public int getMinAllowed() {
        return minAllowed;
    }

    /**
     * This method retrieves the maximum index allowed in an array.
     *
     * @return max The value of the maximum target.
     */
    public int getMaxAllowed() {
        return maxAllowed;
    }

    /**
     * This method gets the value of the index sent.
     *
     * @return The index where a value will be changed.
     */
    public int getValueUsed() {
        return valueUsed;
    }

}
