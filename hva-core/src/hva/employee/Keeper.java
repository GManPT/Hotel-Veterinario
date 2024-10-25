package hva.employee;

import hva.habitat.Habitat;

import java.util.Map;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;


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
        if (!_habitats.containsKey(h.getIdHabitat()))
            _habitats.put(h.getIdHabitat(), h);
    }

    /**
     * get keeper habitats
     * 
     * @return list of habitats
     */
    public List<String> getHabitatKeeper() {

        List<String> list = new ArrayList<String>();

        for (String idHabitat : _habitats.keySet()) {
            list.add(idHabitat); 
        }
        
        return list;
    }

    /**
     * Remove a habitat from the keeper's list of habitats
     * 
     * @param h 
     */
    public void removeHabitat(Habitat h) {
        _habitats.remove(h.getIdHabitat());
    }

    /**
     * Check if the Keeper has a certain habitat as resposibility
     * 
     * @param idHabitat
     * @return true if the keeper has a certain habitat as resposibility
     */
    public boolean hasHabitat(String idHabitat) {
        return _habitats.containsKey(idHabitat);
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