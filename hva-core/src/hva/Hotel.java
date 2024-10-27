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
import java.util.Collections;

import hva.flora.Flora;
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
import hva.calculatesatisfaction.AnimalSatisfaction;
import hva.calculatesatisfaction.KeeperSatisfaction;
import hva.calculatesatisfaction.VeterinarianSatisfaction;
import hva.calculatesatisfaction.CalculateStrategy;


import hva.exceptions.DuplicateAnimalException;
import hva.exceptions.DuplicatedSpeciesNameException;
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
import hva.exceptions.UnknownVaccineException;
import hva.exceptions.UnrecognizedEntryException;
import hva.exceptions.DamagedVaccinationException;


public class Hotel implements Serializable {

    /** Class Serial Number */
    @Serial
    private static final long serialVersionUID = 202407081733L;

    /** Species */
    private Map<String, Specie> _species;

    /** Animals */
    private Map<String, Animal> _animals;

    /** Habitats */
    private Map<String, Habitat> _habitats;

    /** Employees */
    private Map<String, Employee> _employees;

    /** Vaccines */
    private Map<String, Vaccine> _vaccines;

    /** Trees */
    private Map<String, Tree> _trees;

    /** Total Vaccinations */
    private List<String> _vaccinations;

    /** Wrong Vaccinations */
    private List<String> _wrongVaccinations;

    /** Modified */
    private boolean _modified = false;

    /** Flora */
    private Flora _season;

    /**
    * Hotel Constructor
    */
    public Hotel() {
        /** Init the attributes */
        _species = new TreeMap<String, Specie>(String.CASE_INSENSITIVE_ORDER);
        _animals = new TreeMap<String, Animal>(String.CASE_INSENSITIVE_ORDER);
        _habitats = new TreeMap<String, Habitat>(String.CASE_INSENSITIVE_ORDER);
        _employees = new TreeMap<String, Employee>(String.CASE_INSENSITIVE_ORDER);
        _vaccines = new TreeMap<String, Vaccine>(String.CASE_INSENSITIVE_ORDER);
        _trees = new TreeMap<String, Tree>(String.CASE_INSENSITIVE_ORDER);
        _vaccinations = new ArrayList<String>();
        _wrongVaccinations = new ArrayList<String>();

        /** Create a new Flora for the hotel */
        _season = new Flora(this);
    }

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
     * Change the actual season to the next one
     * 
     * @return the number of the new season
     */
    public int changeSeason() {
        _season.getCurrentSeason().advanceSeason();
        return _season.getCurrentSeason().getNumSeason();
    }

    /**
     * get the flora
     * @return flora
     */
    public Flora getFlora() {
        return _season;
    }

    /**
     * get Animal
     * @return Animal
     */
    public Animal getAnimal(String idAnimal) {
        return _animals.get(idAnimal);
    }
    
    /**
     * get Habitat
     * @return Habitat
     */
    public Habitat getHabitat(String idHabitat) {
        return _habitats.get(idHabitat);
    }

    /**
     * get Specie
     * @return Specie
     */
    public Specie getSpecie(String idSpecie) {
        return _species.get(idSpecie);
    }

    /**
     * get Employee
     * @return Employee
     */
    public Employee getEmployee(String idEmployee) {
        return _employees.get(idEmployee);
    }

    /**
     * get Employees KeySet
     * @return keySet
     */
    public Collection<String> getEmployeesKeySet() {
        return _employees.keySet();
    }

    /** 
     * Check if species exist and sends result to App
     * 
     * @param idSpecie
     * @throws UnknownSpeciesException
     */
    public void speciesExists(String idSpecie) throws UnknownSpeciesException {
        /** Verify if idSpecie is a key of _species */
        if (!_species.containsKey(idSpecie)) {
            throw new UnknownSpeciesException(idSpecie);
        }
    }

