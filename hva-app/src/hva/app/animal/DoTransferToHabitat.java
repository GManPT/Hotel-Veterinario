package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;

class DoTransferToHabitat extends Command<Hotel> {

    DoTransferToHabitat(Hotel hotel) {
        super(Label.TRANSFER_ANIMAL_TO_HABITAT, hotel);
        addStringField("animalKey", Prompt.animalKey());
        addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
    }

    @Override
    protected final void execute() throws CommandException {
        String animalKey = stringField("animalKey");
        String idHabitat = stringField("habitatKey");

        _receiver.changeAnimalHabitat(animalKey, idHabitat);
    }
}
