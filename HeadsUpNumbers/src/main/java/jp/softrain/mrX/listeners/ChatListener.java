package jp.softrain.mrX.listeners;

import jp.softrain.mrX.Main;
import jp.softrain.mrX.gamestates.GameStates;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

public class ChatListener implements Listener {

    private Main instance;

    public ChatListener(Main instance) {
        this.instance = instance;
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (instance.getGame().getGameStates() != GameStates.INGAME) {
            event.setCancelled(true);
            return;
        }
        event.setCancelled(true);
        Player player = event.getPlayer();
        String message = event.getMessage();

        if (instance.getGame().getCurrentTurnPlayer() != player) {
            player.sendMessage("§7Its not your turn.");
            return;
        }

        int guess = 0;
        try {
            guess = Integer.parseInt(message);
        } catch (NumberFormatException e) {
            player.sendMessage("§7Das ist keine §aNummer§7.");
            return;
        }
        int correctNumber = instance.getGame().getCorrectNumber();

        if (guess == correctNumber) {
            clearChat();
            Bukkit.broadcastMessage("§a" + player.getName() + " §7guessed §a" + guess + "§7. §aYou Won congratulation!");
            instance.getGame().setGameStates(GameStates.ENDING);
        } else if (guess > correctNumber) {
            Bukkit.broadcastMessage("§a" + player.getName() + " §7guessed §a" + guess + "§7. It was too high!");

        } else {
            Bukkit.broadcastMessage("§a" + player.getName() + " §7guessed §a" + guess + "§7. It was too low!");
        }
        event.setCancelled(true);
        instance.getGame().switchPlayer();
        player.sendMessage("§a" + instance.getGame().getCurrentTurnPlayer().getName() + " §7is now playing!");
        instance.getGame().getCurrentTurnPlayer().sendMessage("§aYour Turn!");
    }

    public void clearChat(){
        for (int i = 0; i < 100; i++){
            Bukkit.broadcastMessage(" ");
        }
    }
}
