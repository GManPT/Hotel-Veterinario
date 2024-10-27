package hva.animal;

import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.Collections;
import java.io.Serializable;


public class Specie implements Serializable{
    private static final long serialVersionUID = 202407081733L;
    
    /** id of the specie */
    private String _idSpecie;

    /** name of the specie */
    private String _nameSpecie;

    /** animals of the specie */
    private Map<String, Animal> _animals;

    /**
     * Constructor of class Specie
     */
    public Specie(String id, String name) {
        _animals = new TreeMap<String, Animal>(String.CASE_INSENSITIVE_ORDER);
        _idSpecie = id;
        _nameSpecie = name;
    }

    /**
     * get number of animals
     * setSpeciesInfluence
     * @return number of animals
     */
    
    public int getNumberAnimals() {
        return _animals.size();
    }

    /**
     * get id of specie
     * 
     * @return id of specie
     */
    public String getIdSpecie() {
        return _idSpecie;
    }

    /**
     * get name of specie
     * 
     * @return name of specie
     */
    public String getNameSpecie() {
        return _nameSpecie;
    }

    /**
     * add animal to specie
     * 
     * @param a animal to add
     */
    public void addAnimaltoSpecie(Animal a) {
        _animals.put(a.getIdAnimal(), a);
    }

    /**
     * get animal from specie
     * 
     * @param id
     */
    public Animal getAnimal(String id) {
        return _animals.get(id);
    }

    /**
     * get all animals from specie
     * 
     * @return all animals from specie
     */
    public Collection<Animal> animals() {
        return Collections.unmodifiableCollection(_animals.values());
    } 
}