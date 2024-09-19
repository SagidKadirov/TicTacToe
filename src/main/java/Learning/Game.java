package Learning;

import java.util.Collections;
import java.util.Scanner;

public class Game extends Thread {
    int turn = 0;
    private Map map;
    private PlayerManager playerManager;

    public Map getMap() {
        return map;
    }

    public PlayerManager getPlayerManager() {
        return playerManager;
    }

    public void checkForWin(Player player) {
        boolean HorizontalFlag = (map.getSymbols()[0][0] == player.getSymbol() && map.getSymbols()[0][0] == map.getSymbols()[0][1] && map.getSymbols()[0][1] == map.getSymbols()[0][2]) || (map.getSymbols()[1][0] == player.getSymbol() && map.getSymbols()[1][0] == map.getSymbols()[1][1] && map.getSymbols()[1][1] == map.getSymbols()[1][2]) || (map.getSymbols()[2][0] == player.getSymbol() && map.getSymbols()[2][0] == map.getSymbols()[2][1] && map.getSymbols()[2][1] == map.getSymbols()[2][2]);
        boolean VerticalFlag = (map.getSymbols()[0][0] == player.getSymbol() && map.getSymbols()[0][0] == map.getSymbols()[1][0] && map.getSymbols()[1][0] == map.getSymbols()[2][0]) || (map.getSymbols()[0][1] == player.getSymbol() && map.getSymbols()[0][1] == map.getSymbols()[1][1] && map.getSymbols()[1][1] == map.getSymbols()[2][1] || (map.getSymbols()[0][2] == player.getSymbol() && map.getSymbols()[0][2] == map.getSymbols()[1][2] && map.getSymbols()[1][2] == map.getSymbols()[2][2]));
        boolean DiagonalFlag = (map.getSymbols()[0][0] == player.getSymbol() && map.getSymbols()[0][0] == map.getSymbols()[1][1] && map.getSymbols()[1][1] == map.getSymbols()[2][2]) || (map.getSymbols()[0][2] == player.getSymbol() && map.getSymbols()[0][2] == map.getSymbols()[1][1] && map.getSymbols()[1][1] == map.getSymbols()[2][0]);
        player.setWinner(HorizontalFlag || VerticalFlag || DiagonalFlag);
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

    public void makeMove() {

        int col, row;
        if (playerManager.getPlayers().getFirst().getSymbol() != 'X') {
            Collections.swap(playerManager.getPlayers(), 0, 1);
        }
        map.drawMap();
        while (!isDrawGame()) {
            System.out.println("Turn №" + (turn + 1) + " of player: " + playerManager.getPlayers().get((turn) % 2).getName());
            do {
                if (playerManager.getPlayers().get((turn) % 2).isHuman()) {
                    System.out.println("Enter your column: ");
                    col = new Scanner(System.in).nextInt();
                    System.out.println("Enter your row: ");
                    row = new Scanner(System.in).nextInt();
                } else {
                    col = (int) (1 + Math.random() * 3);
                    row = (int) (1 + Math.random() * 3);
                }
            } while (row - 1 >= 3 || col - 1 >= 3 || row - 1 < 0 || col - 1 < 0 || map.getSymbols()[col - 1][row - 1] != '.');
            map.getSymbols()[col - 1][row - 1] = playerManager.getPlayers().get((turn) % 2).getSymbol();
            checkForWin(playerManager.getPlayers().get((turn) % 2));
            map.drawMap();
            if (playerManager.getPlayers().get((turn) % 2).isWinner()) {
                System.out.println(playerManager.getPlayers().get((turn) % 2).getName() + " (" + playerManager.getPlayers().get((turn) % 2).getSymbol() + ") is winner!");
                break;
            }
            turn++;
        }
        if (playerManager.getPlayers().stream().noneMatch(Player::isWinner)) {
            System.out.println("It's a draw");
        }
    }

    @Override
    public void run() {
        map = new Map();
        playerManager = new PlayerManager();
        makeMove();
    }
}
