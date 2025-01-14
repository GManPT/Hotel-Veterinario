package hva.app.vaccine;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.exceptions.UnknownVeterinarianException;
import hva.exceptions.VeterinarianAuthorizedException;
import hva.exceptions.DamagedVaccinationException;
import hva.exceptions.UnknownVaccineException;
import hva.exceptions.UnknownAnimalException;
import hva.app.exceptions.UnknownAnimalKeyException;
import hva.app.exceptions.UnknownVaccineKeyException;
import hva.app.exceptions.UnknownVeterinarianKeyException;
import hva.app.exceptions.VeterinarianNotAuthorizedException;

/**
 * Command for vaccinating an animal.
 */
class DoVaccinateAnimal extends Command<Hotel> {
    
    /** @param receiver */
    DoVaccinateAnimal(Hotel receiver) {
        super(Label.VACCINATE_ANIMAL, receiver);
        addStringField("vaccineKey", Prompt.vaccineKey());
        addStringField("veterinarianKey", Prompt.veterinarianKey());
        addStringField("animalKey", hva.app.animal.Prompt.animalKey());
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected final void execute() throws CommandException {
        try {
            String vaccineKey = stringField("vaccineKey");
            String veterinarianKey = stringField("veterinarianKey");
            String animalKey = stringField("animalKey");

            _receiver.shouldBeVaccinated(vaccineKey, veterinarianKey, animalKey);
            _receiver.vaccinateAnimal(vaccineKey, veterinarianKey, animalKey);

        } catch(UnknownVeterinarianException e) {
            throw new UnknownVeterinarianKeyException(e.getId());
        } catch (VeterinarianAuthorizedException e) {
            throw new VeterinarianNotAuthorizedException(e.getIdVet(), e.getId());
        } catch (UnknownVaccineException e) {
            throw new UnknownVaccineKeyException(e.getId());
        } catch (UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(e.getId());
        } catch (DamagedVaccinationException e) {
            _display.popup(Message.wrongVaccine(e.getVaccine(), e.getSpecie()));
        }
    }
}
