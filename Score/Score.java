package Score;

public class Score {

    private static int sco = 0;
    private int score_max = 0;

    public int getSco(){
        return sco;
    }

    public int getScore_max(){
        return score_max;
    }

    public void setScore_max(int num) {
        score_max = num;
    }

    public void addScore(int num){
        sco = sco + num;
    }

    public void init_Score(){
        sco = 0;
    }
}