    /** 
     * Register a new specie by creating a new Specie object
     * 
     * @param specieKey
     * @param nameSpecie
     */
    public void registerNewSpecie(String specieKey, String nameSpecie) 
    throws DuplicatedSpeciesNameException {
        /** Check if the name of the specie already exists */
        for (Specie s : _species.values()) {
            if (s.getNameSpecie().equalsIgnoreCase(nameSpecie)) {
                throw new DuplicatedSpeciesNameException();
            }
        }

        /** Create a new specie only if nameSpecie is not null */
        if (nameSpecie != null) {
            _species.put(specieKey, new Specie(specieKey, nameSpecie));
            _modified = true;
        }
    }

    /** 
     * Register a new animal and also the specie in case it doesn't exist yet
     * 
     * @param animalKey
     * @param nameAnimal
     * @param specieKey
     * @param nameSpecie
     * @param habitatKey
     * @throws DuplicateAnimalException
     * @throws UnknownHabitatException
     */
    public void registerNewAnimal(String animalKey, String nameAnimal, String specieKey,
    String nameSpecie, String habitatKey) throws DuplicateAnimalException, UnknownHabitatException,
    DuplicatedSpeciesNameException {
        /** Check if the animal already exists */
        if (_animals.containsKey(animalKey)) {
            throw new DuplicateAnimalException(animalKey);
        }

        /** Check if the habitat exists */
        Habitat habitat = _habitats.get(habitatKey);
        if (!(_habitats.containsKey(habitatKey))) {
            throw new UnknownHabitatException(habitatKey);
        }

        /** Register the new specie and animal */
        registerNewSpecie(specieKey, nameSpecie);
        Animal a = new Animal(animalKey, nameAnimal, _species.get(specieKey).getIdSpecie(), habitat.getIdHabitat());
        _species.get(specieKey).addAnimaltoSpecie(a);

        /** Add the animal to the habitat */
        habitat.addAnimaltoHabitat(a);
        _animals.put(animalKey, a);

        _modified = true;
    }

    /**
     * Get all animals and send them to App from printing purposes
     * 
     * @return an unmodifiable collection of all animals
     */
    public Collection<Animal> allAnimals() {
        return Collections.unmodifiableCollection(_animals.values());
    }

    /**
     * Change the habitat of an animal. Removes the animal from the old habitat
     * and adds it to the new one
     * 
     * @param animalKey
     * @param habitatKey
     * @throws UnknownHabitatException
     * @throws UnknownAnimalException
     */
    public void changeAnimalHabitat(String animalKey, String habitatKey) 
    throws UnknownHabitatException, UnknownAnimalException {
        /** Check if the animal exists */
        Animal a = _animals.get(animalKey);
        if (a == null) {
            throw new UnknownAnimalException(animalKey);
        }

        /** Check if the habitat exists */
        if (!_habitats.containsKey(habitatKey)) {
            throw new UnknownHabitatException(habitatKey);
        }

        Habitat h = _habitats.get(habitatKey);

        /** Remove from the old habitat */
        _habitats.get(a.getCurrentHabitat()).removeAnimal(a);
        a.setHabitat(h.getIdHabitat());

        /** Add to the new habitat */
        _habitats.get(habitatKey).addAnimaltoHabitat(a);

        _modified = true;
    }

    /**
     * Get a tree from the trees map by its id. Used in Flora
     * 
     * @param treeKey
     * @return tree
     */
    public Tree getTree(String treeKey) {
        return _trees.get(treeKey);
    }

    /**
     * Register a new habitat and add it to the habitats map
     * 
     * @param habitatKey
     * @param name
     * @param area
     * @throws DuplicateHabitatException
     */
    public void registerNewHabitat (String habitatKey, String name, int area) throws
    DuplicateHabitatException {
        /** Check if the habitat already exists */
        if (_habitats.containsKey(habitatKey)) {
            throw new DuplicateHabitatException(habitatKey);
        }

        /** Create a new habitat */
        _habitats.put(habitatKey, new Habitat(habitatKey, name, area));

        _modified = true;
    }

