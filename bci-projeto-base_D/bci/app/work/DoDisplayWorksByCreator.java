package bci.app.work;

import java.util.List;
import java.util.ArrayList;
import bci.core.LibraryManager;
import bci.core.Creator;
import bci.core.Work;
import bci.app.exception.NoSuchCreatorException;
import pt.tecnico.uilib.menus.Command;



/**
 * Display all works by a specific creator.
 */
class DoDisplayWorksByCreator extends Command<LibraryManager> {

    DoDisplayWorksByCreator(LibraryManager receiver) {
        super(Label.SHOW_WORKS_BY_CREATOR, receiver);
        addStringField("name", Prompt.creatorId()); 
    }

    @Override
    protected final void execute() throws NoSuchCreatorException {
        String name = stringField("name");

        Creator creator = _receiver.getCreator(name);
        if (creator == null) {
            throw new NoSuchCreatorException(name);
        }

        List <Work> allWorks = new ArrayList <> (_receiver.getAllWorks());

        boolean hasWorks = false;
        for (Work w : allWorks) {
            if (w.getCreators().contains(creator)) {
                _display.popup(w.toString());
                hasWorks = true;
            }
        }

        if (!hasWorks) {
            _display.popup("Nenhuma obra encontrada para o criador " + name + ".");
        }
    }
}

