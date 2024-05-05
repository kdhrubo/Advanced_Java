package com.pwskills.tanay;

import com.pwskills.utility.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TestInsertApp2 {

    private static final String SQL_INSERT_QUERY = "INSERT INTO users (username, dob) VALUES (?, ?)";



    public static void main(String[] args) {
        //Resource used
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowCount = 0;

        try
        {
            connection = DBUtil.getDBConnection("pwskills_octbatch");

            if (connection != null){
                preparedStatement = connection.prepareStatement(SQL_INSERT_QUERY);
            }
            Scanner scanner = new Scanner(System.in);

            if (preparedStatement != null && scanner != null)
            {

                System.out.println("Enter the username: ");
                String username = scanner.next();

                System.out.println("Enter the DOB(dd-MM-yyyy): ");
                String dob = scanner.next();

                //set the values to the '?' placeholder ; formatting is not required
                preparedStatement.setString(1,username);
                preparedStatement.setDate(2, convertToSQLDate(dob));

                //Execute the query now with the inputs
                rowCount = preparedStatement.executeUpdate();
            }
            if (rowCount == 0){
                System.out.println("Insertion of records failed");
            }
            else {
                System.out.println("No of records inserted is: "+rowCount);
            }

            scanner.close();

        } catch (SQLException | IOException e)
        {
            e.printStackTrace();
        }
        finally {
            DBUtil.cleanUpResources(null,preparedStatement,connection);
        }
    }

    private static Date convertToSQLDate(String dob) {
        try {
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
            java.util.Date utilDate = sdf.parse(dob);
            long inputMs = utilDate.getTime();
            java.sql.Date sqlDate = new java.sql.Date(inputMs);
            return sqlDate;
        } catch (ParseException e) {
            e.printStackTrace(); // Or handle the exception as needed
            return null; // Or return some default value
        }
    }

}
