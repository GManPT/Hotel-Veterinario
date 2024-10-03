package hva.exceptions;

import hva.app.exceptions.UnknownAnimalKeyException;

public class UnknownAnimalException extends Exception {

    public UnknownAnimalException(String id) throws UnknownAnimalKeyException {
        throw new UnknownAnimalKeyException(id);
    }

}

