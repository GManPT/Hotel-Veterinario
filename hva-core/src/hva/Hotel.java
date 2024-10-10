package hva;

import java.io.Serial;
import java.io.Serializable;
import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.LinkedHashMap;
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
import hva.CorrectComparator;

import hva.exceptions.DuplicateAnimalException;
import hva.exceptions.DuplicateHabitatException;
import hva.exceptions.DuplicateTreeException;
import hva.exceptions.DuplicateEmployeeException;
import hva.exceptions.ResponsibilityException;
import hva.exceptions.DuplicateVaccineException;
import hva.exceptions.UnknownSpeciesException;
import hva.exceptions.UnknownVeterinarianException;
import hva.exceptions.VeterinarianAuthorizedException;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.UnknownTreeException;
import hva.exceptions.ImportFileException;
import hva.exceptions.UnrecognizedEntryException;


public class Hotel implements Serializable {

    /** Class Serial Number */
    @Serial
    private static final long serialVersionUID = 202407081733L;

    /** Species */
    public Map<String, Specie> _species;

    /** Animals */
    public LinkedHashMap<String, Animal> _animals;

    /** Habitats */
    public Map<String, Habitat> _habitats;

    /** Employees */
    public Map<String, Employee> _employees;

    /** Vaccines */
    public Map<String, Vaccine> _vaccines;

    /** Trees */
    public Map<String, Tree> _trees;

    /** Total Vaccinations */
    public List<String> _vaccinations;

    /** Wrong Vaccinations */
    public List<String> _wrongVaccinations;


    /** Modified */
    private boolean _modified = false;

    /** 
     * Set the modified attribute to a boolean
     * 
     * @param mod
     */
    public void setModified(boolean mod) {
        _modified = mod;
    }

    /** 
     * Check the value of modified attribute
     * 
     * @return the value of _modified
     */
    public boolean isModified() {
        return _modified;
    }

    /**
    * Hotel Constructor
    */
    public Hotel() {
        _species = new TreeMap<String, Specie>(new CorrectComparator());
        _animals = new LinkedHashMap<String, Animal>();
        _habitats = new TreeMap<String, Habitat>(new CorrectComparator());
        _employees = new TreeMap<String, Employee>(new CorrectComparator());
        _vaccines = new TreeMap<String, Vaccine>(new CorrectComparator());
        _trees = new TreeMap<String, Tree>(new CorrectComparator());
        _vaccinations = new ArrayList<String>();
        _wrongVaccinations = new ArrayList<String>();
    }

    /** 
     * Check if species exist
     * 
     * @param idSpecie
     * @return true if specie exists
     */
    public boolean speciesExists(String idSpecie) {
        return _species.containsKey(idSpecie);
    }

    /** 
     * Get the species
     * 
     * @param idSpecie
     * @return the id of the specie
     */
    public Specie getSpecie(String idSpecie) {
        return _species.get(idSpecie);
    }

    /** 
     * Get all species
     * 
     * @param idAnimal
     * @return true if animal exists
     */
    public boolean animalExists(String idAnimal) {
        return _animals.containsKey(idAnimal);
    }

    /** 
     * Get the animal
     * 
     * @param idAnimal
     * @return Animal object found
     */
    public Animal getAnimal(String idAnimal) {
        return _animals.get(idAnimal);
    }

    /** 
     * Register a new specie
     * 
     * @param idSpecie
     * @param nameSpecie
     */
    public void registerNewSpecie(String idSpecie, String nameSpecie) {
        if (nameSpecie != null) {
            _species.put(idSpecie, new Specie(idSpecie, nameSpecie));
            _modified = true;
        }
    }

