// package EGCI221-Project1;
import java.util.*;
import org.jgrapht.*;

class BombPlacement {
    int row;
    int column;

    public BombPlacement(int row, int column) {
        this.row = row;
        this.column = column;
    }
}

class Board {
    private int N;
    private char[][] board;
    private ArrayList<BombPlacement> bombList;

    public Board(int N) { // Constructor
        this.N = N;
        this.board = new char[N][N]; 
        this.bombList = new ArrayList<>();
        assignBoard();
    }

    public void assignBoard() {
        for (int i=0; i<N; i++) {
            for (int j=0; j<N; j++) {
                board[i][j] = ' ';
            }
        }
    }

    public void addBomb(int row, int column) {
        bombList.add(new BombPlacement(row, column));
    }

    public boolean checkBomb(int row, int column) {    // row 2 col 1
        for (BombPlacement bomb : bombList) {       // Manual input check
            //if (bomb.row == row || bomb.column == column || Math.abs(bomb.row-row) == Math.abs(q.column-column)) return false;
        }
        return true;
    }

    /*public void solve() {
        findSolution(0);                                        // Display total solution
        if (totalSolution>0) System.out.printf("There are %d possible solutions.\n\n", totalSolution);
        else System.out.println("No solution.");
    }

    public void findSolution(int row) {
        if (row == N) {
            totalSolution++;
            if (totalSolution==1) { 
                displayBoard();
                System.out.println("Calculating...");
            }
        }

        if (!manualCheck(row)) findSolution(row+1); // Check Manual to skip row
        for (int col=0; col<N; col++) {
            if (queenCheck(row, col)) {     // Check Queen backtracking
                pushQueen(row, col);
                findSolution(row+1);        // Proceed to next row
                popQueen(row, col);
            }
        }
    }*/

    public void displayBoard() {
        for (int i=0; i<N; i++) {
            if (i == 0) System.out.printf("%s", "Cell IDs");
            else System.out.printf("%8s", ' ');
            for (int j=0; j<N; j++) {
                System.out.printf("%4d: %c", j+(i*N)+1, board[i][j]);
                //if (board[i][j]!=' ') System.out.printf("%c", board[i][j]);
            }
            System.out.println();
        }
    }
}

public class Main {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);

        System.out.println("-----------  N-Queen NxN-Board  -----------");
        while (true) {      // Loop askInput until user want to exit program
            if (!askInput(scanner)) {
                System.out.println("Exiting the program..");
                break;
            }
            System.out.println("============================================");
        }
        scanner.close();
    }

    public static boolean askInput(Scanner scanner) {
        String input;
        int N;

        while (true) {            // Ask for N size
            System.out.println("Enter N for N*N board (N must be at least 5)");
            try {
                N = Integer.parseInt(scanner.nextLine());
                if (N>=5) break;
            } catch (NumberFormatException e) { }
            System.out.println("\nError. Please input 5 or more only.");
        }

        Board board = new Board(N);     // Create board
        board.displayBoard();           // Print empty board
        return true;
    }
}