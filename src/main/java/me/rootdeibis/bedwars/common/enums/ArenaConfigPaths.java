package me.rootdeibis.bedwars.common.enums;

public enum ArenaConfigPaths {

    DISPLAY_NAME("displayname"),
    SPECTATOR_SPAWN("spectator-loc"),

    MIN_TEAMS("min-team-start-game"),
    MIN_PLAYER_PER_TEAM("player-per-team.min"),

    MAX_PLAYEr_PER_TEAM("player-per-team.max"),

    WAITING_SPAWN("waiting-spawn.location"),

    WAITING_SPAWN_POS1("waiting-spawn.pos1"),
    WAITING_SPAWN_POS2("waiting-spawn.pos2"),

    TEAMS_PATH("teams"),

    TEAM_RESOURCE_GENERATOR(TEAMS_PATH.get() + ".%s.generator"),

    TEAM_SHOP_LOCATION(TEAMS_PATH.get() + ".%s.shop-npc"),

    TEAM_UPGRADE_LOCATION(TEAMS_PATH.get() + ".%s.upgrade-npc"),

    TEAM_RESPAWN_LOCATION(TEAMS_PATH.get() + ".%s.respawn-location"),

    RESOURCE_GENERATORS("generators.resource"),

    UPGRADE_GENERATORS("generators.upgrade"),

    SPECIAL_GENERATORS("generators.special");

    private final String path;

     ArenaConfigPaths(String path) {
        this.path = path;
    }

    public String get(){
         return this.path;
    }

    public String get(Object... values) {
         return String.format(this.get(), values);
    }

}
