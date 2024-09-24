package Learning;

import javax.swing.*;
import java.util.Arrays;

public class GameInteract extends Thread {
    private static boolean endGame = true;
    private static boolean isFirstTurn = true;
    private static boolean botsTurn=false;
    private final Thread gameThread=new Thread(this);
    private Map map;
    private PlayerManager pManager ;

    public static void setEndGame(boolean endGame) {
        GameInteract.endGame = endGame;
    }

    public static boolean isEndGame() {
        return endGame;
    }

    public static void setBotsTurn(boolean botsTurn) {
        GameInteract.botsTurn = botsTurn;
    }

    public static void setFirstTurn(boolean isFirstTurn) {
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
        while(gameThread!=null){
            botsTurn();
        }

    }

    public void playNewGame() {
        if(JOptionPane.showConfirmDialog(null, "Do you want to play again?", "Play again?", JOptionPane.YES_NO_OPTION) == 0) {
            map.drawNewMap();
            endGame = false;
            Arrays.fill(pManager.getModes(),false);
            isFirstTurn = true;
            pManager.getPlayers().clear();
            botsTurn = false;
            pManager.setVisible(true);
        }
        else {
            System.exit(0);
        }
    }
    public void botsTurn(){
        int row,col;
        if(botsTurn&&!endGame){
            do{
                row=(int)(Math.random()*4);
                col=(int)(Math.random()*4);
            }while(map.getSymbols()[row][col]!='.');
            if(isFirstTurn&&!pManager.getPlayers().getFirst().isHuman()){
                map.getSymbols()[row][col]='X';
            }else if(!isFirstTurn&&!pManager.getPlayers().getLast().isHuman())
            {
                map.getSymbols()[row][col]='0';
            }
        }
    }
}
