public class NoSuchSeatNumberException extends Exception {
  public NoSuchSeatNumberException(String message) {
    super("ERROR: lugar no existente. " + message);
  }
}