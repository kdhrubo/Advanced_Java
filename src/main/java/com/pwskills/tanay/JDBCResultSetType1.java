package com.pwskills.tanay;

import com.pwskills.utility.DBUtil;
import java.sql.*;

public class JDBCResultSetType1 {
    private static final String SQL_INSERT_QUERY = "INSERT INTO employee (ename, esal, eaddress) VALUES (?,?,?)";
    public static void main(String[] args) {

        try (Connection connection = DBUtil.getDBConnection("pwskills_octbatch")) {

           try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY)){

               try (ResultSet resultSet = statement.executeQuery("SELECT eid,ename,esal,eaddress  FROM  employee")){

                   System.out.println("Records in forward Direction...");
                   System.out.println("EID\tENAME\tESAL\tEADDRESS");
                   while (resultSet.next()){
                       System.out.println(resultSet.getInt(1)
                               +"\t"+ resultSet.getString(2)
                               +"\t"+ resultSet.getInt(3)
                               +"\t"+ resultSet.getString(4) );
                   }
                   System.out.println();
                   System.out.println("Records in backward Direction...");
                   System.out.println("EID\tENAME\tESAL\tEADDRESS");
                   while (resultSet.previous()){
                       System.out.println(resultSet.getInt(1)
                               +"\t"+ resultSet.getString(2)
                               +"\t"+ resultSet.getInt(3)
                               +"\t"+ resultSet.getString(4) );
                   }

                   System.out.println();
                   System.out.println("Exploring the methods of Navigation... ");

                   resultSet.first();
                   System.out.println(resultSet.getInt(1)
                           +"\t"+ resultSet.getString(2)
                           +"\t"+ resultSet.getInt(3)
                           +"\t"+ resultSet.getString(4) );

                   System.out.println();

                   resultSet.last();
                   System.out.println(resultSet.getInt(1)
                           +"\t"+ resultSet.getString(2)
                           +"\t"+ resultSet.getInt(3)
                           +"\t"+ resultSet.getString(4) );

                   System.out.println();
                   //Working with absolute
                   resultSet.first(); // cursor is at top -> 1

                   resultSet.absolute(5);
                   System.out.println(resultSet.getInt(1)
                           +"\t"+ resultSet.getString(2)
                           +"\t"+ resultSet.getInt(3)
                           +"\t"+ resultSet.getString(4) );

                   resultSet.absolute(-3);
                   System.out.println(resultSet.getInt(1)
                           +"\t"+ resultSet.getString(2)
                           +"\t"+ resultSet.getInt(3)
                           +"\t"+ resultSet.getString(4) );

                   /*
                   resultSet.absolute(-30); //beyond range
                   System.out.println(resultSet.getInt(1)
                           +"\t"+ resultSet.getString(2)
                           +"\t"+ resultSet.getInt(3)
                           +"\t"+ resultSet.getString(4) );
                    */

                   resultSet.absolute(3); //Cursor at 3rd position rn
                   System.out.println();

                   resultSet.relative(5);
                   System.out.println(resultSet.getInt(1)
                           +"\t"+ resultSet.getString(2)
                           +"\t"+ resultSet.getInt(3)
                           +"\t"+ resultSet.getString(4) );

                   resultSet.relative(-3);
                   System.out.println(resultSet.getInt(1)
                           +"\t"+ resultSet.getString(2)
                           +"\t"+ resultSet.getInt(3)
                           +"\t"+ resultSet.getString(4) );

                   System.out.println();
                   System.out.println("Cursor pointing to First Row: "+resultSet.isFirst());
                   System.out.println("Cursor pointing to Last Row: "+resultSet.isLast());
                   System.out.println("Cursor pointing to Before First Row: "+resultSet.isBeforeFirst());
                   resultSet.afterLast();
                   System.out.println("Cursor pointing to After Last Row: "+resultSet.isAfterLast());
               }
           }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


