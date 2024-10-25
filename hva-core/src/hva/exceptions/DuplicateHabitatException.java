package hva.exceptions;

/**
 * Exception thrown when trying to add a habitat with an id that already exists.
 */
public class DuplicateHabitatException extends Exception {
    /** Serial number for serialization */
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    /** @param id */
    public DuplicateHabitatException(String id) {
        _id = id;
    }

    /**
     * @return _id
     */
    public String getId() {
        return _id;
    }
}