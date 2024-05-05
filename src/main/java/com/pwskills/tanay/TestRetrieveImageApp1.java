package main.java.com.pwskills.tanay;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Scanner;
import com.pwskills.utility.DBUtil;

public class TestRetrieveImageApp1 {
    private static final String SQL_SELECT_QUERY = "select id,name,photo from person where id = ?";
    public static void main(String[] args) {
        // Resources used
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        ResultSet resultSet = null;
        int id = 0;
        try {
            connection = DBUtil.getDBConnection("pwskills_octbatch");

            // 2. Create a Statement Object
            if (connection != null) {
                preparedStatement =
                        connection.prepareStatement(SQL_SELECT_QUERY);

            }
            Scanner scanner = new Scanner(System.in);

            if (preparedStatement != null && scanner != null) {

                System.out.print("Enter the id of the user:: ");
                id = scanner.nextInt();

                // Set the value to the placeholder '?'
                preparedStatement.setInt(1, id);

                // Execute the Query
                resultSet = preparedStatement.executeQuery();
            }

            // 4. Process the Result
            if (resultSet.next()) {
                System.out.println("ID\tNAME");//photo won't be displayed here on console
                System.out.println(resultSet.getInt(1) + "\t" + resultSet.getString(2));

                System.out.println(displayImageInfo(resultSet.getBinaryStream(3)));
            }
            else {
                System.out.println("Record not found for the given id :: " + id);
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

    private static String displayImageInfo(InputStream binaryStream)throws IOException {

        String location = "download.jpg";
        FileOutputStream fos = new FileOutputStream(location);

        int data = binaryStream.read();
        while (data != -1){
            fos.write(data);
            data = binaryStream.read();
        }
        fos.close();
        binaryStream.close();
        return "Successfully Downloaded with the file name as: "+location;
    }


}



