import java.util.InputMismatchException;
import static java.lang.System.out;
import static java.lang.System.err;
import java.util.Scanner;

public class Cinema {
  private int seatsNumber;
  private int columns;
  private int rows;
  private int occupedSeatsCounter;
  private int[] occupedSeats;
  private int[][] cinema;
  private String movie;
  private int roomNumber;
  private Scanner in;
  
  public Cinema(int seatsNumber, int rows, String movie, int roomNumber) {
    this.seatsNumber = seatsNumber;
    this.rows = rows;
    this.movie = movie;
    this.roomNumber = roomNumber;
    occupedSeatsCounter = 0;
    occupedSeats = new int[seatsNumber];
    cinema = new int[rows][seatsNumber / rows];
    columns = cinema[0].length;
    in = new Scanner(System.in);

    fillSeats();
  }

  public void showCinemaRoom() {
    out.println("Sala " + roomNumber);
    out.println("-1, 0: no displonible.");

    for(int i = 0; i < rows; ++i) {
      out.print("| ");
      for (int j = 0; j < columns; j++)
        out.print(cinema[i][j]  + "" + (checkNumber(cinema[i][j]) <= 1 ? "  " : ' '));
      out.print('|');
      out.println();
      out.println();
    }
  }

  void fillSeats() {
    int seatCounter = 0, auxiliarCounter;
    int rows = cinema.length, columns = cinema[0].length;

    for(int i = 0; i < rows; ++i) {
      auxiliarCounter = i % 2 == 0 ? 0 : 2;
      
      for (int j = 0; j < columns; j++) {
        seatCounter++;
        
        if(auxiliarCounter == 0 || auxiliarCounter == 3) { 
          cinema[i][j] = 0;
          occupedSeats[occupedSeatsCounter++] = seatCounter;
          
          if(auxiliarCounter == 3) auxiliarCounter = -1;
        }

        else cinema[i][j] = seatCounter;
        auxiliarCounter++;
      }
    }
  }

  public boolean searchIfOccuped(int seat) {
    int mid, left = 0, right = occupedSeatsCounter - 1;
    boolean found = false;    
    
    while(left <= right && !found) {
      mid = (left + right) / 2;
      
      if(seat == occupedSeats[mid])
        found = true;

      else if(seat < occupedSeats[mid])
        right = mid - 1;
      else left = mid + 1;
    }

    return found;
  }

  public void markSeatAsOccuped(int ticketSeat) {
    int auxiliar = ticketSeat - 1;
    int auxiliarIndex = occupedSeatsCounter++ - 1;
    
    cinema[auxiliar / columns][auxiliar % columns] = -1;

    while(occupedSeats[auxiliarIndex] > ticketSeat) {
      occupedSeats[auxiliarIndex + 1] = occupedSeats[auxiliarIndex];

      auxiliarIndex--;
    }

    occupedSeats[auxiliarIndex + 1] = ticketSeat;
  }

  public void choiceSeats(int[] tickets) {
    boolean redo = true;

    while(redo) {
      try {
        for(int i = 0; i < tickets.length; i++) {
          out.print("Ingrese el numero del asiento: ");
          tickets[i] = in.nextInt();
          
          if(searchIfOccuped(tickets[i])) 
            throw new NoSeatMatchException("Seleccione otra opción"); 
          else markSeatAsOccuped(tickets[i]);
        }
        
        redo = false;
      } catch (NoSeatMatchException e) {
        err.println(e);
      }
    }

  }

  public void enterTicketsNumber() throws InputMismatchException {
    int ticketsAmount = 0;
    boolean redo = true;
    int[] tickets;
    
    while(redo) {
      ticketsAmount = in.nextInt();
      out.println("Cuántos tickets? ");

      try {
        if(ticketsAmount <= 0 || ticketsAmount > seatsNumber)
          throw new NoSuchSeatNumberException("Intente de nuevo.");
        
        tickets = new int[ticketsAmount];
        choiceSeats(tickets);

        redo = false;
        showCinemaRoom();
      } catch(NoSuchSeatNumberException e) {
        err.println(e);
      }
    }
  }

  public void showOccupedSeats() {
    for (int occuped : occupedSeats) 
      out.print(occuped + " ");
    out.print(occupedSeats[occupedSeatsCounter - 1]);
  }

  private int checkNumber(int seat) {
    return (int)Math.ceil(Math.log10(seat));
  }

  @Override
  public String toString() {
    return movie;
  }
}