package hva.app.animal;

import hva.Hotel;
import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownHabitatException;
import hva.app.exceptions.UnknownAnimalKeyException;
import hva.app.exceptions.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for transferring an animal to a habitat.
 */
class DoTransferToHabitat extends Command<Hotel> {

    /** @param receiver */
    DoTransferToHabitat(Hotel hotel) {
        super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
        addStringField("animalKey", Prompt.animalKey());
        addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected final void execute() throws CommandException {
        try {
            String animalKey = stringField("animalKey");
            String idHabitat = stringField("habitatKey");

            _receiver.changeAnimalHabitat(animalKey, idHabitat);
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getId());
        } catch (UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(e.getId());
        }
    }
}
