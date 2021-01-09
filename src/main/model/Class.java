package model;

import model.Assignment;
import model.Exam;
import java.util.ArrayList;

public class Class {
    private String classCode;
    private ArrayList<Assignment> assignments;
    private ArrayList<Exam> exams;

    public void addAssignment (String name, int date, int weight) {
        assignments.add(new Assignment());
    }

}
