import com.pwskills.utility.DBUtil;
import java.sql.*;
import java.io.*;
public class TestJDBC4 {
    public static void main(String[] args) {

        //Resources used
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet = null;

        try {
            connection = DBUtil.getDBConnection();


            //2. Create a Statement Object
            statement = connection.createStatement();


            //3. Execute the query
            String SqlSelectQuery = "select sid,sname,sage,saddress from student";
            resultSet = statement.executeQuery(SqlSelectQuery);


            //4. Process the result
            System.out.println("\nSID\tSNAME\tSAGE SADDRESS");
            while (resultSet.next())
            {
                int sid = resultSet.getInt(1);
                String sname = resultSet.getString(2);
                int sage = resultSet.getInt(3);
                String saddress = resultSet.getString(4);
                System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+saddress);
            }

        }
        catch (SQLException e)
        {
            e.printStackTrace();
        }
        catch (FileNotFoundException e){
            e.printStackTrace();
        }
        catch (IOException e){
            e.printStackTrace();
        }
        catch (Exception e){
            e.printStackTrace();
        }
        finally {
            DBUtil.cleanUpResources(resultSet, statement, connection);

        }
    }
}
