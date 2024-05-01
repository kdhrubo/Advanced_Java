package com.pwskills.tanay;


import com.pwskills.utility.DBUtil;

import java.sql.*;
import java.util.Scanner;


public class JDBCTransactionApp2 {
    private static final String SQL_INSERT_QUERY = "INSERT INTO employee (ename, esal, eaddress) VALUES (?,?,?)";
    public static void main(String[] args) {
        try (Connection connection = DBUtil.getDBConnection("pwskills_octbatch")) {

            try(Statement statement = connection.createStatement())
            {
                //Disabling the auto commit feature
                connection.setAutoCommit(false);

                statement.executeUpdate("INSERT INTO politicians(name,party) VALUES ('rahul','congress')");
                statement.executeUpdate("INSERT INTO politicians(name,party) VALUES ('modi','bjp')");

                Savepoint sp = connection.setSavepoint();

                statement.executeUpdate("INSERT INTO politicians(name,party) VALUES ('siddu','bjp')");

                System.out.println("OOps! Something went wrong, Operations are Rolled back to last Save Point");
                connection.rollback(sp);

                connection.commit();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


