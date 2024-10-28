package hva.calculatesatisfaction;

import hva.Hotel;
import java.io.Serializable;
import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownSpeciesException;
import hva.habitat.Habitat;

public class AnimalSatisfaction implements CalculateStrategy, Serializable {
    private static final long serialVersionUID = 202407081733L;

    /** Hotel */
    private Hotel _hotel;

    /**
     * Constructor
     * @param hotel
     */
    public AnimalSatisfaction(Hotel hotel) {
        _hotel = hotel;
    }

    /**
     * Calculate the satisfaction of an animal
     * @param idAnimal
     * @return
     */
    @Override
    public double calculate(String idAnimal) {

        String currHabit = _hotel.getAnimal(idAnimal).getCurrentHabitat();
        Habitat h = _hotel.getHabitat(currHabit);

        int equals = h.animalEquals(idAnimal);
        int population = h.getPopulationNumber();
        int diferent = population - (equals + 1);
        double area = (double) h.getArea();
        String influ = h.getSpeciesInfluence(_hotel.getAnimal(idAnimal).getIdSpecie());
        int adequacy = 0;   

        if (influ.equals("POS"))
            adequacy = 20;
        else if (influ.equals("NEG"))
            adequacy = -20;


        double satisfaction = 20 + (3 * equals) - (2 * diferent) + (area / population) + adequacy;
        return satisfaction;
    }

}