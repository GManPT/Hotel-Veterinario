package hva;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;

import hva.habitat.Habitat;
import hva.animal.Animal;
import hva.animal.Specie;
import hva.habitat.Tree;
import hva.habitat.DeciduousTree;
import hva.habitat.EvergreenTree;
import hva.employee.Employee;
import hva.employee.Keeper;
import hva.employee.Veterinarian;
import hva.vaccine.Vaccine;
import hva.exceptions.DuplicateAnimalException;
import hva.exceptions.DuplicateHabitatException;
import hva.exceptions.DuplicateTreeException;
import hva.exceptions.DuplicateEmployeeException;
import hva.exceptions.ResponsibilityException;
import hva.exceptions.DuplicateVaccineException;
import hva.exceptions.UnknownSpeciesException;
import hva.exceptions.UnknownVeterinarianException;
import hva.exceptions.ImportFileException;
import java.io.IOException;


public class Hotel implements Serializable {
    @Serial
    private static final long serialVersionUID = 202407081733L;

    // Definir atributos
    public Map<String, Specie> _species;
    public Map<String, Habitat> _habitats;
    public Map<String, Employee> _employees;
    public Map<String, Vaccine> _vaccines;
    public List<String> _vaccinations;
    public List<String> _wrongVaccinations;


    public Hotel() {
        _species = new TreeMap<String, Specie>();
        _habitats = new TreeMap<String, Habitat>();
        _employees = new TreeMap<String, Employee>();
        _vaccines = new TreeMap<String, Vaccine>();
        _vaccinations = new ArrayList<String>();
        _wrongVaccinations = new ArrayList<String>();
    }


    /**
     * ANIMALS
     */

    public boolean speciesExists(String idSpecie) {
        return _species.containsKey(idSpecie);
    }

    public Specie getSpecie(String idSpecie) {
        return _species.get(idSpecie);
    }

    public boolean animalExists(String idAnimal) {
        for (Specie s : _species.values()) {
            if (s.verifyAnimalIdPresence(idAnimal)) {
                return true;
            }
        }

        return false;
    }

    public Animal getAnimal(String idAnimal) {
        for (Specie s : _species.values()) {
            Animal a = s.getAnimal(idAnimal);

            if (a != null) {
                return a;
            }
        }

        return null;
    }

