package hva.vaccine;

import java.util.List;
import java.util.ArrayList;
import java.io.Serializable;
import hva.animal.Specie;

public class Vaccine implements Serializable{
    private String _idVaccine;
    private String _nameVaccine;
    private List<Specie> _approvedSpecies;
    private List<String> _applications;

    /**
     * Constructor for the Vaccine class
     */
    public Vaccine(String id, String name, ArrayList<Specie> approvedSpecies) {
        _idVaccine = id;
        _nameVaccine = name;
        _approvedSpecies = approvedSpecies;
        _applications = new ArrayList<String>();
    }

    /**
     * get the id of the vaccine
     * 
     * @return the idVaccine
     */
    public String getIdVaccine() {
        return _idVaccine;
    }

    /**
     * get the name of the vaccine
     * 
     * @return the nameVaccine
     */
    public String getNameVaccine() {
        return _nameVaccine;
    }
    
    /**
     * get the number of applications of the vaccine
     * 
     * @return the number of applications
     */
    public int getNumberOfApplications() {
        return _applications.size();
    }

    /**
     * get the list of approved species for the vaccine
     * 
     * @return the list of approved species
     */
    public String speciesList() {
        String species = "";

        if (!_approvedSpecies.isEmpty()) {
            species = "|";

            for (Specie s : _approvedSpecies) {
                species += s.getIdSpecie() + ",";
            }

            species = species.substring(0, species.length() - 1);
        }

        return species;
    }

    /**
     * check if the vaccine is approved for a specific specie
     * 
     * @param s the specie to check
     * @return true if the vaccine is approved for the specie, false otherwise
     */
    public boolean isApprovedFor(Specie s) {
        return _approvedSpecies.contains(s);
    }

    public void addApplication(String vaccination) {
        _applications.add(vaccination);
    }

    /**
     * vaccine to String
     * 
     * @return a vaccine in string format
     */
    @Override
    public String toString() {
        return "VACINA|" + _idVaccine + "|" + _nameVaccine + "|" + getNumberOfApplications() + speciesList();
    }
}