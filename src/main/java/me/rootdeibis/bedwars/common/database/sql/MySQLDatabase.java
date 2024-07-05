package me.rootdeibis.bedwars.common.database.sql;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import me.rootdeibis.bedwars.BedwarsPlugin;
import me.rootdeibis.bedwars.common.database.IDatabase;
import me.rootdeibis.bedwars.common.database.IPlayerDB;
import me.rootdeibis.bedwars.common.database.enums.SQLQueries;
import me.rootdeibis.bedwars.common.database.sql.managers.PlayerSQL;
import me.rootdeibis.bedwars.common.utils.LoggerUtils;
import org.bukkit.Bukkit;
import org.bukkit.ChatColor;

import java.sql.SQLException;

import java.util.concurrent.TimeUnit;


public class MySQLDatabase implements IDatabase {

    private static final LoggerUtils logger = new LoggerUtils(MySQLDatabase.class);
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

        logger.debug("Connected to the database...");

        try {
            this.dataSource = new HikariDataSource(hikariConfig);

            this.createTables();
            this.playerDB = new PlayerSQL(this);

            logger.success("The connection to the database was successfully established.");

        }catch (Exception e) {

            logger.error("Could not connect to the database, please check your details.");
            logger.error(e);
            Bukkit.getPluginManager().disablePlugin(BedwarsPlugin.getInstance());
        }


    }

    @Override
    public void shutdown() {
        if(this.dataSource == null) return;

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


}
