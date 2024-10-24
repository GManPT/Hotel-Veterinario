package hva.exceptions;

/**
 * Exception thrown when trying to access a habitat that does not exist.
 */
public class UnknownVaccineException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    /** @param id */
    public UnknownVaccineException(String id) {
        _id = id;
    }

    /**
     * @return _id
     */
    public String getId() {
        return _id;
    }
}