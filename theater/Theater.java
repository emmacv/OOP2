import static java.lang.System.out;
import static java.lang.System.err;

import java.util.InputMismatchException;
import java.util.Scanner;

public class Theater {
  private String name;
  public Cinema[] rooms;
  Scanner in;
  
  public Theater(String name, String ...movies) {
    this.name = name;
    rooms = new Cinema[3];
    in = new Scanner(System.in);

    for (int i = 0; i < movies.length; i++) {
      int numberRoom = i + 1;

      rooms[i] = new Cinema(22 * (numberRoom + 1), 4, movies[i], numberRoom);
    }
  }

  public int showMenu() throws InputMismatchException {
    int option = 0;
    boolean redo = true;

    do {
      try {
        int i = 0;
        out.println(this);
        out.println("Peliculas en cartelera: ");
        
        for (; i < rooms.length; i++)
          out.println("[ " + (i + 1) + " ] " + rooms[i]);
        out.println("[ " + (i + 1) + " ] Salir");
          
        out.print("Opcion: ");
        if(in.hasNextInt())
          in.nextInt();
          
        redo = false;
      } catch (Exception e) {
        err.println("Error: número no válido. Intente de nuevo");
        in.nextLine();
      }
    }while(redo);
      
    return option;
  }

  public void selectTickets(int roomNumber) {
    try{

      rooms[roomNumber].enterTicketsNumber();
    } catch(InputMismatchException e) {
      err.println(e);
      e.printStackTrace();
    }
  }

  @Override
  public String toString() {
    return "----------< " + name + " >----------";
  }
}