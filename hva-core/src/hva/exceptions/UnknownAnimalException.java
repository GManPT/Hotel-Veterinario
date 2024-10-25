package hva.exceptions;

/**
 * Exception thrown when an animal with a given ID is not found in the system.
 */
public class UnknownAnimalException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    /** @param id */
    public UnknownAnimalException(String id) {
        _id = id;
    }

    /**
     * @return _id
     */
    public String getId() {
        return _id;
    }
}