package hva.app.employee;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.ResponsibilityException;
import hva.exceptions.UnknownEmployeeException;
import hva.app.exceptions.UnknownEmployeeKeyException;
import hva.app.exceptions.NoResponsibilityException;

/**
 * Command for removing a responsibility from an employee.
 */
class DoRemoveResponsibility extends Command<Hotel> {

    /** @param receiver */
    DoRemoveResponsibility(Hotel receiver) {
        super(Label.REMOVE_RESPONSABILITY, receiver);
        addStringField("employeeKey", Prompt.employeeKey());
        addStringField("responsibilityKey", Prompt.responsibilityKey());
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected void execute() throws CommandException {
        try {
            String employeeKey = stringField("employeeKey");
            String responsibilityKey = stringField("responsibilityKey");

            _receiver.removeResponsibility(employeeKey, responsibilityKey);

        } catch (ResponsibilityException e) {
            throw new NoResponsibilityException(e.getId(), e.getResponsibility());
        } catch (UnknownEmployeeException e) {
            throw new UnknownEmployeeKeyException(e.getId());
        }
    }

}
