package hva.app.habitat;

import hva.Hotel;
import hva.exceptions.UnknownHabitatException;
import hva.app.exceptions.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for showing all trees in a habitat.
 */
class DoShowAllTreesInHabitat extends Command<Hotel> {

    /** @param receiver */
    DoShowAllTreesInHabitat(Hotel receiver) {
        super(Label.SHOW_TREES_IN_HABITAT, receiver);
        addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected void execute() throws CommandException {
        try {
            String habitatKey = stringField("habitatKey");
            _display.popup(_receiver.trees(habitatKey));
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getId());
        }
    }

}
