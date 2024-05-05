package com.pwskills.tanay;


import com.pwskills.utility.DBUtil;

import java.sql.*;



public class ResultSetScrollSensitiveApp1 {
    private static final String SQL_INSERT_QUERY = "INSERT INTO employee (ename, esal, eaddress) VALUES (?,?,?)";
    public static void main(String[] args) {

        try (Connection connection = DBUtil.getDBConnection("pwskills_octbatch")) {

            try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){

                try (ResultSet resultSet = statement.executeQuery("SELECT eid,ename,esal,eaddress  FROM  employee")){

                    System.out.println("Records Before Updation...");
                    System.out.println("EID\tENAME\tESAL\tEADDRESS");
                    while (resultSet.next()){
                        System.out.println(resultSet.getInt(1)
                                +"\t"+ resultSet.getString(2)
                                +"\t"+ resultSet.getInt(3)
                                +"\t"+ resultSet.getString(4) );
                    }
                    System.out.println();

                    System.out.println("Application is in pausing state, please update the database...");
                    System.in.read();

                    resultSet.beforeFirst();
                    System.out.println("Records After Updation...");
                    System.out.println("EID\tENAME\tESAL\tEADDRESS");
                    while (resultSet.next()){
                        //To get the latest values from the database (SCROLL_SENSITIVE)
                        resultSet.refreshRow();
                        System.out.println(resultSet.getInt(1)
                                +"\t"+ resultSet.getString(2)
                                +"\t"+ resultSet.getInt(3)
                                +"\t"+ resultSet.getString(4) );
                    }
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}



