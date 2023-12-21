public class NoSeatMatchException extends Exception {
  public NoSeatMatchException(String message) {
    super("Lugar no disponible. " + message);
  }
}