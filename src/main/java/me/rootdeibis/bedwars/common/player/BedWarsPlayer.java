package me.rootdeibis.bedwars.common.player;

import me.rootdeibis.bedwars.common.arena.Arena;
import me.rootdeibis.bedwars.common.enums.PlayerStatus;
import me.rootdeibis.bedwars.common.player.data.PlayerLevelData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BedWarsPlayer implements IPlayer{

    private final UUID uuid;

    private final PlayerLevelData playerLevelData;

    public BedWarsPlayer(UUID uuid) {
        this.uuid = uuid;
        this.playerLevelData = new PlayerLevelData(this);
    }



    @Override
    public Player getPlayer() {
        return Bukkit.getPlayer(this.uuid);
    }

    @Override
    public Arena getPlayerArena() {
        return null;
    }

    @Override
    public PlayerStatus getStatus() {
        return null;
    }

    @Override
    public void update() {

    }

    @Override
    public UUID getUUID() {
        return uuid;
    }
}
