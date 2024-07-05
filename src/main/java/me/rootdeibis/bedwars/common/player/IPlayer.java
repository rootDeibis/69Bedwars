package me.rootdeibis.bedwars.common.player;

import me.rootdeibis.bedwars.common.arena.Arena;
import me.rootdeibis.bedwars.common.enums.PlayerStatus;
import org.bukkit.entity.Player;

import java.util.UUID;

public interface IPlayer {

    Player getPlayer();

    Arena getPlayerArena();

    PlayerStatus getStatus();

    UUID getUUID();


    void update();





}
