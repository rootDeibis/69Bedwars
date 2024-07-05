package me.rootdeibis.bedwars.common.database.enums;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;

public enum SQLQueries {

    CREATE_USERS_TABLE("CREATE TABLE IF NOT EXISTS players ( uuid VARCHAR(255) NOT NULL PRIMARY KEY, joined_at TIMESTAMP )"),
    CREATE_LEVELS_TABLE("CREATE TABLE IF NOT EXISTS players_experience ( player_uuid VARCHAR(255) PRIMARY KEY, experience DOUBLE, FOREIGN KEY (player_uuid) REFERENCES players(uuid) );"),
    CREATE_STATS_TABLE("CREATE TABLE IF NOT EXISTS players_stats ( player_uuid VARCHAR(255) PRIMARY KEY, deaths INTEGER, kills INTEGER, wins INTEGER, played INTEGER, final_kills INTEGER, final_deaths INTEGER, beds_broken INTEGER, FOREIGN KEY (player_uuid) REFERENCES players(uuid) );"),

    CREATE_QUICKSHOP_TABLE("CREATE TABLE IF NOT EXISTS player_quick_shop ( player_uuid VARCHAR(255) PRIMARY KEY, slots VARCHAR(255), FOREIGN KEY (player_uuid) REFERENCES players(uuid) )"),


    CREATE_PLAYER("INSERT INTO players (uuid, joined_at) VALUES (?, ?)"),
    CREATE_PLAYER_LEVEL("INSERT INTO players_experience (player_uuid,experience) VALUES (?,?)"),
    CREATE_PLAYER_STATS("INSERT INTO players_stats (player_uuid, wins, played, kills, final_kills, deaths, final_deaths, beds_broken) VALUES (?,?,?,?,?,?,?,?)"),
    CREATE_PLAYER_QUICKSHOP(""),

    HAS_PLAYER("SELECT 1 FROM players WHERE uuid = ?"),

    GET_PLAYER_LEVEL("SELECT * FROM players_experience WHERE player_uuid = ?"),
    SET_PLAYER_LEVEL("UPDATE players_experience SET experience = ? WHERE player_uuid = ?"),

    GET_PLAYER_STATS("SELECT * FROM players_stats WHERE player_uuid = ?"),

    SET_PLAYER_STATS("UPDATE players_stats SET wins = ? , played = ?, kills = ?, final_kills = ?, deaths = ?, final_deaths = ?, beds_broken = ? WHERE player_uuid = ?");
    private final String query;

    SQLQueries(String query) {
        this.query = query;
    }

    public String getQuery() {
        return query;
    }

    public PreparedStatement prepare(Connection connection,Object... values) throws SQLException {
        PreparedStatement preparedStatement = connection.prepareStatement(this.query);

        for(int i = 0; i < values.length; i++) {
            preparedStatement.setObject(i +  1, values[i]);
        }


        return preparedStatement;


    }

    public boolean run(Connection connection) throws SQLException {
        Statement statement = connection.createStatement();
        return statement.execute(this.query);

    }
}
