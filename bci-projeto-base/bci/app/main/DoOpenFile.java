package bci.app.main;

import bci.core.LibraryManager;
import bci.app.exception.FileOpenFailedException;
import bci.core.exception.MissingFileAssociationException;
import bci.core.exception.UnavailableFileException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import pt.tecnico.uilib.menus.CommandException;

import java.io.IOException;

class DoOpenFile extends Command<LibraryManager> {

  DoOpenFile(LibraryManager receiver) {
    super(Label.OPEN_FILE, receiver);
  }

  @Override
  protected final void execute() throws CommandException {

    if (_receiver.isModified()) {
      boolean wantsToSave = Form.confirm(Prompt.saveBeforeExit());
      if (wantsToSave) {
        try {
          
          if (_receiver.getCurrentFileName() == null) {
            Form form = new Form();
            form.addStringField("newFileName", Prompt.newSaveAs());
            form.parse();
            String newFileName = form.stringField("newFileName");
            _receiver.saveAs(newFileName);
          } else {
            _receiver.save();
          }
        } catch (IOException | MissingFileAssociationException e) {
         
        }
      }
    }

    Form openForm = new Form();
    openForm.addStringField("filename", Prompt.openFile());
    openForm.parse();
    String filename = openForm.stringField("filename");

    try {
      _receiver.load(filename);
    } catch (UnavailableFileException e) {
      throw new FileOpenFailedException(e);
    }
  }
}
