package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.DuplicateHabitatKeyException;
import hva.exceptions.DuplicateHabitatException;

class DoRegisterHabitat extends Command<Hotel> {

    DoRegisterHabitat(Hotel receiver) {
        super(Label.REGISTER_HABITAT, receiver);
        addStringField("habitatKey", Prompt.habitatKey());
        addStringField("habitatName", Prompt.habitatName());
        addIntegerField("habitatArea", Prompt.habitatArea());
    }

    @Override
    protected void execute() throws CommandException {
        try {
            String habitatKey = stringField("habitatKey");
            String habitatName = stringField("habitatName");
            int habitatArea = integerField("habitatArea");

            _receiver.registerNewHabitat(habitatKey, habitatName, habitatArea);
        } catch (DuplicateHabitatException e) {
            throw new DuplicateHabitatKeyException(e.getId());
        }
    }

}
