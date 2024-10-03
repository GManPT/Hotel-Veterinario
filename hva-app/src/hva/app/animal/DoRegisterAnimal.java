package hva.app.animal;

import hva.Hotel;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import hva.exceptions.DuplicateAnimalException;
import hva.app.exceptions.DuplicateAnimalKeyException;

class DoRegisterAnimal extends Command<Hotel> {

    DoRegisterAnimal(Hotel receiver) {
        super(Label.REGISTER_ANIMAL, receiver);
        addStringField("animalKey", Prompt.animalKey());
        addStringField("animalName", Prompt.animalName());
        addStringField("speciesKey", Prompt.speciesKey());
    }

    @Override
    protected final void execute() throws CommandException {
        try {
            String animalKey = stringField("animalKey");
            String name = stringField("animalName");
            String speciesKey = stringField("speciesKey");

            // Verificar se a espeecie existe
            if (!_receiver.speciesExists(speciesKey)) {
                // Ve o uilib (ou o UML se preferires) para ver o que faz isto
                String speciesName = Form.requestString(Prompt.speciesName());
                _receiver.registerNewSpecie(speciesKey, speciesName);
            }
            
            String habitatKey = Form.requestString(hva.app.habitat.Prompt.habitatKey());

            // Chama o metodo do hotel
            _receiver.registerNewAnimal(animalKey, name, speciesKey, habitatKey);

        } catch (DuplicateAnimalException e) {
            throw new DuplicateAnimalKeyException(e);
        }
    }
}