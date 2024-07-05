package me.rootdeibis.bedwars.common.database.sql.managers;

import me.rootdeibis.bedwars.common.database.IPlayerDB;
import me.rootdeibis.bedwars.common.database.enums.SQLQueries;
import me.rootdeibis.bedwars.common.database.sql.MySQLDatabase;
import me.rootdeibis.bedwars.common.player.IPlayer;
import me.rootdeibis.bedwars.common.player.PlayerManager;
import me.rootdeibis.bedwars.common.player.data.PlayerStatsData;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class PlayerSQL implements IPlayerDB {

    private final MySQLDatabase database;

    public PlayerSQL(MySQLDatabase database) {
        this.database = database;
    }

    @Override
    public boolean create(UUID uuid) {

        try {
            Connection connection = this.database.getDataSource().getConnection();
            PreparedStatement playerStatement = SQLQueries.CREATE_PLAYER.prepare(connection,
                    uuid.toString(), new Timestamp(System.currentTimeMillis()));


            playerStatement.execute();


            PreparedStatement levelStatement = SQLQueries.CREATE_PLAYER_LEVEL.prepare(connection,
                    uuid.toString(), 0);


            levelStatement.execute();

            PreparedStatement statsStatement = SQLQueries.CREATE_PLAYER_STATS.prepare(connection, uuid.toString(),
                    0,0,0,0,0,0,0);

            statsStatement.execute();


            return true;

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return false;
    }

    @Override
    public boolean has(UUID uuid) {

        try {
            Connection connection = this.database.getDataSource().getConnection();
            PreparedStatement playerStatement = SQLQueries.HAS_PLAYER.prepare(connection,
                    uuid.toString());


            return playerStatement.executeQuery().next();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public double getLevel(UUID uuid) {
        try {
            Connection connection = this.database.getDataSource().getConnection();
            PreparedStatement playerStatement = SQLQueries.GET_PLAYER_LEVEL.prepare(connection,
                    uuid.toString());

            ResultSet rs =  playerStatement.executeQuery();

            if(rs.next()) {
                return rs.getDouble("experience");
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return 0;
    }

    @Override
    public boolean setLevel(UUID uuid, double experience) {
        try {
            Connection connection = this.database.getDataSource().getConnection();
            PreparedStatement levelStatement = SQLQueries.SET_PLAYER_LEVEL.prepare(connection,
                    experience, uuid.toString());

            return levelStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return false;
    }

    @Override
    public Integer[] getStats(UUID uuid) {
        List<Integer> stats = new ArrayList<>();

        try {
            Connection connection = this.database.getDataSource().getConnection();
            PreparedStatement playerStatement = SQLQueries.GET_PLAYER_STATS.prepare(connection,
                    uuid.toString());

            ResultSet rs =  playerStatement.executeQuery();

            if(rs.next()) {
                stats.add(rs.getInt("wins"));
                stats.add(rs.getInt("played"));
                stats.add(rs.getInt("kills"));
                stats.add(rs.getInt("final_kills"));
                stats.add(rs.getInt("deaths"));
                stats.add(rs.getInt("final_deaths"));
                stats.add(rs.getInt("beds_broken"));
            }

        } catch (SQLException e) {
            e.printStackTrace();

        }


        return stats.toArray(new Integer[0]);
    }

    @Override
    public boolean setStats(UUID uuid) {
        IPlayer player = PlayerManager.get(uuid);
        PlayerStatsData stats = player.getStatsData();
        try {
            Connection connection = this.database.getDataSource().getConnection();
            PreparedStatement levelStatement = SQLQueries.SET_PLAYER_STATS.prepare(connection, stats.getWins(), stats.getPlayed(), stats.getKills(),
                    stats.getFinalKills(),stats.getDeaths(), stats.getFinalDeaths(),stats.getBedsBroken(), uuid.toString());

            return levelStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }

        return false;
    }




}
