package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for showing the wrong vaccinations.
 */
class DoShowWrongVaccinations extends Command<Hotel> {

    /** @param receiver */
    DoShowWrongVaccinations(Hotel receiver) {
        super(Label.WRONG_VACCINATIONS, receiver);
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected void execute() throws CommandException {
        _display.popup(_receiver.wrongVaccinations());
    }
}
