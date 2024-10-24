package hva.app.employee;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.exceptions.ResponsibilityException;
import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.UnknownVeterinarianException;
import hva.app.exceptions.UnknownEmployeeKeyException;
import hva.app.exceptions.NoResponsibilityException;

/**
 * Command for adding a responsibility to an employee.
 */
class DoAddResponsibility extends Command<Hotel> {

    /** @param receiver */
    DoAddResponsibility(Hotel receiver) {
        super(Label.ADD_RESPONSABILITY, receiver);
        addStringField("employeeKey", Prompt.employeeKey());
        addStringField("responsibility", Prompt.responsibilityKey());
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected void execute() throws CommandException {
        try {
            String employeeKey = stringField("employeeKey");
            String idWork = stringField("responsibility");

            try {
                _receiver.isVet(employeeKey);
                _receiver.isEmployee(employeeKey);
                _receiver.addResponsibilityVeterinarian(employeeKey, idWork);
            } catch (UnknownVeterinarianException e) {
                _receiver.isEmployee(employeeKey);
                _receiver.addResponsibilityKeeper(employeeKey, idWork);
            }

        } catch(ResponsibilityException e) {
            throw new NoResponsibilityException(e.getId(), e.getResponsibility());
        } catch (UnknownEmployeeException e) {
            throw new UnknownEmployeeKeyException(e.getId());
        }
    }
}
