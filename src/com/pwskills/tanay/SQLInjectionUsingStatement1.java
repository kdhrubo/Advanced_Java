package com.pwskills.tanay;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class SQLInjectionUsingStatement1 {
    public static void main(String[] args) {
        Scanner scanner = new Scanner(System.in);
        try (Connection connection = DBUtilSQLInjection.getDBConnection("jdbc:mysql://localhost:3306/pwskills_octbatch")){
            try(Statement statement = connection.createStatement()) {

                System.out.println("Enter the username: ");
                String username = scanner.next();
                username= "'"+username+"'";

                System.out.println("Enter the password: ");
                String password = scanner.next();
                password = "'"+password+"'";

                String sqlSelectQuery = "select count(*) from users where name ="+username+" and password = "+password+" ";
                System.out.println(sqlSelectQuery);

                try(ResultSet resultSet = statement.executeQuery(sqlSelectQuery)) {
                    int count = 0;
                  if (resultSet.next()){
                       count = resultSet.getInt(1);
                   }
                  if (count == 1){
                      System.out.println("Valid Credentials...");
                  }
                  else {
                      System.out.println("Invalid Credentials... ");
                  }
                }
            }

        }catch (SQLException se){
            se.printStackTrace();
        }catch (Exception e){
            e.printStackTrace();
        }
        scanner.close();
    }
}
