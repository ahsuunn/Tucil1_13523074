package board;

public class Board{
    public static void fillBoard(int[][] board, int val){
        int n = board.length;
        int m = board[0].length;

        for(int i = 0; i < n; i++){
            for(int j = 0; i < m; j++){
                board[i][j] = val;
            }
        }
    }

    public static int[][] initBoard(int n, int m){
        int[][] board = new int[n][m];

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                board[i][j] = 0;
            }
        }

        return board;
    }
}