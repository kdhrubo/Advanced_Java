package com.pwskills.tanay.repository;
import com.pwskills.tanay.dto.Student;

import java.sql.SQLException;

public interface IStudentRepo  {
    int insertRecord(Student student)throws SQLException;
}

