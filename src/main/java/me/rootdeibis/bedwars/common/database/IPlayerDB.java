package me.rootdeibis.bedwars.common.database;

import me.rootdeibis.bedwars.common.player.IPlayer;

import java.util.UUID;

public interface IPlayerDB {

    boolean has(UUID uuid);
    IPlayer create(UUID uuid);

    double getLevel(UUID uuid);
    boolean setLevel(UUID uuid, double experience);






}
