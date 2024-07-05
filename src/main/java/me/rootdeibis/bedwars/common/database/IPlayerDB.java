package me.rootdeibis.bedwars.common.database;



import java.util.UUID;

public interface IPlayerDB {

    boolean has(UUID uuid);
    boolean create(UUID uuid);

    double getLevel(UUID uuid);
    boolean setLevel(UUID uuid, double experience);

    Integer[] getStats(UUID uuid);

    boolean setStats(UUID uuid);








}
