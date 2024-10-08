package hva;

import java.io.Serial;
import java.io.Serializable;
import java.util.Map;
import java.util.TreeMap;
import java.util.Collection;
import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.io.BufferedReader;
import java.io.FileReader;

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
import hva.exceptions.ImportFileException;
import hva.exceptions.UnrecognizedEntryException;
import java.io.IOException;


public class Hotel implements Serializable {
    @Serial
    private static final long serialVersionUID = 202407081733L;

    // Definir atributos
    public Map<String, Specie> _species;
    public Map<String, Habitat> _habitats;
    public Map<String, Employee> _employees;
    public Map<String, Vaccine> _vaccines;
    public Map<String, Tree> _trees;
    public List<String> _vaccinations;
    public List<String> _wrongVaccinations;


    // Atributo para verificar se houve alteracoes

    private boolean _modified = false;

    public void setModified(boolean mod) {
        _modified = mod;
    }

    public boolean isModified() {
        return _modified;
    }


    public Hotel() {
        _species = new TreeMap<String, Specie>();
        _habitats = new TreeMap<String, Habitat>();
        _employees = new TreeMap<String, Employee>();
        _vaccines = new TreeMap<String, Vaccine>();
        _trees = new TreeMap<String, Tree>();
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

    public void registerNewSpecie(String idSpecie, String nameSpecie) {
        if (nameSpecie != null) {
            _species.put(idSpecie, new Specie(idSpecie, nameSpecie));
            _modified = true;
        }
    }

    public void registerNewAnimal(String idAnimal, String nameAnimal, String idSpecie, String nameSpecie, String idHabitat) 
    throws DuplicateAnimalException {
        // Verificar se o id do animal ja existe
        if (animalExists(idAnimal)) {
            throw new DuplicateAnimalException(idAnimal);
        }

        registerNewSpecie(idSpecie, nameSpecie);
        Animal a = new Animal(idAnimal, nameAnimal, idSpecie, idHabitat);

        // Adicionar o animal ao seu habitat e especie
        _species.get(idSpecie).addAnimaltoSpecie(a);
        _habitats.get(idHabitat).addAnimaltoHabitat(a);
        _modified = true;
    }

    public Collection<Animal> speciesAnimals() {
        Collection<Animal> allAnimals = new ArrayList<>();

        for (Specie specie : _species.values()) {
            allAnimals.addAll(specie.animals());
        }
    
        return Collections.unmodifiableCollection(allAnimals);
    }

    public void changeAnimalHabitat(String idAnimal, String habitatKey) {
        Animal a = getAnimal(idAnimal);

        // Remover o habitat anterior
        getHabitat(a.getCurrentHabitat()).removeAnimal(a);
        a.setHabitat(habitatKey);

        // Adicionar ao novo habitat
        getHabitat(habitatKey).addAnimaltoHabitat(a);

        _modified = true;
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
        _modified = true;
    }

    public void changeHabitatArea(String habitatKey, int area) {
        getHabitat(habitatKey).setArea(area);
        _modified = true;
    }

    public void changeHabitatInfluence(String habitatKey, String speciesKey, String habitatInfluence) {
        getHabitat(habitatKey).setSpeciesInfluence(speciesKey, habitatInfluence);
        _modified = true;
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
        _modified = true;
    }

    public void plantTree(String idTree, String treeName, int treeAge, int treeDifficulty, String treeType) 
    throws DuplicateTreeException {
        if (_trees.containsKey(idTree)) {
            throw new DuplicateTreeException(idTree);
        }

        if (treeType.equals("CADUCA")) {
            _trees.put(idTree, new DeciduousTree(idTree, treeName, treeAge, treeDifficulty));
        } else if (treeType.equals("PERENE")) {
            _trees.put(idTree, new EvergreenTree(idTree, treeName, treeAge, treeDifficulty));
        }
        _modified = true;
    }

    public Collection<Animal> animalsInHabitat(String idHabitat) {
        return getHabitat(idHabitat).getAnimals();
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
        _modified = true;
    }

    public void addResponsability(String idEmployee, String id, boolean isVet)
    throws ResponsibilityException {
        Employee employee = getEmployee(idEmployee);

        // Caso nao exista a especie ou habitat
        if (!(speciesExists(id) || habitatExists(id))) {
            throw new ResponsibilityException(idEmployee, id);
        }

        if (isVet) {
            ((Veterinarian) employee).addSpecie(getSpecie(id));
        } else {
            ((Keeper) employee).addHabitat(getHabitat(id));
        }
        _modified = true;
    }

    public void removeResponsibility(String idEmployee, String id) 
    throws ResponsibilityException {
        Employee employee = getEmployee(idEmployee);

        // Verificar se o employee existe
        if (!employeeExists(idEmployee)) {
            throw new ResponsibilityException(idEmployee, id);
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
        _modified = true;
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
    throws UnknownVeterinarianException, VeterinarianAuthorizedException {
        // Verificar se o employee é veterinario
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

    public List<String> getAllVaccinations() {
        return _vaccinations;
    }

    public List<String> getAllWrongVaccinations() {
        return _wrongVaccinations;
    }

    public Collection<String> vaccinations() {
        return Collections.unmodifiableCollection(getAllVaccinations());
    }

    public Collection<String> wrongVaccinations() {
        return Collections.unmodifiableCollection(getAllWrongVaccinations());
    }

    public Collection<String> veterinarianHistoric(String employeeKey) 
    throws UnknownVeterinarianException{
        if (!isVet(employeeKey)) {
            throw new UnknownVeterinarianException(employeeKey);
        }

        Veterinarian veterinarian = (Veterinarian) getEmployee(employeeKey);
        return veterinarian.HistoricVaccinations();
    }

    public Collection<String> medicalActsOnAnimal(String animalKey) {
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
                    case "ESPECIE" -> {
                        registerNewSpecie(parse[1], parse[2]);
                    }
                    case "ANIMAL" -> {
                        registerNewAnimal(parse[1], parse[2], parse[3], parse[4], parse[5]);
                    }
                    case "HABITAT" -> {
                        registerNewHabitat(parse[1], parse[2], Integer.parseInt(parse[3]));
                    }
                    case "TRATADOR" -> {
                        registerNewEmployee(parse[1], parse[2], "TRT");
                    }
                    case "VETERINARIO" -> {
                        registerNewEmployee(parse[1], parse[2], "VET");
                    }
                    case "VACINA" -> {
                        registerVaccine(parse[1], parse[2], parse[3]);
                    }
                    case "ARVORE" -> {
                        plantTree(parse[1], parse[2], Integer.parseInt(parse[3]), Integer.parseInt(parse[4]), parse[5]);
                    }
                    default -> {
                        throw new UnrecognizedEntryException(filename);
                    }
                }
            }
		} catch (DuplicateAnimalException | DuplicateHabitatException | DuplicateEmployeeException
                | DuplicateVaccineException | UnknownSpeciesException | DuplicateTreeException
                | IOException e) {
            throw new ImportFileException(filename, e);
        }
    }
}