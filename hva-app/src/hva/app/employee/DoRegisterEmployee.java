package hva.app.employee;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.app.exceptions.DuplicateEmployeeKeyException;
import hva.exceptions.DuplicateEmployeeException;

class DoRegisterEmployee extends Command<Hotel> {

    DoRegisterEmployee(Hotel receiver) {
        super(Label.REGISTER_EMPLOYEE, receiver);
        addStringField("employeeKey", Prompt.employeeKey());
        addStringField("employeeName", Prompt.employeeName());
        addOptionField("employeeType", Prompt.employeeType(), new String[] { "VET", "TRT" });
    }

    @Override
    protected void execute() throws CommandException {
        try {
            String employeeKey = stringField("employeeKey");
            String employeeName = stringField("employeeName"); 
            String employeeType = optionField("employeeType");

            _receiver.registerNewEmployee(employeeKey, employeeName, employeeType);

        } catch (DuplicateEmployeeException e) {
            throw new DuplicateEmployeeKeyException(e.getId());
        }
    }

}
