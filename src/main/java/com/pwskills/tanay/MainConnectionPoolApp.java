package main.java.com.pwskills.tanay;

import com.pwskills.utility.DBUtil;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

public class MainConnectionPoolApp {
    private static final String SQL_SELECT_QUERY = "SELECT eid,ename,esal,eaddress FROM employee";
    public static void main(String[] args) {
        try (Connection connection = DBUtil.getDBConnection("pwskills_octbatch")){
            try(Statement statement = connection.createStatement())
            {
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

        }//connection will be closed here: sent back to connection pool
        catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
    }
}
