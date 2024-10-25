package hva.exceptions;

/**
 * Exception thrown when a veterinarian is not authorized to perform an operation on a species.
 */
public class DamagedVaccinationException extends Exception {
    /** Serial number for serialization. */
    private static final long serialVersionUID = 202407081733L;
    private String _vaccineKey;
    private String _specie;

    /**
     * @param vaccineKey
     * @param specie
     */
    public DamagedVaccinationException(String vaccineKey, String specie) {
        _vaccineKey = vaccineKey;
        _specie = specie;
    }

    /**
     * @return _vaccineKey
     */
    public String getVaccine() {
        return _vaccineKey;
    }

    /**
     * @return _specie
     */
    public String getSpecie() {
        return _specie;
    }
}