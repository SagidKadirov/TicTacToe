package Learning;

import java.util.Collections;
import java.util.Scanner;

public class Game {

    private final Map map;
    private final PlayerManager playerManager;

    public Game() {
        map = new Map();
        playerManager = new PlayerManager();
        makeMove();
    }

    public void checkForWin(Player player) {
        boolean HorizontalFlag = (map.getMap()[0][0] == player.getSymbol() && map.getMap()[0][0] == map.getMap()[0][1] && map.getMap()[0][1] == map.getMap()[0][2]) || (map.getMap()[1][0] == player.getSymbol() && map.getMap()[1][0] == map.getMap()[1][1] && map.getMap()[1][1] == map.getMap()[1][2]) || (map.getMap()[2][0] == player.getSymbol() && map.getMap()[2][0] == map.getMap()[2][1] && map.getMap()[2][1] == map.getMap()[2][2]);
        boolean VerticalFlag = (map.getMap()[0][0] == player.getSymbol() && map.getMap()[0][0] == map.getMap()[1][0] && map.getMap()[1][0] == map.getMap()[2][0]) || (map.getMap()[0][1] == player.getSymbol() && map.getMap()[0][1] == map.getMap()[1][1] && map.getMap()[1][1] == map.getMap()[2][1] || (map.getMap()[0][2] == player.getSymbol() && map.getMap()[0][2] == map.getMap()[1][2] && map.getMap()[1][2] == map.getMap()[2][2]));
        boolean DiagonalFlag = (map.getMap()[0][0] == player.getSymbol() && map.getMap()[0][0] == map.getMap()[1][1] && map.getMap()[1][1] == map.getMap()[2][2]) || (map.getMap()[0][2] == player.getSymbol() && map.getMap()[0][2] == map.getMap()[1][1] && map.getMap()[1][1] == map.getMap()[2][0]);
        player.setWinner(HorizontalFlag || VerticalFlag || DiagonalFlag);
    }

    public boolean isDrawGame() {
        for (int i = 0; i < map.getMap().length; i++) {
            for (int j = 0; j < map.getMap()[0].length; j++) {
                if (map.getMap()[i][j] == '.') {
                    return false;
                }
            }
        }
        return true;
    }

    public void makeMove() {
        int turn = 1;
        int col, row;
        if (playerManager.getPlayers().getFirst().getSymbol() != 'X') {
            Collections.swap(playerManager.getPlayers(), 0, 1);
        }
        map.drawMap();
        while (!isDrawGame()) {
            System.out.println("Turn №" + turn + " of player: " + playerManager.getPlayers().get((turn - 1) % 2).getName());
            do {
                if (playerManager.getPlayers().get((turn - 1) % 2).isHuman()) {
                    System.out.println("Enter your column: ");
                    col = new Scanner(System.in).nextInt();
                    System.out.println("Enter your row: ");
                    row = new Scanner(System.in).nextInt();
                } else {
                    col = (int) (1 + Math.random() * 3);
                    row = (int) (1 + Math.random() * 3);
                }
            } while (row - 1 >= 3 || col - 1 >= 3 || row - 1 < 0 || col - 1 < 0 || map.getMap()[col - 1][row - 1] != '.');
            map.getMap()[col - 1][row - 1] = playerManager.getPlayers().get((turn - 1) % 2).getSymbol();
            checkForWin(playerManager.getPlayers().get((turn - 1) % 2));
            map.drawMap();
            if (playerManager.getPlayers().get((turn - 1) % 2).isWinner()) {
                System.out.println(playerManager.getPlayers().get((turn - 1) % 2).getName() + " (" + playerManager.getPlayers().get((turn - 1) % 2).getSymbol() + ") is winner!");
                break;
            }
            turn++;
        }
        if (playerManager.getPlayers().stream().noneMatch(Player::isWinner)) {
            map.drawMap();
            System.out.println("It's a draw");
        }
    }
  }
