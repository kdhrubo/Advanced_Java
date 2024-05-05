package main.java.com.pwskills.tanay;
import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class HikariCPConfigurationApp1 {
    private static final String SQL_SELECT_QUERY = "SELECT eid,ename,esal,eaddress FROM employee";
    public static void main(String[] args) {
        HikariConfig config = new HikariConfig("/Users/tanayjoshi/IdeaProjects/Advanced_Java/src/main/java/com/pwskills/tanay/properties/hikaricp.properties");

        try (HikariDataSource dataSource = new HikariDataSource(config)){
            try(Connection connection = dataSource.getConnection()){
                try(Statement statement = connection.createStatement()) {
                    try(ResultSet resultSet = statement.executeQuery(SQL_SELECT_QUERY)){
                        System.out.println("EID\tENAME\tESAL\tEADDRESS");
                        while (resultSet.next())
                        {
                            System.out.println(resultSet.getInt(1)
                                    +"\t"+resultSet.getString(2)
                                    +"\t"+resultSet.getInt(3)
                                    +"\t"+resultSet.getString(4));
                        }
                    }
                }
            }
            catch (SQLException e){
                e.printStackTrace();
            }
        }
    }
}
