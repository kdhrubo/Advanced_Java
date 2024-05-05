package com.pwskills.tanay.service;
import com.pwskills.tanay.dto.Student;

import java.sql.SQLException;

public interface IStudentService {
    //C-> Create
    //R-> Read
    //U-> Update
    //D-> Delete

    public int insertRecord(Student student) throws SQLException;
}
