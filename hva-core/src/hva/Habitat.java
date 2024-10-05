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
    private int _population;
    private int _numTrees;
    
    private ArrayList<Tree> _trees;
    private Map<String, Animal> _animals;
    private Map<String, String> _speciesInfluence;

    public Habitat(String id, String name, int area) {
        _idHabitat = id;
        _name = name;
        _area = area;
        _numTrees = 0;
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

    public void setArea(int newArea) {
        _area = newArea;
    }

    public int getNumberOfTrees() {
        return _trees.size();
    }

    public int getPopulationNumber() {
        return _animals.size();
    }

    public void addAnimaltoHabitat (String idAnimal,String name,String idHabitat) {
        Animal a = new Animal(idAnimal, name, idHabitat);
        _animals.put(a.getIdAnimal(), a);
    }
}