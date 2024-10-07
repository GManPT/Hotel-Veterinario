package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

class DoChangeHabitatArea extends Command<Hotel> {

    DoChangeHabitatArea(Hotel receiver) {
        super(Label.CHANGE_HABITAT_AREA, receiver);
        addStringField("habitatKey", Prompt.habitatKey());
        addIntegerField("habitatArea", Prompt.habitatArea());
    }

    @Override
    protected void execute() throws CommandException {
        String habitatKey = stringField("habitatKey");
        int habitatArea = integerField("habitatArea");

        _receiver.changeHabitatArea(habitatKey, habitatArea);
    }

}
