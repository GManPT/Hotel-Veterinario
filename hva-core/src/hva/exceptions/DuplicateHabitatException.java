package hva.exceptions;

public class DuplicateHabitatException extends Exception {
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    public DuplicateHabitatException(String id) {
        _id = id;
    }

    public String getId() {
        return _id;
    }
}