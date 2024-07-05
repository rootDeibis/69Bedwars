package me.rootdeibis.bedwars.common.database;



public interface IDatabase {

    void prepare();


    IPlayerDB getPlayerDB();


    void shutdown();



}
