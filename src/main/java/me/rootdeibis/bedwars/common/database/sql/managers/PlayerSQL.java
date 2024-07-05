package me.rootdeibis.bedwars.common.database.sql.managers;

import me.rootdeibis.bedwars.common.database.IPlayerDB;
import me.rootdeibis.bedwars.common.database.enums.SQLQueries;
import me.rootdeibis.bedwars.common.database.sql.MySQLDatabase;
import me.rootdeibis.bedwars.common.player.IPlayer;

import java.sql.*;
import java.util.UUID;

public class PlayerSQL implements IPlayerDB {

    private final MySQLDatabase database;

    public PlayerSQL(MySQLDatabase database) {
        this.database = database;
    }


    @Override
    public boolean has(UUID uuid) {

        try {
            Connection connection = this.database.getDataSource().getConnection();
            PreparedStatement playerStatement = SQLQueries.HAS_PLAYER.prepare(connection,
                    uuid.toString());


            return playerStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();

        }
        return false;
    }

    @Override
    public double getLevel(UUID uuid) {
        try {
            Connection connection = this.database.getDataSource().getConnection();
            PreparedStatement playerStatement = SQLQueries.HAS_PLAYER.prepare(connection,
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
    public IPlayer create(UUID uuid) {

        try {
            Connection connection = this.database.getDataSource().getConnection();
            PreparedStatement playerStatement = SQLQueries.CREATE_PLAYER.prepare(connection,
                    uuid.toString(), new Timestamp(System.currentTimeMillis()));


            playerStatement.execute();

        } catch (SQLException e) {
            e.printStackTrace();
        }

        return null;
    }

    @Override
    public IPlayer update(UUID uuid) {
        return null;
    }
}
