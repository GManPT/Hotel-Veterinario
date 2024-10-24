package hva.app.employee;

import hva.Hotel;
import hva.exceptions.UnknownEmployeeException;
import hva.exceptions.UnknownVeterinarianException;
import hva.app.exceptions.UnknownEmployeeKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for showing the satisfaction of an employee.
 */
class DoShowSatisfactionOfEmployee extends Command<Hotel> {

    /** @param receiver */
    DoShowSatisfactionOfEmployee(Hotel receiver) {
        super(Label.SHOW_SATISFACTION_OF_EMPLOYEE, receiver);
        addStringField("employeeKey", Prompt.employeeKey());
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected void execute() throws CommandException {
        try {
            String employeeKey = stringField("employeeKey");
            _receiver.isVet(employeeKey);
            _display.popup(Math.round(_receiver.getVeterinarianSatisfaction(employeeKey)));
        } catch(UnknownVeterinarianException e) {
            _display.popup(Math.round(_receiver.getKeeperSatisfaction(e.getId())));
        } catch (UnknownEmployeeException e) {
            throw new UnknownEmployeeKeyException(e.getId());
        }
    }

}
