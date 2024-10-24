package hva.employee;
import java.io.Serializable;

public abstract class Employee implements Serializable{


    /** id of the employee */
    private String _idEmployee;

    /** name of the employee */
    private String _nameEmployee;

    /**
     * Constructor for the Employee class
     */
    public Employee(String idEmployee, String nameEmployee) {
        _idEmployee = idEmployee;
        _nameEmployee = nameEmployee;
    }


    /**
     * Get the employee's ID
     * 
     * @return the employee's ID
     */
    public String getIdEmployee() {
        return _idEmployee;
    }

    /**
     * Get the employee's name
     * 
     * @return the employee's name
     */
    public String getNameEmployee() {
        return _nameEmployee;
    }

    /**
     * employee to string format
     * 
     * @return employee to string
     */
    @Override
    public String toString() {
        return "|" + _idEmployee + "|" + _nameEmployee;
    }
}