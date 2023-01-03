package org.techproed.project;

import java.sql.*;
import java.time.Period;
import java.util.Collection;
import java.util.Scanner;

public class Student {
    private String name;
    private String email;
    private int grade;

    public void getStudentDetail() {
        Scanner input = new Scanner(System.in);
        System.out.println("Enter student name: ");
        name = input.nextLine();
        System.out.println("Enter student email: ");
        email = input.nextLine();
        System.out.println("Enter student grade: ");
        grade = input.nextInt();
    }

    public void saveStudent() throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        //Statement statement = conn.createStatement();
        //String queryToCreateTable = "CREATE TABLE student_record (name VARCHAR(50), email VARCHAR(50), grade NUMERIC(2)";
        //statement.executeQuery(queryToCreateTable);
        String query = "INSERT INTO student_record VALUES(?,?,?)";
        PreparedStatement preparedStatement = conn.prepareStatement(query);
        //saving data to columns
        preparedStatement.setString(1, name);
        preparedStatement.setString(2, email);
        preparedStatement.setInt(3, grade);

        preparedStatement.executeUpdate();
        System.out.println("Student information has been added successfully.");
    }

    public void updateStudentName() throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter email of the student to update the name");
        String emailEntered = input.nextLine();
        System.out.println("Enter new student name");
        String nameUpdated = input.nextLine();
        String query1 = "UPDATE student_record SET name  = ? WHERE email =?";
        //preparedStatement() method to send parameterized data
        PreparedStatement preparedStatement = conn.prepareStatement(query1);
        preparedStatement.setString(1, nameUpdated);
        preparedStatement.setString(2, emailEntered);

        //to get number of updated rows. if numberOfUpdatedRows = 0 then no rows have been updated
        int numberOfUpdatedRow = preparedStatement.executeUpdate();
        if (numberOfUpdatedRow > 0) {
            System.out.println("Name has been updated");
        } else {
            System.out.println("The email you entered is not found!");
        }
    }

    public void deleteStudent() throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();

        Scanner input = new Scanner(System.in);
        System.out.println("Please enter email of the student you wish to delete");
        String emailEntered = input.nextLine();
        String query2 = "DELETE FROM student_record WHERE email =?";
        PreparedStatement preparedStatement = conn.prepareStatement(query2);
        preparedStatement.setString(1, emailEntered);

        int numberOfUpdatedRow = preparedStatement.executeUpdate();
        if (numberOfUpdatedRow > 0) {
            System.out.println("The email you entered is not found!");
        } else {
            System.out.println("Student has been deleted from the record.");
        }
    }
    public void searchStudentByName() throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        Scanner input = new Scanner(System.in);
        System.out.println("Please enter name to search.");
        String inputName = input.nextLine();
        String query3  = "SELECT * FROM student_record WHERE name=?";
        PreparedStatement preparedStatement = conn.prepareStatement(query3);
        preparedStatement.setString(1,inputName);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet!=null){
            System.out.println("========= Search Result ===========");
            System.out.println("Name     Email     Grade");

            while (resultSet.next()){
                System.out.print(resultSet.getString("name")+" - ");
                System.out.print(resultSet.getString("email")+" - ");
                System.out.println(resultSet.getInt("grade")+" ");
            }
        }else{
            System.out.println("========= Search Result ===========");
            System.out.println("NO RECORDS FOUND FOR INPUTTED NAME.");
        }
    }
    public void showAllStudents() throws SQLException {
        DBConnection dbConnection = new DBConnection();
        Connection conn = dbConnection.getConnection();
        String query4 = "SELECT * FROM student_record";
        PreparedStatement preparedStatement = conn.prepareStatement(query4);

        ResultSet resultSet = preparedStatement.executeQuery();
        if(resultSet!=null){
            System.out.println("========= Search Result ===========");


            while (resultSet.next()){
                System.out.print("Name: " + resultSet.getString("name")+" ");
                System.out.print("Email: " + resultSet.getString("email")+" ");
                System.out.println("Grade: " + resultSet.getInt("grade")+" ");
            }
        }else{
            System.out.println("========= Search Result ===========");
            System.out.println("NO RECORDS");
        }
    }
}
