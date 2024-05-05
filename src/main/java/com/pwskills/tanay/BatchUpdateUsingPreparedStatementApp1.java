package com.pwskills.tanay;


import com.pwskills.utility.DBUtil;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class BatchUpdateUsingPreparedStatementApp1 {
    private static final String SQL_INSERT_QUERY = "INSERT INTO employee (ename, esal, eaddress) VALUES (?,?,?)";
    public static void main(String[] args) {
        try (Connection connection = DBUtil.getDBConnection("pwskills_octbatch")) {
            try (PreparedStatement preparedStatement = connection.prepareStatement(SQL_INSERT_QUERY)) {

                Scanner scanner = new Scanner(System.in);

                while (true){
                    System.out.print("Enter the employee name: ");
                    String ename = scanner.next();

                    System.out.print("Enter the employee salary: ");
                    int esal = scanner.nextInt();

                    System.out.print("Enter the employee address: ");
                    String eaddress = scanner.next();

                    preparedStatement.setString(1,ename);
                    preparedStatement.setInt(2,esal);
                    preparedStatement.setString(3,eaddress);

                    //Adding the query to batch file
                    preparedStatement.addBatch();

                    System.out.println("Do you want to enter one more record: [Yes/No]");
                    String option = scanner.next();
                    if (option.equalsIgnoreCase("no")){
                        break;
                    }
                }
                // Execute the batch
                preparedStatement.executeBatch();
                System.out.println("Records inserted successfully!");
                scanner.close();
            }

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}
