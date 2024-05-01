package com.pwskills.tanay;


import com.pwskills.utility.DBUtil;

import java.sql.*;



public class ResultSetScrollUpdatableApp1 {
    private static final String SQL_INSERT_QUERY = "INSERT INTO employee (ename, esal, eaddress) VALUES (?,?,?)";
    public static void main(String[] args) {

        try (Connection connection = DBUtil.getDBConnection("pwskills_octbatch")) {

            try(Statement statement = connection.createStatement(ResultSet.TYPE_SCROLL_SENSITIVE, ResultSet.CONCUR_UPDATABLE)){

                try (ResultSet resultSet = statement.executeQuery("SELECT eid,ename,esal,eaddress  FROM  employee")){

                    while (resultSet.next()){
                        //perform update operation
                        int salary = resultSet.getInt(3);
                        if (salary<=28000){
                            int incrSalary = salary + 10000;
                            resultSet.updateInt(3, incrSalary);
                            resultSet.updateRow();
                        }
                    }
                    System.out.println("Records Updated...");
                }
            }
        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}




