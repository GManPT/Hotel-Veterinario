package hva.exceptions;

/**
 * Animal already exists in the system.
 */
public class DuplicateAnimalException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    /** @param id */
    public DuplicateAnimalException(String id) {
        _id = id;
    }

    /**
     * @return _id
     */
    public String getId() {
        return _id;
    }
}