package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoShowAnimalsInHabitat extends Command<Hotel> {

    DoShowAnimalsInHabitat(Hotel receiver) {
        super(Label.ANIMALS_IN_HABITAT, receiver);
        addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
    }

    @Override
    protected void execute() throws CommandException {
        String habitatKey = stringField("habitatKey");
        _display.popup(_receiver.animalsInHabitat(habitatKey));
    }

}
