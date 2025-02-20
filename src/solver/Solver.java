package solver;
import java.util.List;
import java.util.Set;
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
        
        for (int y = Board.getHeight() - 1; y >= 0; y--) {
            for (int x = 0; x < Board.getWidth(); x++) {
                Piece rotatedPiece = piece;

                for (int rotation = 0; rotation < 4; rotation++) { // Coba semua rotasi
                    iterationCount++;
                    if (canPlacePiece(rotatedPiece, x, y)) {
                        Board.placePiece(rotatedPiece, x, y);
                        System.out.println("Placed piece " + (pieceIndex + 1) + " at (" + x + ", " + y + ") with rotation " + rotation);
                        Board.printBoard();
                        if (solve(pieceIndex + 1)) {
                            return true;
                        }
                        
                        Board.removePiece(rotatedPiece, x, y); // Backtrack
                        System.out.println("Remove piece " + (pieceIndex + 1) + " at (" + x + ", " + y + ")");
                    }
                    rotatedPiece = rotatedPiece.rotate90Clockwise();
                }
            }
        }

        return false; // No valid placement found
    }

    public static boolean canPlacePiece(Piece piece, int initX, int initY){
        Set<Coordinate> pieceCoordinate = piece.getPieceShape();
        for (Coordinate coordinate : pieceCoordinate) {
            int x = initX + coordinate.getX();
            int y = (Board.getHeight() - 1 - (initY + coordinate.getY()));
            int boardWidth = Board.getHeight();
            int boardHeight = Board.getHeight();
            if (x<0 || x>= boardWidth || y < 0 || y >= boardHeight){
                return false;
            }
            if(!Board.isEmpty(x, y)){
                return false;
            }
            // System.out.println("Can place piece at X:" + x + "Y:" + y);
        }
        return true;
    }
}