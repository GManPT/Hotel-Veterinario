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
        StringBuilder list = new StringBuilder();

        for (Specie s : _approvedSpecies) {
            list.append(s.getIdSpecie()).append(",");
        }

        if (list.length() > 0) {
            list.setLength(list.length() - 1);
        }

        return list.toString();
    }

    public boolean isApprovedFor(Specie s) {
        return _approvedSpecies.contains(s);
    }

    public void addApplication(String vaccination) {
        _applications.add(vaccination);
    }

    @Override
    public String toString() {
        return "VACINA|" + getIdVaccine() + "|" + getNameVaccine() + "|" + getNumberOfApplications() + "|" + speciesList();
    }
}