package com.pwskills.tanay.controller;

import com.pwskills.tanay.dto.Student;
import com.pwskills.tanay.factory.StudentServiceFactory;
import com.pwskills.tanay.service.IStudentService;

import java.sql.SQLException;

public class StudentControllerImpl implements IStudentController{

    @Override
    public int insertRecord(Student student) throws SQLException{
        IStudentService service = StudentServiceFactory.getStudentService();
        return service.insertRecord(student);

    }
}
