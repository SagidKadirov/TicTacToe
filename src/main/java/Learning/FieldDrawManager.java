package Learning;

import javax.imageio.ImageIO;
import javax.swing.*;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.IOException;

public class FieldDrawManager extends JPanel implements Runnable {
    Game game=new Game();

    private final int FPS = 60;
    private final int oneFieldWidth = 100;
    private final int oneFieldHeight = 100;
    private final int spaceBetweenField = 5;
    private final int GENERALFIELDWIDTH = 400;
    private final int GENERALFIELDHEIGHT = 400;
    BufferedImage num1, num2, num3,xSymbol,oSymbol;

//    private final int WIDTH = 310;
//    private final int HEIGHT = 310;

    private int red = 0;
    private int green = 0;
    private int blue = 0;
    Thread gameThread;

    public FieldDrawManager() {

        this.setPreferredSize(new Dimension(GENERALFIELDWIDTH, GENERALFIELDHEIGHT));
        this.setBackground(Color.BLACK);
        this.setDoubleBuffered(true);
        try {
            num1 = ImageIO.read(getClass().getResourceAsStream("/numbers/1.png"));
            num2 = ImageIO.read(getClass().getResourceAsStream("/numbers/2.png"));
            num3 = ImageIO.read(getClass().getResourceAsStream("/numbers/3.png"));
            xSymbol = ImageIO.read(getClass().getResourceAsStream("/symbols/Printed_X_Symbol.png"));
            oSymbol = ImageIO.read(getClass().getResourceAsStream("/symbols/Printed_0_Symbol.png"));
        } catch (IOException e) {
            throw new RuntimeException(e);
        }

        gameThread = new Thread(this);
        gameThread.start();
        game.start();
    }

    @Override
    public void run() {
        double drawInterval = 1000000000 / FPS;  //0.016666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (gameThread != null) {
            //!  1 UPDATE: update information such as character positions
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
        g2.drawImage(num1, 77, 0, 70, 70, null);
        g2.drawImage(num2, 190, 0, 70, 70, null);
        g2.drawImage(num3, 293, 0, 70, 70, null);
        g2.drawImage(num1, 0, 85, 70, 70, null);
        g2.drawImage(num2, 0, 190, 70, 70, null);
        g2.drawImage(num3, 0, 292, 70, 70, null);
        g2.setColor(Color.white);
        int col = 70;
        int row = 70;
        for (int i = 0; i < 3; i++) {
            for (int j = 0; j < 3; j++) {
                g2.fillRect(col, row, oneFieldWidth, oneFieldHeight);
                if (game.getMap().getMap()[j][i] == 'X') {
                    g2.drawImage(xSymbol, col, row, oneFieldWidth, oneFieldHeight, null);
                }else
                if (game.getMap().getMap()[j][i] == '0') {
                    g2.drawImage(oSymbol, col, row, oneFieldWidth, oneFieldHeight, null);
                }
                row += oneFieldHeight + spaceBetweenField;
            }
            row = 70;
            col += oneFieldWidth + spaceBetweenField;
        }
        g2.dispose();
    }
}
