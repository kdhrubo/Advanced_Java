package com.pwskills.tanay.factory;

import com.pwskills.tanay.repository.IStudentRepo;
import com.pwskills.tanay.repository.StudentRepoImpl;

public class StudentRepoFactory {
    private static StudentRepoImpl studentRepo = null;

    private StudentRepoFactory(){

    }

    //Singleton Pattern
    public static IStudentRepo getStudentRepo(){
        if (studentRepo == null){
            studentRepo = new StudentRepoImpl();
        }
        return studentRepo;
    }
}
