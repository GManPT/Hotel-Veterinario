package hva.employee;

import hva.animal.Specie;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

public class Veterinarian extends Employee {
    private Map<String, Specie> _species;
    private List<String> _historic;

    public Veterinarian(String idEmployee, String nameEmployee) {
        super(idEmployee, nameEmployee);
        _species = new HashMap<String, Specie>();
        _historic = new ArrayList<String>();
    }

    public void addSpecie(Specie s) {
        if (!_species.containsKey(s.getIdSpecie())) {
            _species.put(s.getIdSpecie(), s);
        }
    }

    public void removeSpecie(Specie s) {
        _species.remove(s.getIdSpecie());
    }

    public void addHistoric(String idAnimal) {
        _historic.add(idAnimal);
    }

    public List<String> getHistoric() {
        return _historic;
    }

    public Collection<String> HistoricVaccinations() {
        return Collections.unmodifiableCollection(getHistoric());
    }

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

    public boolean hasPermission(String idSpecie) {
        return _species.containsKey(idSpecie);
    }

    @Override
    public String toString() {
        String species = getSpecialty();

        if (species.equals("")) {
            return "VET"+ "|" + super.toString();
        } else {
            return "VET" + "|" + super.toString() + "|" + species;
        }
    }
}