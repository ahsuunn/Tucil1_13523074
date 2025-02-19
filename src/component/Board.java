package component;

import java.util.Set;

public class Board{
    private Character[][] board;
    private int width;
    private int height;

    public Board(int n, int m) {
        this.width = m;
        this.height = n;
        this.board = new Character[n][m];
        fillBoard(board, 'o'); // Fill board with default character
    }

    public Character[][] getBoard() {
        return board;
    }
    public int getWidth() {
        return width;
    }
    public int getHeight() {
        return height;
    }

    public boolean isEmpty(int x, int y){
            return board[x][y] == 'o';
    }

    public static void fillBoard(Character[][] board, Character val){
        int n = board.length;
        int m = board[0].length;

        for(int i = 0; i < n; i++){
            for(int j = 0; i < m; j++){
                board[i][j] = val;
            }
        }
    }

    public void placePiece(Piece piece, int startX, int startY, char pieceId) {        
        Set<Coordinate> pieceCoordinate = piece.getPieceShape();
        for (Coordinate coord : pieceCoordinate) {
            int x = startX + coord.getX();
            int y = startY + coord.getY();
            board[y][x] = pieceId;
        }
    }
    
    public void removePiece(Piece piece, int startX, int startY) {
        Set<Coordinate> pieceCoordinate = piece.getPieceShape();
        for (Coordinate coord : pieceCoordinate) {
            int x = startX + coord.getX();
            int y = startY + coord.getY();
            board[y][x] = 'o';
        }
    }
}