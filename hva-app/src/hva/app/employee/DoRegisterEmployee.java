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
    }

    @Override
    protected void execute() throws CommandException {
        try {
            String employeeKey = stringField("employeeKey");
            String employeeName = stringField("employeeName"); 
            String employeeType = "";

            while(!(employeeType.equals("VET") || employeeType.equals("TRT"))){
                employeeType = Form.requestString(Prompt.employeeType());
            }

            _receiver.registerNewEmployee(employeeKey, employeeName, employeeType);

        } catch (DuplicateEmployeeException e) {
            throw new DuplicateEmployeeKeyException(e.getId());
        }
    }

}
