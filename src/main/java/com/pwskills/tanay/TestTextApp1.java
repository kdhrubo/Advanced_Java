package com.pwskills.tanay;

import com.pwskills.utility.DBUtil;

import java.io.File;
import java.io.FileReader;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Scanner;

public class TestTextApp1 {

    private static final String SQL_INSERT_QUERY = "INSERT INTO employee(ename,eaddress,resume)values(?, ?, ?)";



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

                System.out.println("Enter the employee name: ");
                String ename = scanner.next();

                System.out.println("Enter the employee address: ");
                String eaddress = scanner.next();

                //set the values to the '?' placeholder ; formatting is not required
                preparedStatement.setString(1,ename);
                preparedStatement.setString(2,eaddress);
                String location = "/Users/tanayjoshi/Desktop/employee.rtf";
                preparedStatement.setCharacterStream(3, new FileReader(new File(location)));

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

