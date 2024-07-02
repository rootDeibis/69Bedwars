package me.rootdeibis.bedwars.common.player;

import me.rootdeibis.bedwars.common.arena.Arena;
import me.rootdeibis.bedwars.common.player.enums.PlayerStatus;
import org.bukkit.entity.Player;

public interface IPlayer {

    Player getPlayer();

    Arena getPlayerArena();

    PlayerStatus getStatus();




}
