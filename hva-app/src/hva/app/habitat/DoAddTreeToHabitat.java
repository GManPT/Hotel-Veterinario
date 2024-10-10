package hva.app.habitat;

import hva.Hotel;
import hva.exceptions.UnknownHabitatException;
import hva.app.exceptions.UnknownHabitatKeyException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import hva.app.exceptions.DuplicateTreeKeyException;
import hva.exceptions.DuplicateTreeException;

/**
 * Command for adding a tree to a habitat.
 */
class DoAddTreeToHabitat extends Command<Hotel> {

    /** @param receiver */
    DoAddTreeToHabitat(Hotel receiver) {
        super(Label.ADD_TREE_TO_HABITAT, receiver);
        addStringField("habitatKey", Prompt.habitatKey());
        addStringField("treeKey", Prompt.treeKey());
        addStringField("treeName", Prompt.treeName());
        addIntegerField("treeAge", Prompt.treeAge());
        addIntegerField("treeDifficulty", Prompt.treeDifficulty());
        addOptionField("treeType", Prompt.treeType(), new String[] {"CADUCA", "PERENE"});
    }

    /** @see pt.tecnico.uilib.menu.Command#execute() */
    @Override
    protected void execute() throws CommandException {
        try {
            String habitatKey = stringField("habitatKey");
            String treeKey = stringField("treeKey");
            String treeName = stringField("treeName");
            int treeAge = integerField("treeAge");
            int treeDifficulty = integerField("treeDifficulty");
            String treeType = optionField("treeType");
                
            _receiver.plantTreeHabitat(habitatKey, treeKey, treeName, treeAge, treeDifficulty, treeType);

        } catch (DuplicateTreeException e) {
            throw new DuplicateTreeKeyException(e.getId());
        } catch (UnknownHabitatException e) {
            throw new UnknownHabitatKeyException(e.getId());
        }
    }

}
