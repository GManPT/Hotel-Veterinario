package hva.app.search;

import hva.Hotel;
import hva.exceptions.UnknownHabitatException;
import hva.app.exceptions.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for showing the animals in a habitat.
 */
class DoShowAnimalsInHabitat extends Command<Hotel> {

    /** @param receiver */
    DoShowAnimalsInHabitat(Hotel receiver) {
        super(Label.ANIMALS_IN_HABITAT, receiver);
        addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected void execute() throws CommandException {
        try {
            String habitatKey = stringField("habitatKey");
            _display.popup(_receiver.animalsInHabitat(habitatKey));
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getId());
        }
    }

}
