package hva.app.animal;

import hva.Hotel;
import hva.exceptions.DuplicateAnimalException;
import hva.exceptions.UnknownHabitatException;
import hva.app.exceptions.DuplicateAnimalKeyException;
import hva.app.exceptions.UnknownHabitatKeyException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for registering a new animal.
 */
class DoRegisterAnimal extends Command<Hotel> {
    
    /** @param receiver */
    DoRegisterAnimal(Hotel receiver) {
        super(Label.REGISTER_ANIMAL, receiver);
        addStringField("animalKey", Prompt.animalKey());
        addStringField("animalName", Prompt.animalName());
        addStringField("speciesKey", Prompt.speciesKey());
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected final void execute() throws CommandException {
        try {
            String animalKey = stringField("animalKey");
            String name = stringField("animalName");
            String speciesKey = stringField("speciesKey");
            String speciesName = null;

            /** Verify if the species exists, if not, ask for the species name */
            if (!_receiver.speciesExists(speciesKey)) {
                speciesName = Form.requestString(Prompt.speciesName());
            }
            
            String habitatKey = Form.requestString(hva.app.habitat.Prompt.habitatKey());
            _receiver.registerNewAnimal(animalKey, name, speciesKey, speciesName, habitatKey);

        } catch (DuplicateAnimalException e) {
            throw new DuplicateAnimalKeyException(e.getId());
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getId());
        }
    }
}