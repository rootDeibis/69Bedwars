package me.rootdeibis.bedwars.common.arena;

import me.rootdeibis.bedwars.common.arena.loader.ArenaWorld;
import me.rootdeibis.bedwars.common.configuration.RFile;
import me.rootdeibis.bedwars.common.enums.ArenaStatus;
import me.rootdeibis.bedwars.common.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class Arena implements IArena {



    private final List<IPlayer> arenaPlayers = new ArrayList<>();

    private ArenaStatus arenaStatus = ArenaStatus.DISABLED;

    private final ArenaWorld arenaWorld;

    private final ArenaConfig arenaConfig;

    private boolean editor = false;

    public Arena(RFile arenaConfigFile) {
        this.arenaConfig = new ArenaConfig(arenaConfigFile);
        this.arenaWorld = new ArenaWorld(this.arenaConfig.getName());

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
    public ArenaStatus getStatus() {
        return arenaStatus;
    }

    @Override
    public ArenaConfig getConfiguration() {
        return arenaConfig;
    }

    @Override
    public boolean isConfigured() {
        return this.arenaConfig.availableToPlay();
    }

    @Override
    public boolean isEditorMode() {
        return editor;
    }

    public void setStatus(ArenaStatus arenaStatus) {
        this.arenaStatus = arenaStatus;
    }
}
