package hva.app.habitat;

import hva.Hotel;
// import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;
//FIXME import other classes if needed

class DoRegisterHabitat extends Command<Hotel> {

    DoRegisterHabitat(Hotel receiver) {
        super(Label.REGISTER_HABITAT, receiver);
        addStringField("habitatKey", Prompt.habitatKey());
        addStringField("habitatName", Prompt.habitatName());
        addIntegerField("habitatArea", Prompt.habitatArea());
        //FIXME add command fields if needed
    }

    @Override
    protected void execute() throws CommandException {
        //FIXME implement command

        String habitatKey = stringField("habitatKey");
        String habitatName = stringField("habitatName");
        int habitatArea = integerField("habitatArea");

        // tirei as verificações se a espécie já existe, isso vai ser feito no métido registerNewAnimAl()
        // tás a lançar duplicateanimalexception em 3 sitios diferentes. Tava aqui um, outro no hotel e outro na espécie

        // Chama o metodo do hotel
        
        _receiver.registerNewHabitat(habitatKey, habitatName, habitatArea);
    }

}
