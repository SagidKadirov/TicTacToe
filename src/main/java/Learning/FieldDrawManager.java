package Learning;

import javax.swing.*;
import java.awt.*;

public class FieldDrawManager extends JPanel implements Runnable {
    Game game = new Game();
    Images images = new Images();
    Thread gameThread;
    MouseHandler mouseHandler = new MouseHandler(this);
    MouseMotionHandler mouseMotionHandler = new MouseMotionHandler(this);
    boolean isFirst = true;
    //Background settings
    int colorChangeFactor = 0;
    private int red = (int) (Math.random() * 250);
    private int green = (int) (Math.random() * 250);
    private int blue = (int) (Math.random() * 250);


    public FieldDrawManager() {
        this.setPreferredSize(new Dimension(400, 500));
        this.setBackground(Color.BLACK);
        this.setFocusable(true);
        this.addMouseListener(mouseHandler);
        this.addMouseMotionListener(mouseMotionHandler);
        this.setDoubleBuffered(true);
        gameThread = new Thread(this);
        gameThread.start();
        game.start();
    }

    @Override
    public void run() {
        makeFPS();
    }

    public void update() {
        changeRGBValues();
        isFirst=game.isFirstTurn;
    }

    public void paintComponent(Graphics g) {
        this.setBackground(new Color(red, green, blue));
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        drawNumbers(g2);
        reDrawMap(g2, game.getMap());

        g2.dispose();
    }

    public void drawNumbers(Graphics2D g2) {
        //!Draw the numbers
        g2.drawImage(images.getNum1(), 77, 0, 70, 70, null);
        g2.drawImage(images.getNum2(), 190, 0, 70, 70, null);
        g2.drawImage(images.getNum3(), 293, 0, 70, 70, null);
        g2.drawImage(images.getNum1(), 0, 85, 70, 70, null);
        g2.drawImage(images.getNum2(), 0, 190, 70, 70, null);
        g2.drawImage(images.getNum3(), 0, 292, 70, 70, null);
    }

    public void reDrawMap(Graphics2D g2, Map map) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                game.getMap().drawEmptyMapSection(g2, row, column);
                game.getMap().drawHints(g2, row, column, game.isFirstTurn, images);
                game.getMap().drawPrintedXor0(g2, row, column, images);
            }
        }
    }

    public void makeFPS() {
        int FPS = 60;
        double drawInterval = (double) 1000000000 / FPS;  //0.016666 seconds
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

    public void changeRGBValues() {
        red = (int) (Math.abs(Math.sin((double) colorChangeFactor++ / 250) * 200));
        green = (int) (Math.abs(Math.sin((double) colorChangeFactor++ / 200) * 200));
        blue = (int) (Math.abs(Math.sin((double) colorChangeFactor++ / 150) * 200));
    }
}
