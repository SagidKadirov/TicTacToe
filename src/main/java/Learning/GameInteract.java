package Learning;

import javax.swing.*;

public class GameInteract extends Thread {
    private static boolean endGame = false;
    private static boolean isFirstTurn = true;
    private static boolean botsTurn=false;
    private Map map;
    private PlayerManager pManager ;

    public static boolean isEndGame() {
        return endGame;
    }

    public static void setBotsTurn(boolean botsTurn) {
        GameInteract.botsTurn = botsTurn;
    }

    public static void setIsFirstTurn(boolean isFirstTurn) {
        GameInteract.isFirstTurn = isFirstTurn;
    }

    public static boolean isBotsTurn() {
        return botsTurn;
    }

    public static boolean isFirstTurnNow() {
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
    }

    public void playNewGame() {
        if(JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION) == 0) {
            map.drawNewMap();
            endGame = false;
            pManager.setGameModeSelected(false);
            isFirstTurn = true;
            pManager.getPlayers().clear();
            botsTurn = false;
            pManager.setVisible(true);
        }
        else {
            System.exit(0);
        }
    }
}
