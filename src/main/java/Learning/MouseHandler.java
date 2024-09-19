package Learning;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class MouseHandler extends MouseAdapter {

    FieldDrawManager fieldDrawManager;

    public MouseHandler(FieldDrawManager fieldDrawManager) {
        this.fieldDrawManager = fieldDrawManager;
    }

    @Override
    public void mousePressed(MouseEvent e) {
        super.mousePressed(e);
        int X = e.getX();
        int Y = e.getY();
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                if (fieldDrawManager.game.getMap().getSymbols()[row][column] == '.' &&
                        X > fieldDrawManager.game.getMap().getStartX()[row][column] &&
                        X < fieldDrawManager.game.getMap().getEndX()[row][column] &&
                        Y > fieldDrawManager.game.getMap().getStartY()[row][column] &&
                        Y< fieldDrawManager.game.getMap().getEndY()[row][column])
                {
                    System.out.println("this: "+row+" "+column);
                }
            }
        }
        System.out.println(fieldDrawManager != null);
    }

    @Override
    public void mouseEntered(MouseEvent e) {
        super.mouseEntered(e);

        System.out.println(fieldDrawManager != null);
    }
}
