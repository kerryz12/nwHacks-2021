package main.model;

import main.model.Assignment;
import main.model.Exam;
import java.util.ArrayList;


public class Class {
    private String classCode;
    private ArrayList<Assignment> assignments;
    private ArrayList<model.Exam> exams;

    public void addAssignment (String name, int date, int weight) {
        assignments.add(new Assignment(name, date, weight));
    }

}
