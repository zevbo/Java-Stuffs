import java.util.Scanner;

public class TicTacToe{
  
  private int[][] board;
  private int playerTurn =  1;
  
  TicTacToe(){board = new int[3][3];}
  
  private int overS(int[][] board,int r1,int c1,int r2,int c2,int r3,int c3){
    if (board[r1][c1] == board[r2][c2] && board[r2][c2] == board[r3][c3])
      return board[r1][c1];
    else
      return 0;}
  
  private boolean full(int[][] board){
    for (int[] row : board){
      for (int col : row){
        if (col == 0)
          return false;
      }}
    return true;
  }
  
  private int over(int[][] board){
    int[] values = new int[]{
      overS(board, 0,0, 0,1, 0,2),
      overS(board, 1,0, 1,1, 1,2),
      overS(board, 2,0, 2,1, 2,2),
      
      overS(board, 0,0, 1,0, 2,0),
      overS(board, 0,1, 1,1, 2,1),
      overS(board, 0,2, 1,2, 2,2),
      
      overS(board, 0,0, 1,1, 2,2),
      overS(board, 2,0, 1,1, 0,2)
    };
    
    for (int value : values){
      if (value != 0)
        return value;
    }
    
    return (full(board) ?  0 : -1);
  }
  
  private int[][] copyBoard(int[][] board){
    int[][] copyArr = board.clone();
    for (int i = 0; i < copyArr.length; i++)
      copyArr[i] = copyArr[i].clone();
    return copyArr;
  }
  
  private int getValue(int[][] board, int turn){
    int overVal = over(board);
    if (overVal == 0)
      return 0;
    else if (overVal == playerTurn)
      return -1;
    else if (overVal == 3 - playerTurn)
      return 1;
    else{
      int[][] values = new int[][]{{-2,-2,-2},{-2,-2,-2},{-2,-2,-2}};
      for (int r = 0; r < 3; r++){
        for (int c = 0; c < 3; c++){
          if (board[r][c] == 0){
            int[][] newBoard = copyBoard(board);
            newBoard[r][c] = turn;
            values[r][c] = getValue(newBoard,3 - turn);
          }}
      }
      int min = values[0][0];
      int max = values[0][0];
      for (int[] row : values){
        for (int col : row){
          if ((col < min || min == -2) && col != -2){
            min = col;
          }
          if (col > max){
            max = col;
        }}
      }
      if (turn == playerTurn){
        return min;
      } else {
        return max;
      }}
  }
  
  public int[] getMove(int[][] board){
    int maxVal = -2;
    int[] maxPos = new int[]{-1,-1};
    for (int r = 0; r < 3; r++){
      for (int c = 0; c < 3; c++){
        if (board[r][c] == 0){
          int[][] newBoard = copyBoard(board);
          newBoard[r][c] = 3 - playerTurn;
          int value = getValue(newBoard,playerTurn);
          System.out.println("value: " + value);
          if (value > maxVal){
            maxVal = value;
            maxPos[0] = r;
            maxPos[1] = c;
          }
        }}}
    System.out.println("moving at: " + maxPos[0] + ", " + maxPos[1]);
    return maxPos;
  }
  
  public static boolean isInt(String str)  
  {  
    try  {int d = Integer.parseInt(str);}  
    catch(NumberFormatException nfe)  
    {return false;}  
    return true;  
  }
  
  private String overMessage(int overVal){
    if (overVal == 0)
      return "The game was a draw!";
    else if (overVal == 1)
      return "X won!";
    else
      return "O won!";
  }
  
  private void printBoard(int[][] board){
    System.out.println(board[0][0] + "|" + board[0][1] + "|" + board[0][2]);
    System.out.println("-----");
    System.out.println(board[1][0] + "|" + board[1][1] + "|" + board[1][2]);
    System.out.println("-----");
    System.out.println(board[2][0] + "|" + board[2][1] + "|" + board[2][2]);
    System.out.println("");
  }

  private void playGame(int turn){
    printBoard(board);
    int overVal = over(board);
    if (overVal != -1)
      System.out.println(overMessage(overVal));
    else if (turn == playerTurn){
      Scanner s = new Scanner(System.in);
      System.out.println("What row would you like to go in?");
      String r = s.nextLine();
      System.out.println("What column would you like to go in?");
      String c = s.nextLine();
      int row;
      int col;
      if (isInt(r) && isInt(c)){
        row = Integer.parseInt(r) - 1;
        col = Integer.parseInt(c) - 1;
        if (board[row][col] == 0){
          board[row][col] = turn;
          playGame(3 - turn);
        }
        else{
          System.out.println("Row " + r + " and column " + c + " is taken. Please try again.");
          playGame(turn);
        }
      }
      else{
        System.out.println("Please make sure your answers are integers!");
        playGame(turn);
      }
    }
    else{
      int[] point = getMove(board);
      board[point[0]][point[1]] = turn;
      playGame(3 - turn);
    }
      
  }
  
  public void play(){
    playGame(1);
  }
}
