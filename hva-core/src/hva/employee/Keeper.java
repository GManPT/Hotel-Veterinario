package hva.employee;

import hva.habitat.Habitat;

import java.util.List;
import java.util.ArrayList;


public class Keeper extends Employee {
    private List<Habitat> _habitats;

    public Keeper(String idEmployee, String nameEmployee) {
        super(idEmployee, nameEmployee);
        _habitats = new ArrayList<Habitat>();
    }

    public void addHabitat(Habitat h) {
        if (!_habitats.contains(h))
            _habitats.add(h);
    }

    public void removeHabitat(Habitat h) {
        _habitats.remove(h);
    }

    public String habitatList() {
        StringBuilder list = new StringBuilder();

        for (Habitat h : _habitats) {
            list.append(h.getIdHabitat()).append(",");
        }

        if (list.length() > 0) {
            list.setLength(list.length() - 1);
        }

        return list.toString();
    }

    @Override
    public String toString() {
        String habitats = habitatList();

        if (habitats.equals("")) {
            return "TRATADOR" + super.toString();
        } else {
            return "TRATADOR" + super.toString() + "|" + habitats;
        }
    }
}