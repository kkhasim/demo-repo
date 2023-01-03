package org.techproed.project;

import java.sql.SQLException;
import java.util.Scanner;

public class StudentRunner{
    public static void main(String[] args) throws SQLException {

        int selection = 0;

        Student student = new Student();

        do{
            System.out.println("WELCOME TO STUDENT RECORD SYSTEM\nPlease select from the option\n1-Student Registration\n2-Update student info\n3-Delete Record\n4-Search for a student\n5-Show all student\n6-Exit from the Application");
            Scanner scanner = new Scanner(System.in);
            selection = scanner.nextInt();
           switch (selection){
               case 1:
                   student.getStudentDetail();
                   student.saveStudent();
                   break;

               case 2:
                   student.updateStudentName();
                   break;
               case 3:
                   student.deleteStudent();
                   break;
               case 4:
                   student.searchStudentByName();
                   break;
               case 5:
                   student.showAllStudents();
                   break;
               case 6:
                   break;
               default:
                   System.out.println("Please enter digits from 1 to 6");
           }

        }while(selection != 6);{
            System.out.println("Thank you for using the application.");
        }
    }
}
