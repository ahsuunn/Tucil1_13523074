package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Scanner;

public class IO {
    public static String[] readConfig(String filepath) throws FileNotFoundException{
        File file = new File(filepath);
        Scanner scanner = new Scanner(file);
        String config = ""; 
        String boardType = "";
        StringBuilder pieces = new StringBuilder();

        try{
            if (scanner.hasNextLine()){
                config = scanner.nextLine();
                if(scanner.hasNextLine()){
                    boardType = scanner.nextLine();
                    while(scanner.hasNextLine()){
                        String  line = scanner.nextLine();
                        pieces.append(line).append(" "); 
                    }
                    scanner.close();
                    pieces.toString().trim();
                }
                else{
                    System.err.println("Tidak terdapat board type");
                }
            }
            else{
                System.err.println("Tidak terdapat format NMP");
            }
        }
        catch (Exception e){
            System.err.println("Error: " + e);
        }
        // System.out.println(config);
        // System.out.println(boardType);

        scanner.close();
        return new String[]{config, boardType, pieces.toString()};
    }

    public static List<char[][]> piecesToMatrix(String[] pieces) {
        List<char[][]> pieceMatrices = new ArrayList<>();
        List<String> currentPiece = new ArrayList<>();

        char tempChar = 'o';

        for (String piece : pieces) {
            char currentChar = piece.charAt(0); // Ngambil char awal
            // Tambahin elemen ke list hanya kalau char uniq atau piece baru
            if (currentChar != tempChar && !currentPiece.isEmpty()) {
                pieceMatrices.add(createPieceMatrix(currentPiece));
                currentPiece.clear();
            }
            currentPiece.add(piece);
            tempChar = currentChar; // Update tempChar
        }

        if (!currentPiece.isEmpty()) {
            pieceMatrices.add(createPieceMatrix(currentPiece));
        }

        return pieceMatrices;
    }

    public static char[][] createPieceMatrix(List<String> rows) {
        int height = rows.size();
        int width = rows.stream().mapToInt(String::length).max().orElse(0);

        char[][] matrix = new char[height][width];

        for (int r = 0; r < height; r++) {
            char[] chars = rows.get(r).toCharArray();
            for (int c = 0; c < width; c++) {
                matrix[r][c] = (c < chars.length) ? chars[c] : '.'; // Kalau kosong diisi dengan titik
            }
        }
        return matrix;
    }

    public static void printMatrix(char[][] matrix) {
        for (char[] row : matrix) {
            System.out.println(Arrays.toString(row));
        }
    }
}
