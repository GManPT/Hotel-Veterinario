package hva.app.search;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoShowMedicalActsOnAnimal extends Command<Hotel> {

    DoShowMedicalActsOnAnimal(Hotel receiver) {
        super(Label.MEDICAL_ACTS_ON_ANIMAL, receiver);
        addStringField("animalKey", hva.app.animal.Prompt.animalKey());
    }

    @Override
    protected void execute() throws CommandException {
        String animalKey = stringField("animalKey");
        _display.popup(_receiver.medicalActsOnAnimal(animalKey));
    }

}
