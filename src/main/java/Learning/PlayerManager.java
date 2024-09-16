package Learning;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class PlayerManager {
    private List<Player> players;

    public List<Player> getPlayers() {
        return players;
    }

    public PlayerManager() {
        players=new ArrayList<>();
        checkPlayers();
        System.out.println(players.getFirst()+" "+players.getLast());
    }

    public void checkPlayers() {
        System.out.println("""
                Select type of game:
                1. Human vs AI
                2. Human vs Human
                3. AI vs AI
                \s""");
        switch (new Scanner(System.in).nextLine()) {
            case "1":
                players.add(new Player(true, Player.setHumanName(), Player.setHumanSymbol()));
                players.add(new Player(false, Player.setAIName(), Player.setAutoSymbol(players.getFirst())));
                break;
            case "2":
                players.add(new Player(true, Player.setHumanName(), Player.setHumanSymbol()));
                players.add(new Player(true, Player.setHumanName(), Player.setAutoSymbol(players.getFirst())));
                break;
            case "3":
                players.add(new Player(false,Player.setAIName(),Player.setAutoSymbol(null)));
                players.add(new Player(false,Player.setAIName(),Player.setAutoSymbol(players.getFirst())));
                break;
            default:
                checkPlayers();
        }
    }


}
