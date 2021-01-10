package main.model;

import main.model.Assignment;
import main.model.Exam;
import java.util.ArrayList;
import java.util.List;


public class Class {
    private String classCode;
    private List<Assignment> assignments;
    private List<Exam> exams;
    private int importance;


    public Class (String code, List<Assignment> assig, List<Exam> exam, int imp) {
        classCode = code;
        assignments = assig;
        exams = exam;
        importance = imp;
    }

    public void addAssignment (String name, int date, int weight) {
        assignments.add(new Assignment(name, date, weight));
    }

    public void addExam (String name, int date, int weight) {
        exams.add(new Exam(name, date, weight));
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public List<Assignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<Assignment> assignments) {
        this.assignments = assignments;
    }

    public List<Exam> getExams() {
        return exams;
    }

    public void setExams(List<Exam> exams) {
        this.exams = exams;
    }

    public int getImportance() {
        return importance;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
