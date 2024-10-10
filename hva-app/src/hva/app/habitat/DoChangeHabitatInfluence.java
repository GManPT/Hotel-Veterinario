package hva.app.habitat;

import hva.Hotel;
import hva.exceptions.UnknownHabitatException;
import hva.exceptions.UnknownSpeciesException;
import hva.app.exceptions.UnknownHabitatKeyException;
import hva.app.exceptions.UnknownSpeciesKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * Command for changing the influence of a habitat on a species.
 */
class DoChangeHabitatInfluence extends Command<Hotel> {
    
    /** @param receiver */
    DoChangeHabitatInfluence(Hotel receiver) {
        super(Label.CHANGE_HABITAT_INFLUENCE, receiver);
        addStringField("habitatKey", Prompt.habitatKey());
        addStringField("speciesKey", hva.app.animal.Prompt.speciesKey());
        addOptionField("habitatInfluence", Prompt.habitatInfluence(), new String[] {"POS", "NEG", "NEU"});
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected void execute() throws CommandException {
        try {
            String habitatKey = stringField("habitatKey");
            String speciesKey = stringField("speciesKey");
            String habitatInfluence = optionField("habitatInfluence");

            _receiver.changeHabitatInfluence(habitatKey, speciesKey, habitatInfluence);
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getId());
        } catch (UnknownSpeciesException e) {
            throw new UnknownSpeciesKeyException(e.getId());
        }
    }

}
