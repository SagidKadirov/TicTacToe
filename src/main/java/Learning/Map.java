package Learning;

import java.util.Arrays;

public class Map {
    private final char[][] map = new char[3][3];

    public Map() {
        for (int i = 0; i < 3; i++) {
            char EMPTY_VALUE = '.';
            Arrays.fill(map[i], EMPTY_VALUE);
        }
    }

    public char[][] getMap() {
        return map;
    }
public void drawMap(){
    System.out.println(
             "   |  1  |  2  |  3  |\n"+
             "----------------------\n"+
             " 1 |  "+map[0][0]+"  |  "+map[0][1]+"  |  "+map[0][2]+"  |\n"+
             "----------------------\n"+
             " 2 |  "+map[1][0]+"  |  "+map[1][1]+"  |  "+map[1][2]+"  |\n"+
             "----------------------\n"+
             " 3 |  "+map[2][0]+"  |  "+map[2][1]+"  |  "+map[2][2]+"  |\n"+
            "-----------------------");
}


}
