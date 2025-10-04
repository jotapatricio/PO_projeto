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

  private List<Creator> _creators;
  private List<Work> _works;
  private List <User> users = new ArrayList<>();
  private int nextUserId = 1;
  private List<Requests> _requests;

  
  
  // FIXME define contructor(s)
  public Library() {
    _currentDate = new Date();
    _isModified = false;
  }
  
  
  // FIXME define more methods
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
  
  
  public User getUser(int id) {
    for (User u : users)
      if (u.getID() == id)
          return u;
    return null;
    }

  public List<User> getAllUsers(){
    return new ArrayList<>(users);
  }

  /**
   * Read text input file at the beginning of the program and populates the
   * the state of this library with the domain entities represented in the text file.
   * 
   * @param filename name of the text input file to process
   * @throws UnrecognizedEntryException if some entry is not correct
   * @throws IOException if there is an IO erro while processing the text file
   **/
  void importFile(String filename) throws UnrecognizedEntryException, IOException /* FIXME maybe other exceptions */  {
    //FIXME implement method
  }
}

