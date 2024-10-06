package hva.app.employee;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.exceptions.ResponsibilityException;
import hva.app.exceptions.NoResponsibilityException;

class DoAddResponsibility extends Command<Hotel> {

    DoAddResponsibility(Hotel receiver) {
        super(Label.ADD_RESPONSABILITY, receiver);
        addStringField("employeeKey", Prompt.employeeKey());
    }

    @Override
    protected void execute() throws CommandException {
        try {
            String employeeKey = stringField("employeeKey");
            boolean type = _receiver.isVet(employeeKey);

            String idWork = type ? Form.requestString(hva.app.animal.Prompt.speciesKey()) :
                                    Form.requestString(hva.app.habitat.Prompt.habitatKey());
            _receiver.addResponsability(employeeKey, idWork, type);

        } catch(ResponsibilityException e) {
            throw new NoResponsibilityException(e.getId(), e.getResponsibility());
        }
    }
}
