package Learning;


public class Game {
    private boolean win = false;
    private boolean draw = false;
    private Map map;
    private PlayerManager playerManager;

    public Game() {
        map = new Map();
        playerManager = new PlayerManager();
//        map.getMap()[0][0]='X';
//        map.getMap()[0][1]='X';
//        map.getMap()[0][2]='X';
//        for (int i = 0; i < map.getMap().length; i++) {
//            System.out.println(Arrays.toString(map.getMap()[i]));
//        }
//        playerManager = new PlayerManager();
//        do {
//            map.checkForWin(playerManager.getPlayers().getFirst());
//            map.checkForWin(playerManager.getPlayers().getLast());
//        } while (win || draw);
    }


}
