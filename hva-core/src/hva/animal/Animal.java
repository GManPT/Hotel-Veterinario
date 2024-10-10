package hva.animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;
import java.io.Serializable;

public class Animal implements Serializable{
    private String _idAnimal;
    private String _nameAnimal;
    private String _currentHabitat;
    private String _idSpecie;
    private List<String> _vaccinations;
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
        return _healthStatus.isEmpty() ? "VOID" : _healthStatus.toString();
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