package me.rootdeibis.bedwars;

import me.rootdeibis.bedwars.common.configuration.FileManager;
import me.rootdeibis.bedwars.common.configuration.RFile;
import me.rootdeibis.bedwars.common.database.IDatabase;
import me.rootdeibis.bedwars.common.database.sql.MySQLDatabase;

import me.rootdeibis.bedwars.listeners.Listeners;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.logging.Logger;

public final class BedwarsPlugin extends JavaPlugin {

    public static Logger logger = Bukkit.getLogger();
    private static BedwarsPlugin bedwarsPlugin;

    private IDatabase database;

    private FileManager fileManager;

    private boolean running = false;

    @Override
    public void onEnable() {
        bedwarsPlugin = this;

        Listeners.register();

        // Prepare FileManager
        this.fileManager = new FileManager(this);
        this.fileManager.setResourcesPath("");

        this.setupConfigurations();
        this.setupDatabase();

        running = true;


    }

    @Override
    public void onDisable() {
        this.database.shutdown();
    }

    public boolean isRunning() {
        return running;
    }

    public IDatabase getDatabase() {
        return database;
    }

    public static BedwarsPlugin getInstance() {
        return bedwarsPlugin;
    }

    private void setupConfigurations() {
        String settingsFile = "settings.yml";

        this.fileManager.use(settingsFile)
                .setDefaults(settingsFile)
                .create();


        this.fileManager.dir("arenas")
                .createIfNoExists();


    }
    private void setupDatabase() {
        RFile config = this.fileManager.use("settings.yml");

        String host = config.getString("database.host", "localhost");
        String database = config.getString("database.name");
        String user = config.getString("database.user");
        String pass = config.getString("database.password");
        int port = config.getInt("database.port", 3306);
        boolean ssl = config.getBoolean("database.ssl", false);
        boolean certificateVerification = config.getBoolean("database.verify-certificate", false);
        int poolSize = config.getInt("database.pool-size");
        int maxLifeTime = config.getInt("database.max-life-time");

        this.database = new MySQLDatabase(host, database, user, pass, port, ssl, certificateVerification, poolSize, maxLifeTime);
        this.database.prepare();

    }
}
