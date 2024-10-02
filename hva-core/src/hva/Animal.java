package hva;

public class Animal extends Specie{
    private String _idAnimal;
    private String _nameAnimal;
    private String _currentHabitat;

    
    public Animal(String id, String name, String habitat) {
        super(id, name);
        _idAnimal = id;
        _nameAnimal = name;
        _currentHabitat = habitat;
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