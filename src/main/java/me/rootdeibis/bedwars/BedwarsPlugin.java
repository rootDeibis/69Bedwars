package me.rootdeibis.bedwars;

import me.rootdeibis.bedwars.common.database.IDatabase;
import me.rootdeibis.bedwars.common.database.sql.MySQLDatabase;
import org.bukkit.Bukkit;
import org.bukkit.plugin.java.JavaPlugin;


import java.util.UUID;
import java.util.logging.Logger;

public final class BedwarsPlugin extends JavaPlugin {

    public static Logger logger = Bukkit.getLogger();
    private static BedwarsPlugin bedwarsPlugin;

    private IDatabase database;

    @Override
    public void onEnable() {
        bedwarsPlugin = this;
       this.database = new MySQLDatabase("localhost", "bedwars", "sycraft", "31025713", 3306, false, false, 20,200);
       this.database.prepare();
    }

    @Override
    public void onDisable() {
        this.database.shutdown();
    }

    public IDatabase getDatabase() {
        return database;
    }

    public static BedwarsPlugin getInstance() {
        return bedwarsPlugin;
    }
}
