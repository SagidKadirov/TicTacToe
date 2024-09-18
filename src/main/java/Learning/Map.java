package Learning;

public class Map {
    private final char[][] map = new char[3][3];
    private final int[][] startX=new int[3][3];
    private final int[][] startY=new int[3][3];
    private final int[][] endX=new int[3][3];
    private final int[][] endY=new int[3][3];

    public Map() {
        int startColumnPosition=70;
        int startRowPosition=70;
        int oneFieldWidth=100;
        int oneFieldHeight=100;
        int spaceBetweenFields=5;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                map[row][column]='.';
                startX[row][column]=startColumnPosition;
                startY[row][column]=startRowPosition;
                endX[row][column]=startColumnPosition+oneFieldWidth;
                endY[row][column]=startRowPosition+oneFieldHeight;
                startColumnPosition+=oneFieldWidth+spaceBetweenFields;
            }
            startColumnPosition=70;
            startRowPosition+=oneFieldHeight+spaceBetweenFields;
        }

    }

    public char[][] getMap() {
        return map;
    }

    public int[][] getEndX() {
        return endX;
    }

    public int[][] getEndY() {
        return endY;
    }

    public int[][] getStartX() {
        return startX;
    }

    public int[][] getStartY() {
        return startY;
    }

    public void drawMap() {
        System.out.println(
                "   |  1  |  2  |  3  |\n" +
                        "----------------------\n" +
                        " 1 |  " + map[0][0] + "  |  " + map[0][1] + "  |  " + map[0][2] + "  |\n" +
                        "----------------------\n" +
                        " 2 |  " + map[1][0] + "  |  " + map[1][1] + "  |  " + map[1][2] + "  |\n" +
                        "----------------------\n" +
                        " 3 |  " + map[2][0] + "  |  " + map[2][1] + "  |  " + map[2][2] + "  |\n" +
                        "-----------------------");
    }


}
