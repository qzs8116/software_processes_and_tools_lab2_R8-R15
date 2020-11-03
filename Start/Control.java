package Start;

import Board.Board;
import Game.Game;
import GoldCoin.Goldcoin;
import Score.Score;
import UI.GameUI;

public class Control {
    private final Game game;
    private final Score score;
    private final Goldcoin goldCoin;
    private final GameUI gu;
    private boolean t = true;

    public Control(){
        game = new Game();
        score = new Score();
        goldCoin = new Goldcoin();
        gu = new GameUI(4,this);
    }

    public Control(Game game, Score score, Goldcoin goldCoin){
        this.game = game;
        this.score = score;
        this.goldCoin = goldCoin;
        gu = new GameUI(4, this);
    }

    public Game getGame(){
        return game;
    }

    /**
     * 运行
     */
    public void run() {
        gu.showButton(score.getSco(), goldCoin.getNum(), score.getScore_max());
        game.refreshSquare();
        gu.changeButton(game.getBoard());
    }

    /**
     * 上移
     */
    public void upControl(){
        t = game.upSquare();
        runStep();
    }

    /**
     * 下移
     */
    public void downControl(){
        t = game.downSquare();
        runStep();
    }

    /**
     * 左移
     */
    public void lfetControl(){
        t = game.leftSquare();
        runStep();
    }

    /**
     * 右移
     */
    public void rightControl(){
        t = game.rightSquare();
        runStep();
    }

    /**
     * 运行步骤
     */
    public void runStep(){
        if(score.getSco() > score.getScore_max()){
            score.setScore_max(score.getSco());
            gu.changMaxScore(score.getScore_max());
            gu.changMaxScore(score.getSco());
        }
        gu.changScore(score.getSco());
        if(goldCoin.getPer_num() != goldCoin.getNum()){
            gu.Prompt("获得金币：" + (goldCoin.getNum()-goldCoin.getPer_num()));
            goldCoin.setPer_num(goldCoin.getNum());
            gu.changCoin(goldCoin.getNum());
        }
        if(t)
            game.refreshSquare();
        gu.changeButton(game.getBoard());
        if(game.checkGameOver()){
            gu.showMessage("Game Over");
        }
    }

    /**
     * 重新开始
     */
    public void restart(){
        game.getBoard().init();
        game.getBoard().init_position();
        score.init_Score();
        Control rc = new Control(game, score, goldCoin);
        rc.run();
    }

    /**
     * 删除方块
     * @param num
     * @return
     */
    public int removeSquare(int num){
        int flag = 0;
        if(goldCoin.getNum() < 10*num)
            return 1;
        for(int i = 0; i < 4; i ++){
            for(int j = 0; j < 4; j++){
                if(game.getBoard().getPosition(i, j).getSquare() != null){
                    if(game.getBoard().getPosition(i, j).getSquare().getNum() == num){
                        game.getBoard().getPosition(i, j).removeSquare();
                        flag++;
                    }
                }
            }
        }
        if(flag == 0)
            return 0;
        goldCoin.minusNum(10*num);
        goldCoin.setPer_num(goldCoin.getNum());
        gu.changCoin(goldCoin.getNum());
        gu.changeButton(game.getBoard());
        return 2;
    }

}
