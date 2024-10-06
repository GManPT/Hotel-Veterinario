package hva.app.vaccine;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.exceptions.DuplicateVaccineException;
import hva.exceptions.UnknownSpeciesException;
import hva.app.exceptions.UnknownSpeciesKeyException;
import hva.app.exceptions.DuplicateVaccineKeyException;

class DoRegisterVaccine extends Command<Hotel> {

    DoRegisterVaccine(Hotel receiver) {
        super(Label.REGISTER_VACCINE, receiver);
	    addStringField("vaccineKey", Prompt.vaccineKey());
        addStringField("vaccineName", Prompt.vaccineName());
        addStringField("species", Prompt.listOfSpeciesKeys());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            String vaccineKey = stringField("vaccineKey");
            String vaccineName = stringField("vaccineName");
            String species = stringField("species");

            _receiver.registerVaccine(vaccineKey, vaccineName, species);
        } catch(DuplicateVaccineException e) {
            throw new DuplicateVaccineKeyException(e.getId());
        } catch(UnknownSpeciesException e) {
            throw new UnknownSpeciesKeyException(e.getId());
        }
    }

}
