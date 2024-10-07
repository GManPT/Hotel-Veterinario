package hva.app.main;

import hva.HotelManager;
import hva.exceptions.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import java.io.IOException;

class DoSaveFile extends Command<HotelManager> {
    DoSaveFile(HotelManager receiver) {
        super(Label.SAVE_FILE, receiver, r -> r.getHotel() != null);
    }

    @Override
    protected final void execute() throws CommandException{
		try {
			try {
				_receiver.save();
			}
			catch (MissingFileAssociationException e) {
				try {
					String filename = Form.requestString(Prompt.newSaveAs());
					_receiver.saveAs(filename);
				}
				catch (MissingFileAssociationException e1){
					e1.printStackTrace();
				}
			}
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
}
