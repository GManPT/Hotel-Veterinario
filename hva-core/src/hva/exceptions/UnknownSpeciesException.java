package hva.exceptions;

public class UnknownSpeciesException extends Exception {
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    public UnknownSpeciesException(String speciesId) {
        super(speciesId);
        _id = speciesId;
    }

    public String getId() {
        return _id;
    }
}