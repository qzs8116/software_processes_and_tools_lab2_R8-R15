package Action;

import Board.Position;

public class Action {

    /**
     * 移动方块，如果位置上两个方块数值相同，则合并
     * @param sourcePosition 初始位置
     * @param targetPosition 目的位置
     */
    public boolean moveSquare(Position sourcePosition, Position targetPosition) {
        if(sourcePosition.getSquare() != null) {
            if (targetPosition.getSquare() == null) {
                targetPosition.setSquare(sourcePosition.getSquare());
                sourcePosition.removeSquare();
                return true;
            } else if (targetPosition.getSquare() != null) {
                if (sourcePosition.getSquare().getNum() == targetPosition.getSquare().getNum() && targetPosition.getSquare().getFlag() != 1) {
                    targetPosition.getSquare().squareChange();
                    sourcePosition.removeSquare();
                    return true;
                }
                return false;
            }
        }
        return false;
    }

}
