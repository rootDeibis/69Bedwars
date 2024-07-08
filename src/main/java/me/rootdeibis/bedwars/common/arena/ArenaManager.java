package me.rootdeibis.bedwars.common.arena;

import me.rootdeibis.bedwars.BedwarsPlugin;
import me.rootdeibis.bedwars.common.configuration.FileManager;
import me.rootdeibis.bedwars.common.configuration.RDirectory;
import me.rootdeibis.bedwars.common.configuration.RFile;

import java.util.ArrayList;
import java.util.List;

public class ArenaManager {

    private static List<IArena> arenas = new ArrayList<>();

    public ArenaManager() {

    }

    public void loadMapsInDirectory() {
        getArenasInDirectory().forEach(file -> {

            IArena arena = new Arena(file);


            if(arena.getArenaWorld().getWorld() != null) {
                arenas.add(arena);
            }

        });
    }


    public static List<RFile> getArenasInDirectory() {
        FileManager fileManager = BedwarsPlugin.getInstance().getFileManager();
        RDirectory arenaDirectory = fileManager.dir("arenas").createIfNoExists();

        arenaDirectory.loadFilesIndirectory();

        return new ArrayList<>(arenaDirectory.getRFiles().values());
    }
}
