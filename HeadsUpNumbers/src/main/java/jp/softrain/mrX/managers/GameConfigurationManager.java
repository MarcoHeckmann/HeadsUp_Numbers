package jp.softrain.mrX.managers;

import jp.softrain.mrX.Main;
import org.bukkit.configuration.file.FileConfiguration;
import org.bukkit.configuration.file.YamlConfiguration;

import java.io.File;
import java.io.IOException;

public class GameConfigurationManager {

    private Main instance;
    private File file;
    private FileConfiguration config;

    public GameConfigurationManager(Main instance){
        this.instance = instance;
    }

    public void setup(){
        file = new File(instance.getDataFolder(), "game.yml");

        if(!file.exists()){
            instance.saveResource("game.yml", false);
        }
        config = YamlConfiguration.loadConfiguration(file);
    }
    public void save(){
        try{
            config.save(file);
        }catch (IOException e){
            instance.getLogger().severe(e.getMessage());
        }
    }
    public void reload(){
        config = YamlConfiguration.loadConfiguration(file);
    }

    public FileConfiguration config(){
        return config;
    }
    public int get(String path){
        if (config == null) {
            throw new IllegalStateException("GameConfigurationManager.setup() wurde noch nicht aufgerufen!");
        }

        return config.getInt(path);
    }

}
