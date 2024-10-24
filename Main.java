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

    public void pushQueen() {}              // **********************************************************************************

    public void popQueen() {}               // **********************************************************************************

    public boolean findSolution(int row) { return true; } // **********************************************************************************

    public void displayBoard() {
        char[][] board = new char[N][N];

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
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        String input;
        int N;


        System.out.println("-----------  N-Queen NxN-Board  -----------");
        while (true) {                  // Ask for N size
            System.out.println("Enter N for N*N board (N must be at least 4)");
            try {
                N = Integer.parseInt(scanner.nextLine());
                if (N>=4) break;
            } catch (Exception e) { }
            System.out.println("\nError. Please input 4 or more only.");
        }


        Board board = new Board(N);
        board.displayBoard();           // Print empty board


        while (true) {                  // ASk for Manual Input
            System.out.println("Manually place the First Queen? (y for yes, n for no)");
            input = scanner.nextLine().toLowerCase();
            if (input.equals("y") || input.equals("n")) break;
            System.out.println("\nError. Please enter valid input.");
        }


        if (input.equals("n")) {        // No (Let program do the work)
            board.findSolution(0);
        } else {                          // Yes (Input manually)
            int[] place = new int[2];
            while (true) {
                System.out.print("Enter row: ");
                try {
                    place[0] = Integer.parseInt(scanner.nextLine());
                    if (place[0]<N+1) break;
                } catch (Exception e) { }
                System.out.printf("\nError. Please enter no more than %d.\n", N+1);
            }
            while (true) {
                System.out.print("Enter column: ");
                try {
                    place[1] = Integer.parseInt(scanner.nextLine());
                    if (place[1]<N+1) break;
                } catch (Exception e) { }
                System.out.printf("\nError. Please enter no more than %d.\n", N+1);
            }
            // ********************************************
        }
        scanner.close();
    }
}