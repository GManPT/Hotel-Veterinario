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
        _species = new TreeMap<String, Specie>(String.CASE_INSENSITIVE_ORDER);
        _animals = new TreeMap<String, Animal>(String.CASE_INSENSITIVE_ORDER);
        _habitats = new TreeMap<String, Habitat>(String.CASE_INSENSITIVE_ORDER);
        _employees = new TreeMap<String, Employee>(String.CASE_INSENSITIVE_ORDER);
        _vaccines = new TreeMap<String, Vaccine>(String.CASE_INSENSITIVE_ORDER);
        _trees = new TreeMap<String, Tree>(String.CASE_INSENSITIVE_ORDER);
        _vaccinations = new ArrayList<String>();
        _wrongVaccinations = new ArrayList<String>();

        // Flora corresponding to this hotel
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

    public Flora getFlora() {
        return _season;
    }

    public int changeSeason() {
        _season.getCurrentSeason().advanceSeason();
        return _season.getCurrentSeason().getNumSeason();
    }

    /** 
     * Check if species exist
     * 
     * @param idSpecie
     * @return true if specie exists
     */
    public void speciesExists(String idSpecie) throws UnknownSpeciesException {
        if (!_species.containsKey(idSpecie)) {
            throw new UnknownSpeciesException(idSpecie);
        }
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

        if (!_species.containsKey(speciesKey)) {
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
        if (!habitatExists(idHabitat)) {
            throw new UnknownHabitatException(idHabitat);
        }

        return _habitats.get(idHabitat).getTrees();
    }

    /**
     * return all habitats and trees
     * 
     * @return all habitats and trees
     */
    public Collection<Habitat> habitatAndTrees() {
        return Collections.unmodifiableCollection(_habitats.values());
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
    public Tree plantTreeHabitat (String idHabitat, String idTree, String treeName, int treeAge, int treeDifficulty, String treeType)
    throws DuplicateTreeException, UnknownHabitatException {
        Habitat habitat = getHabitat(idHabitat);
        
        if (!habitatExists(idHabitat)) {
            throw new UnknownHabitatException(idHabitat);
        }

        for (Habitat h : _habitats.values()) {
            if (h.treeExists(idTree)) {
                throw new DuplicateTreeException(idTree);
            }
        }

        if (treeType.equals("CADUCA")) {
            Tree t = new DeciduousTree(idTree, treeName, treeAge, treeDifficulty);
            habitat.addTree(t);
            _trees.put(idTree, t);
            t.setBioCycle(_season.getCurrentSeason().getBioCycleCaduca());
        } else if (treeType.equals("PERENE")) {
            Tree t = new EvergreenTree(idTree, treeName, treeAge, treeDifficulty);
            habitat.addTree(t);
            _trees.put(idTree, t);
            t.setBioCycle(_season.getCurrentSeason().getBioCyclePerene());
        }

        _season.putFloraSeason(idTree);
        _modified = true;
        return _trees.get(idTree);
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
            Tree t = new DeciduousTree(idTree, treeName, treeAge, treeDifficulty);
            _trees.put(idTree, t);
            t.setBioCycle(_season.getCurrentSeason().getBioCycleCaduca());
            _season.putFloraSeason(idTree);
        } else if (treeType.equals("PERENE")) {
            Tree t = new EvergreenTree(idTree, treeName, treeAge, treeDifficulty);
            _trees.put(idTree, t);
            t.setBioCycle(_season.getCurrentSeason().getBioCyclePerene());
            _season.putFloraSeason(idTree);
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
     * @throws UnknownTreeException
     */
    public void dealTrees(String idHabitat, String trees) throws UnknownHabitatException,
    DuplicateTreeException, UnknownTreeException {
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

        return _habitats.get(idHabitat).getAnimals();
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
    public void isVet(String idEmployee) throws UnknownVeterinarianException {
        if (!(_employees.get(idEmployee) instanceof Veterinarian)) {
            throw new UnknownVeterinarianException(idEmployee);
        }
    }

    public void isEmployee(String idEmployee) throws UnknownEmployeeException {
        if (_employees.get(idEmployee) == null) {
            throw new UnknownEmployeeException(idEmployee);
        }
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
     * add responsibility to a veterinarian
     * 
     * @param idEmployee
     * @param idSpecie
     * @throws ResponsibilityException
     */
    public void addResponsibilityVeterinarian(String idEmployee, String idSpecie)
    throws ResponsibilityException {
        Veterinarian vet = (Veterinarian) getEmployee(idEmployee);

        if (!_species.containsKey(idSpecie)) {
            throw new ResponsibilityException(idEmployee, idSpecie);
        }

        vet.addSpecie(getSpecie(idSpecie));
        _modified = true;
    }

    /** 
     * add responsibility to a keeper
     * 
     * @param idEmployee
     * @param idHabitat
     * @throws ResponsibilityException
     */
    public void addResponsibilityKeeper(String idEmployee, String idHabitat) 
    throws ResponsibilityException, UnknownEmployeeException {
        Keeper k = (Keeper) getEmployee(idEmployee);
        if (k == null) {
            throw new UnknownEmployeeException(idEmployee);
        }

        if (!_habitats.containsKey(idHabitat)) {
            throw new ResponsibilityException(idEmployee, idHabitat);
        }

        k.addHabitat(getHabitat(idHabitat));
        _modified = true;
    }

    public void dealResponsabilities(String idEmployee, String responsibilities, boolean isVet) 
    throws ResponsibilityException, UnknownEmployeeException {
        String[] rs = responsibilities.split(",");
        for (String responsibility : rs) {
            if (isVet)
                addResponsibilityVeterinarian(idEmployee, responsibility);
            else
                addResponsibilityKeeper(idEmployee, responsibility);
        }
    }


    public void removeResponsibilityKeeper(String idEmployee, String idHabitat)
    throws ResponsibilityException{

        Keeper k = (Keeper) getEmployee(idEmployee);

        if (!_habitats.containsKey(idHabitat) || !k.hasHabitat(idHabitat)) {
            throw new ResponsibilityException(idEmployee, idHabitat);
        }

        k.removeHabitat(getHabitat(idHabitat));
        _modified = true;
    }


    public void removeResponsibilityVeterinarian(String idEmployee, String idSpecie) 
    throws ResponsibilityException{
        Veterinarian vet = (Veterinarian) getEmployee(idEmployee);

        if (!_species.containsKey(idSpecie) || !vet.hasPermission(idSpecie)) {
            throw new ResponsibilityException(idEmployee, idSpecie);
        }

        vet.removeSpecie(getSpecie(idSpecie));
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
        // Verificar se o id ja existe
        if (getVaccine(idVaccine) != null) {
            throw new DuplicateVaccineException(idVaccine);
        }

        // Verificar se as especies existem
        String[] species = speciesIdentifiers.split(",");
        TreeMap<String, Specie> approvedSpecies = new TreeMap<String, Specie>(String.CASE_INSENSITIVE_ORDER);

        if (speciesIdentifiers.length() > 0) {
            for (String specie : species) {
                if (!_species.containsKey(specie)) {
                    throw new UnknownSpeciesException(specie);
                }

                approvedSpecies.put(specie, _species.get(specie));
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

        if (!animalExists(animalKey)) {
            throw new UnknownAnimalException(animalKey);
        }
        
        // Verificar se o veterinario pode dar a vacina
        Veterinarian veterinarian = (Veterinarian) getEmployee(veterinarianKey);
        String specieId = getAnimal(animalKey).getIdSpecie();
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
        Animal a = getAnimal(animalKey);
        String application = "REGISTO-VACINA|" + vaccineKey + "|" + veterinarianKey + "|" + a.getIdSpecie();

        // Adicionar ao registo do hotel
        _vaccinations.add(application);
        if (!vaccineExists(vaccineKey)) {
            throw new UnknownVaccineException(vaccineKey);
        }

        if (!getVaccine(vaccineKey).isApprovedFor(getAnimal(animalKey).getIdSpecie())) {
            _wrongVaccinations.add(application);
        }

        // Adicionar ao registo da vacina, veterinario e animal
        getVaccine(vaccineKey).addApplication(application);
        ((Veterinarian) getEmployee(veterinarianKey)).addHistoric(application);
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
     * get the effort of a tree
     * @param idTree
     * @return effort
     */
    public double getTreeEffort(String idTree) {

        Tree t = getTree(idTree);
        int o;
        
        if (t instanceof DeciduousTree) {
            o = 0;
        }
        else {
            o = 1;
        }

        double effort = t.getCleaningDifficulty() * getFlora().getSazonalEffort(o) * Math.log(t.getTreeAge() + 1);

        return effort;
    }

    /**
     * get the satisfaction of an animal
     * 
     * @param idAnimal
     * @return satisfaction
     */
    public double getSatisfactionAnimal(String idAnimal) throws UnknownSpeciesException, UnknownAnimalException{
        
        if (_animals.get(idAnimal) == null) {
            throw new UnknownAnimalException(idAnimal);
        }

        String currHabit = _animals.get(idAnimal).getCurrentHabitat();
        Habitat h = _habitats.get(currHabit);

        int equals = h.animalEquals(idAnimal);
        int population = h.getPopulationNumber();
        int diferent = population - (equals + 1);
        int area = h.getArea();
        String influ = h.getSpeciesInfluence(_animals.get(idAnimal).getIdSpecie());
        int adequacy = 0;

        if (influ == null) {
            throw new UnknownSpeciesException(_animals.get(idAnimal).getIdSpecie());
        }
        
        if (influ.equals("POS")) {
            adequacy = 20;
        } 
        else if (influ.equals("NEG")) {
            adequacy = -20;
        }

        double satisfaction = 20 + (3 * equals) - (2 * diferent) + (area / population) + adequacy;
        return satisfaction;
    }


    /**
     * get the satisfaction of a veterinarian
     * 
     * @param idVeterinarian
     * @return satisfaction
     */
    public double getVeterinarianSatisfaction(String idVeterinarian) {

        Veterinarian e = (Veterinarian) _employees.get(idVeterinarian);

        List<String> vetSpecies = e.getVeterinarianSpecies();
        int counter = 0;
        double work = 0;
        int animPop;

        for (String idSpecie: vetSpecies) {
            counter = 0;
            for (String idVet: _employees.keySet()) {
                Employee emp = _employees.get(idVet);
                if (emp instanceof Veterinarian) {
                    Veterinarian v = (Veterinarian) emp;

                    if (v.hasPermission(idSpecie)) {
                        counter += 1;
                    }
                }
            }
            animPop = _species.get(idSpecie).getNumberAnimals();
            work += (animPop / counter);
        }

        return 20 - work;
    }

    /**
     * get Keeper Satisfaction
     * 
     * @param idKeeper
     * @return satisfaction
     */
    
    public double getKeeperSatisfaction(String idKeeper) {

        Keeper k = (Keeper) _employees.get(idKeeper);

        List<String> keepHabitats = k.getHabitatKeeper();
        double work = 0;
        double habitWork;
        int counter = 0;

        for (String idHabitat: keepHabitats) {

            // Calculate Habitat Work
            Habitat h = _habitats.get(idHabitat);
            habitWork = h.getArea() + (3 * h.getPopulationNumber());
            List<String> habitTrees = h.getHabitatTrees();
            for (String idTree: habitTrees) {
                habitWork += getTreeEffort(idTree);
            }

            // Calculate Number of Keepers who can work in this habitat
            counter = 0;
            for (String idEmployee: _employees.keySet()) {
                Employee emp = _employees.get(idEmployee);
                if (emp instanceof Keeper) {
                    Keeper kep  = (Keeper) emp;

                    if (kep.hasHabitat(idHabitat)) {
                        counter += 1;
                    }
                }
            }

            work += (habitWork / counter);
        }

        return 300 - work;
    }

    /**
     * 
     */
    public int commonCharacters(String str1, String str2) {

        int commonCount = 0;
        StringBuilder commonChars = new StringBuilder();
        
        // Iterate through str1 and check if the character exists in str2
        for (int i = 0; i < str1.length(); i++) {
            char c = str1.charAt(i);
            if (str2.indexOf(c) != -1 && commonChars.indexOf(String.valueOf(c)) == -1) {
                commonChars.append(c);
                commonCount++; // Increment the count when a new common character is found
            }
        }
        
        return commonCount;
    }

    /**
     * 
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
                | UnknownTreeException | IOException e) {
            throw new ImportFileException(filename, e);
        }
    }
}