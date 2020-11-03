package Board;

import Square.Square;

public class Position {

    private final int x;
    private final int y;
    private Square square;

    public Position(int aX, int aY){
        x = aX;
        y = aY;
    }

    public void setSquare(Square aSquare){
        square = aSquare;
    }

    public int getX(){
        return x;
    }

    public int getY(){
        return y;
    }

    public Square getSquare(){
        return square;
    }

    public void removeSquare(){
        square = null;
    }

}
