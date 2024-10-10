package hva.exceptions;

/**
 * Exception thrown when a tree with the same id is added to the system.
 */
public class DuplicateTreeException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    /** @param id */
    public DuplicateTreeException(String id) {
        _id = id;
    }

    /**
     * @return _id
     */
    public String getId() {
        return _id;
    }
}