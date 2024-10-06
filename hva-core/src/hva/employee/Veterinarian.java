package hva.employee;

import hva.animal.Specie;

import java.util.List;
import java.util.ArrayList;

public class Veterinarian extends Employee {
    private List<Specie> _species;
    private List<String> _historic;

    public Veterinarian(String idEmployee, String nameEmployee) {
        super(idEmployee, nameEmployee);
        _species = new ArrayList<Specie>();
        _historic = new ArrayList<String>();
    }

    public void addSpecie(Specie s) {
        if (!_species.contains(s)) {
            _species.add(s);
        }
    }

    public String getSpecialty() {
        StringBuilder list = new StringBuilder();

        for (Specie s : _species) {
            list.append(s.getIdSpecie()).append(",");
        }

        if (list.length() > 0) {
            list.setLength(list.length() - 1);
        }

        return list.toString();
    }
    

    @Override
    public String toString() {
        String species = getSpecialty();

        if (species.equals("")) {
            return "VETERINÁRIO" + super.toString();
        } else {
            return "VETERINÁRIO" + super.toString() + "|" + species;
        }
    }
}