package me.rootdeibis.bedwars.common.player;

import java.util.HashMap;
import java.util.UUID;

public class PlayerManager {
    private static HashMap<UUID, IPlayer> PLAYERS = new HashMap<>();


    public static void push(UUID uuid) {
        if(!PLAYERS.containsKey(uuid))
            PLAYERS.put(uuid, new BedWarsPlayer(uuid));

    }
    public static IPlayer get(UUID uuid) {
        if(!PLAYERS.containsKey(uuid))
            push(uuid);

        return PLAYERS.get(uuid);
    }


}
