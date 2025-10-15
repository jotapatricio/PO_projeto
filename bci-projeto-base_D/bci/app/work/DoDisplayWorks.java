package bci.app.work;

import bci.core.LibraryManager;
import bci.core.Work;
import pt.tecnico.uilib.menus.Command;

/**
 * 4.3.1. Mostrar todas as obras.
 */
class DoDisplayWorks extends Command<LibraryManager> {

  DoDisplayWorks(LibraryManager receiver) {
    super(Label.SHOW_WORKS, receiver);
  }

  protected final void execute() {
    for (Work work : _receiver.getAllWorks()) {
      _display.addLine(work.toString());
    }
    _display.display();
  }
}