package hva.app.habitat;

import hva.Hotel;
import hva.exceptions.UnknownHabitatException;
import hva.app.exceptions.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for changing the area of a habitat.
 */
class DoChangeHabitatArea extends Command<Hotel> {

    /** @param receiver */
    DoChangeHabitatArea(Hotel receiver) {
        super(Label.CHANGE_HABITAT_AREA, receiver);
        addStringField("habitatKey", Prompt.habitatKey());
        addIntegerField("habitatArea", Prompt.habitatArea());
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected void execute() throws CommandException {
        try {
            String habitatKey = stringField("habitatKey");
            int habitatArea = integerField("habitatArea");

            _receiver.changeHabitatArea(habitatKey, habitatArea);
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getId());
        }
    }

}
