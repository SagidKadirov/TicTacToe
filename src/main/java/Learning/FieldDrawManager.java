package Learning;

import javax.swing.*;
import java.awt.*;

public class FieldDrawManager extends JPanel implements Runnable {
    Game game=new Game();
    Images images=new Images();
    private final int FPS = 60;
    private final int fieldWidth = 400;
    private final int fieldHeight = 400;

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    Thread gameThread;

    public FieldDrawManager() {

        this.setPreferredSize(new Dimension(fieldWidth, fieldHeight));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        gameThread = new Thread(this);
        gameThread.start();
        game.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;  //0.016666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            //!  1 UPDATE: update information about game
            update();
            //!  2 DRAW: draw the screen with the updated information
            repaint();

            try {
                double remainingTime = nextDrawTime - System.nanoTime();
                remainingTime = remainingTime / 1000000;
                if (remainingTime < 0) {
                    remainingTime = 0;
                }
                Thread.sleep((long) remainingTime);
                nextDrawTime += drawInterval;
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }

    int count = 0;

    public void update() {
        count++;
        if (count > 100) {
            count = 0;
        }

    }

    public void paintComponent(Graphics g) {
        if (count == 0) {
            this.setBackground(new Color((red += 2) % 249, (green += 3) % 249, (blue += 4) % 249));
        }

        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        //!Draw the numbers
        g2.drawImage(images.getNum1(), 77, 0, 70, 70, null);
        g2.drawImage(images.getNum2(), 190, 0, 70, 70, null);
        g2.drawImage(images.getNum3(), 293, 0, 70, 70, null);
        g2.drawImage(images.getNum1(), 0, 85, 70, 70, null);
        g2.drawImage(images.getNum2(), 0, 190, 70, 70, null);
        g2.drawImage(images.getNum3(), 0, 292, 70, 70, null);
        g2.setColor(Color.white);

        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                g2.fillRect(game.getMap().getStartX()[row][column], game.getMap().getStartY()[row][column], 100, 100);
                if (game.getMap().getMap()[row][column] == 'X') {
                    g2.drawImage(images.getxSymbolPrinted(),game.getMap().getStartX()[row][column], game.getMap().getStartY()[row][column], 100, 100, null);
                }else
                if (game.getMap().getMap()[row][column] == '0') {
                    g2.drawImage(images.getoSymbolPrinted(), game.getMap().getStartX()[row][column], game.getMap().getStartY()[row][column], 100, 100, null);
                }
            }
        }
        g2.dispose();
    }
}
