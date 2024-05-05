package com.pwskills.tanay;

import com.pwskills.utility.DBUtil;

import java.sql.*;


public class TestDataBaseExchange1 {

    private static final String SQL_INSERT_QUERY = "INSERT INTO canarabank (`accno`, `holdername`, `balance`) VALUES (?, ?, ?)";

    private static final String SQL_SELECT_QUERY = "select accno, holdername, balance from syndicatebank ";


    public static void main(String[] args) {
        //Resource used
            //Resource used
            Connection pwconnection = null;
            Connection demopwconnection = null;

            Statement statement = null;
            PreparedStatement preparedStatement = null;
            ResultSet resultSet = null;

            try {
                demopwconnection = DBUtil.getDBConnection("demopwskills_octbatch");
                pwconnection = DBUtil.getDBConnection("pwskills_octbatch");

                if (demopwconnection != null){
                   statement = demopwconnection.createStatement();
                }
                if (pwconnection != null){
                    preparedStatement = pwconnection.prepareStatement(SQL_INSERT_QUERY);
                }
                if (statement != null){
                    resultSet = statement.executeQuery(SQL_SELECT_QUERY);
                }
                if (resultSet != null && preparedStatement != null){
                    while (resultSet.next()){
                        //set the values to ? of placeholder
                        preparedStatement.setInt(1,resultSet.getInt(1));
                        preparedStatement.setString(2,resultSet.getString(2));
                        preparedStatement.setFloat(3,resultSet.getFloat(3));

                        preparedStatement.executeUpdate();
                    }
                    System.out.println("Records are copied from demoDB to pwDB...");
                }
            } catch (Exception e) {
                e.printStackTrace();
            } finally {
               DBUtil.cleanUpResources(resultSet, statement, demopwconnection);
               DBUtil.cleanUpResources(null, preparedStatement, pwconnection);
            }
        }

    }





