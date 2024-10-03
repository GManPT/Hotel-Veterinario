package hva;

import java.util.Map;
import java.util.TreeMap;

import hva.exceptions.DuplicateAnimalException;

public class Specie {
    private String _idSpecie;
    private String _nameSpecie;
    private Map<String, Animal> _animals;

    public Specie(String id, String name) {
        _idSpecie = id;
        _nameSpecie = name;
        _animals = new TreeMap<String, Animal>();
    }

    public String getIdSpecie() {
        return _idSpecie;
    }

    public String getNameSpecie() {
        return _nameSpecie;
    }

    public void put(Animal a) throws DuplicateAnimalException {
        if (_animals.get(a.getIdAnimal()) != null) {
            throw new DuplicateAnimalException(a.getIdAnimal());
        }

        _animals.put(a.getIdAnimal(), a);
    }

    public Animal getAnimal(String id) {
        Animal a = _animals.get(id);

        if (a == null) {
            //FIXME chamar a exceção
        }

        return a;
    }
}