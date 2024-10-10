package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for showing all habitats.
 */
class DoShowAllHabitats extends Command<Hotel> {

    /** @param receiver */
    DoShowAllHabitats(Hotel receiver) {
        super(Label.SHOW_ALL_HABITATS, receiver);
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected void execute() {
        _display.popup(_receiver.habitatAndTrees());
    }
}
