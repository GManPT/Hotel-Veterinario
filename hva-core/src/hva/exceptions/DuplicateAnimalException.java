package hva.exceptions;

public class DuplicateAnimalException extends Exception {
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    public DuplicateAnimalException(String id) {
        _id = id;
    }

    public String getId() {
        return _id;
    }
}