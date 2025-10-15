package bci.core;

import java.io.*;
import bci.core.exception.UnrecognizedEntryException;
import java.util.*;
// FIXME import classes

/**
 * Class that represents the library as a whole.
 */
class Library implements Serializable {

  /** Serial number for serialization. */
  @Serial
  private static final long serialVersionUID = 202501101348L;

  // FIXME define attributes
  private Date _currentDate;
  private boolean _isModified;
  private List <Creator> _creators = new ArrayList<>();
  private List <Work> _works = new ArrayList<>();
  private int _nextWorkId = 1;
  private List <User> users = new ArrayList<>();
  private int nextUserId = 1;
  private List <Requests> _requests = new ArrayList<>();

  /**
  * Construtor da classe Library.
  */

  Library() {
    _currentDate = new Date();
    _isModified = false;
  }
  
  /**
  * Retorna a data atual do sistema.
  * @return A data atual.
  */
  Date getCurrentDate() {
    _currentDate.getCurrentDate();
    return _currentDate;
  }

  /**
  * Avança o tempo de simulação por um certo número de dias.
  * @param days O número de dias a avançar.
  * @return A nova data atual.
  */

  Date advanceDays(int days) {
    _currentDate.advanceDays(days);
    _isModified = true;
    return _currentDate;
  }
  
  /**
  * Registra um novo usuário (utente).
  * @param name Nome do usuário.
  * @param email Email do usuário.
  */
  void registerUser(String name, String email){
        User user = new User(nextUserId, name, email);
        users.add(user);
        nextUserId++;
        _isModified = true;
    }
  
  /**
  * Procura e devolve um usuário pelo seu ID.
  * @param id O ID do usuário a procurar.
  * @return O objeto User encontrado, ou null se não existir.
  */

  User getUser(int id) {
    for (User u : users)
      if (u.getID() == id)
          return u;
    return null;
    }
  
  /**
  * Retorna uma lista de todos os usuários, ordenada para apresentação.
  * @return Lista não modificável de usuários ordenados.
  */

  List<User> getAllUsers() {
    List<User> sortedUsers = new ArrayList<>(users);
    //Define a ordenação: primária por nome, secundária por ID.
    sortedUsers.sort(
        Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER).thenComparingInt(User::getID));
    //lista ordenada, protegida contra modificação externa.
    return Collections.unmodifiableList(sortedUsers);
  }

  /**
  * Regista um novo Livro na biblioteca.
  */

  void registerBook(String title, int copies, Category category, int preco, String isbn, List<Creator> autor){
    int newId = _nextWorkId++;
    List <Creator> creatorsList = new ArrayList<>(autor);
    Work book = new Book(newId, title, copies, category, preco, isbn, creatorsList);
    _works.add(book);
    _isModified = true;
  }

  /**
  * Regista um novo DVD na biblioteca.
  */

  void registerDVD(String title, int copies, Category category, int preco, int igac, List<Creator> diretor){
    int newId = _nextWorkId++;
    Work dvd = new DVD(newId, title, copies, category, preco, igac, diretor);
    _works.add(dvd);
    _isModified = true;
  }

  /**
  * Registra um criador (autor/diretor). Se já existir, retorna o existente (get-or-create).
  * @param name Nome do criador.
  * @return O objeto Creator, novo ou existente.
  */

  Creator registerCreator(String name){
        Creator existing = getCreator(name);
        if (existing != null) {
            return existing;
        }
        Creator creator = new Creator(name);
        _creators.add(creator);
        _isModified = true;
        return creator;
    }
  
  /**
  * Busca e retorna um criador pelo nome exato.
  * @param name O nome do criador.
  * @return O objeto Creator encontrado, ou null.
  */

  Creator getCreator(String name){
    for (Creator c : _creators)
      if (c.getName().equals(name))
          return c;
    return null;
    }
  
  /**
  * Busca e retorna uma obra pelo seu ID.
  * @param id O ID da obra.
  * @return O objeto Work encontrado, ou null.
  */

  Work getWork(int id) {
    for (Work w : _works) {
        if (w.getId() == id) {
            return w;
        }
    }
    return null; // ou lança exceção aqui se preferires
  }

  /**
  * Retorna uma lista de todas as obras, ordenada pelo ID.
  * @return Lista não modificável de obras ordenadas.
  */

  List <Work> getAllWorks() {
    List<Work> list = new ArrayList<>(_works);
    //Ordena a cópia pelo ID.
    list.sort(Comparator.comparingInt(Work::getId));
    return Collections.unmodifiableList(list);
  }

  /**
  * Getter para o flag de modificação.
  * @return true se o estado da biblioteca foi modificado, false caso contrário.
  */

  public boolean getIsModified() {
    return _isModified;
  }

  /**
  * Reseta o flag de modificação para false (usado após salvar o estado).
  */
  public void makeSetUnmodified() {
    _isModified = false;
  }

  /**
   * Read text input file at the beginning of the program and populates the
   * the state of this library with the domain entities represented in the text file.
   * 
   * @param filename name of the text input file to process
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  
   void importFile(String filename) throws UnrecognizedEntryException, IOException {
        MyParser parser = new MyParser(this);
        parser.parseFile(filename);
    }
}

