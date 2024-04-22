import com.pwskills.utility.DBUtil;
import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class TestInsertApp1 {

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
            String sqlInsertQuery = null;
            if (scanner != null)
            {
                System.out.println("Enter the sid: ");
                int sid = scanner.nextInt();

                System.out.println("Enter the sname: ");
                String sname = scanner.next();

                System.out.println("Enter the sage: ");
                int sage = scanner.nextInt();

                System.out.println("Enter the saddress: ");
                String saddress = scanner.next();

                sqlInsertQuery = "insert into student values(" + sid + ",\t '" + sname + "',\t " + sage + ",\t '" + saddress + "')";
                System.out.println(sqlInsertQuery);
            }

            if (statement != null && sqlInsertQuery != null)
            {
               rowCount =  statement.executeUpdate(sqlInsertQuery);
            }
            if (rowCount==0){
                System.out.println("failure in insertion...");
            }
            else {
                System.out.println("record inserted succesfully...");
            }

        } catch (IOException | SQLException e)
        {
            e.printStackTrace();
        }
        finally {
            DBUtil.cleanUpResources(null,statement,connection);
        }

    }
}
