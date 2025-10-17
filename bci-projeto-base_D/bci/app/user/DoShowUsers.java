package bci.app.user;

import bci.core.LibraryManager;
import bci.core.User;
import pt.tecnico.uilib.menus.Command;
//FIXME add more imports if needed

/**
 * 4.2.4. Show all users.
 */
class DoShowUsers extends Command<LibraryManager> {

  DoShowUsers(LibraryManager receiver) {
    super(Label.SHOW_USERS, receiver);
  }

  @Override
  protected final void execute() {
    for (User user : _receiver.getAllUsers()) {
      _display.addLine(user.toString());
    }
    _display.display();
  }
}