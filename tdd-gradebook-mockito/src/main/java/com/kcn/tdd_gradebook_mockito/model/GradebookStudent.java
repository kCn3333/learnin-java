package com.kcn.tdd_gradebook_mockito.model;

public class GradebookStudent extends Student {

    private int id;

    private StudentGrades studentGrades;

    public GradebookStudent(int id, String firstname, String lastname, String emailAddress, StudentGrades studentGrades) {
        super(firstname, lastname, emailAddress);
        this.studentGrades = studentGrades;
        this.id = id;
    }

    public StudentGrades getStudentGrades() {
        return studentGrades;
    }

    public void setStudentGrades(StudentGrades studentGrades) {
        this.studentGrades = studentGrades;
    }

    public int getId() {
        return id;
    }

}