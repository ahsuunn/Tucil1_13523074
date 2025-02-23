package main;
import io.IO;
import solver.Solver;

import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.util.Set;

import component.Board;
import component.Coordinate;
import component.Piece;


public class Main {
    public static void main(String[] args) throws Exception{
        // Read File
        String inputPath = "test\\input\\";
        System.out.println("Masukkan nama file dari folder input (1.txt)");

        try(Scanner scanner = new Scanner(System.in);){
            String filePath = scanner.nextLine();
            inputPath = inputPath.concat(filePath);
            String[] config = IO.readConfig(inputPath);
            
            // Take element
            String stringNMP = config[0];
            // String boardtype = config[1];
            String stringPieces = config[2];
            // System.out.println(stringPieces);
            
            // Convert to Matrix
            String[] arrayPieces = stringPieces.split("\n");
            List<char[][]> matrixPieces = IO.piecesToMatrix(arrayPieces);
    
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
    
                pieceCoordinate = Piece.matrixToCoordinate(matrixPieces.get(i));
                Color color = IO.COLORS[i];
                Piece newPiece = new Piece(pieceCoordinate, character, color);
                pieces.add(i, newPiece);     
            }
            
            // Setup Board
            String[] splitNMP = stringNMP.split(" ");
            int N = Integer.parseInt(splitNMP[0]);
            int M = Integer.parseInt(splitNMP[1]);
            // int P = Integer.parseInt(splitNMP[2]);
            new Board(N, M); 
            Solver.setPieces(pieces);
            
            if(Solver.checkTotalPieceAgainstBoardSize()){
            }
            else{
                System.out.println("Unsolvable, Matrix piece is not equal to the board size");
                System.exit(0);
            }
            
            System.out.println("Searching for the solution, this may take a while...");
            // Start Timer
            long startTime = System.nanoTime();
                // Solver
            if(Solver.solve(0)){
                System.out.println("Finished one solution found!");
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
            Map<Character, String> colorMap = new HashMap<>();
            for (char c = 'A'; c <= 'Z'; c++) {
                colorMap.put(c, IO.TEXTCOLORS[c - 'A']);
            }

            Piece.printColorizedMatrix(solutionMatrix, colorMap);

            while(true){
                try {
                    IO.saveMatrixToFile(solutionMatrix, pieces);
                    break;
                } catch (Exception e) {
                    System.err.println(e);
                }
            }
            
        }
        catch(Exception e){
            System.err.println(e);
        }

    }
}
