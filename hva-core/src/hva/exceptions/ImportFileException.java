package hva.exceptions;

import java.io.Serial;

/**
 * exception for import file errors
 */
public class ImportFileException extends Exception {

    /** Serial number for serialization. */
    @Serial
    private static final long serialVersionUID = 202407081733L;

    private static final String ERROR_MESSAGE = "Erro a processar ficheiro de import: ";

    /** @param filename */
    public ImportFileException(String filename) {
        super(ERROR_MESSAGE + filename);
    }

    /** 
     * @param filename 
     * @param cause
    */
    public ImportFileException(String filename, Exception cause) {
        super(ERROR_MESSAGE + filename, cause);
    }

}
