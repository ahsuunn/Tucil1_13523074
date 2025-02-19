package component;

public class Coordinate {
    int x,y;

    public Coordinate(int x, int y){
        this.x = x ;
        this.y = y;
    }

    public int getX(){
        return x;
    }
    public int getY(){
        return y;
    }

    public Coordinate rotate90Clockwise(){
        return new Coordinate(y, -x);
    }
    public Coordinate rotate90CounterClockwise(){
        return new Coordinate(-y, x);
    }

    @Override
    public boolean equals(Object obj){
        if(this == obj){
            return true;
        }
        else if(!(obj instanceof Coordinate)){
            return false;
        }
        else{
            Coordinate c = (Coordinate) obj;
            return this.x == c.x && this.y == c.y;
        }
    }

    @Override
    public int hashCode(){
        return 31 * x + y;
    }

    @Override
    public String toString(){
        return "(" + x + ", " + y + ")";
    }

}   
