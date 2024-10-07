package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.UnknownVeterinarianException;
import hva.app.exceptions.UnknownVeterinarianKeyException;

class DoShowMedicalActsByVeterinarian extends Command<Hotel> {

    DoShowMedicalActsByVeterinarian(Hotel receiver) {
        super(Label.MEDICAL_ACTS_BY_VET, receiver);
        addStringField("employeeKey", hva.app.employee.Prompt.employeeKey());
    }

    @Override
    protected void execute() throws CommandException {
        try {
            String employeeKey = stringField("employeeKey");
            _display.popup(_receiver.veterinarianHistoric(employeeKey));
        }
        catch (UnknownVeterinarianException e) {
            throw new UnknownVeterinarianKeyException(e.getId());
        }
    }

}
