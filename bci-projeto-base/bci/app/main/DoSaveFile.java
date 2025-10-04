package bci.app.main;

import bci.core.LibraryManager;
import bci.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
// FIXME add more imports if needed
import pt.tecnico.uilib.menus.CommandException;

import java.io.IOException;

/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<LibraryManager> {

  DoSaveFile(LibraryManager receiver) {
    super(Label.SAVE_FILE, receiver);
  }

    @Override
  protected final void execute() {
    String _currentFileName = _receiver.getCurrentFileName();
    String _fileNameToSave = _currentFileName;

    if (_fileNameToSave == null) {
      Form form = new Form();
      form.addStringField("_newFileName", Prompt.saveAs());
      form.parse();

      _fileNameToSave = form.stringField("_newFileName");
    }
    try { 
      if (_currentFileName == null) {
        _receiver.saveAs(_fileNameToSave);
      } else {
        _receiver.save();
      }
    } catch (IOException | MissingFileAssociationException e){}
  }  
}
