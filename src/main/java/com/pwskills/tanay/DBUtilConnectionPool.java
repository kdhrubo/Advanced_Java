package com.pwskills.tanay;


import com.mysql.cj.jdbc.MysqlConnectionPoolDataSource;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.Properties;

public final class DBUtilConnectionPool {
    private static Properties properties = null;
    private static MysqlConnectionPoolDataSource dataSource = null;

    private DBUtilConnectionPool() {}

    static {
        FileInputStream fis = null;
        String fileInfo = "/Users/tanayjoshi/IdeaProjects/Advanced_Java/src/com/pwskills/tanay/properties/database.properties";

        try {
            fis = new FileInputStream(fileInfo);
            if (fis != null) {
                properties = new Properties();
                properties.load(fis);
            }
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            if (fis != null) {
                try {
                    fis.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
        }

        // Initialize dataSource
        dataSource = new MysqlConnectionPoolDataSource();
        dataSource.setURL(properties.getProperty("pwskills_octbatch.url"));
        dataSource.setUser(properties.getProperty("pwskills_octbatch.user"));
        dataSource.setPassword(properties.getProperty("pwskills_octbatch.password"));
    }

    public static Connection getDBConnection(String dbName) throws IOException, SQLException {
        // This method can be used to get connections for different databases if needed
        // For now, it's set to "pwskills_octbatch" as per original code
        return dataSource.getConnection();
    }
}
