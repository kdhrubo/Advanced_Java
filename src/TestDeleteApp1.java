import com.pwskills.utility.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.Scanner;

public class TestDeleteApp1 {

    private static final String SQL_DELETE_QUERY = "delete from student where sid = ?";

    public static void main(String[] args) {
        // Resource used
        Connection connection = null;
        PreparedStatement preparedStatement = null;
        int rowCount = 0;

        try {
            connection = DBUtil.getDBConnection();

            if (connection != null) {
                preparedStatement = connection.prepareStatement(SQL_DELETE_QUERY);
            }
            Scanner scanner = new Scanner(System.in);

            if (scanner != null && preparedStatement != null) {
                System.out.println("Enter the sid: ");
                int sid = scanner.nextInt();

                // Set the value for the parameter
                preparedStatement.setInt(1, sid);

                // Execute the delete operation
                rowCount = preparedStatement.executeUpdate();

                System.out.println("No of rows deleted is: " + rowCount);

                // pausing the application
                System.in.read();

                // Reusing the same PreparedStatement Object to run query with different input
                System.out.println("\nEnter the sid: ");
                sid = scanner.nextInt(); // You can reuse the variable sid here

                // Set the value for the parameter
                preparedStatement.setInt(1, sid);

                // Execute the delete operation
                rowCount = preparedStatement.executeUpdate();

                System.out.println("No of rows deleted is: " + rowCount);

                scanner.close();
            }

        } catch (IOException | SQLException e) {
            e.printStackTrace();
        } finally {
            DBUtil.cleanUpResources(null, preparedStatement, connection);
        }
    }
}
