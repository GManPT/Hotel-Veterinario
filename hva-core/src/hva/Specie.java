package hva;

import java.util.Map;
import java.util.TreeMap;

import hva.exceptions.DuplicateAnimalException;

public class Specie {
    private String _idSpecie;
    private String _nameSpecie;
    private Map<String,Animal> _animals;

    public Specie(String id, String name) {
        this._idSpecie = id;
        this._nameSpecie = name;
        this._animals = new TreeMap<String, Animal>();
    }

    public String getIdSpecie() {
        return this._idSpecie;
    }

    public String getNameSpecie() {
        return this._nameSpecie;
    }

    public void put(Animal a) throws DuplicateAnimalException {

        if (_animals.get(a.getIdAnimal()) != null) {
            throw new DuplicateAnimalException(a.getIdAnimal());
        }
        _animals.put(a.getIdAnimal(), a);
    }
}