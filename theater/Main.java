import java.util.InputMismatchException;
import static java.lang.System.out;
import static java.lang.System.err;
import java.util.Scanner;

public class Main {
  static Scanner in = new Scanner(System.in);
  private static final int EXIT = 4;

  public static void main(String ...args) {
    String[] movies = new String[]{"El Conjuro 3", "Mortal Kombat", "Raya y el Último dragón"};
    Theater cinema = new Theater("Cinemas Xeva", movies);
    int currentOption = 0;

    while(true) {

      do {
          currentOption = cinema.showMenu();
          clearScreen();
      }while(currentOption < 1 || currentOption > 5);

      if(currentOption == EXIT)  
        break;
      
      cinema.selectTickets(currentOption - 1);
    }

    out.println("Excelente día! ");
  }

  private static void clearScreen() {
    out.print("\033[H\033[2J");  
    out.flush();  
  }
}