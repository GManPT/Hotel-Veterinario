package hva.app.main;

import hva.HotelManager;
import hva.app.exceptions.FileOpenFailedException;
import hva.exceptions.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

class DoOpenFile extends Command<HotelManager> {
    DoOpenFile(HotelManager receiver) {
        super(Label.OPEN_FILE, receiver);
        addStringField("filename", Prompt.openFile());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            String filename = stringField("filename");
            _receiver.load(filename);
        } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        }
    }
}
