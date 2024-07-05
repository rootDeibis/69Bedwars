package me.rootdeibis.bedwars.common.arena;

import me.rootdeibis.bedwars.common.arena.loader.ArenaWorld;
import me.rootdeibis.bedwars.common.enums.ArenaStatus;
import me.rootdeibis.bedwars.common.player.IPlayer;

import java.util.ArrayList;
import java.util.List;

public class Arena implements IArena {

    private final String name;
    private final String displayName;

    private final List<IPlayer> arenaPlayers = new ArrayList<>();

    private ArenaStatus arenaStatus = ArenaStatus.DISABLED;

    private final ArenaWorld arenaWorld;

    private final ArenaConfig arenaConfig;

    private boolean editor = false;

    public Arena(String name, String displayName, String worldName) {
        this.name = name;
        this.displayName = displayName;
        this.arenaWorld = new ArenaWorld(worldName);
        this.arenaConfig = new ArenaConfig(this);

        if(this.arenaWorld.getWorld() == null) {
            this.arenaWorld.create();
            editor = true;
        }
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
    public ArenaStatus getStatus() {
        return arenaStatus;
    }

    @Override
    public ArenaConfig getConfiguration() {
        return arenaConfig;
    }

    @Override
    public boolean isEditorMode() {
        return editor;
    }

    public void setStatus(ArenaStatus arenaStatus) {
        this.arenaStatus = arenaStatus;
    }
}
