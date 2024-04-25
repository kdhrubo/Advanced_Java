package com.pwskills.utility;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Properties;

public final class DBUtil {
    private static Properties properties = null;
    private DBUtil(){}

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
        }
        finally {
            if (fis != null){
                try {
                    fis.close();
                }catch (IOException e){
                    e.printStackTrace();
                }
            }
        }
    }

    public static Connection getDBConnection(String dbName) throws IOException, SQLException {
        // Establishing the Connection
        return DriverManager.getConnection(properties.getProperty(dbName + ".url"),
                properties.getProperty(dbName + ".user"),
                properties.getProperty(dbName + ".password"));
    }

    public static void cleanUpResources (ResultSet resultSet, Statement statement, Connection connection){
        // Close the resources

        // Closing ResultSet
        if (resultSet != null)
        {
            try
            {
                resultSet.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        // Closing Statement
        if (statement != null)
        {
            try
            {
                statement.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }

        // Closing Connection
        if (connection != null)
        {
            try {
                connection.close();
            }
            catch (SQLException e)
            {
                e.printStackTrace();
            }
        }
    }
}
