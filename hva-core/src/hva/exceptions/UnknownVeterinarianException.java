package hva.exceptions;

public class UnknownVeterinarianException extends Exception {
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    public UnknownVeterinarianException(String id) {
        super(id);
        _id = id;
    }

    public String getId() {
        return _id;
    }
}