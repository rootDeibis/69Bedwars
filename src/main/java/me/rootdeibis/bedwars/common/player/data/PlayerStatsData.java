package me.rootdeibis.bedwars.common.player.data;

import me.rootdeibis.bedwars.common.player.IPlayer;


public class PlayerStatsData {

    private final IPlayer player;
    private int wins = 0;
    private int played = 0;

    private int kills = 0;
    private int finalKills = 0;

    private int deaths = 0;
    private int finalDeaths = 0;

    private int bedsBroken = 0;

    public PlayerStatsData(IPlayer player) {
        this.player = player;
    }

    public int getWins() {
        return wins;
    }

    public int getLosses() {
        return played == 0 ? 0 : played - wins;
    }
    public int getPlayed() {
        return played;
    }

    public int getKills() {
        return kills;
    }

    public int getFinalKills() {
        return finalKills;
    }

    public int getDeaths() {
        return deaths;
    }

    public int getFinalDeaths() {
        return finalDeaths;
    }

    public int getBedsBroken() {
        return bedsBroken;
    }

    public void setWins(int wins) {
        this.wins = wins;
    }

    public void setPlayed(int played) {
        this.played = played;
    }

    public void setKills(int kills) {
        this.kills = kills;
    }

    public void setFinalKills(int finalKills) {
        this.finalKills = finalKills;
    }

    public void setDeaths(int deaths) {
        this.deaths = deaths;
    }

    public void setFinalDeaths(int finalDeaths) {
        this.finalDeaths = finalDeaths;
    }

    public void setBedsBroken(int bedsBroken) {
        this.bedsBroken = bedsBroken;
    }


}
