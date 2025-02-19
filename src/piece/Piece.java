package piece;
import java.util.Set;
import java.awt.Color;
import java.util.HashSet;

public class Piece{ 
    Set<Coordinate> shape;
    Color color;

    public Piece(Set<Coordinate> shape, Color color){
        this.shape = shape;
        this.color = color;
    }

    public Set<Coordinate> translateCoordinates(int dx, int dy){
        Set<Coordinate> newShape = new HashSet<Coordinate>();
        for(Coordinate coord : shape){
            newShape.add(new Coordinate(coord.x + dx, coord.y + dy));
        }
        return newShape;
    }
}