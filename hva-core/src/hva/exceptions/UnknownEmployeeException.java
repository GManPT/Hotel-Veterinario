package hva.exceptions;

/**
 * Exception thrown when an employee with a given ID is not found in the system.
 */
public class UnknownEmployeeException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    /** @param id */
    public UnknownEmployeeException(String id) {
        _id = id;
    }

    /**
     * @return _id
     */
    public String getId() {
        return _id;
    }
}