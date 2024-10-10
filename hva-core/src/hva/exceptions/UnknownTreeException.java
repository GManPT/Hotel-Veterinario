package hva.exceptions;

/**
 * Exception thrown when trying to access a habitat that does not exist.
 */
public class UnknownTreeException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    /** @param id */
    public UnknownTreeException(String id) {
        super(id);
    }

    /**
     * @return _id
     */
    public String getId() {
        return _id;
    }
}