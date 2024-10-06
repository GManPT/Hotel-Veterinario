package hva.exceptions;

public class ResponsibilityException extends Exception {
    private static final long serialVersionUID = 202407081733L;
    private String _id;
    private String _responsibility;

    public ResponsibilityException(String id, String responsibility) {
        super(id);
        _id = id;
        _responsibility = responsibility;
    }

    public String getId() {
        return _id;
    }

    public String getResponsibility() {
        return _responsibility;
    }
}