// Introduced in Chapter 2
/* Jordan, James, & Logan
 * AI 440
 * Dr. M&M */
/** The game of Domineering. */
public class Domineering {

  /** For reading from the console. */
  public static final java.util.Scanner INPUT
    = new java.util.Scanner(System.in);

  /** The player who plays their dominoes horizontally. */
  public static final boolean HORIZONTAL = false;

  /** The player who plays their dominoes vertically. */
  public static final boolean VERTICAL = true;
 
  /** Array of board squares, true if occupied. */
  private boolean[][] squares;

  /** The board is initially empty. */
  public Domineering() 
  {
    squares = new boolean[8][8];
    // Java initializes all array elements to false
  }

  /**
   * Return true if there is a legal move for the specified player.
   */
  public boolean hasLegalMoveFor(boolean player) 
  {
    int rowOffset = 0;
    int columnOffset = 0;
    if (player == HORIZONTAL) 
    {
        columnOffset = 1;
    } 
    else 
    {
        rowOffset = 1;
    }
    for (int row = 0; row < (8 - rowOffset); row++) 
    {
        for (int column = 0; column < (8 - columnOffset); column++) 
        {
            if (!(squares[row][column] || squares[row + rowOffset][column + columnOffset])) 
            {
                return true;
            }
        }
    }
    return false;
  }
 
  /** Play until someone wins. */
  public void play() 
  {
    //boolean player = HORIZONTAL; //original code
      boolean player = choosePlay();
      boolean valid, legal;
      int row = 0;
      int column = 0;
      VerticalAgent_007 agent = null;
    while (true) 
    {
    	 System.out.println("\n" + this);
         if (player == HORIZONTAL) 
         {
             System.out.println("Horizontal to play");
         } 
         else 
         {
             System.out.println("Vertical AI thinking");
             agent = new VerticalAgent_007(squares);
             agent.playBestMove();
         }
         if(player == HORIZONTAL){
        do    
        {
           
            if (!(hasLegalMoveFor(player))) 
            {
                System.out.println("No legal moves -- you lose!");
                return;
            }
            System.out.print("Row: ");
            //for non-number row check
            if(INPUT.hasNextInt())
            {
                row = INPUT.nextInt();
                System.out.print("Column: ");
                //for non-number column check
                if(INPUT.hasNextInt())
                {
                    column = INPUT.nextInt();
                    valid = isValid(row, column, player);
                }
                else
                {
                    valid = false;
                    System.out.println("\nThat is not a valid input, you must enter a numeric value.");
                    INPUT.next();
                }    
            }
            else
            {
                valid = false;
                System.out.println("\nThat is not a valid input, you must enter a numeric value.");
                INPUT.next();

            }
            
            if(valid)
            {
                legal = isLegal(row, column, player);
            }
            else
            {
                legal = false;
            }    
        } while(!valid || !legal);    //check if in bounds or if not legal move
         }
        if(player == VERTICAL)
        {
        	row = agent.getBestRow();
        	column = agent.getBestColumn();
        }
        playAt(row, column, player);    
        player = !player;
    }
  }

  /**
   * Play a domino with its upper left corner at row, column.
   */
  public void playAt(int row, int column, boolean player) 
  {
    squares[row][column] = true;
    if (player == HORIZONTAL) 
    {
        squares[row][column + 1] = true;
    } 
    else 
    {
        squares[row + 1][column] = true;
    }
  }

  public String toString() 
  {
    String result = "  0 1 2 3 4 5 6 7";
    for (int row = 0; row < 8; row++) 
    {
        result += "\n" + row;
        for (int column = 0; column < 8; column++) 
        {
            if (squares[row][column]) 
            {
                result += " #";
            }     
            else 
            {
                result += " .";
            }
        }
    }
    return result;
  }
 
  ///////////////////////////////////////////////////////////////////////
  /*
   * End of original methods
   * The choose play and valid methods are new
   *
   *////////////////////////////////////////////////////////////////////
 
  public boolean choosePlay()  //allows the user to choose if they want to play vertical or horizontal
  {
      System.out.print("enter 'V' to play vertical or 'H' to play horizontal: ");
      String direction = INPUT.next();
      if (direction.equals("h") || direction.equals("H"))
      {
          return false;
      }
      else if(direction.equals("v") || direction.equals("V"))
      {
          return true;
      }
      else
      {
          System.out.print("\nThat is not a valid input, you have been assigned horizontal");
      }
      return false; //don't actually end with this, include a reselect instead
  }
 
  public boolean isValid(int a, int b, boolean HORIZONTAL) // makes sure that the entered move is on the board
  {
      boolean allowed = true;
      if(HORIZONTAL)
      {
          if(a < 0 || a > 6 || b < 0 || b > 7)
          {
              allowed = false;
              System.out.println("\nLocation out of bounds.\nTry again.");
          }
      }
      else
      {
          if(a < 0 || a > 7 || b < 0 || b > 6)
          {
              allowed = false;
              System.out.println("\nLocation out of bounds.\nTry again.");
          }
      }
      return allowed;
      
  }
  
  public boolean isLegal(int a, int b, boolean HORIZONTAL) // makes sure that the entered move does not overlap
  {
      boolean legal = true;
      if(HORIZONTAL)
      {
          if(squares[a][b] || squares[a+1][b])
          {
              legal = false;
              System.out.println("\nLocation overlaps a previously placed domino.\nTry again.");
          }
      }
      else
      {
          if(squares[a][b] || squares[a][b+1])
          {
              legal = false;
              System.out.println("\nLocation overlaps a previously placed domino.\nTry again.");
          }
      }
      return legal;
      
  }
  
  public boolean[][] getSquares()
  {
      return squares;
  }
 
  /** Create and play the game. */
  public static void main(String[] args) 
  {
    System.out.println("Welcome to Domineering.");
    Domineering game = new Domineering();
    game.play();
  }

}
