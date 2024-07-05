package me.rootdeibis.bedwars.listeners;

import me.rootdeibis.bedwars.BedwarsPlugin;
import org.bukkit.Bukkit;

public class Listeners {

    public static void register() {
        Bukkit.getPluginManager().registerEvents(new PlayerJoinAndQuitListenere(), BedwarsPlugin.getInstance());
    }
}
