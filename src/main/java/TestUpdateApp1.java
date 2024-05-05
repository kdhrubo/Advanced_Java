import com.pwskills.utility.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestUpdateApp1 {

    public static void main(String[] args) {
        //Resource used
        Connection connection = null;
        Statement statement = null;
        int rowCount = 0;

        try
        {
            connection = DBUtil.getDBConnection("pwskills_octbatch");

            if (connection != null){
                statement = connection.createStatement();
            }
            Scanner scanner = new Scanner(System.in);
            String sqlUpdateQuery = null;
            if (scanner != null)
            {
                System.out.println("Enter the sid: ");
                int sid = scanner.nextInt();

                System.out.println("Enter the sname: ");
                String sname = scanner.next();

                sqlUpdateQuery = "UPDATE student SET sname = '" + sname + "' WHERE sid = " + sid;

                System.out.println(sqlUpdateQuery);
            }

            if (statement != null && sqlUpdateQuery != null)
            {
                rowCount =  statement.executeUpdate(sqlUpdateQuery);
            }
            if (rowCount==0){
                System.out.println("Record not available for updation...");
            }
            else {
                System.out.println("Record updated succesfully...");
            }

        } catch (SQLException | IOException e)
        {
            e.printStackTrace();
        }
        finally {
            DBUtil.cleanUpResources(null,statement,connection);
        }

    }
}

