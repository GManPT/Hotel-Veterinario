package hva;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.ArrayList;
import java.util.Collections;

import hva.habitat.Habitat;
import hva.animal.Animal;
import hva.animal.Specie;
import hva.habitat.Tree;
import hva.habitat.DeciduousTree;
import hva.habitat.EvergreenTree;
import hva.exceptions.ImportFileException;
import java.io.IOException;


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


    /**
     * ANIMALS
     */

    public boolean speciesExists(String idSpecie) {
        return _species.containsKey(idSpecie);
    }

    public void registerNewAnimal(String idAnimal, String nameAnimal, String idSpecie, String nameSpecie, String idHabitat) {
        // Verificar se o id do animal ja existe
        for (Specie s : _species.values()) {
            if (s.verifyAnimalIdPresence(idAnimal)) {
                
            }
        }

        if (nameSpecie != null) {
            _species.put(idSpecie, new Specie(idSpecie, nameSpecie));
        }
        
        Animal a = new Animal(idAnimal, nameAnimal, idSpecie, idHabitat);

        // Adicionar o animal ao seu habitat e especie
        _species.put(idSpecie, new Specie(idSpecie, nameSpecie));
        _species.get(idSpecie).addAnimaltoSpecie(a);
        _habitats.get(idHabitat).addAnimaltoHabitat(a);
    }

    public Collection<Animal> speciesAnimals() {
        Collection<Animal> allAnimals = new ArrayList<>();

        for (Specie specie : _species.values()) {
            allAnimals.addAll(specie.animals());
        }
    
        return Collections.unmodifiableCollection(allAnimals);
    }


    /**
     * HABITATS
     */
    public void registerNewHabitat (String idHabitat, String name, int area) {
        _habitats.put(idHabitat, new Habitat(idHabitat, name, area));
    }

    public Habitat getHabitat(String idHabitat) {
        return _habitats.get(idHabitat);
    }

    // Apenas arvores
    public Collection<Tree> trees(String idHabitat) {
        Collection<Tree> allTrees = new ArrayList<>();
        Habitat habitat = getHabitat(idHabitat);

        if (habitat == null) {

        }

        allTrees.addAll(habitat.getTrees());
        return Collections.unmodifiableCollection(allTrees);
    }

    // Tanto arvores e habitats
    public Collection<Object> habitatAndTrees() {
        Collection<Object> allHabitats = new ArrayList<>();

        for (Habitat habitat : _habitats.values()) {
            allHabitats.add(habitat);
            allHabitats.addAll(habitat.getTrees());
        }

        return Collections.unmodifiableCollection(allHabitats);
    }

    // FIXME adicionar excecoes 
    public void plantTreeHabitat (String idHabitat, String idTree, String treeName, int treeAge, int treeDifficulty, String treeType) {
        Habitat habitat = getHabitat(idHabitat);

        if (habitat == null) {

        }

        if (habitat.treeExists(idTree)) {

        }

        if (treeType.equals("CADUCA")) {
            habitat.addTree(new DeciduousTree(idTree, treeName, treeAge, treeDifficulty));
        } else if (treeType.equals("PERENE")) {
            habitat.addTree(new EvergreenTree(idTree, treeName, treeAge, treeDifficulty));
        }
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