    public void registerNewAnimal(String idAnimal, String nameAnimal, String idSpecie, String nameSpecie, String idHabitat) 
    throws DuplicateAnimalException {
        // Verificar se o id do animal ja existe
        if (animalExists(idAnimal)) {
            throw new DuplicateAnimalException(idAnimal);
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
    public Habitat getHabitat(String idHabitat) {
        return _habitats.get(idHabitat);
    }

    public boolean habitatExists(String idHabitat) {
        return _habitats.containsKey(idHabitat);
    }

    public void registerNewHabitat (String idHabitat, String name, int area) throws
    DuplicateHabitatException {
        if (getHabitat(idHabitat) != null) {
            throw new DuplicateHabitatException(idHabitat);
        }

        _habitats.put(idHabitat, new Habitat(idHabitat, name, area));
    }

    // Apenas arvores
    public Collection<Tree> trees(String idHabitat) {
        Collection<Tree> allTrees = new ArrayList<>();
        Habitat habitat = getHabitat(idHabitat);

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
    public void plantTreeHabitat (String idHabitat, String idTree, String treeName, int treeAge, int treeDifficulty, String treeType)
    throws DuplicateTreeException {
        Habitat habitat = getHabitat(idHabitat);

        if (habitat.treeExists(idTree)) {
            throw new DuplicateTreeException(idTree);
        }

        if (treeType.equals("CADUCA")) {
            habitat.addTree(new DeciduousTree(idTree, treeName, treeAge, treeDifficulty));
        } else if (treeType.equals("PERENE")) {
            habitat.addTree(new EvergreenTree(idTree, treeName, treeAge, treeDifficulty));
        }
    }


    /**
     * EMPLOYEES
     */
    public Employee getEmployee(String idEmployee) {
        return _employees.get(idEmployee);
    }

    public boolean isVet(String idEmployee) {
        return _employees.get(idEmployee) instanceof Veterinarian;
    }

    public boolean employeeExists(String idEmployee) {
        return _employees.containsKey(idEmployee);
    }

    public void registerNewEmployee(String idEmployee, String nameEmployee, String employeeType)
    throws DuplicateEmployeeException {
        if (employeeExists(idEmployee)) {
            throw new DuplicateEmployeeException(idEmployee);
        }

        if (employeeType.equals("TRT")) {
            _employees.put(idEmployee, new Keeper(idEmployee, nameEmployee));
        } else if (employeeType.equals("VET")) {
            _employees.put(idEmployee, new Veterinarian(idEmployee, nameEmployee));
        }
    }

    public void addResponsability(String idEmployee, String id, boolean isVet)
    throws ResponsibilityException {
        Employee employee = getEmployee(idEmployee);

        // Caso nao exista a especie ou habitat
        if (!speciesExists(id) || !habitatExists(id)) {
            throw new ResponsibilityException(idEmployee, id);
        }

        if (isVet) {
            ((Veterinarian) employee).addSpecie(getSpecie(id));
        } else {
            ((Keeper) employee).addHabitat(getHabitat(id));
        }
    }

    public Collection<Employee> employees() {
        Collection<Employee> allEmployees = new ArrayList<>();
        allEmployees.addAll(_employees.values());
        return Collections.unmodifiableCollection(allEmployees);
    }


    /**
     * VACCINES
     */
    public Vaccine getVaccine(String idVaccine) {
        return _vaccines.get(idVaccine);
    }

    public boolean vaccineExists(String idVaccine) {
        return _vaccines.containsKey(idVaccine);
    }

    public void registerVaccine(String idVaccine, String nameVaccine, String speciesIdentifiers)
    throws DuplicateVaccineException, UnknownSpeciesException {
        // Verificar se o id ja existe
        if (getVaccine(idVaccine) != null) {
            throw new DuplicateVaccineException(idVaccine);
        }

        // Verificar se as especies existem
        String[] species = speciesIdentifiers.split(",");
        ArrayList<Specie> approvedSpecies = new ArrayList<>();

        for (String specie : species) {
            if (!speciesExists(specie)) {
                throw new UnknownSpeciesException(specie);
            }

            approvedSpecies.add(getSpecie(specie));
        }

        _vaccines.put(idVaccine, new Vaccine(idVaccine, nameVaccine, approvedSpecies));
    }

    public Collection<Vaccine> vaccines() {
        Collection<Vaccine> allVaccines = new ArrayList<>();
        allVaccines.addAll(_vaccines.values());
        return Collections.unmodifiableCollection(allVaccines);
    }

    public boolean wasAppropriatelyVaccinated(String animalSpecieId, String vaccineKey) {
        Vaccine vaccine = getVaccine(vaccineKey);
        String SpecieId = getAnimal(animalSpecieId).getIdSpecie();

        return vaccine.isApprovedFor(getSpecie(SpecieId));
    }

    public boolean shouldBeVaccinated(String vaccineKey, String veterinarianKey, String animalKey)
    throws UnknownVeterinarianException {
        // Verificar se o veterinario existe
        if (!isVet(veterinarianKey)) {
            throw new UnknownVeterinarianException(veterinarianKey);
        }

        return true;
    }

    public void vaccinateAnimal(String vaccineKey, String veterinarianKey, String animalKey) {
        String application = vaccineKey + "|" + veterinarianKey + "|" + getAnimal(animalKey).getIdSpecie();
        // Adicionar ao registo do hotel
        _vaccinations.add(application);
        if (!wasAppropriatelyVaccinated(animalKey, vaccineKey)) {
            _wrongVaccinations.add(application);
        }

        // Adicionar ao registo da vacina
        getVaccine(vaccineKey).addApplication(veterinarianKey + "|" + getAnimal(animalKey).getIdSpecie());
    }

    public Collection<String> vaccinations() {
        // Adicionar "Registo vacina" a todas as aplicacoes
        List<String> formattedVaccinations = new ArrayList<>();
        for (String vaccination : _vaccinations) {
            formattedVaccinations.add("REGISTO-VACINA|" + vaccination);
        }

        return Collections.unmodifiableCollection(formattedVaccinations);
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