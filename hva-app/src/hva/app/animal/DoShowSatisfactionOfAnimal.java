package hva.app.animal;

import hva.Hotel;
import hva.exceptions.UnknownAnimalException;
import hva.exceptions.UnknownSpeciesException;
import hva.app.exceptions.UnknownAnimalKeyException;
import hva.app.exceptions.UnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for showing the satisfaction of an animal.
 */
class DoShowSatisfactionOfAnimal extends Command<Hotel> {

    /** @param receiver */
    DoShowSatisfactionOfAnimal(Hotel receiver) {
        super(Label.SHOW_SATISFACTION_OF_ANIMAL, receiver);
        addStringField("animalKey", Prompt.animalKey());
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected final void execute() throws CommandException {
        try {
            String animalKey = stringField("animalKey");
            _display.popup(Math.round(_receiver.getSatisfactionAnimal(animalKey)));
        } catch (UnknownAnimalException e) {
            throw new UnknownAnimalKeyException(e.getId());
        } catch (UnknownSpeciesException e) {
            throw new UnknownSpeciesKeyException(e.getId());
        }
    }
}
