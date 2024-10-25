package hva.exceptions;


/**
 * Exception thrown when trying to add an employee with an id that already exists.
 */
public class DuplicateEmployeeException extends Exception {

    /** Serial number for Serialization */
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    /** @param id */
    public DuplicateEmployeeException(String id) {
        _id = id;
    }

    /**
     * @return _id
     */
    public String getId() {
        return _id;
    }
}