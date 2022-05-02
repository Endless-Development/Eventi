package kheeto.endlessnetwork;

import kheeto.endlessnetwork.Commands.HostCommand;
import net.md_5.bungee.api.ProxyServer;
import net.md_5.bungee.api.plugin.Plugin;
import net.md_5.bungee.config.Configuration;
import net.md_5.bungee.config.ConfigurationProvider;
import net.md_5.bungee.config.YamlConfiguration;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.util.logging.Level;

public final class EventiBungee extends Plugin {

    private static EventiBungee instance;
    private static Configuration config;
    private File configFile;

    @Override
    public void onEnable(){
        instance = this;

        // Loads Config
        Configuration loadedConfig = GetOrCreateConfiguration();
        if(loadedConfig != null) config = loadedConfig;


        // Register Commands
        getLogger().info("Plugin EventiBungee attivato!");
        ProxyServer.getInstance().getPluginManager().registerCommand(this, new HostCommand());
    }

    @Override
    public void onDisable() {
        getLogger().severe("Plugin EventiBungee disattivato!!");
    }

    public static EventiBungee getInstance() {
        return instance;
    }

    public static Configuration getConfig() {
        return config;
    }

    private Configuration GetOrCreateConfiguration() {

        Configuration newConfig = null;

        configFile = new File(ProxyServer.getInstance().getPluginsFolder() + "/EventiBungee/config.yml");

        try {
            // Check if the file already exists and if it doesn't, creates a new file.
            if(configFile.exists()) {
                newConfig = setConfigValues(configFile);
                ConfigurationProvider.getProvider(YamlConfiguration.class).save(newConfig, configFile);
            } else {
                if(configFile.createNewFile()) {
                    newConfig = setConfigValues(configFile);
                    ConfigurationProvider.getProvider(YamlConfiguration.class).save(newConfig, configFile);
                } else {
                    getLogger().severe("Couldn't create a new config file");
                };

            }
        } catch (IOException e) {
            e.printStackTrace();
        }

        return newConfig;

    };

    private Configuration setConfigValues(File file) throws IOException {
        Configuration cfg = ConfigurationProvider.getProvider(YamlConfiguration.class).load(file);

        cfg.set("UHC_Server", "uhc");
        cfg.set("UHC", "uhc");
        cfg.set("Spleef_Server", "spleef");
        cfg.set("Spleef", "spleef");
        cfg.set("Parkour_Server", "parkour");
        cfg.set("Parkour", "parkour");
        cfg.set("Sumo_Server", "sumo");
        cfg.set("Sumo", "sumo");
        cfg.set("Sender_Message", "§aHai hostato un evento!");
        cfg.set("No_Event", "§cDevi specificare un evento! (spleef/parkour/uhc/sumo)");
        cfg.set("No_Permission", "§cNon hai i permessi adatti per usare quel comando!");

        return cfg;
    }


}
