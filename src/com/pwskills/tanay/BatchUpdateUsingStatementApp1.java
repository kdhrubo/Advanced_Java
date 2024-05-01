package com.pwskills.tanay;

import com.pwskills.utility.DBUtil;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class BatchUpdateUsingStatementApp1 {
    // Driver Code
    public static void main(String[] args) {
        try (Connection connection = DBUtil.getDBConnection("pwskills_octbatch")) {
            try (Statement statement = connection.createStatement()) {

                // Adding the queries to batch file
                statement.addBatch("INSERT INTO employee (ename, esal, eaddress) VALUES ('shahid', 25000, 'RCB')");
                statement.addBatch("UPDATE employee SET esal = esal + 1000 WHERE esal < 30000");
                statement.addBatch("DELETE FROM employee WHERE esal > 35000");

                // Execute the batch
                int[] count = statement.executeBatch();

                int updateCount = 0;
                // Process the output
                for (int result : count) {
                    updateCount += result;
                }
                System.out.println("No of rows altered is: " + updateCount);
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
