package main;
import io.IO;
import solver.Solver;

import java.awt.Color;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import component.Board;
import component.Coordinate;
import component.Piece;


public class Main {
    public static void main(String[] args) throws Exception{
        // Read File
        String inputPath = "test\\input\\";
        String filepath = "1.txt";
        inputPath = inputPath.concat(filepath);
        System.out.println(inputPath);
        String[] config = IO.readConfig(inputPath);
        
        // Take element
        String stringNMP = config[0];
        // String boardtype = config[1];
        String stringPieces = config[2];
        System.out.println(stringPieces);
        
        // Convert to Matrix
        String[] arrayPieces = stringPieces.split("\n");
        // System.out.println(Arrays.toString(arrayPieces));
        List<char[][]> matrixPieces = IO.piecesToMatrix(arrayPieces);
        for (int i = 0; i < matrixPieces.size(); i++) {
            IO.printMatrix(matrixPieces.get(i));
            System.out.println();
        }

        // Convert to Coordinate
        List<Piece> pieces = new ArrayList<Piece>();
        for(int i=0; i < matrixPieces.size(); i++){
            Set<Coordinate> pieceCoordinate = new HashSet<Coordinate>();
            
            boolean foundChar = false;
            Character character = null;
            int j = 0;
            while(!foundChar){
                character = matrixPieces.get(i)[0][j];
                if(character == '.'){
                    j++;
                }
                else{
                    foundChar = true;
                }
            } 
            character = matrixPieces.get(i)[0][j];
            System.out.println(character);

            pieceCoordinate = Piece.matrixToCoordinate(matrixPieces.get(i));
            System.out.println("Character:"+ character + ", Coordinate:" + pieceCoordinate);
            Color color = IO.colors[i];
            Piece newPiece = new Piece(pieceCoordinate, character, color);
            pieces.add(i, newPiece);     
        }
        
        for (Piece piece : pieces) {
            System.out.println(piece);
        }

        // Setup Board
        String[] splitNMP = stringNMP.split(" ");
        int N = Integer.parseInt(splitNMP[0]);
        int M = Integer.parseInt(splitNMP[1]);
        // int P = Integer.parseInt(splitNMP[2]);
        new Board(N, M); //Nanti Cek Boardtype 

        // Start Timer
        long startTime = System.nanoTime();

        // Solver
        Solver.setPieces(pieces);
        if(Solver.solve(0)){
            System.out.println("Finished one solution found!");
            Board.printBoard();
        }else{
            System.out.println("Didn't found any solution");
        }

        // Finish Timer        
        long endTime = System.nanoTime();
        long executionTime
            = (endTime - startTime) / 1000000;
        System.out.println("Execution time: " + executionTime + "ms");
        System.out.println("Iteration : " + Solver.getIterationCount());
        

        // Output File
        Character[][] solutionMatrix = Board.getBoard();
        while(true){
            try {
                IO.saveMatrixToFile(solutionMatrix, pieces);
                break;
            } catch (Exception e) {
                System.err.println(e);
            }
        }
    }
}
