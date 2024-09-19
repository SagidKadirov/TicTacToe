package Learning;

public class Map {
    private final char[][] symbols = new char[3][3];
    private final int[][] startX = new int[3][3];
    private final int[][] startY = new int[3][3];
    private final int[][] endX = new int[3][3];
    private final int[][] endY = new int[3][3];
    private final boolean[][] visited = new boolean[3][3];

    public Map() {
        int startColumnPosition = 70;
        int startRowPosition = 70;
        int oneFieldWidth = 100;
        int oneFieldHeight = 100;
        int spaceBetweenFields = 5;
        for (int row = 0; row < 3; row++) {
            for (int column = 0; column < 3; column++) {
                symbols[row][column] = '.';
                startX[row][column] = startColumnPosition;
                startY[row][column] = startRowPosition;
                endX[row][column] = startColumnPosition + oneFieldWidth;
                endY[row][column] = startRowPosition + oneFieldHeight;
                startColumnPosition += oneFieldWidth + spaceBetweenFields;
            }
            startColumnPosition = 70;
            startRowPosition += oneFieldHeight + spaceBetweenFields;
        }

    }

    public boolean[][] getVisited() {
        return visited;
    }

    public char[][] getSymbols() {
        return symbols;
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
                        " 1 |  " + symbols[0][0] + "  |  " + symbols[0][1] + "  |  " + symbols[0][2] + "  |\n" +
                        "----------------------\n" +
                        " 2 |  " + symbols[1][0] + "  |  " + symbols[1][1] + "  |  " + symbols[1][2] + "  |\n" +
                        "----------------------\n" +
                        " 3 |  " + symbols[2][0] + "  |  " + symbols[2][1] + "  |  " + symbols[2][2] + "  |\n" +
                        "-----------------------");
    }


}
