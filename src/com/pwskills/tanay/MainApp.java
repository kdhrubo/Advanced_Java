package com.pwskills.tanay;

import com.pwskills.tanay.controller.IStudentController;
import com.pwskills.tanay.dto.Student;
import com.pwskills.tanay.factory.StudentControllerFactory;

import java.sql.SQLException;
import java.util.Scanner;

public class MainApp {
    public static void main(String[] args) {

        //main() : driver code
       IStudentController controller = StudentControllerFactory.getStudentControllerObj();
        Student student = new Student();
        Scanner scanner = new Scanner(System.in);

        System.out.println("Enter the SID: ");
        Integer sid = scanner.nextInt();

        System.out.println("Enter the sname: ");
        String sname = scanner.next();

        System.out.println("Enter the sage: ");
        int sage = scanner.nextInt();

        System.out.println("Enter the saddress: ");
        String saddress = scanner.next();

        student.setSid(sid);
        student.setSname(sname);
        student.setSage(sage);
        student.setSaddress(saddress);

        try {
            System.out.println("No of records inserted is: "+controller.insertRecord(student));
        }catch (SQLException e){
            System.out.println("Insertion failure...");
        }
        scanner.close();
    }
}
