package bci.app.work;

import bci.core.LibraryManager;
import bci.core.Work;
import bci.app.exception.NoSuchWorkException;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

/**
 * 4.3.2. Mostrar obra.
 */
class DoDisplayWork extends Command<LibraryManager> {

  DoDisplayWork(LibraryManager receiver) {
    super(Label.SHOW_WORK, receiver);
    addIntegerField("id", Prompt.workId());
  }

  protected final void execute() throws CommandException {
    int id = integerField("id");

    try {
      Work work = _receiver.getWork(id);
      if (work == null){ 
        throw new NoSuchWorkException(id);
      }
      _display.popup(work.toString());
    } catch (NoSuchWorkException e) {
      throw e; 
    }
  }
}