    /** 
     * Register a new animal and also the specie in case it doesn't exist yet
     * 
     * @param idAnimal
     * @param nameAnimal
     * @param idSpecie
     * @param nameSpecie
     * @param idHabitat
     * @throws DuplicateAnimalException
     * @throws UnknownHabitatException
     */
    public void registerNewAnimal(String idAnimal, String nameAnimal, String idSpecie,
                                String nameSpecie, String idHabitat) 
    throws DuplicateAnimalException, UnknownHabitatException {
        // Verificar se o id do animal ja existe
        if (animalExists(idAnimal)) {
            throw new DuplicateAnimalException(idAnimal);
        }

        Habitat habitat = getHabitat(idHabitat);
        if (!habitatExists(idHabitat)) {
            throw new UnknownHabitatException(idHabitat);
        }

        registerNewSpecie(idSpecie, nameSpecie);
        Animal a = new Animal(idAnimal, nameAnimal, idSpecie, idHabitat);
        getSpecie(idSpecie).addAnimaltoSpecie(a);

        habitat.addAnimaltoHabitat(a);
        _animals.put(idAnimal, a);

        _modified = true;
    }

    /**
     * Get all animals
     * 
     * @return all animals
     */
    public Collection<Animal> speciesAnimals() {
        return Collections.unmodifiableCollection(_animals.values());
    }

    /**
     * changes the animal habitat
     * 
     * @param idAnimal
     * @param habitatKey
     * @throws UnknownHabitatException
     * @throws UnknownAnimalException
     */
    public void changeAnimalHabitat(String idAnimal, String habitatKey) 
    throws UnknownHabitatException, UnknownAnimalException {
        Animal a = getAnimal(idAnimal);
        if (a == null) {
            throw new UnknownAnimalException(idAnimal);
        }

        if (!habitatExists(habitatKey)) {
            throw new UnknownHabitatException(habitatKey);
        }

        /** Remove from the old habitat */
        getHabitat(a.getCurrentHabitat()).removeAnimal(a);
        a.setHabitat(habitatKey);

        /** Add to the new habitat */
        getHabitat(habitatKey).addAnimaltoHabitat(a);

        _modified = true;
    }

    /**
     * get the habitat
     * 
     * @param idHabitat
     * @return habitat 
     */
    public Habitat getHabitat(String idHabitat) {
        return _habitats.get(idHabitat);
    }

    /**
     * get the tree
     * 
     * @param idTree
     * @return tree
     */
    public Tree getTree(String idTree) {
        return _trees.get(idTree);
    }

    /**
     * check if habitat exists
     * 
     * @param idHabitat
     * @return true if habitat exists
     */
    public boolean habitatExists(String idHabitat) {
        return _habitats.containsKey(idHabitat);
    }

    /**
     * check if tree exists
     * 
     * @param idTree
     * @return true if the tree exists
     */
    public boolean treeExists(String idTree) {
        return _trees.containsKey(idTree);
    }

    /**
     * register a new habitat
     * 
     * @param idHabitat
     * @param name
     * @param area
     * @throws DuplicateHabitatException
     */
    public void registerNewHabitat (String idHabitat, String name, int area) throws
    DuplicateHabitatException {
        if (habitatExists(idHabitat)) {
            throw new DuplicateHabitatException(idHabitat);
        }

        _habitats.put(idHabitat, new Habitat(idHabitat, name, area));
        _modified = true;
    }

    /**
     * change the habitat area
     * 
     * @param habitatKey
     * @param area
     * @throws UnknownHabitatException
     */
    public void changeHabitatArea(String habitatKey, int area)
    throws UnknownHabitatException {
        if (!habitatExists(habitatKey)) {
            throw new UnknownHabitatException(habitatKey);
        }

        getHabitat(habitatKey).setArea(area);
        _modified = true;
    }

    /**
     * change the habitat influence
     * 
     * @param habitatKey
     * @param speciesKey
     * @param habitatInfluence
     */
    public void changeHabitatInfluence(String habitatKey, String speciesKey, String habitatInfluence)
    throws UnknownHabitatException, UnknownSpeciesException {
        if (!habitatExists(habitatKey)) {
            throw new UnknownHabitatException(habitatKey);
        }

        if (!speciesExists(speciesKey)) {
            throw new UnknownSpeciesException(speciesKey);
        }

        getHabitat(habitatKey).setSpeciesInfluence(speciesKey, habitatInfluence);
        _modified = true;
    }

