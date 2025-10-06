package bci.core;

import java.io.*;
import bci.core.exception.UnrecognizedEntryException;
import bci.app.exception.UserRegistrationFailedException;
import java.util.*;
// FIXME import classes

/**
 * Class that represents the library as a whole.
 */
public class Library implements Serializable {

  /** Serial number for serialization. */
  @Serial
  private static final long serialVersionUID = 202501101348L;

  // FIXME define attributes
  private Date _currentDate;
  private boolean _isModified;

  private List<Creator> _creators = new ArrayList<>();
  private List<Work> _works = new ArrayList<>();
  private int _nextWorkId = 1;
  private List <User> users = new ArrayList<>();
  private int nextUserId = 1;
  private List<Requests> _requests = new ArrayList<>();

  
  public Library() {
    _currentDate = new Date();
    _isModified = false;
  }
  

  Date getCurrentDate() {
    _currentDate.getCurrentDate();
    return _currentDate;
  }

  Date advanceDays(int days) {
    _currentDate.advanceDays(days);
    _isModified = true;
    
    return _currentDate;
  }
  
  public void registerUser(String name, String email){
        User user = new User(nextUserId++, name, email);
        users.add(user);
    }
  public void registerBook(String title, int copies, Category category, int preco, String isbn, List<Creator> diretor){
    int newId = _nextWorkId++;
    
    Work book = new Book(newId ,title,copies,category,preco,isbn,new ArrayList<Creator>());

    _works.add(book);
    }
  public void registerDVD(String title, int copies, Category category, int preco, int igac, List<Creator> diretor){
    // 1. Gerar o ID aqui
    int newId = _nextWorkId++;
    
    // 2. Criar o objeto DVD (assumindo que o seu construtor de DVD aceita estes argumentos)
    Work dvd = new DVD(newId, title, copies, category, preco, igac, diretor);
    
    // 3. Adicionar à lista
    _works.add(dvd);
    }
  public Creator registerCreator(String name){
        Creator existing = getCreator(name);
        if (existing != null) {
            return existing;
        }
        Creator creator = new Creator(name);
        _creators.add(creator);
        return creator;
    }
  
  public Creator getCreator(String name){
    for (Creator c : _creators)
      if (c.getName().equals(name))
          return c;
    return null;
    }
  
  public User getUser(int id) {
    for (User u : users)
      if (u.getID() == id)
          return u;
    return null;
    }

  public List<User> getAllUsers(){
    return new ArrayList<>(users);
  }
  
  public Work getWork(int id) {
    for (Work w : _works) {
        if (w.getId() == id) {
            return w;
        }
    }
    return null; // ou lança exceção aqui se preferires
  }

  public List<Work> getAllWorks() {
    List<Work> list = new ArrayList<>(_works);
    list.sort(Comparator.comparingInt(Work::getId));
    return list;
  }

  /**
   * Read text input file at the beginning of the program and populates the
   * the state of this library with the domain entities represented in the text file.
   * 
   * @param filename name of the text input file to process
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  public void importFile(String filename) throws UnrecognizedEntryException, IOException {
        MyParser parser = new MyParser(this);
        parser.parseFile(filename);
        _isModified = true;
    }

}

