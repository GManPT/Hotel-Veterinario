package hva;

import java.io.Serial;
import java.io.Serializable;

import hva.exceptions.ImportFileException;
import hva.exceptions.UnrecognizedEntryException;
import java.io.IOException;

//FIXME import other Java classes
//FIXME import project classes

public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    //FIXME define attributes
    //FIXME define constructor(s)
    //FIXME define methods

    public void registerNewAnimal(String idAnimal, String name, String idSpecie, String idHabitat) {
        
    }

    /**
     * Read text input file and create domain entities.
     *
     * @param file
     * name name of the text input file
     * @throws ImportFileException
     */
    void importFile(String filename) throws ImportFileException {
	    /*try {
                // FIXME open import file and create the associated objects
	        // ....
            } catch (IOException | UnrecognizedEntryException e) {
                throw new ImportFileException(filename, e);
            }*/
     }

}
