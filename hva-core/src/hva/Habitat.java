package hva;

import java.util.Map;
import java.util.TreeMap;
import java.util.ArrayList;

public class Habitat {
    // Attributes
    private String _idHabitat;
    private String _name;
    private int _area;

    // Necessario? Pode-se tornar ambos getters. Faz as mudancas que achares melhor
    // private int _population;
    // private int _numTrees;
    
    private ArrayList<Tree> _trees;
    private Map<String, Animal> _animals;
    private Map<String, Specie> _speciesInfluence;

    public Habitat(String id, String name) {
        _idHabitat = id;
        _name = name;
        _animals = new TreeMap<String, Animal>();
        _trees = new ArrayList<Tree>();
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

    public int getNumberOfTrees() {
        return _trees.size();
    }

    public int getPopulationNumber() {
        return _animals.size();
    }

    public void addAnimaltoHabitat (Animal a) {
        _animals.put(a.getIdAnimal(), a);
    }
}