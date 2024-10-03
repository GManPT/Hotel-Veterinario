package hva;

import java.util.ArrayList;

public class Animal extends Specie{
    private String _idAnimal;
    private String _nameAnimal;
    private String _currentHabitat;
    private ArrayList<String> _healthStatus;
    
    public Animal(String idAnimal, String name, String idHabitat) {
        super();
        _idAnimal = idAnimal;
        _nameAnimal = name;
        _currentHabitat = idHabitat;
        _healthStatus = new ArrayList<>();
    }
    
    public String getIdAnimal() {
        return _idAnimal;
    }

    public String getNameAnimal() {
        return _nameAnimal;
    }

    public String getCurrentHabitat() {
        return _currentHabitat;
    }

    public void setHabitat(String id) {
        _currentHabitat = id;
    }

}