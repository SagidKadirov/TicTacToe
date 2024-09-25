package Learning;

import javax.swing.*;
import java.util.Arrays;
import java.util.Scanner;

public class GameInteract extends Thread {
    private static boolean endGame = true;
    private boolean isFirstTurn = true;
    private final Thread gameThread = new Thread(this);
    private Map map;
    private PlayerManager pManager;

    public static void setEndGame(boolean endGame) {
        GameInteract.endGame = endGame;
    }

    public void setFirstTurn(boolean isFirstTurn) {
        this.isFirstTurn = isFirstTurn;
    }

    public static boolean isEndGame() {
        return endGame;
    }

    public boolean isFirstTurnNow() {
        return isFirstTurn;
    }

    public GameInteract(Map map, PlayerManager pManager) {
        this.map = map;
        this.pManager = pManager;
    }

    public void checkForWin() {
        for (Player player : pManager.getPlayers()) {
            boolean HorizontalFlag = (map.getSymbols()[0][0] == player.getSymbol() && map.getSymbols()[0][0] == map.getSymbols()[0][1] && map.getSymbols()[0][1] == map.getSymbols()[0][2]) || (map.getSymbols()[1][0] == player.getSymbol() && map.getSymbols()[1][0] == map.getSymbols()[1][1] && map.getSymbols()[1][1] == map.getSymbols()[1][2]) || (map.getSymbols()[2][0] == player.getSymbol() && map.getSymbols()[2][0] == map.getSymbols()[2][1] && map.getSymbols()[2][1] == map.getSymbols()[2][2]);
            boolean VerticalFlag = (map.getSymbols()[0][0] == player.getSymbol() && map.getSymbols()[0][0] == map.getSymbols()[1][0] && map.getSymbols()[1][0] == map.getSymbols()[2][0]) || (map.getSymbols()[0][1] == player.getSymbol() && map.getSymbols()[0][1] == map.getSymbols()[1][1] && map.getSymbols()[1][1] == map.getSymbols()[2][1] || (map.getSymbols()[0][2] == player.getSymbol() && map.getSymbols()[0][2] == map.getSymbols()[1][2] && map.getSymbols()[1][2] == map.getSymbols()[2][2]));
            boolean DiagonalFlag = (map.getSymbols()[0][0] == player.getSymbol() && map.getSymbols()[0][0] == map.getSymbols()[1][1] && map.getSymbols()[1][1] == map.getSymbols()[2][2]) || (map.getSymbols()[0][2] == player.getSymbol() && map.getSymbols()[0][2] == map.getSymbols()[1][1] && map.getSymbols()[1][1] == map.getSymbols()[2][0]);
            player.setWinner(HorizontalFlag || VerticalFlag || DiagonalFlag);
        }
    }

    public boolean isDrawGame() {
        for (int i = 0; i < map.getSymbols().length; i++) {
            for (int j = 0; j < map.getSymbols()[0].length; j++) {
                if (map.getSymbols()[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    @Override
    public void run() {
        while (gameThread != null) {
            checkForEndGame();
            checkBotsTurn();
        }
    }

    public void playNewGame() {
        if (JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION) == 0) {
            map.drawNewMap();
            Arrays.fill(pManager.getModes(), false);
            isFirstTurn = true;
            pManager.getPlayers().clear();
            pManager.setVisible(true);
            endGame = false;
        } else {
            System.exit(0);
        }
    }

    public void checkBotsTurn() {
        if (!pManager.getPlayers().isEmpty() && !GameInteract.isEndGame()) {
            int row, col;
            do {
                row = (int) (Math.random() * 3);
                col = (int) (Math.random() * 3);
            } while (map.getSymbols()[row][col] != '.');

            if (pManager.getModes()[0]) {
                if (pManager.getPlayers().getFirst().isHuman() && !isFirstTurn) {
                    map.getSymbols()[row][col] = '0';
                    isFirstTurn = true;
                }
                if (!pManager.getPlayers().getFirst().isHuman() && isFirstTurn) {
                    map.getSymbols()[row][col] = 'X';
                    isFirstTurn = false;
                }
            }
            if (pManager.getModes()[2]) {
                try {
                    Thread.sleep(500);
                } catch (InterruptedException e) {
                    throw new RuntimeException(e);
                }
                map.getSymbols()[row][col] = isFirstTurn ? 'X' : '0';
                isFirstTurn= !isFirstTurnNow();
                map.drawConsoleMap();
            }
        }
    }


    public void checkForEndGame() {
        if (!pManager.getPlayers().isEmpty() && !GameInteract.isEndGame()) {
            checkForWin();
            for (Player player : pManager.getPlayers()) {
                if (player.isWinner()) {
                    JOptionPane.showMessageDialog(null, player.getName() + " is winner!");
                    playNewGame();
                    return;
                }
            }
            if (isDrawGame()) {
                JOptionPane.showMessageDialog(null, "It's a draw");
                playNewGame();
            }
        } else {
            try {
                Thread.sleep(10);
            } catch (InterruptedException e) {
                throw new RuntimeException(e);
            }
        }
    }
}
