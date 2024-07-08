package me.rootdeibis.bedwars.common.arena;

import me.rootdeibis.bedwars.common.configuration.RFile;
import me.rootdeibis.bedwars.common.enums.ArenaConfigPaths;

import java.util.Arrays;

public class ArenaConfig {


    private final RFile config;

    public ArenaConfig(RFile config) {
        this.config = config;
    }

    public void set(ArenaConfigPaths path, Object value, Object... format) {
        this.config.set(path.get(format), value);
    }

    public boolean isSet(ArenaConfigPaths... path) {
        return Arrays.stream(path).allMatch(p -> this.config.contains(p.get()));
    }



    public String getName() {
        return this.config.getFile().getName().replaceAll(".yml", "");
    }
    public RFile getConfig() {
        return config;
    }
}
