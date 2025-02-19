package main;
import io.IO;
import board.Board;
import piece.Coordinate;
import piece.Piece;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;


public class Main {
    public static void main(String[] args) throws Exception{
        // Read File
        String filepath = "C:\\Users\\thema\\Informatics\\Language\\Java\\Tucil1_13523074\\test\\1.txt";
        String[] config = IO.readConfig(filepath);
        
        // Take element
        String stringNMP = config[0];
        String boardtype = config[1];
        String stringPieces = config[2];
        
        // Convert to Matrix
        String[] arrayPieces = stringPieces.split(" ");
        List<char[][]> matrixPieces = IO.piecesToMatrix(arrayPieces);
        
        // Convert to Coordinate
        List<Piece> pieces = new ArrayList<Piece>();
        for(int i=0; i < matrixPieces.size(); i++){
            Set<Coordinate> pieceCoordinate = new HashSet<Coordinate>();
            pieceCoordinate = Piece.matrixToCoordinate(matrixPieces.get(i));
            Piece newPiece = new Piece(pieceCoordinate, new Color(9*i,9*i,9*i));
            pieces.add(i, newPiece);     
        }
        
        for (Piece piece : pieces) {
            System.out.println(piece);
        }

        String[] splitNMP = stringNMP.split(" ");
        int N = Integer.parseInt(splitNMP[0]);
        int M = Integer.parseInt(splitNMP[1]);
        int P = Integer.parseInt(splitNMP[2]);
        
        // for (int k = 0; k < matrixPieces.size(); k++) {
        //     System.out.println("Piece " + (char) ('A' + k) + ":");
        //     IO.printMatrix(matrixPieces.get(k));
        //     System.out.println();
        // }
        // System.out.println(Arrays.toString(config));
        // System.out.println(arrayPieces);
        // System.out.println(boardtype);
        // System.out.println(N);
        // System.out.println(M);
        // System.out.println(P);

        int[][] board = Board.initBoard(N, M); //Nanti Cek Boardtype 






    }
}
