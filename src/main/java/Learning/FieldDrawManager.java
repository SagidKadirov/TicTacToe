package Learning;

import javax.swing.*;
import java.awt.*;

public class FieldDrawManager extends JPanel implements Runnable {
    private final Map map = new Map();
    private final PlayerManager playerManager = new PlayerManager();
    private final GameInteract gameInteract = new GameInteract(map, playerManager);
    private final Images images = new Images();
    private final Thread drawThread = new Thread(this);

    public Map getMap() {
        return map;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public GameInteract getGameInteract() {
        return gameInteract;
    }

    MouseHandler mouseHandler = new MouseHandler(this);
    MouseMotionHandler mouseMotionHandler = new MouseMotionHandler(this);

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
        gameInteract.start();
        drawThread.start();
    }

    @Override
    public void run() {
        playGameWithFPS(60);
    }

    public void update() {
        changeRGBValues();
    }

    public void paintComponent(Graphics g) {
        this.setBackground(new Color(red, green, blue));
        super.paintComponent(g);
        Graphics2D g2 = (Graphics2D) g;
        g2.setColor(Color.white);
        drawNumbers(g2);
        reDrawMap(g2, map);
        g2.dispose();
    }

    public void drawNumbers(Graphics2D g2) {
        //!Draw the numbers
        g2.setFont(new Font("Vineta BT", Font.BOLD, 68));
        g2.drawString("1", 90, 60);
        g2.drawString("2", 195, 60);
        g2.drawString("3", 300, 60);
        g2.drawString("1", 4, 140);
        g2.drawString("2", 4, 245);
        g2.drawString("3", 4, 350);
    }

    public void reDrawMap(Graphics2D g2, Map map) {
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                try {
                    map.drawEmptyMapSection(g2, row, column);
                    map.drawHints(g2, row, column, gameInteract.isFirstTurnNow(), images);
                    map.drawPrintedXor0(g2, row, column, images);
                    writeTurn(gameInteract.isFirstTurnNow(), g2);
                } catch (Exception e) {
                    break;
                }
            }
        }
    }

    public void playGameWithFPS(int FPS) {
        double drawInterval = (double) 1000000000 / FPS;  //0.016666 seconds
        double nextDrawTime = System.nanoTime() + drawInterval;
        while (drawThread != null) {
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
        red = (int) (Math.abs(Math.sin((double) colorChangeFactor++ / 250) * 150));
        green = (int) (Math.abs(Math.sin((double) colorChangeFactor++ / 200) * 100));
        blue = (int) (Math.abs(Math.sin((double) colorChangeFactor++ / 150) * 100));
    }

    public void writeTurn(boolean isFirstTurn, Graphics2D g2) {
        if (!GameInteract.isEndGame()) {
            g2.setFont(new Font("TimesRoman", Font.BOLD, 32));
            g2.drawString("The " + (isFirstTurn ? playerManager.getPlayers().getFirst().getName() : playerManager.getPlayers().getLast().getName()) + " player's turn", 10, 430);
        }
    }
}
