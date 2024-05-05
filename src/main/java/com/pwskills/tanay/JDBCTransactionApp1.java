package com.pwskills.tanay;


import com.pwskills.utility.DBUtil;

import java.sql.*;
import java.util.Scanner;


public class JDBCTransactionApp1 {
    private static final String SQL_INSERT_QUERY = "INSERT INTO employee (ename, esal, eaddress) VALUES (?,?,?)";
    public static void main(String[] args) {
        try (Connection connection = DBUtil.getDBConnection("pwskills_octbatch")) {

            try(Statement statement = connection.createStatement())
            {
                try(ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts"))
                {
                    System.out.println("Data before transaction....");
                    while (resultSet.next())
                    {
                        System.out.println(resultSet.getString(1)+ " "+ resultSet.getInt(2));
                    }
                }

                System.out.println();
                System.out.println("****Transaction Begins****");
                connection.setAutoCommit(false);

                //Starting the transaction
                statement.executeUpdate("UPDATE accounts SET balance = balance - 5000 where Name = 'sachin'");
                statement.executeUpdate("UPDATE accounts SET balance = balance + 5000 where Name = 'dhoni'");

                //Confirming the transaction
                Scanner scanner = new Scanner(System.in);
                System.out.println("Can you please confirm the transaction of rs5000: [yes/no]");
                String option = scanner.next();
                if (option.equalsIgnoreCase("yes")){
                    connection.commit();
                    System.out.println("Transaction commited");
                }
                else {
                    connection.rollback();
                    System.out.println("Transaction rolledback...");
                }

                System.out.println();
                //Printing the ResultSet information after the transaction
                try(ResultSet resultSet = statement.executeQuery("SELECT * FROM accounts"))
                {
                    System.out.println("Data after transaction....");
                    while (resultSet.next())
                    {
                        System.out.println(resultSet.getString(1)+ " "+ resultSet.getInt(2));
                    }
                }
                scanner.close();
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}

