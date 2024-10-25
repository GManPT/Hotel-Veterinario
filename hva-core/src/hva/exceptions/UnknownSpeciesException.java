package hva.exceptions;

/**
 * Exception thrown when a species is not found in the database.
 */
public class UnknownSpeciesException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    /** @param speciesId */
    public UnknownSpeciesException(String speciesId) {
        super(speciesId);
        _id = speciesId;
    }

    /**
     * @return _id
     */
    public String getId() {
        return _id;
    }
}