package com.pwskills.tanay;

import java.io.*;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.pwskills.utility.DBUtil;
import org.apache.commons.io.IOUtils;

public class TestTextWriteApp1 {
    private static final String SQL_SELECT_QUERY = "SELECT eid, ename, eaddress, resume FROM employee WHERE eid = ?";

    public static void main(String[] args) {
        // Resources used
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int eid = 0;
        try {
            connection = DBUtil.getDBConnection("pwskills_octbatch");

            // 2. Create a Statement Object
            if (connection != null) {
                preparedStatement =
                        connection.prepareStatement(SQL_SELECT_QUERY);

            }
            Scanner scanner = new Scanner(System.in);

            if (preparedStatement != null && scanner != null) {

                System.out.print("Enter the id of the employee:: ");
                eid = scanner.nextInt();

                // Set the value to the placeholder '?'
                preparedStatement.setInt(1, eid);

                // Execute the Query
                resultSet = preparedStatement.executeQuery();
            }

            // 4. Process the Result
            if (resultSet.next()) {
                System.out.println("EID\tENAME\tEADDRESS\tRESUME");//photo won't be displayed here on console
                String resumeLocation = "/Users/tanayjoshi/Desktop/employee.txt";
                System.out.println("File saved to: " + new File(resumeLocation).getAbsolutePath());

                //Framework Code or API Code
                FileWriter fileWriter = new FileWriter(new File(resumeLocation));
                IOUtils.copy(resultSet.getCharacterStream(4),fileWriter);
                fileWriter.flush();
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2) + "\t" + "\t" + resumeLocation);
            }
            else {
                System.out.println("Record not found for the given id :: " + eid);
            }
            scanner.close();
        } catch (SQLException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (IOException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {

            DBUtil.cleanUpResources(resultSet, preparedStatement, connection);
        }
    }

}



