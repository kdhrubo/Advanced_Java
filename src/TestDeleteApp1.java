import com.pwskills.utility.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestDeleteApp1 {

    public static void main(String[] args) {
        //Resource used
        Connection connection = null;
        Statement statement = null;
        int rowCount = 0;

        try
        {
            connection = DBUtil.getDBConnection();

            if (connection != null){
                statement = connection.createStatement();
            }
            Scanner scanner = new Scanner(System.in);
            String sqlDeleteQuery = null;
            if (scanner != null)
            {
                System.out.println("Enter the sid: ");
                int sid = scanner.nextInt();
                sqlDeleteQuery = "delete from student where sid = " + sid +"";
                System.out.println(sqlDeleteQuery);
                scanner.close();
            }

            if (statement != null && sqlDeleteQuery != null)
            {
                rowCount =  statement.executeUpdate(sqlDeleteQuery);
            }
            if (rowCount==0){
                System.out.println("Record not available for deletion...");
            }
            else {
                System.out.println("Record deleted succesfully...");
            }
        }
        catch (IOException | SQLException e) {
            e.printStackTrace();
        }
        finally {
            DBUtil.cleanUpResources(null,statement,connection);
        }
    }
}


