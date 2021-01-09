package main.model;

import main.model.Assignment;
import main.model.Exam;
import java.util.ArrayList;


public class Class {
    private String classCode;
    private ArrayList<Assignment> assignments;
    private ArrayList<Exam> exams;
    private int importance;

    public Class (String code, ArrayList<Assignment> assig, ArrayList<Exam> exam, int imp) {
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
}
