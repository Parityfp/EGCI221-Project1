// package EGCI221-Project2;

import java.util.*;
//import org.jgrapht.graph.DefaultEdge;
//import org.jgrapht.graph.SimpleGraph;

public class Main {
    //private static final int[][] knightMoves = {{2, 1}, {2, -1}, {-2, 1}, {-2, -1},     // Up / Down
    //                                            {1, 2}, {1, -2}, {-1, 2}, {-1, -2}};    // Left / Right
    static int N, knightPos;

    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        Map<Integer, Cell> board = new HashMap<>();
        
        while (true) {
            if (!askInput(board, scanner)) {
                System.out.println("Exiting the program...");
                break;
            }
            printBoard(board);
            knightToCastle(board);
        }
        scanner.close();
    }


    public static boolean askInput(Map<Integer, Cell> board, Scanner scanner) {
        int numInput;
        String[] strInput;

        // Ask 1 : Choose Board size
        System.out.println("Enter N for N*N board (N must be at least 5)");
        while (true) {
            try {
                N = Integer.parseInt(scanner.nextLine());
                if (N < 5) {
                    System.out.println("N must be at least 5");
                    continue;
                }
                break;
            } catch (NumberFormatException e) { System.out.println("Invalid input."); }
        }

        // Initialize for Empty Board
        for (int cellID=0; cellID<N*N; cellID++) board.put(cellID, new Cell(cellID/N, cellID%N, ' '));
        printBoard(board);

        // Ask 2 : Put Knight
        System.out.println("Enter Knight ID");
        while (true) {
            try {
                numInput = Integer.parseInt(scanner.nextLine());
                if (numInput < 0 || numInput > (N*N)-1) {
                    System.out.println("Input must be according to Cell IDs.");
                    continue;
                } else {
                    if (board.get(numInput).type != ' ') {
                        System.out.println("This cell is already occupied.");
                        continue;
                    }
                }
                break;
            } catch (NumberFormatException e) { System.out.println("Invalid input."); }
        }
        setCell(board, numInput, 'K');
        knightPos = numInput;

        // Ask 3 : Put Castle
        System.out.println("Enter Castle ID");
        while (true) {
            try {
                numInput = Integer.parseInt(scanner.nextLine());
                if (numInput < 0 || numInput > (N*N)-1) {
                    System.out.println("Input can only be from given Cell IDs.");
                    continue;
                } else {
                    if (board.get(numInput).type != ' ') {
                        System.out.println("This cell is already occupied.");
                        continue;
                    }
                }
                break;
            } catch (NumberFormatException e) { System.out.println("Invalid input."); }
        }
        setCell(board, numInput, 'C');

        // Ask 4 : Put Bomb
        System.out.println("Enter bomb IDs separated by comma (Invalid IDs will be ignored)");
        strInput = scanner.nextLine().split(",");
        for (String bomb : strInput) {
            try {
                int bombID = Integer.parseInt(bomb);
                if (bombID < 0 || bombID > (N*N)-1) continue;   // Ignore Invalid number input
                setCell(board, bombID, 'b');
            } catch (NumberFormatException e) { /* Ignore Error Input */ System.out.println("Error Input:" + bomb); }
        }

        printBoard(board);
        knightToCastle(board);
        return true;
    }

    public static void knightToCastle(Map<Integer, Cell> board) {
        System.out.printf("\nChecking [%s]: %s\n", knightPos, board.get(knightPos).type);
        // if ......................................
    }

    public static void setCell(Map<Integer, Cell> board, int cellID, char type) {
        if (board.get(cellID).type == ' ') {
            board.get(cellID).type = type;
        }
    }

    public static void printBoard(Map<Integer, Cell> board) {
        System.out.printf("%8s", "Cell IDs");
        for (int cellID=0; cellID<N*N; cellID++) {
            System.out.printf("%5d: %2s", cellID, board.get(cellID).type);
            if (cellID%N == N-1) System.out.printf("\n%8s", " ");
        }
        System.out.println();
        /*
        for (int row=0; row<N; row++) {
            if (row == 0) System.out.printf("%8s", "Cell IDs");
            else System.out.printf("%8s", " ");
        
            for (int col=0; col<N; col++) {
                String position = row + "," + col;
                System.out.printf("%5d: %2s", col+(row*N), board.get(position).type);
            }
            System.out.println();
        }
        */
    }
}


class Cell { // Cells
    int row, col;
    char type;

    public Cell(int row, int col, char type) {
        this.row = row;
        this.col = col;
        this.type = type;
    }
    
    @Override
    public String toString() {
        return type + "";
    }
}



/*
for (int i=0; i<N; i++) {
    if (i == 0) System.out.printf("%8s", "Cell IDs");
    else System.out.printf("%8s", " ");

    for (int j=0; j<N; j++) {
        System.out.printf("%5d", j+(i*N));
    }
    System.out.println();
}
*/