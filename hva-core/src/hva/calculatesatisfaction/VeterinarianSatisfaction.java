package hva.calculatesatisfaction;

import java.util.List;
import java.io.Serializable;
import hva.Hotel;
import hva.employee.Employee;
import hva.employee.Veterinarian;

public class VeterinarianSatisfaction implements CalculateStrategy, Serializable {
    private static final long serialVersionUID = 202407081733L;

    /** Hotel */
    private Hotel _hotel;

    /**
     * Constructor
     * @param hotel
     */
    public VeterinarianSatisfaction(Hotel hotel) {
        _hotel = hotel;
    }

    /**
     * Calculate the satisfaction of a veterinarian
     * @param idEmployee
     * @return
     */
    @Override
    public double calculate(String idEmployee) {

        Veterinarian e = (Veterinarian) _hotel.getEmployee(idEmployee);

        List<String> vetSpecies = e.getVeterinarianSpecies();
        int counter = 0;
        double work = 0;
        int animPop;

        for (String idSpecie: vetSpecies) {
            counter = 0;
            for (String idVet: _hotel.getEmployeesKeySet()) {
                Employee emp = _hotel.getEmployee(idVet);
                if (emp instanceof Veterinarian) {
                    Veterinarian v = (Veterinarian) emp;

                    if (v.hasPermission(idSpecie)) {
                        counter += 1;
                    }
                }
            }
            animPop = _hotel.getSpecie(idSpecie).getNumberAnimals();
            work += (animPop / counter);
        }

        return 20 - work;
    }

}