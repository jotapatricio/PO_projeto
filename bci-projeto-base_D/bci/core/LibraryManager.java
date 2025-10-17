package bci.core;

import bci.core.exception.*;
import java.io.*;
import java.util.*;

/**
 * The façade class. Represents the manager of this application. It manages the current
 * library and works as the interface between the core and user interaction layers.
 */
public class LibraryManager {

  private Library _library;
  private String _filename;

  /**
  * Construtor do LibraryManager.
  */

  public LibraryManager() {
    _library = new Library();
    _filename = null;
  }

  /**
  * Retorna o nome do arquivo atualmente associado ao estado da biblioteca.
  */

  public String getCurrentFileName(){
    return _filename;
  }

  /**
  * Obtém a data atual do sistema.
  * @return A data atual.
  */

  public int getCurrentDate() {
    return _library.getCurrentDate().getCurrentDate();
  }

  /**
  * Avança o tempo de simulação por um número de dias.
  * @param days Número de dias a avançar.
  * @return A nova data.
  */
  public int advanceDays(int days) {
    return _library.advanceDays(days).getCurrentDate();
  }

  /**
  * Registra um novo usuário.
  */

  public void registerUser(String name, String email){
        _library.registerUser(name, email);
  }

  /**
  * Procura um usuário pelo ID.
  */

  public User getUserById(int id) throws NoSuchUserException{
      return _library.getUser(id);
  }

  /**
  * Retorna a lista ordenada e não modificável de todos os usuários.
  */

  public List <User> getAllUsers() {
      return _library.getAllUsers();
  }
  
  /**
  * Busca uma obra pelo ID.
  * @param id O ID da obra.
  * @return A Work encontrada.
  * @throws NoSuchWorkException Se a obra não existir.
  */

  public Work getWork(int id) throws NoSuchWorkException {
    Work work = _library.getWork(id); // chama o método da Library
    if (work == null) {
        throw new NoSuchWorkException(id); // exceção definida no projeto
    }
    return work;
  }

  /**
  * Retorna uma coleção não modificável de todas as obras.
  */

  public Collection <Work> getAllWorks() {
    return Collections.unmodifiableCollection(_library.getAllWorks());
  }

  /**
  * Busca um criador pelo nome.
  */

  public Creator getCreator(String name) {
        return _library.getCreator(name);
    }
  
  /**
  * Verifica se o estado da Library foi modificado desde o último save/load.
  */

  public boolean isModified() {
    return _library.getIsModified();
  }

  /**
  * Reseta o flag de modificação para false (usado após salvar ou carregar).
  */

  public void setUnmodified() {
    _library.makeSetUnmodified();
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

    //Criar um ObjectOutputStream para escrever o objeto
    try (ObjectOutputStream oos = new ObjectOutputStream(new FileOutputStream(_filename))) {
        //Escrever o objeto _library para o arquivo
        oos.writeObject(_library);
    }
    setUnmodified();
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
    //Guardar o novo filename
    _filename = filename;
    
    //Chamar o método save() que já faz o trabalho
    save();
    setUnmodified();
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
        //Ler o objeto do arquivo e fazer cast para Library
        _library = (Library) ois.readObject();
        
        //Guardar o filename para futuras operações save()
        _filename = filename;
        setUnmodified();
        
    } catch (IOException | ClassNotFoundException e) {
        //Se algo correr mal, lançar a exceção apropriada
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
      //Verifica se o nome do arquivo é válido.
      if (datafile != null && !datafile.isEmpty())
        _library.importFile(datafile);
    } catch (IOException | UnrecognizedEntryException /* FIXME maybe other exceptions */ e) {
      throw new ImportFileException(datafile, e);
    }
  } 
}