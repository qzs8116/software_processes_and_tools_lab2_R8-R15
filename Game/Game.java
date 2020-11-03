package Game;

import Action.Action;
import Board.*;
import Square.Square;

import java.util.Random;

public class Game {

    private Board board;
    private Action action;

    public Game(){
        board = new Board();
        action = new Action();
    }

    public Board getBoard() {
        return board;
    }

    /**
     * 将所有方块都向上移动
     */
    public boolean upSquare(){
        flagClearZero();
        int count = 0;
        for (int i = 0; i < 4; i++){
            for(int j = 2; j >= 0; j--){
                for(int k = 1; k <= 3-j; k++){
                    if(board.getPosition(i, j+k).getSquare() != null){
                        if(!action.moveSquare(board.getPosition(i, j), board.getPosition(i, j+k))) {
                            if (k - 1 != 0) {
                                if (action.moveSquare(board.getPosition(i, j), board.getPosition(i, j + k - 1))) {
                                    count++;
                                }
                            }
                        }
                        else{
                            count++;
                        }
                        break;
                    }
                    else {
                        if(k == 3-j)
                            if(action.moveSquare(board.getPosition(i, j), board.getPosition(i, j+k)))
                                count++;
                    }
                }
            }
        }
        return count != 0;
    }

    /**
     * 将所有方块都向下移动
     */
    public boolean downSquare(){
        flagClearZero();
        int count = 0;
        for(int i = 0; i < 4; i++){
            for(int j = 1; j < 4; j++){
                for (int k = 1; k <= j; k++){
                    if(board.getPosition(i, j-k).getSquare() != null) {
                        if(!action.moveSquare(board.getPosition(i, j), board.getPosition(i, j-k))) {
                            if (k - 1 != 0) {
                                if (action.moveSquare(board.getPosition(i, j), board.getPosition(i, j - k + 1))) {
                                    count++;
                                }
                            }
                        }
                        else {
                            count++;
                        }
                        break;
                    }
                    else {
                        if(k == j)
                            if(action.moveSquare(board.getPosition(i, j), board.getPosition(i, j-k)))
                                count++;
                    }
                }
            }
        }
        return count != 0;
    }

    /**
     * 将所有方块都向左移动
     */
    public boolean leftSquare(){
        flagClearZero();
        int count = 0;
        for(int j = 0; j < 4; j++){
            for(int i = 1; i < 4; i++){
                for(int k = 1; k <= i; k++) {
                    if(board.getPosition(i-k, j).getSquare() != null) {
                        if(!action.moveSquare(board.getPosition(i, j), board.getPosition(i-k, j))) {
                            if (k - 1 != 0) {
                                if(action.moveSquare(board.getPosition(i, j), board.getPosition(i - k + 1, j))){
                                    count++;
                                }
                            }
                        }
                        else {
                            count++;
                        }
                        break;
                    }
                    else {
                        if(k == i)
                            if(action.moveSquare(board.getPosition(i, j), board.getPosition(i-k, j)))
                                count++;
                    }
                }
            }
        }
        return count != 0;
    }

    /**
     * 将所有方块都向右移动
     */
    public boolean rightSquare(){
        flagClearZero();
        int count = 0;
        for(int j = 0; j < 4; j++){
            for(int i = 2; i >= 0; i--){
                for(int k = 1; k <= 3-i; k++) {
                    if(board.getPosition(i+k, j).getSquare() != null){
                        if(!action.moveSquare(board.getPosition(i, j), board.getPosition(i+k, j))) {
                            if (k - 1 != 0) {
                                if(action.moveSquare(board.getPosition(i, j), board.getPosition(i + k - 1, j))){
                                    count++;
                                }
                            }
                        }
                        else {
                            count++;
                        }
                        break;
                    }
                    else {
                        if(k == 3-i)
                            if(action.moveSquare(board.getPosition(i, j), board.getPosition(i+k, j)))
                                count++;
                    }

                }
            }
        }
        return count != 0;
    }

    /**
     * 随机位置生成随机数
     */
    public void refreshSquare(){
        int num = 0;
        Random rand = new Random();
        int temp = rand.nextInt(95)%6;
        if(temp == 0){
            num = 4;
        }
        else{
            num = 2;
        }
        Square square = new Square(num);
        while(true){
            int row = rand.nextInt(99)%4;
            int col = rand.nextInt(99)%4;
            if(board.getPosition(row, col).getSquare() == null){
                board.getPosition(row, col).setSquare(square);
                break;
            }
        }
    }

    /**
     * 检测游戏是否Game Over
     * @return  如果结束返回true，否则false
     */
    public boolean checkGameOver(){
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(board.getPosition(i ,j).getSquare() == null)
                    return false;
            }
        }
        for(int i = 0; i < 4; i++){
            for(int j = 0; j < 4; j++){
                if(checkAroundSquareSame(board.getPosition(i, j)))
                    return false;
            }
        }
        return true;
    }

    /**
     * 将Square中的标志清零
     */
    public void flagClearZero(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                if(board.getPosition(i, j).getSquare() != null){
                    board.getPosition(i, j).getSquare().cleanFlag();
                }
            }
        }
    }

    /***
     * 检查某个位置周围是否有方块的数字相同
     * @param position  位置信息
     * @return  若果有返回true，否则false
     */
    public boolean checkAroundSquareSame(Position position){
        int x = position.getX();
        int y = position.getY();
        int num = position.getSquare().getNum();
        if(x == 0){
            if(y == 0){
                if (num == board.getPosition(x, y+1).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x+1, y).getSquare().getNum())
                    return true;
            }
            else if (y == 3){
                if (num == board.getPosition(x, y-1).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x+1, y).getSquare().getNum())
                    return true;
            }
            else {
                if (num == board.getPosition(x, y+1).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x, y-1).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x+1, y).getSquare().getNum())
                    return true;
            }
        }
        else if (x == 3){
            if(y == 0){
                if (num == board.getPosition(x, y+1).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x-1, y).getSquare().getNum())
                    return true;
            }
            else if (y == 3){
                if (num == board.getPosition(x, y-1).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x-1, y).getSquare().getNum())
                    return true;
            }
            else {
                if (num == board.getPosition(x, y+1).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x, y-1).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x-1, y).getSquare().getNum())
                    return true;
            }
        }
        else {
            if (y == 0){
                if (num == board.getPosition(x, y+1).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x-1, y).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x+1, y).getSquare().getNum())
                    return true;
            }
            else if (y == 3){
                if (num == board.getPosition(x, y-1).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x+1, y).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x-1, y).getSquare().getNum())
                    return true;
            }
            else {
                if (num == board.getPosition(x, y+1).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x, y-1).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x+1, y).getSquare().getNum())
                    return true;
                if (num == board.getPosition(x-1, y).getSquare().getNum())
                    return true;
            }
        }
        return false;
    }

}
