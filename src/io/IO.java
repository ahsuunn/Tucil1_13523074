package io;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.FileWriter;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Scanner;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import javax.imageio.ImageIO;
import component.*;


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
                        pieces.append(line).append("\n"); 
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

        Character tempChar = 'o';

        for (String piece : pieces) {
            Character currentChar = null;
            boolean foundChar = false;
            int i = 0; 

            while(!foundChar && i < piece.length()){
                currentChar = piece.charAt(i);
                if(Character.isWhitespace(currentChar)){
                    i++;
                }
                else{
                    foundChar = true;
                }
            }

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
                Character tempChar = matrix[r][c];
                if(Character.isWhitespace(tempChar)){
                    matrix[r][c] = '.';
                }
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

    public static void saveMatrixToFile(Character[][] matrix, List<Piece> pieces) throws IOException{
        Scanner scanner = new Scanner(System.in);
        String directoryPath =  "test\\output\\";
        String outputPath = "";
        outputPath = outputPath.concat(directoryPath);
        
        while (true) {
            System.out.println("Save Hasil ke file?(Y/n)");
            char save = scanner.nextLine().charAt(0);
            if (save == 'Y' || save == 'y') {
                System.out.println("Masukkan nama file e.g (output): ");
                String filename = scanner.nextLine();
                outputPath = outputPath.concat(filename);
                String outputPathTXT = outputPath.concat(".txt");
                String outputPathPNG = outputPath.concat(".png");
                System.out.println(outputPathPNG);

                boolean isFileExists = checkFilePath(outputPath);
                if (isFileExists){
                    System.out.println("Terdapat file dengan nama yang sama apakah anda ingin overwrite? (y/N)");
                    char overwrite = scanner.next().charAt(0);
                    if (overwrite == 'Y' || overwrite == 'y'){
                        writeMatrixToFile(matrix, outputPathTXT);
                        matrixToImage(matrix, pieces, outputPathPNG);
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

    
    public static void matrixToImage(Character[][] board, List<Piece> pieces, String outputPath) {
        int cellSize = 50;
        int width = board[0].length * cellSize;
        int height = board.length * cellSize;

        BufferedImage image = new BufferedImage(width, height, BufferedImage.TYPE_INT_RGB);
        Graphics2D g = image.createGraphics();
        
        // background color
        g.setFont(new Font("SansSerif", Font.PLAIN, 30));
        g.setColor(Color.WHITE);
        g.fillRect(0, 0, width, height);
        g.rotate(Math.toRadians(180), width / 2.0, height / 2.0); // Rotate 180deg

        for (int row = 0; row < board.length; row++) {
            for (int col = 0; col < board[row].length; col++) {
                char ch = board[row][col];
                for (Piece piece : pieces) {
                    if(piece.getPieceCharacter() == ch){
                        Color pieceColor = piece.getColor();
                        g.setColor(pieceColor);
                    }
                }
                int xRect = col * cellSize;
                int yRect = height - (row + 1) * cellSize;
                g.fillRoundRect(xRect, yRect, cellSize, cellSize, 15, 15 );
                g.setColor(Color.WHITE);
                g.drawRect(xRect, yRect, cellSize, cellSize);
                
                // int xChar = col * cellSize + 15;
                // int yChar = (row + 1) * cellSize - 10;
                // g.drawString(String.valueOf(ch), xChar, yChar);

            }
        }
        g.dispose(); 

        try {
            File output = new File(outputPath);
            System.out.println(outputPath);
            ImageIO.write(image, "png", output);
            System.out.println("Image saved: " + output.getAbsolutePath());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static final Color[] colors = {
        new Color(255, 87, 51),   
        new Color(46, 204, 113),  
        new Color(41, 128, 185),  
        new Color(52, 152, 219),  
        new Color(39, 174, 96),   
        new Color(241, 196, 15),  
        new Color(230, 126, 34),  
        new Color(192, 57, 43),   
        new Color(155, 89, 182),  
        new Color(142, 68, 173),  
        new Color(211, 84, 0),    
        new Color(189, 195, 199), 
        new Color(22, 160, 133),  
        new Color(231, 76, 60),   
        new Color(243, 156, 18),  
        new Color(255, 153, 51),  
        new Color(127, 140, 141), 
        new Color(44, 62, 80),    
        new Color(102, 204, 255), 
        new Color(153, 51, 255),  
        new Color(204, 255, 102), 
        new Color(255, 102, 102),
        new Color(255, 102, 204), 
        new Color(102, 102, 255), 
        new Color(0, 255, 204),   
        new Color(255, 255, 102), 
    };
}


