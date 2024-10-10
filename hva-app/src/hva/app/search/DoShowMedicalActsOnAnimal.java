package hva.app.search;

import hva.Hotel;
import hva.exceptions.UnknownAnimalException;
import hva.app.exceptions.UnknownAnimalKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for showing the medical acts on an animal.
 */
class DoShowMedicalActsOnAnimal extends Command<Hotel> {

    /** @param receiver */
    DoShowMedicalActsOnAnimal(Hotel receiver) {
        super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
        addStringField("animalKey", hva.app.animal.Prompt.animalKey());
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected void execute() throws CommandException {
        try {
            String animalKey = stringField("animalKey");
            _display.popup(_receiver.medicalActsOnAnimal(animalKey));
        } catch (UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(e.getId());
        }
    }

}
