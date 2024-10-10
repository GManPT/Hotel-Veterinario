package hva.exceptions;

import java.io.Serial;

/**
 * Exception thrown when a file is not available.
 */
public class UnavailableFileException extends Exception {

	/** Serial number for serialization. */
	@Serial
	private static final long serialVersionUID = 202407081733L;

	/** The requested filename. */
	String _filename;

	public UnavailableFileException(String filename) {
	  super("Erro a processar ficheiro " + filename);
	  _filename = filename;
	}

	/**
     * @return _filename
     */
	public String getFilename() {
		return _filename;
	}

}
