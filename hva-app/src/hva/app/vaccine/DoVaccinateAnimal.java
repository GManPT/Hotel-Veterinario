package hva.app.vaccine;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.exceptions.UnknownVeterinarianException;
import hva.app.exceptions.UnknownVeterinarianKeyException;
import hva.exceptions.VeterinarianAuthorizedException;
import hva.app.exceptions.VeterinarianNotAuthorizedException;

class DoVaccinateAnimal extends Command<Hotel> {

    DoVaccinateAnimal(Hotel receiver) {
        super(Label.VACCINATE_ANIMAL, receiver);
        addStringField("vaccineKey", Prompt.vaccineKey());
        addStringField("veterinarianKey", Prompt.veterinarianKey());
        addStringField("animalKey", hva.app.animal.Prompt.animalKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            String vaccineKey = stringField("vaccineKey");
            String veterinarianKey = stringField("veterinarianKey");
            String animalKey = stringField("animalKey");

            if (_receiver.shouldBeVaccinated(vaccineKey, veterinarianKey, animalKey)) {
                if (!_receiver.wasAppropriatelyVaccinated(animalKey, vaccineKey)) {
                    _display.popup(Message.wrongVaccine(vaccineKey, animalKey));
                }
                
                _receiver.vaccinateAnimal(vaccineKey, veterinarianKey, animalKey);
            }
        } catch(UnknownVeterinarianException e) {
            throw new UnknownVeterinarianKeyException(e.getId());
        } catch (VeterinarianAuthorizedException e) {
            throw new VeterinarianNotAuthorizedException(e.getIdVet(), e.getId());
        }

    }

}
