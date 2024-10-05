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
        String habitatKey = Form.requestString(hva.app.habitat.Prompt.habitatKey());

        // tirei as verificações se a espécie já existe, isso vai ser feito no métido registerNewAnimAl()
        // tás a lançar duplicateanimalexception em 3 sitios diferentes. Tava aqui um, outro no hotel e outro na espécie

        // Chama o metodo do hotel

        _receiver.registerNewAnimal(animalKey, name, speciesKey, habitatKey);
    }
}