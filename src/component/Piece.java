package component;
import java.util.Set;
import java.awt.Color;
import java.util.HashSet;
// import java.util.Random;
import java.util.Map;

public class Piece{ 
    Set<Coordinate> shape;
    Character character;
    Color color;

    public Piece(Set<Coordinate> shape, Character character, Color color){
        this.shape = shape;
        this.character = character;
        this.color = color;
    }

    public Set<Coordinate> getPieceShape(){
        return shape;
    }
    public Character getPieceCharacter(){
        return character;
    }
    public Color getColor(){
        return color;
    }
    
    @Override
    public String toString() {
        return "Piece{shape=" + shape + ", char=" +  character+ ", color=" + color + "}";
    }

    public static Set<Coordinate> matrixToCoordinate(char[][] piece){
        Set<Coordinate> pieceCoordinate = new HashSet<Coordinate>();
        int height = piece.length;
        int width = piece[0].length;
        // System.out.println("height: "+ height);
        // System.out.println("width: " + width + "\n");
        int x = 0;
        int y = 0;

        for(int r = 0; r < height; r++){
            for(int c = 0; c < width; c++){
                if (piece[r][c] != '.'){
                    x = c ;
                    y = height - r - 1;
                    // System.out.println(piece[r][c]);
                    // System.out.println("x: " + x);
                    // System.out.println("y: "+ y);
                    Coordinate newCoordinate = new Coordinate(x, y);
                    pieceCoordinate.add(newCoordinate);
                }
            }
        }
        return pieceCoordinate;
    }
    
    public Piece rotate90Clockwise(){
        Set<Coordinate> rotatedShape = new HashSet<>();
        for (Coordinate coordinate : shape) {
            Coordinate rotatedCoordinate = coordinate.rotate90Clockwise();
            rotatedShape.add(rotatedCoordinate);
        }
        return new Piece(rotatedShape, character, color);
    }
    
    public Piece flipPieceHorizontal(){
        Set<Coordinate> rotatedShape = new HashSet<>();
        for (Coordinate coordinate : shape) {
            Coordinate rotatedCoordinate = coordinate.flipHorizontal();
            rotatedShape.add(rotatedCoordinate);
        }
        return new Piece(rotatedShape, character, color);
    }
    public Piece flipPieceVertical(){
        Set<Coordinate> rotatedShape = new HashSet<>();
        for (Coordinate coordinate : shape) {
            Coordinate rotatedCoordinate = coordinate.flipVertical();
            rotatedShape.add(rotatedCoordinate);
        }
        return new Piece(rotatedShape, character, color);
    }

    public Set<Coordinate> translateCoordinates(int dx, int dy){
        Set<Coordinate> newShape = new HashSet<Coordinate>();
        for(Coordinate coord : shape){
            newShape.add(new Coordinate(coord.x + dx, coord.y + dy));
        }
        return newShape;
    }

    public static final String RESET = "\u001B[0m";

    public static void printColorizedMatrix(Character[][] matrix, Map<Character, String> colorMap) {
        for (Character[] row : matrix) {
            for (Character piece : row) {
                String color = colorMap.getOrDefault(piece, RESET); 
                System.out.print(color + piece + RESET + " ");
            }
            System.out.println();
        }
    }

    public static final String[] TEXTCOLORS = {
        "\u001B[31m", 
        "\u001B[32m", 
        "\u001B[33m", 
        "\u001B[34m", 
        "\u001B[35m", 
        "\u001B[36m", 
        "\u001B[91m", 
        "\u001B[92m", 
        "\u001B[93m", 
        "\u001B[94m", 
        "\u001B[95m", 
        "\u001B[96m", 
        "\u001B[97m", 
        "\u001B[90m", 
        "\u001B[37m", 
        "\u001B[41m", 
        "\u001B[42m", 
        "\u001B[43m", 
        "\u001B[44m", 
        "\u001B[45m", 
        "\u001B[46m", 
        "\u001B[47m", 
        "\u001B[100m", 
        "\u001B[101m", 
        "\u001B[102m",
        "\u001B[30m"  
    };

}