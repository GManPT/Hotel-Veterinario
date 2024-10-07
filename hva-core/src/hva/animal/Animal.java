package hva.animal;

import java.util.ArrayList;
import java.util.List;
import java.util.Collection;
import java.util.Collections;

public class Animal {
    private String _idAnimal;
    private String _nameAnimal;
    private String _currentHabitat;
    private String _idSpecie;
    private List<String> _vaccinations;
    private List<String> _healthStatus;
    
    public Animal(String idAnimal, String name, String idSpecie, String idHabitat) {
        _idSpecie = idSpecie;
        _idAnimal = idAnimal;
        _nameAnimal = name;
        _currentHabitat = idHabitat;
        _vaccinations = new ArrayList<>();
        _healthStatus = new ArrayList<>();
    }
    
    public String getIdAnimal() {
        return _idAnimal;
    }

    public String getIdSpecie() {
        return _idSpecie;
    }

    public String getNameAnimal() {
        return _nameAnimal;
    }

    public String getCurrentHabitat() {
        return _currentHabitat;
    }

    public String getHealthStatus() {
        if (_healthStatus.isEmpty()) {
            return "VOID";
        }
        return _healthStatus.toString();
    }

    public void setHabitat(String id) {
        _currentHabitat = id;
    }

    public void addVaccination(String idVaccination) {
        _vaccinations.add(idVaccination);
    }

    public Collection<String> getVaccinations() {
        return Collections.unmodifiableCollection(_vaccinations);
    }

    @Override
    public String toString() {
        return "ANIMAL|" + getIdAnimal() + "|" + getNameAnimal() + "|" + getIdSpecie() + "|" + getHealthStatus() + "|" + getCurrentHabitat();
    }

}