    /**
     * get all trees
     * 
     * @param idHabitat
     * @return all habitats
     */
    public Collection<Tree> trees(String idHabitat) throws UnknownHabitatException {
        Collection<Tree> allTrees = new ArrayList<>();
        if (!habitatExists(idHabitat)) {
            throw new UnknownHabitatException(idHabitat);
        }

        Habitat habitat = getHabitat(idHabitat);
        allTrees.addAll(habitat.getTrees());
        return Collections.unmodifiableCollection(allTrees);
    }

    /**
     * return all habitats and trees
     * 
     * @return all habitats and trees
     */
    public Collection<Object> habitatAndTrees() {
        Collection<Object> allHabitats = new ArrayList<>();

        for (Habitat habitat : _habitats.values()) {
            allHabitats.add(habitat);
            allHabitats.addAll(habitat.getTrees());
        }

        return Collections.unmodifiableCollection(allHabitats);
    }

    /**
     * plant a tree in a habitat
     * 
     * @param idHabitat
     * @param idTree
     * @param treeName
     * @param treeAge
     * @param treeDifficulty
     * @param treeType
     * @throws DuplicateTreeException
     * @throws UnknownHabitatException
     */
    public void plantTreeHabitat (String idHabitat, String idTree, String treeName, int treeAge, int treeDifficulty, String treeType)
    throws DuplicateTreeException, UnknownHabitatException {
        Habitat habitat = getHabitat(idHabitat);
        
        if (!habitatExists(idHabitat)) {
            throw new UnknownHabitatException(idHabitat);
        }

        if (habitat.treeExists(idTree)) {
            throw new DuplicateTreeException(idTree);
        }

        if (treeType.equals("CADUCA")) {
            habitat.addTree(new DeciduousTree(idTree, treeName, treeAge, treeDifficulty));
        } else if (treeType.equals("PERENE")) {
            habitat.addTree(new EvergreenTree(idTree, treeName, treeAge, treeDifficulty));
        }

        _modified = true;
    }

    /**
     * plant a tree
     * 
     * @param idTree
     * @param treeName
     * @param treeAge
     * @param treeDifficulty
     * @param treeType
     * @throws DuplicateTreeException
     */
    public void plantTree(String idTree, String treeName, int treeAge, int treeDifficulty, String treeType) 
    throws DuplicateTreeException {
        if (treeExists(idTree)) {
            throw new DuplicateTreeException(idTree);
        }

        if (treeType.equals("CADUCA")) {
            _trees.put(idTree, new DeciduousTree(idTree, treeName, treeAge, treeDifficulty));
        } else if (treeType.equals("PERENE")) {
            _trees.put(idTree, new EvergreenTree(idTree, treeName, treeAge, treeDifficulty));
        }
        _modified = true;
    }

    /**
     * deal with trees
     * 
     * @param idHabitat
     * @param trees
     * @throws DuplicateTreeException
     * @throws UnknownHabitatException
     */
    public void dealTrees(String idHabitat, String trees) throws DuplicateTreeException, UnknownHabitatException,
    UnknownTreeException {
        String[] ts = trees.split(",");
        for (String tree : ts) {
            if (!treeExists(tree)) {
                throw new UnknownTreeException(tree);
            }

            Tree t = getTree(tree);
            plantTreeHabitat(idHabitat, t.getIdTree(), t.getName(), t.getTreeAge(),
            t.getCleaningDifficulty(), t instanceof DeciduousTree ? "CADUCA" : "PERENE");  
        }
    }

    /**
     * get all animals in a habitat
     * 
     * @param idHabitat
     * @return all animals in a habitat
     */
    public Collection<Animal> animalsInHabitat(String idHabitat)
    throws UnknownHabitatException {
        if (!habitatExists(idHabitat)) {
            throw new UnknownHabitatException(idHabitat);
        }

        return getHabitat(idHabitat).getAnimals();
    }

