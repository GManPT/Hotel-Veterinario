package hva.exceptions;

public class VeterinarianAuthorizedException extends Exception {
    private static final long serialVersionUID = 202407081733L;
    private String _id;
    private String _idVet;

    public VeterinarianAuthorizedException(String speciesId, String idVet) {
        _id = speciesId;
        _idVet = idVet;
    }

    public String getId() {
        return _id;
    }

    public String getIdVet() {
        return _idVet;
    }
}