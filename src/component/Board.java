package component;

import java.util.Arrays;
import java.util.Set;

public class Board{
    private static Character[][] board;
    private static int width;
    private static int height;

    public Board(int n, int m) {
        height = n;
        width = m;
        board = new Character[n][m];
        fillBoard(board, 'o'); // Fill board with default character
    }

    public static Character[][] getBoard() {
        return board;
    }
    public static int getWidth() {
        return width;
    }
    public static int getHeight() {
        return height;
    }

    public static Coordinate findFirstEmptyCol() {
        for (int y = 0; y < getHeight(); y++) {
            for (int x = 0; x < getWidth(); x++) {
                if (isEmpty(x, y)) {
                    return new Coordinate(x, y); // Return the first row that contains an empty space
                }
            }
        }
        return new Coordinate(0, 0); // If no empty space is found
    }

    public static boolean isEmpty(int x, int y){
            return board[y][x] == 'o';
    }
    
    public static boolean isFull(){
        for (Character[] characters : board) {
            for (Character character : characters) {
                if(character == 'o'){
                    return false;
                }
            }
        }
        return true;
    }

    public static void fillBoard(Character[][] board, Character val){
        int n = board.length;
        int m = board[0].length;

        for(int i = 0; i < n; i++){
            for(int j = 0; j < m; j++){
                board[i][j] = val;
            }
        }
    }
    
    public static void placePiece(Piece piece, int startX, int startY) {        
        Set<Coordinate> pieceCoordinate = piece.getPieceShape();
        for (Coordinate coord : pieceCoordinate) {
            int x = startX + coord.getX();
            int y = (Board.getHeight() - 1 - (startY + coord.getY()));
            board[y][x] = piece.character;
        }
    }
    
    public static void removePiece(Piece piece, int startX, int startY) {
        Set<Coordinate> pieceCoordinate = piece.getPieceShape();
        for (Coordinate coord : pieceCoordinate) {
            int x = startX + coord.getX();
            int y = (Board.getHeight() - 1 - (startY + coord.getY())) ;
            board[y][x] = 'o';
        }
    }

    public static void printBoard(){
        for (Character[] row : board) {
            System.out.println(Arrays.toString(row));
        }
    }
}