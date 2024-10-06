package hva.app.habitat;

import hva.Hotel;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
import pt.tecnico.uilib.forms.Form;
import hva.app.exceptions.DuplicateTreeKeyException;
import hva.exceptions.DuplicateTreeException;

class DoAddTreeToHabitat extends Command<Hotel> {

    DoAddTreeToHabitat(Hotel receiver) {
        super(Label.ADD_TREE_TO_HABITAT, receiver);
        addStringField("habitatKey", hva.app.habitat.Prompt.habitatKey());
        addStringField("treeKey", Prompt.treeKey());
        addStringField("treeName", Prompt.treeName());
        addIntegerField("treeAge", Prompt.treeAge());
        addIntegerField("treeDifficulty", Prompt.treeDifficulty());
    }

    @Override
    protected void execute() throws CommandException {
        try {
            String habitatKey = stringField("habitatKey");
            String treeKey = stringField("treeKey");
            String treeName = stringField("treeName");
            int treeAge = integerField("treeAge");
            int treeDifficulty = integerField("treeDifficulty");
            String treeType = "";

            while (!(treeType.equals("CADUCA") || treeType.equals("PERENE"))) {
                treeType = Form.requestString(Prompt.treeType());
            }
                
            _receiver.plantTreeHabitat(habitatKey, treeKey, treeName, treeAge, treeDifficulty, treeType);

        } catch (DuplicateTreeException e) {
            throw new DuplicateTreeKeyException(e.getId());
        }
    }

}
