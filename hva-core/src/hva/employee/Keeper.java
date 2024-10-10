package hva.employee;

import hva.habitat.Habitat;

import java.util.Map;
import java.util.HashMap;


public class Keeper extends Employee {

    /** habitats of the keeper */
    private Map<String, Habitat> _habitats;

    /**
     * Constructor for the Keeper class
     */
    public Keeper(String idEmployee, String nameEmployee) {
        super(idEmployee, nameEmployee);
        _habitats = new HashMap<String, Habitat>();
    }

    /**
     * Add a habitat to the keeper's list of habitats
     * 
     * @param h 
     */
    public void addHabitat(Habitat h) {
        if (_habitats.get(h) == null)
            _habitats.put(h.getIdHabitat(), h);
    }

    /**
     * Remove a habitat from the keeper's list of habitats
     * 
     * @param h 
     */
    public void removeHabitat(Habitat h) {
        _habitats.remove(h);
    }

    /**
     * Get the list of habitats of a keeper
     * 
     * @return List<Habitat> 
     */
    public String habitatList() {
        StringBuilder list = new StringBuilder();

        for (Habitat h : _habitats.values()) {
            list.append(h.getIdHabitat()).append(",");
        }

        if (list.length() > 0) {
            list.setLength(list.length() - 1);
        }

        return list.toString();
    }

    /**
     * keeper to string
     * 
     * @return keeper to string
     */
    @Override
    public String toString() {
        String habitats = habitatList();
        return habitats.equals("") ? "TRT" + super.toString() :
                                    "TRT" + super.toString() + "|" + habitats;
    }
}