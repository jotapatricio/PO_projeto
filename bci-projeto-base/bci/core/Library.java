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

  
  Library() {
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
  
  void registerUser(String name, String email){
        User user = new User(nextUserId, name, email);
        users.add(user);
        nextUserId++;
        _isModified = true;
    }
  
  User getUser(int id) {
    for (User u : users)
      if (u.getID() == id)
          return u;
    return null;
    }

  List<User> getAllUsers() {
    List<User> sortedUsers = new ArrayList<>(users);

    sortedUsers.sort(
        Comparator.comparing(User::getName, String.CASE_INSENSITIVE_ORDER).thenComparingInt(User::getID));

    return Collections.unmodifiableList(sortedUsers);
  }

  void registerBook(String title, int copies, Category category, int preco, String isbn, List<Creator> autor){
    int newId = _nextWorkId++;
    List <Creator> creatorsList = new ArrayList<>(autor);
    Work book = new Book(newId, title, copies, category, preco, isbn, creatorsList);
    _works.add(book);
    _isModified = true;
  }

  void registerDVD(String title, int copies, Category category, int preco, int igac, List<Creator> diretor){
    int newId = _nextWorkId++;
    Work dvd = new DVD(newId, title, copies, category, preco, igac, diretor);
    _works.add(dvd);
    _isModified = true;
  }

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
  
  Creator getCreator(String name){
    for (Creator c : _creators)
      if (c.getName().equals(name))
          return c;
    return null;
    }
  
  Work getWork(int id) {
    for (Work w : _works) {
        if (w.getId() == id) {
            return w;
        }
    }
    return null; // ou lança exceção aqui se preferires
  }

  List <Work> getAllWorks() {
    List<Work> list = new ArrayList<>(_works);
    list.sort(Comparator.comparingInt(Work::getId));
    return Collections.unmodifiableList(list);
  }

  public boolean getIsModified() {
    return _isModified;
  }

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