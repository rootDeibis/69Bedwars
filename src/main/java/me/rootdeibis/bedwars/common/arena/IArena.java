package me.rootdeibis.bedwars.common.arena;

import me.rootdeibis.bedwars.common.arena.enums.Status;
import me.rootdeibis.bedwars.common.arena.loader.ArenaWorld;
import me.rootdeibis.bedwars.common.player.IPlayer;

import java.util.List;

public interface IArena {

    String getName();
    String getDisplayName();

    ArenaWorld getArenaWorld();

    List<IPlayer> getPlayers();

    Status getStatus();


    ArenaConfig getConfiguration();



}
