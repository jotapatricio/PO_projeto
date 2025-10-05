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
  public void registerBook(int id,int copies,String title, int preco, Category category){
        Work work = new Work(id,title,copies,category,preco, new ArrayList<Creator>());
        _works.add(work);
    }
  public void registerDVD(int id,int copies,String title, int preco, Category category){
        Work work = new Work(id,title,copies,category,preco, new ArrayList<Creator>());
        _works.add(work);
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
  void importFile(String filename) throws UnrecognizedEntryException, IOException {
  try (BufferedReader reader = new BufferedReader(new FileReader(filename))) {
    String line;
    while ((line = reader.readLine()) != null) {
      String[] parts = line.split(":");
      if (parts.length == 0) continue;
      String entryType = parts[0].trim();

      try {
        switch (entryType) {
          case "USER": {
            if (parts.length < 3) throw new UnrecognizedEntryException(line);
            String userName = parts[1].trim();
            String userEmail = parts[2].trim();
            User user = new User(nextUserId++, userName, userEmail);
            users.add(user);
            break;
          }

          case "BOOK": {
            // Expect: BOOK:title:authors(comma-separated):price:category:isbn:copies
            if (parts.length < 7) throw new UnrecognizedEntryException(line);
            String bookTitle = parts[1].trim();
            String authorsStr = parts[2].trim();
            int bookPreco = Integer.parseInt(parts[3].trim());
            Category bookCategory = Category.valueOf(parts[4].trim());
            String isbn = parts[5].trim();
            int bookCopies = Integer.parseInt(parts[6].trim());

            List<Creator> creators = new ArrayList<>();
            for (String a : authorsStr.split(",")) {
              String name = a.trim();
              if (!name.isEmpty()) creators.add(new Creator(name));
            }
            Book book = new Book(_nextWorkId++, bookTitle, bookCopies, bookCategory, bookPreco, isbn, creators);
            _works.add(book);
            break;
          }

          case "DVD": {
            // Expect: DVD:title:director:price:category:igac:copies
            if (parts.length < 7) throw new UnrecognizedEntryException(line);
            String title = parts[1].trim();
            String directorStr = parts[2].trim();
            int preco = Integer.parseInt(parts[3].trim());
            Category category = Category.valueOf(parts[4].trim());
            int igac = Integer.parseInt(parts[5].trim());
            int copies = Integer.parseInt(parts[6].trim());

            List<Creator> directors = new ArrayList<>();
            if (!directorStr.isEmpty()) directors.add(new Creator(directorStr));
            DVD dvd = new DVD(_nextWorkId++, title, copies, category, preco, igac, directors);
            _works.add(dvd);
            break;
          }

          default:
            throw new UnrecognizedEntryException(entryType);
        }
        _isModified = true;
        } catch (IllegalArgumentException e) {
          throw new UnrecognizedEntryException(line, e);
        }
    }
  } catch (FileNotFoundException e) {
    throw new IOException(e);
  } catch (IOException e) {
    throw new IOException(e);
  }
  }

}

