package me.rootdeibis.bedwars.common.arena.loader;


import me.rootdeibis.bedwars.BedwarsPlugin;
import me.rootdeibis.bedwars.common.configuration.RDirectory;
import me.rootdeibis.bedwars.common.utils.LoggerUtils;
import me.rootdeibis.bedwars.common.utils.ZipUtils;
import org.bukkit.Bukkit;
import org.bukkit.World;
import org.bukkit.WorldCreator;
import org.bukkit.WorldType;

import java.io.File;
import java.io.IOException;

public class ArenaWorld {

    private final LoggerUtils logger = new LoggerUtils(ArenaWorld.class);
    private final String worldName;

    private final RDirectory cacheDir;
    private final String zipFilePath;
    private World world;

    public ArenaWorld(String worldName) {
        this.worldName = worldName;
        this.cacheDir = BedwarsPlugin.getInstance().getFileManager().dir("cache");
        this.cacheDir.createIfNoExists();
        this.zipFilePath = cacheDir.getFile().getAbsolutePath() + "\\" + worldName + ".zip";

        this.loadWorld();

    }

    public void loadWorld() {
        File worldContainer = new File(Bukkit.getWorldContainer(), this.worldName);

        if(worldContainer.exists() && worldContainer.isDirectory()) {
           Bukkit.unloadWorld(this.worldName,  false);
           worldContainer.delete();
        }

        if(!new File(this.zipFilePath).exists()) {
            logger.debug("Creating world: " + this.worldName);
            this.create();
        } else {
            logger.debug("Extracting world "+this.worldName+" from cache...");
            this.unzipWorld();
        }

        WorldCreator creator = new WorldCreator(this.worldName);
        voidWorld(creator);

        this.world = creator.createWorld();


        logger.success("World " + this.worldName + " loaded");
    }


    public void create() {
        WorldCreator worldCreator = new WorldCreator(this.worldName);
        voidWorld(worldCreator);
    }

    private void voidWorld(WorldCreator creator) {
        creator.generateStructures(false);
        creator.type(WorldType.FLAT);
        creator.generatorSettings("2;0;1;");
    }


    public void zipWorld() {
            ZipUtils.zipDirectory(this.world.getWorldFolder(), this.zipFilePath);

    }

    public void unzipWorld() {
        try {
            ZipUtils.unZipFolder(this.zipFilePath, Bukkit.getServer().getWorldContainer().getAbsolutePath());
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
    }



    public void save() {
        Bukkit.unloadWorld(this.worldName, true);
        this.zipWorld();
    }

    public void restore() {
        this.world.getWorldFolder().delete();

        this.unzipWorld();
        this.loadWorld();

    }

    public World getWorld() {
        return world;
    }
}
