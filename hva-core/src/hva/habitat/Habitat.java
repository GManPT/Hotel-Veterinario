package hva.habitat;

import hva.animal.Animal;
import hva.CorrectComparator;

import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Habitat implements Serializable{

    /** id of the habitat */
    private String _idHabitat;

    /** name of the habitat */
    private String _name;

    /** area of the habitat */
    private int _area;
    
    /** trees in the habitat */
    private Map<String, Tree> _trees;

    /** animals in the habitat */
    private Map<String, Animal> _animals;

    /** species influence */
    private Map<String, String> _speciesInfluence;
    
    /**
     * Constructor of the class Habitat
     */
    public Habitat(String id, String name, int area) {
        _idHabitat = id;
        _name = name;
        _area = area;
        _trees = new TreeMap<String, Tree>(new CorrectComparator());
        _animals = new TreeMap<String, Animal>(new CorrectComparator());
        _speciesInfluence = new TreeMap<String, String>();
    }

    /**
     * get id habitat
     * 
     * @return id habitat
     */
    public String getIdHabitat() {
        return _idHabitat;
    }

    /**
     * get name habitat
     * 
     * @return name habitat
     */
    public String getNameHabitat() {
        return _name;
    }

    /**
     * get area habitat
     * 
     * @return area
     */
    public int getArea() {
        return _area;
    }

    /**
     * set area habitat
     * 
     * @param area
     */
    public void setArea(int area) {
        _area = area;
    }

    /**
     * get number of trees
     * 
     * @return number of trees
     */
    public int getNumberOfTrees() {
        return _trees.size();
    }

    /**
     * get population number
     * 
     * @return number of animals in habitat
     */
    public int getPopulationNumber() {
        return _animals.size();
    }

    /**
     * add animal to habitat
     * 
     * @param a
     */
    public void addAnimaltoHabitat (Animal a) {
        _animals.put(a.getIdAnimal(), a);
        _speciesInfluence.put(a.getIdSpecie(), "NEU");
    }

    /**
     * has animals of specie
     * 
     * @param idSpecie
     * 
     * @return true if there are animals of that specie in the habitat
     */
    private boolean hasAnimalsOfSpecie(String idSpecie) {
        for (Animal animal : _animals.values()) {
            if (animal.getIdSpecie().equals(idSpecie)) {
                return true;
            }
        }
        return false;
    }

    /**
     * remove animal from habitat
     * 
     * @param a
     */
    public void removeAnimal(Animal a) {
        _animals.remove(a.getIdAnimal());

        if (!hasAnimalsOfSpecie(a.getIdSpecie())) {
            _speciesInfluence.remove(a.getIdSpecie());
        }
    }

    /**
     * get trees
     * 
     * @return trees
     */
    public Collection<Tree> getTrees() {
        return Collections.unmodifiableCollection(_trees.values());
    }

    /**
     * get animals
     * 
     * @return animals
     */
    public Collection<Animal> getAnimals() {
        return Collections.unmodifiableCollection(_animals.values());
    }

    /**
     * add tree
     * 
     * @param t
     */
    public void addTree(Tree t) {
        _trees.put(t.getIdTree(),t);
    }

    /**
     * set species influence
     * 
     * @param idSpecie
     * @param influence
     */
    public void setSpeciesInfluence(String idSpecie, String influence) {
        if (hasAnimalsOfSpecie(idSpecie)) {
            _speciesInfluence.put(idSpecie, influence);
        }
    }

    /**
     * tree exists
     * 
     * @param idTree
     * @return true if tree exists
     */
    public boolean treeExists(String idTree) {
        return _trees.containsKey(idTree);
    }

    /**
     * habitat to string
     * 
     * @return habitat to string
     */
    @Override
    public String toString() {
        return "HABITAT|" + _idHabitat + "|" + _name + "|" + _area + "|" + getNumberOfTrees();
    }
}