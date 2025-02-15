package me.rootdeibis.bedwars.common.arena;

import me.rootdeibis.bedwars.common.enums.ArenaStatus;
import me.rootdeibis.bedwars.common.arena.loader.ArenaWorld;
import me.rootdeibis.bedwars.common.player.IPlayer;

import java.util.List;

public interface IArena {



    ArenaWorld getArenaWorld();

    List<IPlayer> getPlayers();

    ArenaStatus getStatus();


    ArenaConfig getConfiguration();

    boolean isConfigured();

    boolean isEditorMode();


}
