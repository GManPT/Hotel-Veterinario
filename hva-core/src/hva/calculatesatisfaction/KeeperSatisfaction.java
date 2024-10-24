package hva.calculatesatisfaction;

import java.util.List;
import java.io.Serializable;
import hva.Hotel;
import hva.employee.Employee;
import hva.employee.Keeper;
import hva.habitat.Habitat;

public class KeeperSatisfaction implements CalculateStrategy, Serializable {
    private static final long serialVersionUID = 202407081733L;
    private Hotel _hotel;

    public KeeperSatisfaction(Hotel hotel) {
        _hotel = hotel;
    }

    @Override
    public double calculate(String idKeeper) {

        Keeper k = (Keeper) _hotel.getEmployee(idKeeper);

        List<String> keepHabitats = k.getHabitatKeeper();
        double work = 0;
        double habitWork;
        int counter = 0;

        for (String idHabitat: keepHabitats) {

            // Calculate Habitat Work
            Habitat h = _hotel.getHabitat(idHabitat);
            habitWork = h.getArea() + (3 * h.getPopulationNumber());
            List<String> habitTrees = h.getHabitatTrees();

            CalculateStrategy treeEff = new TreeEffort(_hotel);

            for (String idTree: habitTrees) {
                habitWork += treeEff.calculate(idTree);
            }

            // Calculate Number of Keepers who can work in this habitat
            counter = 0;
            for (String idEmployee: _hotel.getEmployeesKeySet()) {
                Employee emp = _hotel.getEmployee(idEmployee);
                if (emp instanceof Keeper) {
                    Keeper kep  = (Keeper) emp;

                    if (kep.hasHabitat(idHabitat)) {
                        counter += 1;
                    }
                }
            }

            work += (habitWork / counter);
        }

        return 300 - work;
    }

}