package jp.softrain.mrX.listeners;

import jp.softrain.mrX.Main;
import jp.softrain.mrX.gamestates.GameStates;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.PlayerJoinEvent;

public class JoinListener implements Listener {

    private Main instance;
    public JoinListener(Main instance){
        this.instance = instance;
    }

    @EventHandler
    public void onJoin(PlayerJoinEvent event){
        Player player = event.getPlayer();

        if (instance.getGame().getGameStates() == GameStates.INGAME
                || instance.getGame().getGameStates() == GameStates.ENDING){
            player.kickPlayer("Ingame");
            return;
        }

        if (instance.getGame().getPlayer1() == null){
            instance.getGame().setPlayer1(player);
        } else if (instance.getGame().getPlayer2() == null){
            instance.getGame().setPlayer2(player);
            // Jetzt sind beide Spieler da → Spiel starten
            instance.getGame().startGame();
        } else {
            player.kickPlayer("Server voll.");
        }
    }
}
