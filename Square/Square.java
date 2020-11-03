package Square;

import Board.Board;
import GoldCoin.Goldcoin;
import Score.Score;

/**
 * 2048中每个方块的类，记录方块上的值
 */
public class Square {

    private int num;
    private int flag;
    private Score score = new Score();
    private Board board = new Board();
    private Goldcoin g = new Goldcoin();

    public Square(int aNum){
        num = aNum;
        flag = 0;
    }

    public int getNum(){
        return num;
    }

    public int getFlag(){
        return flag;
    }

    public void setFlag(){
        flag = 1;
    }

    public void cleanFlag(){
        flag = 0;
    }

}
