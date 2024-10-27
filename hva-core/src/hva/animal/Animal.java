package hva.animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.io.Serializable;

public class Animal implements Serializable {
    /** Class Serial Number */
    private static final long serialVersionUID = 202407081733L;

    /** id of the animal */
    private String _idAnimal;

    /** name of the animal */
    private String _nameAnimal;

    /** current habitat of the animal */
    private String _currentHabitat;

    /** id of the specie of the animal */
    private String _idSpecie;

    /** list of vaccinations of the animal */
    private List<String> _vaccinations;

    /** list of health status of the animal */
    private List<String> _healthStatus;
    
    /**
     * Constructor for the Animal class
     */
    public Animal(String idAnimal, String name, String idSpecie, String idHabitat) {
        _idSpecie = idSpecie;
        _idAnimal = idAnimal;
        _nameAnimal = name;
        _currentHabitat = idHabitat;
        _vaccinations = new ArrayList<>();
        _healthStatus = new ArrayList<>();
    }
    
    /**
     * getIdAnimal
     * 
     * @return idAnimal
     */
    public String getIdAnimal() {
        return _idAnimal;
    }

    /**
     * getIdSpecie
     * 
     * @return idSpecie
     */
    public String getIdSpecie() {
        return _idSpecie;
    }

    /**
     * getNameAnimal
     * 
     * @return nameAnimal
     */
    public String getNameAnimal() {
        return _nameAnimal;
    }

    /**
     * getCurrentHabitat
     * 
     * @return currentHabitat
     */
    public String getCurrentHabitat() {
        return _currentHabitat;
    }

    /**
     * getHealthStatus
     * 
     * @return healthStatus
     */
    public String getHealthStatus() {
        String result = String.join(",", _healthStatus);
        return _healthStatus.isEmpty() ? "VOID" : result;
    }

    /**
     * set health status
     * @param String healthStatus
     */
    public void setHealthStatus(String healthStatus) {
        _healthStatus.add(healthStatus);
    }

    /**
     * setHabitat
     * 
     * @param id
     */
    public void setHabitat(String id) {
        _currentHabitat = id;
    }

    /**
     * addVaccination
     * 
     * @param idVaccination
     */
    public void addVaccination(String idVaccination) {
        _vaccinations.add(idVaccination);
    }

    /**
     * get Vaccinations
     * 
     * @return vaccinations
     */
    public Collection<String> getVaccinations() {
        return Collections.unmodifiableCollection(_vaccinations);
    }

    /**
     * Animal to String
     * 
     * @return Animal to String
     */
    @Override
    public String toString() {
        return "ANIMAL|" + _idAnimal + "|" + _nameAnimal + "|" + _idSpecie + "|" +
                getHealthStatus() + "|" + _currentHabitat;
    }

}