package azerot.azerot.DB;








import azerot.azerot.WorldUtils;
import azerot.azerot.azerot;


import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.Getter;
import org.bukkit.Bukkit;
import org.bukkit.plugin.Plugin;
import org.bukkit.scheduler.BukkitRunnable;

import java.io.File;
import java.io.PrintWriter;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Properties;


public class RPGDataBase {
    private String host = "localhost";

    public static HikariDataSource hikari = null;



    public String getHost() {
        return this.host;
    }

    private String port = "3306";

    public String getPort() {
        return this.port;
    }

    private static HikariConfig config = new HikariConfig();

    private String database = "s265432_misferia";

    public String getDatabase() {
        return this.database;
    }

    private String username = "u265432_BEs9Q3r4Uj";

    public String getUsername() {
        return this.username;
    }

    private String password = "d9f50gI#@s9Y";

    @Getter
    private Connection connection;

    public String getPassword() {
        return this.password;
    }

    public boolean isConnected() throws SQLException {
        return (hikari != null || hikari.getConnection() != null);
    }

    public void connect() throws ClassNotFoundException, SQLException {

        config.setUsername("u265432_BEs9Q3r4Uj");
        config.setPassword("OLX7LQmdqq!i@C3wFeHrIqjy");
        config.setJdbcUrl("jdbc:mysql://u265432_BEs9Q3r4Uj:OLX7LQmdqq!i%40C3wFeHrIqjy@mysql-ru-r1.joinserver.xyz:3306/s265432_misferia");
        config.setDriverClassName("com.mysql.cj.jdbc.Driver");
        config.setConnectionTimeout(500000L);
        config.setMaximumPoolSize(20);
        config.setAutoCommit(true);
        hikari = new HikariDataSource(config);
    }

    public void disconnect() throws SQLException {
        if (isConnected())
            try {
                getHikari().getConnection().close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
    }

    public HikariDataSource getHikari() {
        return hikari;
    }

}
