package Learning;

import java.util.Arrays;

public class Map {
    private final char EMPTY_VALUE = '.';
    private char[][] map = new char[3][3];

    public Map() {
        for (int i = 0; i < 3; i++) {
            Arrays.fill(map[i], EMPTY_VALUE);
        }
    }

    public char[][] getMap() {
        return map;
    }


    public boolean checkForWin(Player player) {
        boolean HorizontalFlag = false;
        boolean VerticalFlag = false;

        System.out.println("Horizontal flag: " + HorizontalFlag);
        System.out.println("Vertical flag: " + VerticalFlag);
        return false;
    }
}
