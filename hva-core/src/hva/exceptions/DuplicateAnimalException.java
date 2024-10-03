package hva.exceptions;

import hva.app.exceptions.DuplicateAnimalKeyException;

public class DuplicateAnimalException extends Exception {

    public DuplicateAnimalException(String id) throws DuplicateAnimalKeyException{
        throw new DuplicateAnimalKeyException(id);
    }

}