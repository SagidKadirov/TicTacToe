package Learning;

import java.util.Scanner;

public class Player {
    private static int aiCount = 1;
    private final boolean isHuman;
    private final char symbol;
    private final String name;
    private boolean isWinner;

    public void setWinner(boolean winner) {
        isWinner = winner;
    }

    public boolean isWinner() {
        return isWinner;
    }

    public char getSymbol() {
        return symbol;
    }

    public boolean isHuman() {
        return isHuman;
    }

    public String getName() {
        return name;
    }

    public Player(boolean isHuman, String name, char symbol,boolean inWinner) {
        this.isHuman = isHuman;
        this.name = name;
        this.symbol = symbol;
        this.isWinner = inWinner;
    }

    static String setAIName() {
        return "AI №-" + aiCount++;
    }

    static char setAutoSymbol(Player player) {
        if (player == null) {
            return 'X';
        }
        if (player.getSymbol() == 'X') {
            return '0';
        } else return 'X';
    }

    @Override
    public String toString() {
        return "Player{" +
                "isHuman=" + isHuman +
                ", symbol=" + symbol +
                ", name='" + name + '\'' +
                '}';
    }


}
