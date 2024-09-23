package Learning;

import java.awt.event.MouseEvent;

public class MouseMotionHandler extends java.awt.event.MouseMotionAdapter {

    FieldDrawManager fieldDrawManager;

    public MouseMotionHandler(FieldDrawManager fieldDrawManager) {
        this.fieldDrawManager = fieldDrawManager;
    }

    public void mouseMoved(MouseEvent e) {
        if (!fieldDrawManager.getGameInteract().isDrawGame()&&fieldDrawManager.getPlayerManager().isGameModeSelected()) {
            super.mouseMoved(e);
            int X = e.getX();
            int Y = e.getY();
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    if (fieldDrawManager.getMap().getSymbols()[row][column] == '.' &&
                            X > fieldDrawManager.getMap().getStartX()[row][column] &&
                            X < fieldDrawManager.getMap().getEndX()[row][column] &&
                            Y > fieldDrawManager.getMap().getStartY()[row][column] &&
                            Y < fieldDrawManager.getMap().getEndY()[row][column]) {
                        fieldDrawManager.getMap().getVisited()[row][column] = true;
                    } else {
                        fieldDrawManager.getMap().getVisited()[row][column] = false;
                    }
                }
            }
        }
    }
}
