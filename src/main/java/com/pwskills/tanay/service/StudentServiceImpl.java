package com.pwskills.tanay.service;

import com.pwskills.tanay.dto.Student;
import com.pwskills.tanay.factory.StudentRepoFactory;
import com.pwskills.tanay.repository.IStudentRepo;

import java.sql.SQLException;

public class StudentServiceImpl implements IStudentService {

    @Override
    public int insertRecord(Student student)throws SQLException {
        //abstraction
        IStudentRepo repo =  StudentRepoFactory.getStudentRepo();
        return repo.insertRecord(student);

    }
}
