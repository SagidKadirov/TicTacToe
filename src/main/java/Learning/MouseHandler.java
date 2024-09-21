package Learning;

import javax.swing.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    FieldDrawManager fieldDrawManager;

    public MouseHandler(FieldDrawManager fieldDrawManager) {
        this.fieldDrawManager = fieldDrawManager;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        if (fieldDrawManager.game.getPlayerManager().isGameModeSelected()) {
            super.mousePressed(e);
            int X = e.getX();
            int Y = e.getY();
            for (int row = 0; row < 3; row++) {
                for (int column = 0; column < 3; column++) {
                    if (fieldDrawManager.game.getMap().getSymbols()[row][column] == '.' &&
                            X > fieldDrawManager.game.getMap().getStartX()[row][column] &&
                            X < fieldDrawManager.game.getMap().getEndX()[row][column] &&
                            Y > fieldDrawManager.game.getMap().getStartY()[row][column] &&
                            Y < fieldDrawManager.game.getMap().getEndY()[row][column]) {
                        if (fieldDrawManager.game.getPlayerManager().isFirstTurn()) {
                            fieldDrawManager.game.getMap().getSymbols()[row][column] = 'X';
                            fieldDrawManager.game.getPlayerManager().setFirstTurn(false);
                        } else {
                            System.out.println("this: " + row + " " + column);
                            fieldDrawManager.game.getMap().getSymbols()[row][column] = 'O';
                            fieldDrawManager.game.getPlayerManager().setFirstTurn(true);
                        }
                    }
                }
            }
        }
        else {
            JOptionPane.showMessageDialog(null,"Select the game Mode");
            fieldDrawManager.game.getPlayerManager().setVisible(true);
            fieldDrawManager.game.getPlayerManager().setState(JFrame.NORMAL);
        }
    }
}
