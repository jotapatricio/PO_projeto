package bci.app.main;

import bci.core.LibraryManager;
import pt.tecnico.uilib.menus.Command;


/**
 * 4.1.3. Advance the current date.
 */
class DoAdvanceDate extends Command<LibraryManager> {


  DoAdvanceDate(LibraryManager receiver) {
    super(Label.ADVANCE_DATE, receiver);
    addIntegerField("days", Prompt.daysToAdvance());
  }

  @Override
  protected final void execute() {
    int daysToAdvance = integerField("days");
    if (daysToAdvance <= 0) return;
       
    _receiver.advanceDays(daysToAdvance);
    
  }
}
