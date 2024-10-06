package hva.exceptions;

public class DuplicateVaccineException extends Exception {
    private static final long serialVersionUID = 202407081733L;
    private String _id;

    public DuplicateVaccineException(String id) {
        super(id);
        _id = id;
    }

    public String getId() {
        return _id;
    }
}