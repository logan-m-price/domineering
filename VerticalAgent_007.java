//****************************
// Jordan, James, & Logan
// AI 440
// Domineering
// Dr. M&M
//****************************
public class VerticalAgent_007
{
    private boolean[][]sq;
    private int scoreCount;
    private boolean player;
    private int bestColumn;
    private int bestRow;
            
    public VerticalAgent_007(boolean[][] board)
    {
        sq = board;
        scoreCount = 0;
        player = true;     //vert = true & hori = false
        bestRow = -1;
        bestColumn = -1;
    }
    
    public void playBestMove()
    {
        int score;
        int bestScore = -1000000000;
      
            if (gameOver(player))
            {
              return;
            }
        for (int row = 0; row < 7; row++)
        {
          for (int col = 0; col < 8; col++)
          {    
              //sq = getSquares();
             //System.out.println("row: " + row +"\ncol: "+ col +"  |||"+ sq[row][col] + "||||" + sq[row+1][col]);
            if (sq[row][col] == false && sq[row+1][col] == false)
            {
              sq[row][col] = true;
              sq[row+1][col] = true;
              score = horiCount(0);
              if (score > bestScore)
              {
                bestScore = score;
                bestRow = row;
                bestColumn = col;
              }
              sq[row][col] = false;
              sq[row+1][col] = false;
            }
            System.out.println(bestScore);
          }
        }
        //sq[bestRow][bestColumn] = true;
        //sq[bestRow+1][bestColumn] = true;
       // int count = 1;
       // System.out.println(count);
       // count++;
       // playAt(bestRow,bestColumn, player);
    }
    
    public int vertCount(int iteration)
    {
        player = true;        //sets player to vert
        int score = score(player);
        if(gameOver(player) || iteration > 4)
        {
            return score;
        }
        int bestScore = -1000000000;
        for (int row = 0; row < 7; row++)
        {
            for (int col = 0; col < 8; col++)
            {
                if (sq[row][col] == false && sq[row+1][col] == false)
                {
                    sq[row][col] = true;
                    sq[row+1][col] = true;
                    iteration++;
                    score = horiCount(iteration);
                    if (score > bestScore)
                    {
                      bestScore = score;
                    }
                    sq[row][col] = false;
                    sq[row+1][col] = false;
                }
            }
        }
        
        return bestScore;
    }
    public int horiCount(int iteration)
    {    
        player = false;        //sets player to hori
        int score = score(false);
        if(gameOver(player) || iteration > 4)
        {
            return score;
        }
        int bestScore = 1000000000;
        for (int row = 0; row < 8; row++)
        {
            for (int col = 0; col < 7; col++)
            {
                if (sq[row][col] == false && sq[row][col+1] == false)
                {
                    sq[row][col] = true;
                    sq[row][col+1] = true;
                    iteration++;
                    score = vertCount(iteration);
                    if (score < bestScore)
                    {
                      bestScore = score;
                    }
                    sq[row][col] = false;
                    sq[row][col+1] = false;
                  }
            }
        }
        return bestScore;
    }
    
    public int score(boolean player)
    {
       scoreCount = 0;
    	if(player == true)    //vertical scoring
        {
            for (int row = 0; row < 7; row++)
            {
                for (int col = 0; col < 8; col++)
                {
                    if (sq[row][col] == false && sq[row+1][col] == false)
                    {
                        scoreCount++;
                        //System.out.println("V: " +scoreCount);
                    }
                }
            }
        }
        else                //horizontal scoring
        {
            for (int row = 0; row < 8; row++)
            {
                for (int col = 0; col < 7; col++)
                {
                    if (sq[row][col] == false && sq[row][col+1] == false)
                    {
                        scoreCount--;
                        //System.out.println("H: " +scoreCount);
                    }
                }
            }
        }
        return scoreCount;
    }
    
    public boolean gameOver(boolean player)
      {
        int rowOffset = 0;
        int columnOffset = 0;
        boolean over = true;
        if (player == false)
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
                if (!sq[row][column] && !sq[row + rowOffset][column + columnOffset])
                {
                    return false; 
                }
            }
        }
        return true;
      }
    
    public int getBestRow()
    {
    	return bestRow;
    }
    
    public int getBestColumn()
    {
    	return bestColumn;
    }
}
