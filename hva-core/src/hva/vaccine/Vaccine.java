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

    public Vaccine(String id, String name, ArrayList<Specie> approvedSpecies) {
        _idVaccine = id;
        _nameVaccine = name;
        _approvedSpecies = approvedSpecies;
        _applications = new ArrayList<String>();
    }

    public String getIdVaccine() {
        return _idVaccine;
    }

    public String getNameVaccine() {
        return _nameVaccine;
    }
    
    public int getNumberOfApplications() {
        return _applications.size();
    }

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

    public boolean isApprovedFor(Specie s) {
        return _approvedSpecies.contains(s);
    }

    public void addApplication(String vaccination) {
        _applications.add(vaccination);
    }

    @Override
    public String toString() {
        return "VACINA|" + getIdVaccine() + "|" + getNameVaccine() + "|" + getNumberOfApplications() + speciesList();
    }
}