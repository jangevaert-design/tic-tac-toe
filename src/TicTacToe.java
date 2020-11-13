import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Random;
import java.util.Scanner;

public class TicTacToe {

  static ArrayList<Integer> playerPosition= new ArrayList<>();
  static ArrayList<Integer> cpuPosition= new ArrayList<>();

  public static void main(String[] args) {
    char[][] gameBoard = {{' ', '|', ' ', '|', ' '},
                          {'-', '+', '-', '+', '-'},
                          {' ', '|', ' ', '|', ' '},
                          {'-', '+', '-', '+', '-'},
                          {' ', '|', ' ', '|', ' '}};


    while(true) {
      Scanner scan = new Scanner(System.in);
      System.out.println("Give me a number between 1 and 9 inclusive");
      int playerPos = scan.nextInt();
      while(playerPosition.contains(playerPos) || cpuPosition.contains(playerPosition)) {
        System.out.println("Position taken. Enter a correct position.");
        playerPos = scan.nextInt();
      }

      placePiece(gameBoard, playerPos, "player");

      Random random = new Random();
      int cpuPos = random.nextInt(9) + 1;
      while(playerPosition.contains(cpuPos) || cpuPosition.contains(cpuPos)) {
        System.out.println("Position taken. Enter a correct position.");
        cpuPos = random.nextInt(9) + 1;
      }
      placePiece(gameBoard, cpuPos, "cpu");

      printGameBoard(gameBoard);

      String result = checkWinner();
      System.out.println(result);
    }

  }

  public static void printGameBoard(char[][] gameBoard) {
    for(char[] row: gameBoard) {
      for(char c: row) {
        System.out.print(c);
      }
      System.out.println();
    }
  }

  public static void placePiece(char[][] gameBoard, int pos, String user) {
    char symbol = ' ';
    if(user.equals("player")) {
      symbol = 'X';
      playerPosition.add(pos);
    } else if (user.equals("cpu")) {
      symbol = 'O';
      cpuPosition.add(pos);
    }
    switch(pos) {
      case 1:
        gameBoard[0][0] = symbol;
        break;
      case 2:
        gameBoard[0][2] = symbol;
        break;
      case 3:
        gameBoard[0][4] = symbol;
        break;
      case 4:
        gameBoard[2][0] = symbol;
        break;
      case 5:
        gameBoard[2][2] = symbol;
        break;
      case 6:
        gameBoard[2][4] = symbol;
        break;
      case 7:
        gameBoard[4][0] = symbol;
        break;
      case 8:
        gameBoard[4][2] = symbol;
        break;
      case 9:
        gameBoard[4][4] = symbol;
        break;
      default:
        break;
    }
  }

  public static String checkWinner() {
    List topRow = Arrays.asList(1, 2, 3);
    List midRow = Arrays.asList(4, 5, 6);
    List botRow = Arrays.asList(7, 8, 9);
    List leftCol = Arrays.asList(1, 4, 7);
    List midCol = Arrays.asList(2, 5, 8);
    List rightCol = Arrays.asList(3, 6, 9);
    List leftDia = Arrays.asList(1, 5, 9);
    List rightDia = Arrays.asList(3, 5, 7);

    List<List> winning = new ArrayList<List>();
    winning.add(topRow);
    winning.add(midRow);
    winning.add(botRow);
    winning.add(leftCol);
    winning.add(midCol);
    winning.add(rightCol);
    winning.add(leftDia);
    winning.add(rightDia);

    for (List l: winning) {
      if(playerPosition.contains(l)) {
        return "Congratulations, you won!";
      } else if(cpuPosition.contains(l)) {
        return "CPU wins!. Sorry.";
      } else if(playerPosition.size() + cpuPosition.size() == 9) {
        return "CAT!";
      }
    }
    return "";
  }

}
