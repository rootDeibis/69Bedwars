package me.rootdeibis.bedwars.common.player;

import me.rootdeibis.bedwars.BedwarsPlugin;
import me.rootdeibis.bedwars.common.arena.Arena;
import me.rootdeibis.bedwars.common.database.IDatabase;
import me.rootdeibis.bedwars.common.enums.PlayerStatus;
import me.rootdeibis.bedwars.common.player.data.PlayerLevelData;
import me.rootdeibis.bedwars.common.player.data.PlayerStatsData;
import org.bukkit.Bukkit;
import org.bukkit.entity.Player;

import java.util.UUID;

public class BedWarsPlayer implements IPlayer{

    private final UUID uuid;

    private final PlayerLevelData playerLevelData;
    private final PlayerStatsData playerStatsData;

    public BedWarsPlayer(UUID uuid) {
        this.uuid = uuid;
        this.playerLevelData = new PlayerLevelData(this);
        this.playerStatsData = new PlayerStatsData(this);

        IDatabase database = BedwarsPlugin.getInstance().getDatabase();

        System.out.println(database.getPlayerDB().has(uuid));
        if(!database.getPlayerDB().has(uuid)) {
            database.getPlayerDB().create(uuid);
        } else {
            this.playerLevelData.fetch();
            this.playerStatsData.fetch();
        }
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
    public PlayerLevelData getLevelData() {
        return playerLevelData;
    }

    @Override
    public PlayerStatsData getStatsData() {
        return playerStatsData;
    }

    @Override
    public UUID getUUID() {
        return uuid;
    }

    @Override
    public void update() {
        this.playerLevelData.update();
        this.playerStatsData.update();
    }
}
