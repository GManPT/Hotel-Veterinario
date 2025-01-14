package hva.app.vaccine;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for showing all vaccines.
 */
class DoShowAllVaccines extends Command<Hotel> {

    /** @param receiver */
    DoShowAllVaccines(Hotel receiver) {
        super(Label.SHOW_ALL_VACCINES, receiver);
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected final void execute() {
        _display.popup(_receiver.vaccines());
    }
}
