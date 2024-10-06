package hva.employee;

public abstract class Employee {
    private String _idEmployee;
    private String _nameEmployee;

    public Employee(String idEmployee, String nameEmployee) {
        _idEmployee = idEmployee;
        _nameEmployee = nameEmployee;
    }

    public String getIdEmployee() {
        return _idEmployee;
    }

    public String getNameEmployee() {
        return _nameEmployee;
    }

    @Override
    public String toString() {
        return "|" + getIdEmployee() + "|" + getNameEmployee();
    }
}