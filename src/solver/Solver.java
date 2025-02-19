package solver;
import java.util.List;
import java.util.Set;
import component.*;

public class Solver{
    private Board board;
    private List<Piece> pieces;

    public Solver(Board board, List<Piece> pieces){
        this.board = board;
        this.pieces = pieces;
    }

    public static boolean canPlacePiece(Board board, Piece piece, int initX, int initY){
        Set<Coordinate> pieceCoordinate = piece.getPieceShape();
        for (Coordinate coordinate : pieceCoordinate) {
            int x = initX + coordinate.getX();
            int y = initY + coordinate.getY();
            int boardWidth = board.getWidth();
            int boardHeight = board.getHeight();
            if (x<0 || x>= boardWidth || y < 0 || y >= boardHeight){
                return false;
            }
            if(!board.isEmpty(x, y)){
                return false;
            }
        }
        return true;
    }

}