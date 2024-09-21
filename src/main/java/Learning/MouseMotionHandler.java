package Learning;

import java.awt.event.MouseEvent;

public class MouseMotionHandler extends java.awt.event.MouseMotionAdapter {

    FieldDrawManager fieldDrawManager;

    public MouseMotionHandler(FieldDrawManager fieldDrawManager) {
        this.fieldDrawManager = fieldDrawManager;
    }

    public void mouseMoved(MouseEvent e) {
        if (fieldDrawManager.game.getPlayerManager().isGameModeSelected()) {
            super.mouseMoved(e);
            int X = e.getX();
            int Y = e.getY();
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    if (fieldDrawManager.game.getMap().getSymbols()[row][column] == '.' &&
                            X > fieldDrawManager.game.getMap().getStartX()[row][column] &&
                            X < fieldDrawManager.game.getMap().getEndX()[row][column] &&
                            Y > fieldDrawManager.game.getMap().getStartY()[row][column] &&
                            Y < fieldDrawManager.game.getMap().getEndY()[row][column]) {
                        fieldDrawManager.game.getMap().getVisited()[row][column] = true;
                    } else {
                        fieldDrawManager.game.getMap().getVisited()[row][column] = false;
                    }
                }
            }
        }
    }
}
