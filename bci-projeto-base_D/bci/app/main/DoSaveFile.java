package bci.app.main;

import bci.core.LibraryManager;
import bci.core.exception.MissingFileAssociationException;
import pt.tecnico.uilib.forms.Form;
import pt.tecnico.uilib.menus.Command;
import java.io.IOException;
// FIXME add more imports if needed


/**
 * Save to file under current name (if unnamed, query for name).
 */
class DoSaveFile extends Command<LibraryManager> {

  DoSaveFile(LibraryManager receiver) {
    super(Label.SAVE_FILE, receiver);
  }

    @Override
  protected final void execute() {
    String currentFileName = _receiver.getCurrentFileName();
    String fileNameToSave = currentFileName;

    if (fileNameToSave == null) {
      Form form = new Form();
      form.addStringField("_newFileName", Prompt.newSaveAs());
      form.parse();

      fileNameToSave = form.stringField("_newFileName");
    }
    try { 
      if (currentFileName == null) {
        _receiver.saveAs(fileNameToSave);
      } else {
        _receiver.save();
      }
    } catch (IOException | MissingFileAssociationException e){}
  }  
}
