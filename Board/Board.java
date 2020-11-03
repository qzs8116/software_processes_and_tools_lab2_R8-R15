package Board;

public class Board {

    private Position[][] board_2048 = new Position[4][4];
    private static int num_max = 128;   //记录当前可以获得金币的数值
    private static int coin = 5;    //获得金币数

    public Board(){
        init_position();
    }

    public void setNum_max(int num){
        num_max = num;
    }

    public void setCoin(int num){
        coin = num;
    }

    public int getNum_max(){
        return num_max;
    }

    public int getCoin(){
        return coin;
    }

    public void init(){
        num_max = 128;
        coin = 5;
    }

    public void init_position(){
        for (int i = 0; i < 4; i++){
            for (int j = 0; j < 4; j++){
                board_2048[i][j] = new Position(i, j);
            }
        }
    }

    public Position getPosition(int x, int y){
        return board_2048[x][y];
    }

}
