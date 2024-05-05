package com.pwskills.tanay;


import com.pwskills.utility.DBUtil;

import java.sql.*;
import java.util.Scanner;


public class TransactionIsolationLevelsApp1 {
    private static final String SQL_INSERT_QUERY = "INSERT INTO employee (ename, esal, eaddress) VALUES (?,?,?)";
    public static void main(String[] args) {
        try (Connection connection = DBUtil.getDBConnection("pwskills_octbatch")) {

            System.out.println(connection.getTransactionIsolation());
            connection.setTransactionIsolation(8);
            System.out.println(connection.getTransactionIsolation());

        } catch (SQLException se) {
            se.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}


