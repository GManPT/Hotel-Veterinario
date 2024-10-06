package hva.animal;

import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.Collections;


public class Specie {
    private String _idSpecie;
    private String _nameSpecie;
    private Map<String, Animal> _animals;

    public Specie(String id, String name) {
        _animals = new TreeMap<String, Animal>();
        _idSpecie = id;
        _nameSpecie = name;
    }

    public String getIdSpecie() {
        return _idSpecie;
    }

    public String getNameSpecie() {
        return _nameSpecie;
    }

    public void addAnimaltoSpecie(Animal a) {
        if (_animals.get(a.getIdAnimal()) != null) {
            
        }

        _animals.put(a.getIdAnimal(), a);
    }

    public Animal getAnimal(String id) {
        Animal a = _animals.get(id);

        if (a == null) {
            
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