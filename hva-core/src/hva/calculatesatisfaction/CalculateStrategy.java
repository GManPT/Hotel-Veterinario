package hva.calculatesatisfaction;

import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownSpeciesException;

public interface CalculateStrategy {
    public double calculate(String idTree);
}