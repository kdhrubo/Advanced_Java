package com.pwskills.tanay.controller;

import com.pwskills.tanay.dto.Student;

import java.sql.SQLException;

public interface IStudentController  {
    //C-> Create
    //R-> Read
    //U-> Update
    //D-> Delete

    public int insertRecord(Student student) throws SQLException;
}