    /**
     * Change the area of a habitat
     * 
     * @param habitatKey
     * @param area
     * @throws UnknownHabitatException
     */
    public void changeHabitatArea(String habitatKey, int area)
    throws UnknownHabitatException {
        /** Check if the habitat exists */
        if (!_habitats.containsKey(habitatKey)) {
            throw new UnknownHabitatException(habitatKey);
        }

        /** Change the area of the habitat */
        _habitats.get(habitatKey).setArea(area);
        _modified = true;
    }

    /**
     * Change the influence of a habitat on a species based on the value
     * of the parameter habitatInfluence
     * 
     * @param habitatKey
     * @param speciesKey
     * @param habitatInfluence (POS, NEG, NEU)
     */
    public void changeHabitatInfluence(String habitatKey, String speciesKey, String habitatInfluence)
    throws UnknownHabitatException, UnknownSpeciesException {
        /** Check if the habitat exists */
        if (!_habitats.containsKey(habitatKey)) {
            throw new UnknownHabitatException(habitatKey);
        }

        /** Check if the species exists */
        if (!_species.containsKey(speciesKey)) {
            throw new UnknownSpeciesException(speciesKey);
        }
        
        /** Change the influence of the habitat on the species */
        _habitats.get(habitatKey).setSpeciesInfluence(_species.get(speciesKey).getIdSpecie(), habitatInfluence);

        _modified = true;
    }

    /**
     * Get all trees from an habitat in a collection and send them to
     * App for printing
     * 
     * @param habitatKey
     * @return a collection of all trees in a habitat
     */
    public Collection<Tree> allTrees(String habitatKey) throws UnknownHabitatException {
        if (!_habitats.containsKey(habitatKey)) {
            throw new UnknownHabitatException(habitatKey);
        }

        /** Get all trees from the habitat */
        return _habitats.get(habitatKey).getTrees();
    }

    /**
     * Get all habitats followed by their trees and send them to App
     * for printing
     * 
     * @return all habitats and trees
     */
    public Collection<Habitat> habitatAndTrees() {
        return Collections.unmodifiableCollection(_habitats.values());
    }

    /**
     * Plant a tree in a habitat and add a tree to a flora to keep
     * track of the season it was planted
     * 
     * @param habitatKey
     * @param treeKey
     * @param treeName
     * @param treeAge
     * @param treeDifficulty
     * @param treeType
     * @throws DuplicateTreeException
     * @throws UnknownHabitatException
     * @return the tree planted
     */
    public Tree plantTreeHabitat (String habitatKey, String treeKey, String treeName, int treeAge,
    int treeDifficulty, String treeType) throws DuplicateTreeException, UnknownHabitatException {
        Habitat habitat = _habitats.get(habitatKey);
        
        if (!_habitats.containsKey(habitatKey)) {
            throw new UnknownHabitatException(habitatKey);
        }

        /** Check if the tree already exists in the habitat */
        for (Habitat h : _habitats.values()) {
            if (h.treeExists(treeKey)) {
                throw new DuplicateTreeException(treeKey);
            }
        }

        if (treeType.equals("CADUCA")) {
            Tree t = new DeciduousTree(treeKey, treeName, treeAge, treeDifficulty);
            habitat.addTree(t);
            _trees.put(treeKey, t);

            /** Set the biocycle of the tree to Caduca */
            t.setBioCycle(_season.getCurrentSeason().getBioCycleCaduca());
        } else if (treeType.equals("PERENE")) {
            Tree t = new EvergreenTree(treeKey, treeName, treeAge, treeDifficulty);
            habitat.addTree(t);
            _trees.put(treeKey, t);

            /** Set the biocycle of the tree to Perene */
            t.setBioCycle(_season.getCurrentSeason().getBioCyclePerene());
        }

        /** Update the flora with a tree so it can keep track of the season */
        _season.putFloraSeason(treeKey);
        _modified = true;
        return _trees.get(treeKey);
    }