    /**
     * get Employee
     * 
     * @param idEmployee
     * @return Employee
     */
    public Employee getEmployee(String idEmployee) {
        return _employees.get(idEmployee);
    }

    /**
     * check if employee is a veterinarian
     * 
     * @param idEmployee
     * @return true if employee is a veterinarian
     */
    public boolean isVet(String idEmployee) {
        return _employees.get(idEmployee) instanceof Veterinarian;
    }

    /**
     * check if employee exists
     * 
     * @param idEmployee
     * @return true if employee exists
     */
    public boolean employeeExists(String idEmployee) {
        return _employees.containsKey(idEmployee);
    }

    /**
     * register a new employee
     * 
     * @param idEmployee
     * @param nameEmployee
     * @param employeeType
     * @throws DuplicateEmployeeException
     */
    public void registerNewEmployee(String idEmployee, String nameEmployee, 
    String employeeType) throws DuplicateEmployeeException {
        if (employeeExists(idEmployee)) {
            throw new DuplicateEmployeeException(idEmployee);
        }

        if (employeeType.equals("TRT")) {
            _employees.put(idEmployee, new Keeper(idEmployee, nameEmployee));
        } else if (employeeType.equals("VET")) {
            _employees.put(idEmployee, new Veterinarian(idEmployee, nameEmployee));
        }
        _modified = true;
    }

    /**
     * add responsibility to an employee
     * 
     * @param idEmployee
     * @param id
     * @param isVet
     * @throws ResponsibilityException
     * @throws UnknownEmployeeException
     */
    public void addResponsability(String idEmployee, String id, boolean isVet)
    throws ResponsibilityException, UnknownEmployeeException {
        Employee employee = getEmployee(idEmployee);
        if (!employeeExists(idEmployee)) {
            throw new UnknownEmployeeException(idEmployee);
        }
        
        boolean speciesExists = speciesExists(id);
        boolean habitatExists = habitatExists(id);

        /** Check if the specie or habitat exists */
        if (!speciesExists && !habitatExists) {
            throw new ResponsibilityException(idEmployee, id);
        }

        if (isVet) {
            if (speciesExists) {
                ((Veterinarian) employee).addSpecie(getSpecie(id));
            } else {
                throw new ResponsibilityException(idEmployee, id);
            }
        } else {
            if (habitatExists) {
                ((Keeper) employee).addHabitat(getHabitat(id));
            } else {
                throw new ResponsibilityException(idEmployee, id);
            }
        }

        _modified = true;
    }

    public void dealResponsabilities(String idEmployee, String responsibilities, boolean isVet) 
    throws ResponsibilityException, UnknownEmployeeException {
        String[] rs = responsibilities.split(",");
        for (String responsibility : rs) {
            addResponsability(idEmployee, responsibility, isVet);
        }
    }

    /**
     * remove responsibility from an employee
     * 
     * @param idEmployee
     * @param id
     * @throws UnknownEmployeeException
     * @throws ResponsibilityException
     */
    public void removeResponsibility(String idEmployee, String id) 
    throws UnknownEmployeeException, ResponsibilityException {
        Employee employee = getEmployee(idEmployee);

        // Verificar se o employee existe
        if (!employeeExists(idEmployee)) {
            throw new UnknownEmployeeException(idEmployee);
        }

        // Verificar se a especie ou habitat existe
        if (speciesExists(id)) {
            ((Veterinarian) employee).removeSpecie(getSpecie(id));
        } else if (habitatExists(id)) {
            ((Keeper) employee).removeHabitat(getHabitat(id));
        } else {
            throw new ResponsibilityException(idEmployee, id);
        }
        _modified = true;
    }

    /**
     * get all employees
     * 
     * @return all employees
     */
    public Collection<Employee> employees() {
        Collection<Employee> allEmployees = new ArrayList<>();
        allEmployees.addAll(_employees.values());
        return Collections.unmodifiableCollection(allEmployees);
    }


