package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
// import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoShowAllHabitats extends Command<Hotel> {

    DoShowAllHabitats(Hotel receiver) {
        super(Label.SHOW_ALL_HABITATS, receiver);
    }

    @Override
    protected void execute() {
        //FIXME implement command
    }
}
