package bci.core.exception;

/**
 * Class encoding reference to an invalid user id.
 */
public class NoSuchUserException extends Exception {

  @java.io.Serial
  private static final long serialVersionUID = 202501091828L;

  /**
   * @param id unknown user id
   */
  public NoSuchUserException(int id) {
    super("O utente " + id + " não existe.");
  }
}