    /**
     * Plant a tree in the hotel and add it to the trees map
     * 
     * @param treeKey
     * @param treeName
     * @param treeAge
     * @param treeDifficulty
     * @param treeType
     * @throws DuplicateTreeException
     */
    public void plantTree(String treeKey, String treeName, int treeAge, int treeDifficulty, String treeType) 
    throws DuplicateTreeException {
        /** Check if the tree already exists */
        if (_trees.containsKey(treeKey)) {
            throw new DuplicateTreeException(treeKey);
        }

        /** Same logic as plantTreeHabit() */
        if (treeType.equals("CADUCA")) {
            Tree t = new DeciduousTree(treeKey, treeName, treeAge, treeDifficulty);
            _trees.put(treeKey, t);
            t.setBioCycle(_season.getCurrentSeason().getBioCycleCaduca());
        } else if (treeType.equals("PERENE")) {
            Tree t = new EvergreenTree(treeKey, treeName, treeAge, treeDifficulty);
            _trees.put(treeKey, t);
            t.setBioCycle(_season.getCurrentSeason().getBioCyclePerene());
        }

        _season.putFloraSeason(treeKey);
        _modified = true;
    }   

    /**
     * Deal with importFile's trees when imported with an habitat.
     * Adds which trees are in which habitat
     * 
     * @param habitatKey
     * @param trees
     * @throws DuplicateTreeException
     * @throws UnknownHabitatException
     * @throws UnknownTreeException
     */
    public void dealTrees(String habitatKey, String trees) throws UnknownHabitatException,
    DuplicateTreeException, UnknownTreeException {
        /** Split the trees string into a list */
        String[] ts = trees.split(",");
        for (String tree : ts) {
            if (!_trees.containsKey(tree)) {
                throw new UnknownTreeException(tree);
            }

            /** Get the tree */
            Tree t = _trees.get(tree);

            /** Call plantTreeHabitat() to add the tree to the habitat */
            plantTreeHabitat(_habitats.get(habitatKey).getIdHabitat(), t.getIdTree(), t.getName(),
            t.getTreeAge(), t.getCleaningDifficulty(), t instanceof DeciduousTree ? "CADUCA" : "PERENE");  
        }
    }

    /**
     * Get all animals in a habitat and send them to App for printing
     * 
     * @param habitatKey
     * @return all animals in a habitat
     */
    public Collection<Animal> animalsInHabitat(String habitatKey)
    throws UnknownHabitatException {
        if (!_habitats.containsKey(habitatKey)) {
            throw new UnknownHabitatException(habitatKey);
        }
        
        /** Get all animals in the habitat */
        return _habitats.get(habitatKey).getAnimals();
    }

    /**
     * Check if employee is a veterinarian. Important to help with App
     * verifications.
     * 
     * @param employeeKey
     * @throws UnknownVeterinarianException
     * @return true if employee is a veterinarian
     */
    public void isVet(String employeeKey) throws UnknownVeterinarianException {
        /** Check if the employee is an instace of Veterinarian */
        if (!(_employees.get(employeeKey) instanceof Veterinarian)) {
            throw new UnknownVeterinarianException(employeeKey);
        }
    }

    /**
     * Check if the employees id exists in the employees map
     * 
     * @param employeeKey
     * @throws UnknownEmployeeException
     * @return true if employee is a keeper
     */
    public void isEmployee(String employeeKey) throws UnknownEmployeeException {
        /** Check if the employee exists */
        if (_employees.get(employeeKey) == null) {
            throw new UnknownEmployeeException(employeeKey);
        }
    }

    /**
     * Register a new employee and add it to the employees map
     * 
     * @param employeeKey
     * @param nameEmployee
     * @param employeeType
     * @throws DuplicateEmployeeException
     */
    public void registerNewEmployee(String employeeKey, String nameEmployee, 
    String employeeType) throws DuplicateEmployeeException {
        /** Check if the employee already exists */
        if (_employees.containsKey(employeeKey)) {
            throw new DuplicateEmployeeException(employeeKey);
        }

        /** Check if the employee is a keeper or a veterinarian */
        if (employeeType.equals("TRT")) {
            _employees.put(employeeKey, new Keeper(employeeKey, nameEmployee));
        } else if (employeeType.equals("VET")) {
            _employees.put(employeeKey, new Veterinarian(employeeKey, nameEmployee));
        }

        _modified = true;
    }

