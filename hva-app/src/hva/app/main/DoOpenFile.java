package hva.app.main;

import java.io.IOException;

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

        if (_receiver.changed() && Form.confirm(Prompt.saveBeforeExit())) { 
            DoSaveFile file = new DoSaveFile(_receiver);
            file.execute();
        }

        try {
            String filename = stringField("filename");
            _receiver.load(filename);
        } catch (UnavailableFileException e) {
            throw new FileOpenFailedException(e);
        } catch (IOException e) {
            throw new FileOpenFailedException(e);
        } catch (ClassNotFoundException e) {
            throw new FileOpenFailedException(e);
        }
    }
}
