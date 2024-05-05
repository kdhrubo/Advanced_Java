import com.pwskills.utility.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestInsertApp1 {

    private static final String SQL_INSERT_QUERY = "INSERT INTO student (sid, sname, sage, saddress) VALUES (?, ?, ?, ?)";


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
                System.out.println("Enter the sid: ");
                int sid = scanner.nextInt();

                System.out.println("Enter the sname: ");
                String sname = scanner.next();

                System.out.println("Enter the sage: ");
                int sage = scanner.nextInt();

                System.out.println("Enter the saddress: ");
                String saddress = scanner.next();

                //set the values to the '?' placeholder ; formatting is not required
                preparedStatement.setInt(1, sid);
                preparedStatement.setString(2,sname);
                preparedStatement.setInt(3,sage);
                preparedStatement.setString(4,saddress);

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
}
