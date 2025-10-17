package bci.core.exception;

/**
 * Class encoding reference to an invalid work id.
 */
public class NoSuchWorkException extends Exception {
  @java.io.Serial
  private static final long serialVersionUID = 202507171003L;

  /**
   * @param id unknown work id
   */
  public NoSuchWorkException(int id) {
      super("A obra " + id + " não existe.");
  }
}
