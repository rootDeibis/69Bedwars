package me.rootdeibis.bedwars.common.player.data;

import me.rootdeibis.bedwars.BedwarsPlugin;
import me.rootdeibis.bedwars.common.player.IPlayer;

public class PlayerLevelData {

    private final IPlayer player;

    private double experience = 0;

    public PlayerLevelData(IPlayer player) {
        this.player = player;
    }

    public void fetch() {
        this.experience = BedwarsPlugin.getInstance().getDatabase().getPlayerDB().getLevel(this.player.getUUID());
    }

    public void setExperience(double experience) {
        this.experience = experience;
    }

    public double getExperience() {
        return experience;
    }
}
