package com.kcn.tdd_gradebook_mockito.model;

import jakarta.persistence.*;

@Entity
@Table(name="history_grade")
public class HistoryGrade implements Grade {

    @Id
    @GeneratedValue(strategy= GenerationType.IDENTITY)
    private int id;
    @Column(name="student_id")
    private int studentId;
    @Column(name="grade")
    private double grade;

    public HistoryGrade() {
    }

    public HistoryGrade(double grade, int studentId) {
        this.grade = grade;
        this.studentId = studentId;
    }

    public double getGrade() {
        return grade;
    }

    public int getId() {
        return id;
    }

    public void setGrade(double grade) {
        this.grade = grade;
    }

    public int getStudentId() {
        return studentId;
    }

    public void setStudentId(int studentId) {
        this.studentId = studentId;
    }
}
