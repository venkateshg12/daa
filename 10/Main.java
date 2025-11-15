public class Main {

    static int N = 4; 

    static boolean isSafe(int board[][], int row, int col) {

        // check same row on left
        for (int i = 0; i < col; i++)
            if (board[row][i] == 1)
                return false;

        // check upper-left diagonal
        for (int i = row, j = col; i >= 0 && j >= 0; i--, j--)
            if (board[i][j] == 1)
                return false;

        // check lower-left diagonal
        for (int i = row, j = col; j >= 0 && i < N; i++, j--)
            if (board[i][j] == 1)
                return false;

        return true;
    }

    static boolean solveNQ(int board[][], int col) {
        if (col == N)  
            return true;

        for (int row = 0; row < N; row++) {

            if (isSafe(board, row, col)) {
                board[row][col] = 1;   

                if (solveNQ(board, col + 1))  
                    return true;

                board[row][col] = 0;   
            }
        }
        return false;
    }

    public static void main(String[] args) {
        int[][] board = new int[N][N];

        if (solveNQ(board, 0)) {
            System.out.println("Solution:");
            for (int[] row : board) {
                for (int cell : row)
                    System.out.print(cell + " ");
                System.out.println();
            }
        } else {
            System.out.println("No solution exists");
        }
    }
}
