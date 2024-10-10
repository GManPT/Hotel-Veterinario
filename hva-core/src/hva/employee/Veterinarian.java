package hva.employee;

import hva.animal.Specie;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Veterinarian extends Employee {

    /** species of the veterinarian */
    private Map<String, Specie> _species;

    /** historic of vaccinations of the veterinarian */
    private List<String> _historic;

    /**
     * Constructor for Veterinarian
     */
    public Veterinarian(String idEmployee, String nameEmployee) {
        super(idEmployee, nameEmployee);
        _species = new HashMap<String, Specie>();
        _historic = new ArrayList<String>();
    }

    /**
     * Add a specie to the Veterinarian
     * 
     * @param s Specie to add
     */
    public void addSpecie(Specie s) {
        if (!_species.containsKey(s.getIdSpecie())) {
            _species.put(s.getIdSpecie(), s);
        }
    }

    /**
     * Remove a specie from the Veterinarian
     * 
     * @param s Specie to remove
     */
    public void removeSpecie(Specie s) {
        _species.remove(s.getIdSpecie());
    }

    /**
     * Get the species of the Veterinarian
     * 
     * @return Map with the species
     */
    public void addHistoric(String idAnimal) {
        _historic.add(idAnimal);
    }

    /**
     * Get the historic of vaccinations of the Veterinarian
     * 
     * @return list with the historic of vaccinations
     */
    public List<String> getHistoric() {
        return _historic;
    }

    /**
     * Get all vaccinations of the Veterinarian
     * 
     * @return all vaccinations
     */
    public Collection<String> HistoricVaccinations() {
        return Collections.unmodifiableCollection(getHistoric());
    }

    /**
     * get specialty of the Veterinarian
     * 
     * @return specialty
     */
    public String getSpecialty() {
        StringBuilder list = new StringBuilder();

        for (Specie s : _species.values()) {
            list.append(s.getIdSpecie()).append(",");
        }

        if (list.length() > 0) {
            list.setLength(list.length() - 1);
        }

        return list.toString();
    }

    /**
     * Check if the Veterinarian has permission to a specie
     * 
     * @param idSpecie id of the specie
     * @return true if the Veterinarian has permission, false otherwise
     */
    public boolean hasPermission(String idSpecie) {
        return _species.containsKey(idSpecie);
    }

    /**
     * veternarian to string
     * 
     * @return veterinarian to string
     */
    @Override
    public String toString() {
        String species = getSpecialty();
        return species.equals("") ? "VET" + super.toString() :
                                    "VET" + super.toString() + "|" + species;
    }
}