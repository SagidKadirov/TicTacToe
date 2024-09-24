package Learning;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.util.Arrays;

public class MouseHandler extends MouseAdapter {

    FieldDrawManager fieldDrawManager;

    public MouseHandler(FieldDrawManager fieldDrawManager) {
        this.fieldDrawManager = fieldDrawManager;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (!GameInteract.isEndGame()) {
            super.mousePressed(e);
            int X = e.getX();
            int Y = e.getY();
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    if (fieldDrawManager.getMap().getSymbols()[row][column] == '.' &&
                            X > fieldDrawManager.getMap().getStartX()[row][column] &&
                            X < fieldDrawManager.getMap().getEndX()[row][column] &&
                            Y > fieldDrawManager.getMap().getStartY()[row][column] &&
                            Y < fieldDrawManager.getMap().getEndY()[row][column]) {
                        if (GameInteract.isFirstTurnNow()) {
                            fieldDrawManager.getMap().getSymbols()[row][column] = 'X';
                            GameInteract.setFirstTurn(false);
                            fieldDrawManager.getMap().drawConsoleMap();
                        } else {
                            fieldDrawManager.getMap().getSymbols()[row][column] = '0';
                            GameInteract.setFirstTurn(true);
                            fieldDrawManager.getMap().drawConsoleMap();
                        }
                    }
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"Select the game Mode");
            fieldDrawManager.getPlayerManager().setVisible(true);
            fieldDrawManager.getPlayerManager().setState(JFrame.NORMAL);
        }
    }

    @Override
    public void mouseReleased(MouseEvent e) {
        super.mouseReleased(e);
        MouseMotionHandler mmh=new MouseMotionHandler(fieldDrawManager);
        mmh.mouseMoved(e);
    }
}
