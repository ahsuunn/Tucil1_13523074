package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
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

    public static boolean checkFilePath(String outputPath) throws IOException{
        File file = new File(outputPath);
        return file.exists();
    }

    public static void writeMatrixToFile(Character[][] matrix, String outputPath) throws IOException {
        // Will overwrite if there's any file with the same name, implement with file path checking
        FileWriter fileWriter = new FileWriter(outputPath);
        PrintWriter printWriter = new PrintWriter(fileWriter);

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[i].length; j++) {
                printWriter.print(matrix[i][j]); 
            }
            printWriter.println();
        }

        printWriter.close(); 
    }

    public static void saveMatrixToFile(Character[][] matrix) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String directoryPath =  "test\\output\\";
        String outputPath = "";
        outputPath = outputPath.concat(directoryPath);
        
        while (true) {
            System.out.println("Save Hasil ke file?(Y/n)");
            char save = scanner.nextLine().charAt(0);
            if (save == 'Y' || save == 'y') {
                System.out.println("Masukkan nama file e.g (out.txt): ");
                String filename = scanner.nextLine();
                outputPath = outputPath.concat(filename);

                boolean isFileExists = checkFilePath(outputPath);
                if (isFileExists){
                    System.out.println("Terdapat file dengan nama yang sama apakah anda ingin overwrite? (y/N)");
                    char overwrite = scanner.next().charAt(0);
                    if (overwrite == 'Y' || overwrite == 'y'){
                        writeMatrixToFile(matrix, outputPath);
                        System.out.println("File berhasil di simpan pada " + outputPath);
                        break;
                    }
                    else if (overwrite == 'n' || overwrite == 'N') {
                        System.out.println("File tidak disimpan\n");
                        break;
                    } else {
                        System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
                        break;
                    }
                }
                else{
                    writeMatrixToFile(matrix, outputPath);
                    System.out.println("File berhasil di simpan pada " + outputPath);
                }

                break;
            }

            else if (save == 'n' || save == 'N') {
                System.out.println("File tidak disimpan.\n");
                break; // Exit the loop without saving
            } else {
                System.out.println("Masukkan 'Y' untuk ya atau 'n' untuk tidak\n");
                break;
            }
        }
        scanner.close();
    }
}
