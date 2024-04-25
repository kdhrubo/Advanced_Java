package com.pwskills.tanay;

import com.pwskills.utility.DBUtil;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TestImageApp1 {

    private static final String SQL_INSERT_QUERY = "INSERT INTO person (name, photo) VALUES (?, ?)";

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


                //set the values to the '?' placeholder ; formatting is not required
                preparedStatement.setString(1,username);
                preparedStatement.setBinaryStream(2, new FileInputStream("/Users/tanayjoshi/Desktop/carbon-2.jpg"));


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

        } catch (IOException | SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            DBUtil.cleanUpResources(null,preparedStatement,connection);
        }
    }

}

