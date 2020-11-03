package GoldCoin;

public class Goldcoin {
    private static int num = 0;
    private int per_num;

    public Goldcoin(){
        per_num = 0;
    }

    public int getNum(){
        return num;
    }

    public int getPer_num(){
        return per_num;
    }

    public void setPer_num(int count){
        per_num = count;
    }

    public void addNum(int count){
        num = num + count;
    }

    public void minusNum(int count){
        num = num - count;
    }

    public void cleanNum(){
        num = 0;
        per_num = 0;
    }

}
