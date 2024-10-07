package hva.habitat;

import hva.animal.Animal;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Habitat {
    // Attributes
    private String _idHabitat;
    private String _name;
    private int _area;
    
    private List<Tree> _trees;
    private Map<String, Animal> _animals;
    private Map<String, String> _speciesInfluence;
    
    // Constructor
    public Habitat(String id, String name, int area) {
        _idHabitat = id;
        _name = name;
        _area = area;
        _trees = new ArrayList<Tree>();
        _animals = new TreeMap<String, Animal>();
        _speciesInfluence = new TreeMap<String, String>();
    }

    public String getIdHabitat() {
        return _idHabitat;
    }

    public String getNameHabitat() {
        return _name;
    }

    public int getArea() {
        return _area;
    }

    public void setArea(int area) {
        _area = area;
    }

    public int getNumberOfTrees() {
        return _trees.size();
    }

    public int getPopulationNumber() {
        return _animals.size();
    }

    public void addAnimaltoHabitat (Animal a) {
        _animals.put(a.getIdAnimal(), a);
        _speciesInfluence.put(a.getIdSpecie(), "NEU");
    }

    private boolean hasAnimalsOfSpecie(String idSpecie) {
        for (Animal animal : _animals.values()) {
            if (animal.getIdSpecie().equals(idSpecie)) {
                return true;
            }
        }
        return false;
    }

    public void removeAnimal(Animal a) {
        _animals.remove(a.getIdAnimal());

        if (!hasAnimalsOfSpecie(a.getIdSpecie())) {
            _speciesInfluence.remove(a.getIdSpecie());
        }
    }

    public Collection<Tree> getTrees() {
        return Collections.unmodifiableCollection(_trees);
    }

    public Collection<Animal> getAnimals() {
        return Collections.unmodifiableCollection(_animals.values());
    }

    public void addTree(Tree t) {
        _trees.add(t);
    }

    public void setSpeciesInfluence(String idSpecie, String influence) {
        if (hasAnimalsOfSpecie(idSpecie)) {
            _speciesInfluence.put(idSpecie, influence);
        }
    }

    public boolean treeExists(String idTree) {
        for (Tree t : _trees) {
            if (t.getIdTree().equals(idTree)) {
                return true;
            }
        }
        return false;
    }

    @Override
    public String toString() {
        return "HABITAT|" + getIdHabitat() + "|" + getNameHabitat() + "|" + getArea() + "|" + getNumberOfTrees();
    }
}