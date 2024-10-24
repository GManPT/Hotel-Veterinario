package hva.app.main;

import hva.HotelManager;
import hva.exceptions.StateChangedException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoNewFile extends Command<HotelManager> {
    DoNewFile(HotelManager receiver) {
        super(Label.NEW_FILE, receiver);
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            _receiver.changed();
        } catch (StateChangedException e) {
            if (Form.confirm(Prompt.saveBeforeExit())) {
                DoSaveFile file = new DoSaveFile(_receiver);
                file.execute();
            }
        }
        _receiver.resetHotel();

    }
}
