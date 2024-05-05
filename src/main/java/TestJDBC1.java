import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.Statement;

public class TestJDBC1 {
    public static void main(String[] args)throws Exception {
        //1. Load and register the driver
        //public static void registerDriver(Driver) throws SQLException;
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        System.out.println("Driver Loaded Successfully...");

        //2. Establish the connection b/w Java application and database
        //public static Connection getConnection(String) throws SQLException;
        String dbProtocol = "jdbc:mysql://localhost:3306/pwskills_octbatch";
        String userName = "root";
        String password = "Toonoo@050504";
        Connection con = DriverManager.getConnection(dbProtocol,userName,password);
        System.out.println("Connection is established to: "+dbProtocol);
        System.out.println("Connection: "+con.getClass().getName());

        //3. Creating a Statement Object
        //public abstract Statement createStatement() throws SQLException;
        Statement stmt = con.createStatement();
        System.out.println("Statement object got created");
        System.out.println("Statement: "+stmt.getClass().getName());

        //4. Execute the Query
       //public abstract ResultSet executeQuery(String) throws SQLException;
        String selectQuery="select sid,sname,sage,saddress from student";
        ResultSet result = stmt.executeQuery(selectQuery);
        System.out.println("ResultSet Object got created...");
        System.out.println("Resultset: "+result.getClass().getName());

        //5. Processing the result
        System.out.println("\nSID\tSNAME\tSAGE\tSADDRESS");
        while (result.next())
        {
            int sid = result.getInt(1);
            int sage = result.getInt(3);
            String sname = result.getString(2);
            String saddress = result.getString(4);
            System.out.println(sid+"\t"+sname+"\t"+sage+"\t"+saddress);
        }

        //6. Close the resources
        //Note: Close it in the reverse order of Creation!
        result.close();
        stmt.close();
        con.close();
        System.out.println("\nClosing the resources...");
    }
}
