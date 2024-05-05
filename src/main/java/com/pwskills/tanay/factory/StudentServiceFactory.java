package com.pwskills.tanay.factory;

import com.pwskills.tanay.service.IStudentService;
import com.pwskills.tanay.service.StudentServiceImpl;

public class StudentServiceFactory {
    private static IStudentService studentService = null;

    private StudentServiceFactory(){

    }

    public static IStudentService getStudentService(){
        if (studentService == null){
            studentService = new StudentServiceImpl();
        }
        return studentService;
    }
}
