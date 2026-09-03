package jp.softrain.mrX;

import jp.softrain.mrX.game.Game;
import jp.softrain.mrX.listeners.ChatListener;
import jp.softrain.mrX.listeners.JoinListener;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private Main instance = this;
    private Game game;

    @Override
    public void onEnable() {
        instance = this;
        game = new Game(this);

        Bukkit.getPluginManager().registerEvents(new JoinListener(this),this);
        Bukkit.getPluginManager().registerEvents(new ChatListener(this),this);
        // Plugin startup logic
    }

    @Override
    public void onDisable() {
        // Plugin shutdown logic
    }

    public Game getGame() {
        return game;
    }
}