    /**
     * Add a responsibility to a veterinarian. Note that the specie must exist
     * 
     * @param employeeKey
     * @param specieKey
     * @throws ResponsibilityException
     */
    public void addResponsibilityVeterinarian(String employeeKey, String specieKey)
    throws ResponsibilityException {
        Veterinarian vet = (Veterinarian) _employees.get(employeeKey);

        /** Check if the specie exists */
        if (!_species.containsKey(specieKey)) {
            throw new ResponsibilityException(employeeKey, specieKey);
        }

        /** Add the specie to the veterinarian */
        vet.addSpecie(_species.get(specieKey));
        _modified = true;
    }

    /** 
     * Add a responsibility to a keeper. Note that the habitat must exist
     * 
     * @param idEmployee
     * @param idHabitat
     * @throws ResponsibilityException
     */
    public void addResponsibilityKeeper(String idEmployee, String idHabitat) 
    throws ResponsibilityException, UnknownEmployeeException {
        Keeper k = (Keeper) _employees.get(idEmployee);
        if (k == null) {
            throw new UnknownEmployeeException(idEmployee);
        }

        /** Check if the habitat exists */
        if (!_habitats.containsKey(idHabitat)) {
            throw new ResponsibilityException(idEmployee, idHabitat);
        }

        /** Add the habitat to the keeper */
        k.addHabitat(_habitats.get(idHabitat));
        _modified = true;
    }

    /**
     * Deal with importFile's responsibilities when imported with an employee.
     * Adds which responsibilities are in which employee
     * 
     * @param employeeKey
     * @param responsibilities
     * @param isVet
     * @throws ResponsibilityException
     * @throws UnknownEmployeeException
     */
    public void dealResponsabilities(String employeeKey, String responsibilities, boolean isVet) 
    throws ResponsibilityException, UnknownEmployeeException {
        String[] rs = responsibilities.split(",");

        /** Loop through all responsibilities and add them to the employee */
        for (String responsibility : rs) {
            if (isVet)
                addResponsibilityVeterinarian(employeeKey, responsibility);
            else
                addResponsibilityKeeper(employeeKey, responsibility);
        }
    }

    /**
     * Remove a responsibility from a keeper.
     * 
     * @param employeeKey
     * @param habitatKey
     * @throws ResponsibilityException
     */
    public void removeResponsibilityKeeper(String employeeKey, String habitatKey)
    throws ResponsibilityException{

        Keeper k = (Keeper) _employees.get(employeeKey);

        /** Check if the habitat exists and if the keeper has the habitat */
        if (!_habitats.containsKey(habitatKey) || !k.hasHabitat(habitatKey)) {
            throw new ResponsibilityException(employeeKey, habitatKey);
        }

        /** Remove the habitat from the keeper */
        k.removeHabitat(_habitats.get(habitatKey));
        _modified = true;
    }

    /**
     * Remove a responsibility from a veterinarian.
     * 
     * @param employeeKey
     * @param specieKey
     * @throws ResponsibilityException
     */
    public void removeResponsibilityVeterinarian(String employeeKey, String specieKey) 
    throws ResponsibilityException{
        Veterinarian vet = (Veterinarian) _employees.get(employeeKey);

        /** Check if the specie exists and if the veterinarian has the specie */
        if (!_species.containsKey(specieKey) || !vet.hasPermission(specieKey)) {
            throw new ResponsibilityException(employeeKey, specieKey);
        }

        vet.removeSpecie(_species.get(specieKey));
        _modified = true;
    }

