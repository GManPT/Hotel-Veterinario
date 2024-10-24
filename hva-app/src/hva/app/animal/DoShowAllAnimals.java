package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;

/**
 * Command for showing all animals.
 */
class DoShowAllAnimals extends Command<Hotel> {

    /** @param receiver */
    DoShowAllAnimals(Hotel receiver) {
        super(Label.SHOW_ALL_ANIMALS, receiver);
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected final void execute() {
        _display.popup(_receiver.allAnimals());
    }

}
