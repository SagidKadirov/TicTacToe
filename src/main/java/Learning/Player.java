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

    public Player(boolean isHuman, String name, char symbol) {
        this.isHuman = isHuman;
        this.name = name;
        this.symbol = symbol;
    }

    static String setHumanName() {
        System.out.println("Enter your name: ");
        return new Scanner(System.in).nextLine();
    }

    static String setAIName() {
        return "AI №-" + aiCount++;
    }

    static char setHumanSymbol() {
        Scanner scanner = new Scanner(System.in);
        String choice;
        do {
            System.out.println("1. X (first turn)\n2. 0 (Second turn)\nSet your turn: ");
            choice = scanner.nextLine();
        } while (!choice.equals("1") && !choice.equals("2"));
        return choice.equals("1") ? 'X' : '0';
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
