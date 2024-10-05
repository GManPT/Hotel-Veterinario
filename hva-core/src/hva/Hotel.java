package hva;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;

import hva.exceptions.ImportFileException;

/* 
import hva.exceptions.UnrecognizedEntryException;


import hva.app.exceptions.DuplicateAnimalKeyException;
import hva.app.exceptions.DuplicateHabitatKeyException;
import hva.app.exceptions.UnknownHabitatKeyException;

import java.io.IOException;

*/

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





    public void registerNewAnimal(String idAnimal, String name, String idSpecie, String idHabitat) /*throws DuplicateAnimalKeyException, UnknownHabitatKeyException*/ {

        // disco - ir buscar ficheito .dat das especies, animais e habitats

        // verificar se a especie existe -> criar especie caso nao exista

        if (!_species.containsKey(idSpecie)) {

            _species.put(idSpecie, new Specie(idSpecie, name));
        }

        // verificar se o habitat existe

        /*
        if (!_habitats.containsKey(idHabitat)) {
            throw new UnknownHabitatKeyException(idHabitat);
        }
        */

        // Verificar se o id do animal ja existe

        /* 
        for (Specie s : _species.values()) {
            if (s.verifyAnimalIdPresence(idAnimal)) {
                throw new DuplicateAnimalKeyException(idAnimal);
            }
        }
        */

        // Adicionar o animal ao seu habitat e especie
        _species.get(idSpecie).addAnimaltoSpecie(idAnimal, name, idHabitat);

        // tirei o new animal porque isso implicaria que a classe hotel tivesse associação com a classe animal
        
        _habitats.get(idHabitat).addAnimaltoHabitat(idAnimal, name, idHabitat);

        // Disco - Guardar

    }

    public void registerNewHabitat(String idHabitat, String nameHabitat, int area) /*throws DuplicateHabitatKeyException*/ {

        // disco - importar ficheiros .dat dos habitats

        //verificar se o habitat existe

        /*
        if (_habitats.containsKey(idHabitat)) {
            throw new DuplicateHabitatKeyException(idHabitat);
        }
        */

        _habitats.put(idHabitat, new Habitat(idHabitat, nameHabitat, area));

    }

    public void showAllAnimals() {
        // Implementar o codigo

        
    }

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