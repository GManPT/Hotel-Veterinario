package hva.app.employee;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.ResponsibilityException;
import hva.app.exceptions.NoResponsibilityException;
import pt.tecnico.uilib.forms.Form;

class DoRemoveResponsibility extends Command<Hotel> {

    DoRemoveResponsibility(Hotel receiver) {
        super(Label.REMOVE_RESPONSABILITY, receiver);
        addStringField("employeeKey", Prompt.employeeKey());
        addStringField("responsibilityKey", Prompt.responsibilityKey());
    }

    @Override
    protected void execute() throws CommandException {
        try {
            String employeeKey = stringField("employeeKey");
            String responsibilityKey = stringField("responsibilityKey");

            _receiver.removeResponsibility(employeeKey, responsibilityKey);

        } catch (ResponsibilityException e) {
            throw new NoResponsibilityException(e.getId(), e.getResponsibility());
        }
    }

}
