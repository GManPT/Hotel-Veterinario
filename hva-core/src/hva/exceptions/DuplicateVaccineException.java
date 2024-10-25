package hva.exceptions;

/**
 * Exception for when a vaccine with the same ID is already registered in the system.
 */
public class DuplicateVaccineException extends Exception {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    /** @param id */
    public DuplicateVaccineException(String id) {
        _id = id;
    }

    /**
     * @return _id
     */
    public String getId() {
        return _id;
    }
}