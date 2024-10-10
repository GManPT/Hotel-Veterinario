package hva.exceptions;

/**
 * Exception thrown when a veterinarian is not authorized to perform an operation on a species.
 */
public class VeterinarianAuthorizedException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202407081733L;
    private String _id;
    private String _idVet;

    /**
     * @param speciesId
     * @param idVet
     */
    public VeterinarianAuthorizedException(String speciesId, String idVet) {
        _id = speciesId;
        _idVet = idVet;
    }

    /**
     * @return _id
     */
    public String getId() {
        return _id;
    }

    /**
     * @return _idVet
     */
    public String getIdVet() {
        return _idVet;
    }
}