package main;
import io.IO;
import board.Board;
import piece.Coordinate;
import java.util.Arrays;
import java.util.List;


public class Main {
    public static void main(String[] args) throws Exception{
        String filepath = "C:\\Users\\thema\\Informatics\\Language\\Java\\Tucil1_13523074\\test\\1.txt";
        String[] config = IO.readConfig(filepath);
        String stringNMP = config[0];
        String boardtype = config[1];
        String pieces = config[2];
        String[] arrayPieces = pieces.split(" ");
        List<char[][]> matrixPieces = IO.piecesToMatrix(arrayPieces);
        // for (int k = 0; k < matrixPieces.size(); k++) {
        //     System.out.println("Piece " + (char) ('A' + k) + ":");
        //     IO.printMatrix(matrixPieces.get(k));
        //     System.out.println();
        // }

        String[] splitNMP = stringNMP.split(" ");
        int N = Integer.parseInt(splitNMP[0]);
        int M = Integer.parseInt(splitNMP[1]);
        int P = Integer.parseInt(splitNMP[2]);

        // System.out.println(Arrays.toString(config));
        // System.out.println(arrayPieces);
        // System.out.println(boardtype);
        // System.out.println(N);
        // System.out.println(M);
        // System.out.println(P);

        int[][] board = Board.initBoard(N, M); //Nanti Cek Boardtype 






    }
}
