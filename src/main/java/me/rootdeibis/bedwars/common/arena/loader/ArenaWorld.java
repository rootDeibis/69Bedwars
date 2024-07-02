package me.rootdeibis.bedwars.common.arena.loader;


import me.rootdeibis.bedwars.common.arena.ArenaConfig;
import org.bukkit.*;

public class ArenaWorld {

    private final String worldName;
    private World world;

    public ArenaWorld(String worldName) {
        this.worldName = worldName;
        this.world = Bukkit.getServer().getWorld(worldName);
    }


    public void create() {
        WorldCreator worldCreator = new WorldCreator(this.worldName);
        worldCreator.generateStructures(false);
        worldCreator.type(WorldType.FLAT);
        worldCreator.generatorSettings("2;0;1;");

        this.world = Bukkit.createWorld(worldCreator);

    }


    public void save() {
        this.world.save();
    }

    public World getWorld() {
        return world;
    }
}
