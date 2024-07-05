package me.rootdeibis.bedwars.common.database.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.rootdeibis.bedwars.BedwarsPlugin;
import me.rootdeibis.bedwars.common.database.IDatabase;
import me.rootdeibis.bedwars.common.database.IPlayerDB;
import me.rootdeibis.bedwars.common.database.enums.SQLQueries;
import me.rootdeibis.bedwars.common.database.sql.managers.PlayerSQL;
import me.rootdeibis.bedwars.common.player.IPlayer;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Timestamp;

import java.util.UUID;
import java.util.concurrent.TimeUnit;


public class MySQLDatabase implements IDatabase {

    private HikariDataSource dataSource;
    private final String host;
    private final String database;
    private final String user;
    private final String pass;
    private final int port;
    private final boolean ssl;
    private final boolean certificateVerification;
    private final int poolSize;
    private final int maxLifetime;


    private IPlayerDB playerDB;


    public MySQLDatabase(String host, String db, String user, String pass, int port, boolean ssl,  boolean certificateVerification, int poolSize, int maxLifetime) {
        this.host = host;
        this.database = db;
        this.user = user;
        this.pass = pass;
        this.port = port;
        this.ssl = ssl;
        this.certificateVerification = certificateVerification;
        this.poolSize = poolSize;
        this.maxLifetime = maxLifetime;


    }

    @Override
    public void prepare() {

        HikariConfig hikariConfig = new HikariConfig();
        hikariConfig.setPoolName("69BedwarsMySQLPool");



        hikariConfig.setMaximumPoolSize(poolSize);
        hikariConfig.setMaxLifetime(maxLifetime * 1000L);


        hikariConfig.setJdbcUrl("jdbc:mysql://" + host + ":" + port + "/" + database);

        hikariConfig.setUsername(user);
        hikariConfig.setPassword(pass);


        hikariConfig.addDataSourceProperty("useSSL", String.valueOf(ssl));
        if (!certificateVerification) {
            hikariConfig.addDataSourceProperty("verifyServerCertificate", String.valueOf(false));
        }

        hikariConfig.addDataSourceProperty("characterEncoding", "utf8");
        hikariConfig.addDataSourceProperty("encoding", "UTF-8");
        hikariConfig.addDataSourceProperty("useUnicode", "true");

        hikariConfig.addDataSourceProperty("rewriteBatchedStatements", "true");
        hikariConfig.addDataSourceProperty("jdbcCompliantTruncation", "false");

        hikariConfig.addDataSourceProperty("cachePrepStmts", "true");
        hikariConfig.addDataSourceProperty("prepStmtCacheSize", "275");
        hikariConfig.addDataSourceProperty("prepStmtCacheSqlLimit", "2048");

        hikariConfig.addDataSourceProperty("socketTimeout", String.valueOf(TimeUnit.SECONDS.toMillis(30)));

        this.dataSource = new HikariDataSource(hikariConfig);



        try {
            dataSource.getConnection();
            this.createTables();

            this.playerDB = new PlayerSQL(this);

        } catch (SQLException e) {
           e.printStackTrace();
        }


    }

    @Override
    public void shutdown() {
        if(!this.dataSource.isClosed()) {
            this.dataSource.close();
        }
    }

    @Override
    public IPlayerDB getPlayerDB() {
        return this.playerDB;
    }

    private void createTables() {
        SQLQueries[] queries = {SQLQueries.CREATE_USERS_TABLE, SQLQueries.CREATE_LEVELS_TABLE,SQLQueries.CREATE_STATS_TABLE, SQLQueries.CREATE_QUICKSHOP_TABLE};

        for (SQLQueries query : queries) {

            try {
                dataSource.getConnection().createStatement().executeUpdate(query.getQuery());
            } catch (SQLException e) {

                System.out.println(query.getQuery());
                throw new RuntimeException(e);
            }

        }

    }

    public HikariDataSource getDataSource() {
        return dataSource;
    }

    public void createPlayer(UUID player) {

        try {

            // CREATE PLAYER ROW IN PLAYERS TABLE


            PreparedStatement playerStatement = this.dataSource.getConnection().prepareStatement(SQLQueries.CREATE_PLAYER.getQuery());

            playerStatement.setString(1, player.toString());
            playerStatement.setTimestamp(2, new Timestamp(System.currentTimeMillis()));
            playerStatement.setString(3, "rootDeibis");

            playerStatement.execute();



            // CREATE PLAYER LEVEL ROW IN LEVEL TABLE

            PreparedStatement levelStatement = this.dataSource.getConnection().prepareStatement(SQLQueries.CREATE_PLAYER_LEVEL.getQuery());

            levelStatement.setString(1, player.toString());
            levelStatement.setInt(2, 0);


            levelStatement.execute();

            // CREATE PLAYER STATS

            PreparedStatement statsStatement = this.dataSource.getConnection().prepareStatement(SQLQueries.CREATE_PLAYER_STATS.getQuery());


            statsStatement.setString(1, player.toString());
            statsStatement.setInt(2, 0);
            statsStatement.setInt(3, 0);
            statsStatement.setInt(4, 0);
            statsStatement.setInt(5, 0);
            statsStatement.setInt(6, 0);
            statsStatement.setInt(7, 0);
            statsStatement.setInt(8, 0);

            statsStatement.execute();

            BedwarsPlugin.logger.info("Player [" + player.toString() + " : " + "rootDeibis" + "]");



        } catch (SQLException e) {
            e.printStackTrace();
        }

    }

    public boolean existsPlayer(IPlayer player) {
        return false;
    }

    public void updatePlayer(IPlayer player) {

    }
}
