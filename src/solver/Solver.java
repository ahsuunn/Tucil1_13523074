package solver;
import java.util.List;
import java.util.Set;
import component.Piece;
import component.*;

public class Solver{
    private static List<Piece> pieces;
    static int iterationCount = 0;

    public static void setPieces(List<Piece> pieceList) {
        pieces = pieceList;
    }

    public static int getIterationCount(){
        return iterationCount;
    }

    public static boolean solve(int pieceIndex) {
        if (pieceIndex >= pieces.size()) {
            return Board.isFull(); // Kalau index sudah melebihi sizenya cek apakah boardnya sudah terisi penuh
        }

        Piece piece = pieces.get(pieceIndex);

        Coordinate firstEmptyCol = Board.findFirstEmptyCol();

        for (int y = Board.getHeight() - firstEmptyCol.getY() - 1; y >= 0; y--) {
            for (int x = firstEmptyCol.getX(); x < Board.getWidth(); x++) {
                for(int flip = 0; flip < 3; flip++){
                    Piece flipedPiece = piece;

                    if(flip == 1){
                        flipedPiece = flipedPiece.flipPieceHorizontal();
                    }else if (flip==2){
                        flipedPiece = flipedPiece.flipPieceVertical();
                    }

                    Piece rotatedPiece = flipedPiece;
                    for (int rotation = 0; rotation < 4; rotation++) { // Coba semua rotasi
                        iterationCount++;
                        if (canPlacePiece(rotatedPiece, x, y)) {
                            Board.placePiece(rotatedPiece, x, y);
                            if (solve(pieceIndex + 1)) {
                                return true;
                            }
                            
                            Board.removePiece(rotatedPiece, x, y); // Backtrack
                        }
                        rotatedPiece = rotatedPiece.rotate90Clockwise();
                    }
                }
            }
        }

        return false;
    }

    public static boolean canPlacePiece(Piece piece, int initX, int initY){
        Set<Coordinate> pieceCoordinate = piece.getPieceShape();
        for (Coordinate coordinate : pieceCoordinate) {
            int x = initX + coordinate.getX();
            int y = (Board.getHeight() - 1 - (initY + coordinate.getY()));
            int boardWidth = Board.getWidth();
            int boardHeight = Board.getHeight();
            if (x<0 || x>= boardWidth || y < 0 || y >= boardHeight){
                return false;
            }
            if(!Board.isEmpty(x, y)){
                return false;
            }
        }
        return true;
    }

    public static boolean checkTotalPieceAgainstBoardSize(){
        int count = 0;
        for (Piece piece : pieces) {
            Set<Coordinate> tempCoordinate = piece.getPieceShape();
            for (@SuppressWarnings("unused") Coordinate Coordinate : tempCoordinate) {
                count++;
            }
        }
        int boardsize = Board.getWidth() * Board.getHeight(); 
        if((boardsize) == count){
            return true;
        }
        else{
            return false;
        }
    }
}