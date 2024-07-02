package me.rootdeibis.bedwars.common.arena;

import me.rootdeibis.bedwars.common.arena.enums.Status;
import me.rootdeibis.bedwars.common.arena.loader.ArenaWorld;
import me.rootdeibis.bedwars.common.player.IPlayer;
import org.bukkit.World;

import java.util.ArrayList;
import java.util.List;

public class Arena implements IArena {

    private final String name;
    private final String displayName;

    private final List<IPlayer> arenaPlayers = new ArrayList<>();

    private Status arenaStatus = Status.DISABLED;

    private final ArenaWorld arenaWorld;

    private final ArenaConfig arenaConfig;

    public Arena(String name, String displayName, String worldName) {
        this.name = name;
        this.displayName = displayName;
        this.arenaWorld = new ArenaWorld(worldName);
        this.arenaConfig = new ArenaConfig(this);
    }
    @Override
    public String getName() {
        return name;
    }

    @Override
    public String getDisplayName() {
        return displayName;
    }

    @Override
    public ArenaWorld getArenaWorld() {
        return arenaWorld;
    }

    @Override
    public List<IPlayer> getPlayers() {
        return arenaPlayers;
    }

    @Override
    public Status getStatus() {
        return arenaStatus;
    }

    @Override
    public ArenaConfig getConfiguration() {
        return arenaConfig;
    }

    public void setStatus(Status arenaStatus) {
        this.arenaStatus = arenaStatus;
    }
}
