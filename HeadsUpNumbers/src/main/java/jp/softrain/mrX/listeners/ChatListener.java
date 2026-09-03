package jp.softrain.mrX.listeners;

import jp.softrain.mrX.Main;
import jp.softrain.mrX.gamestates.GameStates;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerChatEvent;

import java.text.NumberFormat;
import java.util.Locale;

public class ChatListener implements Listener {

    private Main instance;
    private int maxGuesses;
    private int currentGuesses;

    public ChatListener(Main instance) {
        this.instance = instance;

        this.maxGuesses = instance.getGame().getMaxGuesses();
        this.currentGuesses = instance.getGame().getCurrentGuesses();
    }

    @EventHandler
    public void onChat(AsyncPlayerChatEvent event) {
        if (instance.getGame().getGameStates() != GameStates.INGAME) {
            return;
        }

        event.setCancelled(true);
        Player player = event.getPlayer();
        String message = event.getMessage();
        message = message.replace(".", "");

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

        NumberFormat euFormat = NumberFormat.getNumberInstance(Locale.GERMANY);
        String formattedGuess = euFormat.format(guess);

        int correctNumber = instance.getGame().getCorrectNumber();

        if (guess == correctNumber) {
            clearChat();
            Bukkit.broadcastMessage("§a" + player.getName() + " §7guessed §a" + formattedGuess + "§7. §aYou Won congratulation!");
            instance.getGame().setGameStates(GameStates.ENDING);

        } else if (guess > correctNumber) {
            currentGuesses++;
            Bukkit.broadcastMessage("§a" + player.getName() + " §7guessed §a" + formattedGuess + "§7. It was too high! §8[§a"+currentGuesses+"§7/§a"+maxGuesses+"§8]");
        } else {
            currentGuesses++;
            Bukkit.broadcastMessage("§a" + player.getName() + " §7guessed §a" + formattedGuess + "§7. It was too low! §8[§a"+currentGuesses+"§7/§a"+maxGuesses+"§8]");
        }

        if (currentGuesses >= maxGuesses){
            Bukkit.broadcastMessage("§aYou Reached your maximum Guesses, the game is over. The Number was §e§l" + instance.getGame().getCorrectNumber() + ". §8[§a"+currentGuesses+"§7/§a"+maxGuesses+"§8]");
            instance.getGame().setGameStates(GameStates.ENDING);
            return;
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
