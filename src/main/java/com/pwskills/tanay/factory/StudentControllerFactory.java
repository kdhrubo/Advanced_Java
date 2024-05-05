package com.pwskills.tanay.factory;

import com.pwskills.tanay.controller.IStudentController;
import com.pwskills.tanay.controller.StudentControllerImpl;

public class StudentControllerFactory {
    private static IStudentController controller = null;
    public static IStudentController getStudentControllerObj(){
        if (controller == null){
            controller = new StudentControllerImpl();
        }
        return controller;
    }
}
