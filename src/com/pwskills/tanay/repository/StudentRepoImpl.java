package com.pwskills.tanay.repository;

import com.pwskills.tanay.dto.Student;
import com.pwskills.utility.DBUtil;

import java.io.IOException;
import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

public class StudentRepoImpl implements IStudentRepo {

    private static Connection connection = null;

    static {
        try {
            connection = DBUtil.getDBConnection("pwskills_octbatch");
        } catch (IOException e) {
            throw new RuntimeException(e);
        } catch (SQLException e) {
            throw new RuntimeException(e);
        }
    }

    public static Connection getConnection() {
        return connection;
    }

    @Override
    public int insertRecord(Student student)throws SQLException {
        Statement statement = null;
        try {
            if (connection != null)
                statement = connection.createStatement();

            String sqlInsertQuery;
            sqlInsertQuery = String.format("INSERT INTO student VALUES(%d,'%s',%d,'%s')", student.getSid(), student.getSname(), student.getSage(), student.getSaddress());
            System.out.println(sqlInsertQuery);

            if (statement != null && sqlInsertQuery != null) {
                return statement.executeUpdate(sqlInsertQuery);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            // Close resources here if necessary
        }
        return 0;
    }
}
