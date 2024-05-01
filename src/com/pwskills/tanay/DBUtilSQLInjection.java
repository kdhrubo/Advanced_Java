package com.pwskills.tanay;


import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

public final class DBUtilSQLInjection {
    private static Properties properties = null;
    private DBUtilSQLInjection(){}

    static {
        FileInputStream fis = null;
        String fileInfo = "/Users/tanayjoshi/IdeaProjects/Advanced_Java/src/com/pwskills/tanay/properties/databasesqlinjection.properties";
        System.out.println(properties);
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

}


