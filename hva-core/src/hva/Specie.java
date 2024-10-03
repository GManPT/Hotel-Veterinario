package hva;

import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.Collections;

import hva.exceptions.DuplicateAnimalException;
import hva.exceptions.UnknownAnimalException;

public class Specie {
    private String _idSpecie;
    private String _nameSpecie;
    private Map<String, Animal> _animals;

    public Specie() {
        
    }

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

    public void addAnimaltoSpecie(Animal a) throws DuplicateAnimalException {
        if (_animals.get(a.getIdAnimal()) != null) {
            throw new DuplicateAnimalException(a.getIdAnimal());
        }

        _animals.put(a.getIdAnimal(), a);
    }

    public Animal getAnimal(String id) throws UnknownAnimalException {
        Animal a = _animals.get(id);

        if (a == null) {
            throw new UnknownAnimalException(a.getIdAnimal());
        }

        return a;
    }

    public boolean verifyAnimalIdPresence(String id) {
        return _animals.containsKey(id);
    }

    // Necessario depois para a impressao
    public Collection<Animal> animals() {
        return Collections.unmodifiableCollection(_animals.values());
    } 
}