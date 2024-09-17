package Learning;


public class Game {
    private boolean win = false;
    private boolean draw = false;
    private Map map;
    private PlayerManager playerManager;

    public Game() {
        map = new Map();
        playerManager = new PlayerManager();

        //for test

        map.getMap()[0][0]='0'; map.getMap()[0][1]='0'; map.getMap()[0][2]='X';
        map.getMap()[1][0]='X'; map.getMap()[1][1]='X'; map.getMap()[1][2]='0';
        map.getMap()[2][0]='0'; map.getMap()[2][1]='X'; map.getMap()[2][2]='0';
        checkForWin(map,playerManager.getPlayers().getFirst());
        checkForWin(map,playerManager.getPlayers().getLast());
        if(playerManager.getPlayers().stream().filter(p->p.isWinner()).count()==0)
        {
            if(checkForDraw(map)){
                System.out.println("It's a draw");
            }
        }
//        for (int i = 0; i < map.getMap().length; i++) {
//            System.out.println(Arrays.toString(map.getMap()[i]));
//        }
//        playerManager = new PlayerManager();
//        do {
//            map.checkForWin(playerManager.getPlayers().getFirst());
//            map.checkForWin(playerManager.getPlayers().getLast());
//        } while (win || draw);
    }

    public void checkForWin(Map map, Player player) {
        boolean HorizontalFlag = (map.getMap()[0][0] == player.getSymbol() && map.getMap()[0][0] == map.getMap()[0][1] && map.getMap()[0][1] == map.getMap()[0][2]) || (map.getMap()[1][0] == player.getSymbol() && map.getMap()[1][0] == map.getMap()[1][1] && map.getMap()[1][1] == map.getMap()[1][2]) || (map.getMap()[2][0] == player.getSymbol() && map.getMap()[2][0] == map.getMap()[2][1] && map.getMap()[2][1] == map.getMap()[2][2]);
        boolean VerticalFlag = (map.getMap()[0][0] == player.getSymbol() && map.getMap()[0][0] == map.getMap()[1][0] && map.getMap()[1][0] == map.getMap()[2][0]) || (map.getMap()[0][1] == player.getSymbol() && map.getMap()[0][1] == map.getMap()[1][1] && map.getMap()[1][1] == map.getMap()[2][1] || (map.getMap()[0][2] == player.getSymbol() && map.getMap()[0][2] == map.getMap()[1][2] && map.getMap()[1][2] == map.getMap()[2][2]));
        boolean DiagonalFlag = (map.getMap()[0][0] == player.getSymbol() && map.getMap()[0][0] == map.getMap()[1][1] && map.getMap()[1][1] == map.getMap()[2][2]) || (map.getMap()[0][2] == player.getSymbol() && map.getMap()[0][2] == map.getMap()[1][1] && map.getMap()[1][1] == map.getMap()[2][0]);
        System.out.print(player.getName());
//        System.out.println("Horizontal flag: " + HorizontalFlag);
//        System.out.println("Vertical flag: " + VerticalFlag);
//        System.out.println("Diagonal flag: " + DiagonalFlag);
        player.setWinner(HorizontalFlag || VerticalFlag || DiagonalFlag);
        System.out.println(" is Winner: " + player.isWinner());
    }
    public boolean checkForDraw(Map map){
        boolean drawFlag=true;
        for(int i=0;i<map.getMap().length;i++){
            for(int j=0;j<map.getMap()[0].length;j++){
                if(map.getMap()[i][j]=='.')
                {
                    drawFlag=false;
                }
            }
        }
        return drawFlag;
    }
}