    /**
     * get vaccine
     * 
     * @return vaccine
     */
    public Vaccine getVaccine(String idVaccine) {
        return _vaccines.get(idVaccine);
    }

    /**
     * check if vaccine exists
     * 
     * @param idVaccine
     * @return true if vaccine exists
     */
    public boolean vaccineExists(String idVaccine) {
        return _vaccines.containsKey(idVaccine);
    }

    /**
     * register a new vaccine
     * 
     * @param idVaccine
     * @param nameVaccine
     * @param speciesIdentifiers
     * @throws DuplicateVaccineException
     * @throws UnknownSpeciesException
     */
    public void registerVaccine(String idVaccine, String nameVaccine, String speciesIdentifiers)
    throws DuplicateVaccineException, UnknownSpeciesException {
        // Verificar se o id ja existe
        if (getVaccine(idVaccine) != null) {
            throw new DuplicateVaccineException(idVaccine);
        }

        // Verificar se as especies existem
        String[] species = speciesIdentifiers.split(",");
        ArrayList<Specie> approvedSpecies = new ArrayList<>();

        if (speciesIdentifiers.length() > 0) {
            for (String specie : species) {
                if (!speciesExists(specie)) {
                    throw new UnknownSpeciesException(specie);
                }

                approvedSpecies.add(getSpecie(specie));
            }
        }

        _vaccines.put(idVaccine, new Vaccine(idVaccine, nameVaccine, approvedSpecies));
        _modified = true;
    }

    /**
     * get all vaccines
     * 
     * @return all vaccines
     */
    public Collection<Vaccine> vaccines() {
        Collection<Vaccine> allVaccines = new ArrayList<>();
        allVaccines.addAll(_vaccines.values());
        return Collections.unmodifiableCollection(allVaccines);
    }

    /**
     * check if animal was appropriately vaccinated
     * 
     * @param animalSpecieId
     * @param vaccineKey
     * @return true if animal was vaccinated
     */
    public boolean wasAppropriatelyVaccinated(String animalSpecieId, String vaccineKey) {
        Vaccine vaccine = getVaccine(vaccineKey);
        String SpecieId = getAnimal(animalSpecieId).getIdSpecie();

        return vaccine.isApprovedFor(getSpecie(SpecieId));
    }

    /**
     * check if animal should be vaccinated
     * 
     * @param vaccineKey
     * @param veterinarianKey
     * @param animalKey
     * @return true if animal should be vaccinated
     * @throws UnknownVeterinarianException
     * @throws VeterinarianAuthorizedException
     */
    public boolean shouldBeVaccinated(String vaccineKey, String veterinarianKey, String animalKey)
    throws UnknownVeterinarianException, VeterinarianAuthorizedException {
        // Verificar se o employee e veterinario
        if (!isVet(veterinarianKey)) {
            throw new UnknownVeterinarianException(veterinarianKey);
        }
        
        // Verificar se o veterinario pode dar a vacina
        Veterinarian veterinarian = (Veterinarian) getEmployee(veterinarianKey);
        String specieId = getAnimal(animalKey).getIdSpecie();
        if (!veterinarian.hasPermission(specieId)) {
            throw new VeterinarianAuthorizedException(specieId, veterinarianKey);
        }

        return true;
    }

    /**
     * vaccinate an animal
     * 
     * @param vaccineKey
     * @param veterinarianKey
     * @param animalKey
     */
    public void vaccinateAnimal(String vaccineKey, String veterinarianKey, String animalKey) {
        Animal a = getAnimal(animalKey);
        String application = "REGISTO-VACINA|" + vaccineKey + "|" + veterinarianKey + "|" + a.getIdSpecie();

        // Adicionar ao registo do hotel
        _vaccinations.add(application);
        if (!wasAppropriatelyVaccinated(animalKey, vaccineKey)) {
            _wrongVaccinations.add(application);
        }

        // Adicionar ao registo da vacina, veterinario e animal
        getVaccine(vaccineKey).addApplication(application);
        ((Veterinarian) getEmployee(veterinarianKey)).addHistoric(application);
        a.addVaccination(application);
        _modified = true;
    }

