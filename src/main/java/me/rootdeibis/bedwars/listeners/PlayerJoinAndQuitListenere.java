package me.rootdeibis.bedwars.listeners;

import me.rootdeibis.bedwars.BedwarsPlugin;
import me.rootdeibis.bedwars.common.player.PlayerManager;
import org.bukkit.ChatColor;
import org.bukkit.event.EventHandler;
import org.bukkit.event.Listener;
import org.bukkit.event.player.AsyncPlayerPreLoginEvent;
import org.bukkit.event.player.PlayerJoinEvent;
import org.bukkit.event.player.PlayerQuitEvent;

public class PlayerJoinAndQuitListenere implements Listener {

    @EventHandler()
    public void onPreLogin(AsyncPlayerPreLoginEvent e) {
        if(!BedwarsPlugin.getInstance().isRunning()) {
            e.setLoginResult(AsyncPlayerPreLoginEvent.Result.KICK_OTHER);
            e.setKickMessage(ChatColor.translateAlternateColorCodes('&',
                    "&cBedWars &7> Maps are loading, please try again later."));
        }
    }

    @EventHandler()
    public void onJoin(PlayerJoinEvent e) {
        PlayerManager.get(e.getPlayer().getUniqueId());

    }
    @EventHandler()
    public void onQuit(PlayerQuitEvent e) {
        PlayerManager.get(e.getPlayer().getUniqueId()).update();
    }
}
