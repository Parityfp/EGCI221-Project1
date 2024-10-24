// package EGCI221-Project1;

import java.util.*;

class QueenPlacement {
    int row;
    int column;

    public QueenPlacement(int row, int column) {
        this.row = row;
        this.column = column;
    }

}

class Board {
    private int N;
    private int totalSolution;
    private boolean showSolution;
    private ArrayList<QueenPlacement> queenList;

    public Board(int N) {                                           // Constructor
        this.N = N;
        this.totalSolution = 0;
        this.showSolution = false;
        this.queenList = new ArrayList<>();
    }

    public void displayBoard() {
        char board[][] = new char[N][N];

        for (int i=0; i<N; i++) {                                   // Fills board with '.' as empty space
            for (int j=0; j<N; j++) {
                board[i][j] = '.';
            }
        }

        for (QueenPlacement q: queenList) {                         // Assign Queen
            board[q.row][q.column] = 'Q';
        }

        System.out.printf("%10s", "");                              // Display board (only up-to N=99)
        for (int i=0; i<N; i++) System.out.printf("%-3d", i+1);
        for (int i=0; i<N; i++) {
            System.out.printf("\nrow %-2d |", i+1);
            for (int j=0; j<N; j++) {
                System.out.printf("%3c", board[i][j]);
            }
        }
        System.out.print("\n==========");
        for (int i=0; i<N; i++) System.out.print("===");
        System.out.println("");

        if (showSolution) {                                         // Display total solution
            if (totalSolution>0) System.out.printf("There are %d possible solutions.\n", totalSolution);
            else System.out.println("No solution.");
        }
    }
}

public class Main {
    public static void main(String args[]) {
        Scanner scanner = new Scanner(System.in);
        String input;
        int N;

        System.out.println("-----------  N-Queen NxN-Board  -----------");
        System.out.println("Enter N for N*N board (N must be at least 4)");
        while (true) {                  // Ask for N size
            try {
                N = Integer.parseInt(scanner.nextLine());
                if (N>3) break;
            } catch (Exception e) { }
            System.out.println("Error. Please enter valid input.");
        }
        Board board = new Board(N);
        board.displayBoard();           // Print empty board

        System.out.println("\nManually place the First Queen? (y for yes, n for no)");
        while (true) {                  // ASk for Manual Input
            input = scanner.nextLine().toLowerCase();
            if (input.equals("y") || input.equals("n")) break;
            System.out.println("Error. Please enter valid input.");
        }
        scanner.close();
        /*if (input.equals("n")) {        // No
             *******************
        }
        else {                          // Yes
             *******************
        }*/
    }
}