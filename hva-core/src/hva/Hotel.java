package hva;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import hva.exceptions.ImportFileException;
import hva.exceptions.UnrecognizedEntryException;
import hva.exceptions.DuplicateAnimalException;
import java.io.IOException;

//FIXME import other Java classes
//FIXME import project classes

public class Hotel implements Serializable {

    @Serial
    private static final long serialVersionUID = 202407081733L;

    // Definir atributos
    public Map<String, Specie> _species;
    public Map<String, Habitat> _habitats;

    public Hotel() {
        _species = new TreeMap<String, Specie>();
        _habitats = new TreeMap<String, Habitat>();
    }
    //FIXME define methods


    /* ANIMALS (tudo relacionado ao processo) */
    public void registerNewSpecie(String idSpecie, String name) {
        _species.put(idSpecie, new Specie(idSpecie, name));
    }

    public boolean speciesExists(String idSpecie) {
        return _species.containsKey(idSpecie);
    }

    public void registerNewAnimal(String idAnimal, String name, String idSpecie, String idHabitat) throws DuplicateAnimalException {
        // Verificar se o id do animal ja existe
        for (Specie s : _species.values()) {
            if (s.verifyAnimalIdPresence(idAnimal)) {
                throw new DuplicateAnimalException(idAnimal);
            }
        }

        // Adicionar o animal ao seu habitat e especie
        _species.get(idSpecie).addAnimaltoSpecie(new Animal(idAnimal, name, idHabitat));
        _habitats.get(idHabitat).addAnimaltoHabitat(new Animal(idAnimal, name, idHabitat));
    }

    public void showAllAnimals() {
        // Implementar o codigo
    }


    /* HABITATS (tudo relacionado ao processo) 
        FIXME LEONARDO! Deves tentar registar um habitat. Isto sera necessario para registar um animal
        Olha atentamente como esta a implementacao acima do animal, DoRegisterAnimal.java, Animal.java e Specie.java
    */


    /**
     * Read text input file and create domain entities.
     *
     * @param file
     * name name of the text input file
     * @throws ImportFileException
     */
    void importFile(String filename) throws ImportFileException {
	    /*try {
                // FIXME open import file and create the associated objects
	        // ....
            } catch (IOException | UnrecognizedEntryException e) {
                throw new ImportFileException(filename, e);
            }*/
     }

}