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
    private ArrayList<QueenPlacement> queenList;
    //public int manualInput;

    public Board(int N) {                                           // Constructor
        this.N = N;
        this.totalSolution = 0;
        this.queenList = new ArrayList<>();
    }

    public void pushQueen(int row, int column) {
        queenList.add(new QueenPlacement(row, column));
    }

    public void popQueen(int row, int column) {
        if (!queenList.isEmpty()) {
            queenList.remove(queenList.size() - 1);
        }
    }

    public boolean queenCheck(int row, int column) {
        for (QueenPlacement q : queenList) {
            if (q.row == row || q.column == column || Math.abs(q.row-row) == Math.abs(q.column-column))
                return false;
        }
        return true;
    }
    /*public boolean queenRowCheck(int row, int column) {
        for (QueenPlacement q : queenList) {
            if (q.row == row)
                return true;
        }
        return false;
    }*/

    public void solve() {
        findSolution(0);                                        // Display total solution
        if (totalSolution>0) System.out.printf("There are %d possible solutions.\n", totalSolution);
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
        //if (row == this.manualInput-1) return;
        for (int col=0; col<N; col++) {
            if (queenCheck(row, col)) {
                pushQueen(row, col);
                findSolution(row+1);        // Proceed to next row
                popQueen(row, col);
            }
        }
    }

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


        if (input.equals("n")) {            // No (Let program do the work)
            board.solve();
        } else {                            // Yes (Input manually)
            int row, col;
            while (true) {
                System.out.print("Enter row: ");
                try {
                    row = Integer.parseInt(scanner.nextLine())-1;
                    if (row<N+1) break;
                } catch (Exception e) { }
                System.out.printf("\nError. Please enter no more than %d.\n", N);
            }
            while (true) {
                System.out.print("Enter column: ");
                try {
                    col = Integer.parseInt(scanner.nextLine())-1;
                    if (col<N+1) break;
                } catch (Exception e) { }
                System.out.printf("\nError. Please enter no more than %d.\n", N);
            }
            board.pushQueen(row, col); // board.manualInput = row;
            board.displayBoard();
            board.solve(); // ***************************************************
        }
        scanner.close();
    }
}