    /**
     * get all employees
     * 
     * @return all employees
     */
    public Collection<Employee> employees() {
        return Collections.unmodifiableCollection(_employees.values());
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
        /** Check if the vaccine already exists */
        if (getVaccine(idVaccine) != null) {
            throw new DuplicateVaccineException(idVaccine);
        }

        /** Remove spaces from speciesIdentifiers */
        speciesIdentifiers = speciesIdentifiers.replaceAll("\\s+", "");

        /** Work with given species */
        String[] species = speciesIdentifiers.split(",");
        TreeMap<String, Specie> approvedSpecies = new TreeMap<String, Specie>(String.CASE_INSENSITIVE_ORDER);

        if (speciesIdentifiers.length() > 0) {
            for (String specie : species) {
                if (!_species.containsKey(specie)) {
                    throw new UnknownSpeciesException(specie);
                }

                approvedSpecies.put(_species.get(specie).getIdSpecie(), _species.get(specie));
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
     * check if animal should be vaccinated
     * 
     * @param vaccineKey
     * @param veterinarianKey
     * @param animalKey
     * @return true if animal should be vaccinated
     * @throws UnknownVeterinarianException
     * @throws VeterinarianAuthorizedException
     */
    public void shouldBeVaccinated(String vaccineKey, String veterinarianKey, String animalKey)
    throws UnknownVeterinarianException, VeterinarianAuthorizedException, UnknownAnimalException {
        // Verificar se o employee e veterinario
        if (!(_employees.get(veterinarianKey) instanceof Veterinarian)) {
            throw new UnknownVeterinarianException(veterinarianKey);
        }

        if (!_animals.containsKey(animalKey)) {
            throw new UnknownAnimalException(animalKey);
        }
        
        // Verificar se o veterinario pode dar a vacina
        Veterinarian veterinarian = (Veterinarian) _employees.get(veterinarianKey);
        String specieId = _animals.get(animalKey).getIdSpecie();
        if (!veterinarian.hasPermission(specieId)) {
            throw new VeterinarianAuthorizedException(specieId, veterinarianKey);
        }
    }

    /**
     * vaccinate an animal
     * 
     * @param vaccineKey
     * @param veterinarianKey
     * @param animalKey
     */
    public void vaccinateAnimal(String vaccineKey, String veterinarianKey, String animalKey) 
    throws DamagedVaccinationException, UnknownVaccineException {
        Animal a = _animals.get(animalKey);
        String application = "REGISTO-VACINA|" + vaccineKey + "|" + veterinarianKey + "|" + a.getIdSpecie();

        // Adicionar ao registo do hotel
        _vaccinations.add(application);
        if (!vaccineExists(vaccineKey)) {
            throw new UnknownVaccineException(vaccineKey);
        }

        if (!getVaccine(vaccineKey).isApprovedFor(_animals.get(animalKey).getIdSpecie())) {
            _wrongVaccinations.add(application);
        }

        // Adicionar ao registo da vacina, veterinario e animal
        getVaccine(vaccineKey).addApplication(application);
        ((Veterinarian) _employees.get(veterinarianKey)).addHistoric(application);
        a.addVaccination(application);
        _modified = true;

        Vaccine v = getVaccine(vaccineKey);
        String specieId = a.getIdSpecie();

        if(!v.isApprovedFor(specieId)) {

            int dam = damage(vaccineKey, animalKey);

            if (dam == 0) {
                a.setHealthStatus("CONFUSÃO");
            }

            else if (dam <= 4) {
                a.setHealthStatus("ACIDENTE");;
            }

            else {
                a.setHealthStatus("ERRO");
            }

            throw new DamagedVaccinationException(vaccineKey, animalKey);
        }

        else {
            a.setHealthStatus("NORMAL");
        }
        
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

    /**UnknownVeterinarianException
     * get all historic vaccinations of a veterinarian
     * 
     * @param employeeKey
     * @return all historic vaccinations of a veterinarian
     * @throws UnknownVeterinarianException
     */
    public Collection<String> veterinarianHistoric(String employeeKey) 
    throws UnknownVeterinarianException{
        if (!(_employees.get(employeeKey) instanceof Veterinarian)) {
            throw new UnknownVeterinarianException(employeeKey);
        }

        Veterinarian veterinarian = (Veterinarian) _employees.get(employeeKey);
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
        if (!_animals.containsKey(animalKey)) {
            throw new UnknownAnimalException(animalKey);
        }

        return _animals.get(animalKey).getVaccinations();
    }

    
    /**
     * get the effort of a tree
     * @param treeKey
     * @return effort
     */
    public double getTreeEffort(String treeKey) {

        Tree t = _trees.get(treeKey);
        int o;
        
        if (t instanceof DeciduousTree) {
            o = 0;
        }
        else {
            o = 1;
        }

        double effort = t.getCleaningDifficulty() * _season.getSazonalEffort(o) * Math.log(t.getTreeAge() + 1);

        return effort;
    }

    /**
     * Get the satisfaction of an animal
     * 
     * @param idAnimal
     * @return satisfaction
     */
    public double getSatisfactionAnimal(String animalKey) throws UnknownAnimalException, UnknownSpeciesException{
        /** Check if the animal exists */
        if (!_animals.containsKey(animalKey)) {
            throw new UnknownAnimalException(animalKey);
        }

        Habitat h = _habitats.get(_animals.get(animalKey).getCurrentHabitat());

        if (h.getSpeciesInfluence(_animals.get(animalKey).getIdSpecie()) == null) {
            throw new UnknownSpeciesException(_animals.get(animalKey).getIdSpecie());
        }

        CalculateStrategy aniSat = new AnimalSatisfaction(this);
        return aniSat.calculate(animalKey);
    }


    /**
     * get the satisfaction of a veterinarian
     * 
     * @param idVeterinarian
     * @return satisfaction
     */
    public double getVeterinarianSatisfaction(String idVeterinarian) {

        CalculateStrategy vetSat = new VeterinarianSatisfaction(this);
        return vetSat.calculate(idVeterinarian);
    }

    /**
     * get Keeper Satisfaction
     * 
     * @param idKeeper
     * @return satisfaction
     */
    public double getKeeperSatisfaction(String idKeeper) {

        CalculateStrategy keepSat = new KeeperSatisfaction(this);
        return keepSat.calculate(idKeeper);
    }

    /**
     * return the number of common characters between two strings
     * 
     * @param str1
     * @param str2
     * @return commonCount
     */
    public int commonCharacters(String str1, String str2) {

        int commonCount = 0;
        StringBuilder commonChars = new StringBuilder();
        
        str1 = str1.toLowerCase();
        str2 = str2.toLowerCase();
        
        for (int i = 0; i < str1.length(); i++) {
            char c = str1.charAt(i);
            
            if (Character.isLetter(c)) {
                if (str2.indexOf(c) != -1 && commonChars.indexOf(String.valueOf(c)) == -1) {
                    commonChars.append(c);
                    commonCount++;
                }
            }
        }
        
        return commonCount;
    }

    /**
     * calculates the damage of a vaccination
     * 
     * @param idVaccine
     * @param idAnimal
     * @return damage
     */
    public int damage(String idVaccine, String idAnimal) {
        
        Animal a = _animals.get(idAnimal);
        Vaccine v = _vaccines.get(idVaccine);
        String currIdSpecie = a.getIdSpecie();
        int damage = 0;
        int max = 0;
        List<String> acceptedSpecies = v.getAcceptedSpecies();

        for(String idSpecie: acceptedSpecies) {
            String s1 = _species.get(currIdSpecie).getNameSpecie();
            String s2 = _species.get(idSpecie).getNameSpecie();
            max = Math.max(s1.length(),s2.length()) - commonCharacters(s1,s2);

            if (max > damage) {
                damage = max;
            }
        }

        return damage;
    }

    public double globalSatisfaction() throws UnknownSpeciesException, UnknownAnimalException {
        
        double globalSats = 0;

        for (String idAnimal: _animals.keySet()) {
            globalSats += getSatisfactionAnimal(idAnimal);
        }
        
        for (String idEmployee: _employees.keySet()) {
            Employee e = _employees.get(idEmployee);

            if (e instanceof Keeper) {
                globalSats += getKeeperSatisfaction(idEmployee);
            }
            else {
                globalSats += getVeterinarianSatisfaction(idEmployee);
            }
        }
        
        return globalSats;
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
                | UnknownTreeException | DuplicatedSpeciesNameException | IOException e) {
            throw new ImportFileException(filename, e);
        }
    }
}
