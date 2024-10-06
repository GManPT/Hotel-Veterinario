package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;


class DoRegisterAnimal extends Command<Hotel> {
    DoRegisterAnimal(Hotel receiver) {
        super(Label.REGISTER_ANIMAL, receiver);
        addStringField("animalKey", Prompt.animalKey());
        addStringField("animalName", Prompt.animalName());
        addStringField("speciesKey", Prompt.speciesKey());
    }

    @Override
    protected final void execute() throws CommandException {
        String animalKey = stringField("animalKey");
        String name = stringField("animalName");
        String speciesKey = stringField("speciesKey");
        String speciesName = null;

        // Esta verificacao tem de estar presente aqui porque quer-se mostrar mais texto
        if (!_receiver.speciesExists(speciesKey)) {
            speciesName = Form.requestString(Prompt.speciesName());
        }
        
        String habitatKey = Form.requestString(hva.app.habitat.Prompt.habitatKey());

        // Chama o metodo do hotel
        _receiver.registerNewAnimal(animalKey, name, speciesKey, speciesName, habitatKey);
    }
}