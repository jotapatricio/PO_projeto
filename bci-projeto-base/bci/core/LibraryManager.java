package bci.core;

import bci.core.exception.*;
import java.io.*;
import java.util.*;

/**
 * The façade class. Represents the manager of this application. It manages the current
 * library and works as the interface between the core and user interaction layers.
 */
public class LibraryManager {


  /** The object doing all the actual work. */
  /* The current library */
  private Library _library;

  private String _filename;

  public LibraryManager() {
    _library = new Library();

  }

  public int getCurrentDate() {
    return _library.getCurrentDate().getCurrentDate();
  }

  public int advanceDays(int days) {
    return _library.advanceDays(days).getCurrentDate();
  }



  /**
   * Saves the serialized application's state into the file associated to the current library
   *
   * @throws FileNotFoundException if for some reason the file cannot be created or opened. 
   * @throws MissingFileAssociationException if the current library does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void save() throws MissingFileAssociationException, FileNotFoundException, IOException {
    if (_filename == null)
      throw new MissingFileAssociationException();

    // 2. Criar um ObjectOutputStream para escrever o objeto
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(_filename))) {
        // 3. Escrever o objeto _library para o arquivo
        oos.writeObject(_library);
    }
    // O try-with-resources fecha automaticamente o stream
  }

  /**
   * Saves the serialized application's state into the specified file. The current library is
   * associated to this file.
   *
   * @param filename the name of the file.
   * @throws FileNotFoundException if for some reason the file cannot be created or opened.
   * @throws MissingFileAssociationException if the current library does not have a file.
   * @throws IOException if there is some error while serializing the state of the network to disk.
   **/
  public void saveAs(String filename) throws FileNotFoundException, MissingFileAssociationException, IOException {
    // 1. Guardar o novo filename
    _filename = filename;
    
    // 2. Chamar o método save() que já faz o trabalho
    save();
  }

  /**
   * Loads the previously serialized application's state as set it as the current library.
   *
   * @param filename name of the file containing the serialized application's state
   *        to load.
   * @throws UnavailableFileException if the specified file does not exist or there is
   *         an error while processing this file.
   **/
  public void load(String filename) throws UnavailableFileException {
    try (ObjectInputStream ois = new ObjectInputStream(new FileInputStream(filename))) {
        // 1. Ler o objeto do arquivo e fazer cast para Library
        _library = (Library) ois.readObject();
        
        // 2. Guardar o filename para futuras operações save()
        _filename = filename;
        
    } catch (IOException | ClassNotFoundException e) {
        // 3. Se algo correr mal, lançar a exceção apropriada
        throw new UnavailableFileException(filename);
    }
  }

  /**
   * Read text input file and initializes the current library (which should be empty)
   * with the domain entities representeed in the import file.
   *
   * @param datafile name of the text input file
   * @throws ImportFileException if some error happens during the processing of the
   * import file.
   **/
  public void importFile(String datafile) throws ImportFileException {
    try {
      if (datafile != null && !datafile.isEmpty())
        _library.importFile(datafile);
    } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(datafile, e);
    }
  } 
}
