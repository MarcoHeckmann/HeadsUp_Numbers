package jp.softrain.mrX;

import jp.softrain.mrX.game.Game;
import jp.softrain.mrX.listeners.ChatListener;
import jp.softrain.mrX.listeners.JoinListener;
import jp.softrain.mrX.managers.GameConfigurationManager;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;

public final class Main extends JavaPlugin {

    private Main instance = this;
    private GameConfigurationManager gameConfig;
    private Game game;

    @Override
    public void onEnable() {
        instance = this;
        gameConfig = new GameConfigurationManager(this);
        gameConfig.setup();
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

    public GameConfigurationManager getGameConfig() {
        return gameConfig;
    }
}
