import java.sql.*;
import java.util.*;
import java.io.*;
public class TestJDBC2 {
    public static void main(String[] args)throws Exception {
        //1. Load and register the driver
        //public static void registerDriver(Driver) throws SQLException;
        DriverManager.registerDriver(new com.mysql.cj.jdbc.Driver());
        System.out.println("Driver Loaded Successfully...");
        //2. Establish the connection b/w java application and database
        /*
        public static Connection getConnection
        (String,String,String) throws SQLException;
        public static Connection getConnection
        (String,Properties) throws SQLException;
        public static Connection getConnection(String) throws SQLException;
        */
        String dbProtocol = "jdbc:mysql://localhost:3306/pwskills_octbatch";
        Properties props = new Properties();
        FileInputStream fis = new FileInputStream("/Users/tanayjoshi/IdeaProjects/Advanced_Java/src/database.properties");
        props.load(fis);
        Connection con = DriverManager.getConnection(dbProtocol,props);
        System.out.println("Connection is established to ::"+dbProtocol);
        System.out.println("Connection :: "+con.getClass().getName());

        //3. Creating a Statement Object
        //public abstract Statement createStatement() throws SQLException;
        Statement stmt = con.createStatement();
        System.out.println("\nStatement Object got created...");
        System.out.println("Statement :: "+stmt.getClass().getName());

        //4. Execute the Query
        //public abstract ResultSet executeQuery(String) throws SQLException;
        String selectQuery="select sid,sname,sage,saddress from student";
        ResultSet result = stmt.executeQuery(selectQuery);
        System.out.println("\nResultSet Object got created...");
        System.out.println("ResultSet :: "+result.getClass().getName());

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
        result.close();
        stmt.close();
        con.close();
        System.out.println("\nClosing the resources...");
    }
}
