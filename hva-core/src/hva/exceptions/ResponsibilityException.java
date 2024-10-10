package hva.exceptions;

/**
 * Exception to be thrown when a user has no responsibility and tries to do an action.
 */ 
public class ResponsibilityException extends Exception {

    /** Serial number for serialization. */
    private static final long serialVersionUID = 202407081733L;
    private String _id;
    private String _responsibility;

    /**
     * @param id
     * @param responsibility
     */
    public ResponsibilityException(String id, String responsibility) {
        _id = id;
        _responsibility = responsibility;
    }

    /**
     * @return _id
     */
    public String getId() {
        return _id;
    }

    /**
     * @return _responsibility
     */
    public String getResponsibility() {
        return _responsibility;
    }
}