    /**
     * get all vaccinations
     * 
     * @return all vaccinations
     */
    public Collection<String> vaccinations() {
        return Collections.unmodifiableCollection(_vaccinations);
    }

    /**
     * get all wrong vaccinations
     * 
     * @return all wrong vaccinations
     */
    public Collection<String> wrongVaccinations() {
        return Collections.unmodifiableCollection(_wrongVaccinations);
    }

    /**
     * get all historic vaccinations of a veterinarian
     * 
     * @param employeeKey
     * @return all historic vaccinations of a veterinarian
     * @throws UnknownVeterinarianException
     */
    public Collection<String> veterinarianHistoric(String employeeKey) 
    throws UnknownVeterinarianException{
        if (!isVet(employeeKey)) {
            throw new UnknownVeterinarianException(employeeKey);
        }

        Veterinarian veterinarian = (Veterinarian) getEmployee(employeeKey);
        return veterinarian.HistoricVaccinations();
    }

    /**
     * get all medical acts on an animal
     * 
     * @param animalKey
     * @throws UnknownAnimalException
     * @return all medical acts on an animal
     */
    public Collection<String> medicalActsOnAnimal(String animalKey)
    throws UnknownAnimalException {
        if (!animalExists(animalKey)) {
            throw new UnknownAnimalException(animalKey);
        }

        return getAnimal(animalKey).getVaccinations();
    }

    /**
     * Read text input file and create domain entities.
     *
     * @param file
     * name name of the text input file
     * @throws ImportFileException
     */
    void importFile(String filename) throws ImportFileException, UnrecognizedEntryException {
        if (filename == null || filename.equals(""))
            return;

	    try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
			String s;

			while ((s = reader.readLine()) != null) {
                String line = new String(s.getBytes(), "UTF-8");
                if (line.charAt(0) == '#')
                    continue;
				
                String[] parse = line.split("\\|");
                switch (parse[0]) {
                    case "ESPÉCIE" -> {
                        registerNewSpecie(parse[1], parse[2]);
                    }
                    case "ANIMAL" -> {
                        registerNewAnimal(parse[1], parse[2], parse[3], null, parse[4]);
                    }
                    case "HABITAT" -> {
                        registerNewHabitat(parse[1], parse[2], Integer.parseInt(parse[3]));
                        if (parse.length > 4) {
                            dealTrees(parse[1], parse[4]);
                        }
                    }
                    case "TRATADOR" -> {
                        registerNewEmployee(parse[1], parse[2], "TRT");
                        if (parse.length > 3) {
                            dealResponsabilities(parse[1], parse[3], false);
                        }
                    }
                    case "VETERINÁRIO" -> {
                        registerNewEmployee(parse[1], parse[2], "VET");
                        if (parse.length > 3) {
                            dealResponsabilities(parse[1], parse[3], true);
                        }
                    }
                    case "VACINA" -> {
                        if (parse.length == 4) {
                            registerVaccine(parse[1], parse[2], parse[3]);
                        } else {
                            registerVaccine(parse[1], parse[2], "");
                        }
                    }
                    case "ÁRVORE" -> {
                        plantTree(parse[1], parse[2], Integer.parseInt(parse[3]), Integer.parseInt(parse[4]), parse[5]);
                    }
                    default -> {
                        throw new UnrecognizedEntryException(filename);
                    }
                }
            }
		} catch (DuplicateAnimalException | DuplicateHabitatException | DuplicateEmployeeException
                | DuplicateVaccineException | UnknownSpeciesException | DuplicateTreeException
                | UnknownHabitatException | UnknownEmployeeException | ResponsibilityException 
                | UnknownTreeException | IOException e) {
            throw new ImportFileException(filename, e);
        }
    }
}