package main.model;

import java.util.ArrayList;
import java.util.List;


public class Class {
    private String classCode;
    private List<GradedItem> tasks;
    private int importance;

    /**
     * Constructor for given taskList, importance, and code.
     *
     * @param code        String representing the specific class.
     * @param gradedItems List of gradedItems that belong to the class.
     * @param imp         importance is an int from 0-10 that expresses the amount of interest the user has in this class.
     */
    public Class(String code, List<GradedItem> gradedItems, int imp) {
        classCode = code;
        tasks = gradedItems;
        importance = imp;
    }

    /**
     * Constructor for no previous task list.
     * Creates new list for tasks.
     *
     * @param code
     * @param imp
     */
    public Class(String code, int imp) {
        classCode = code;
        tasks = new ArrayList<GradedItem>();
        importance = imp;
    }

    /**
     * Constructor for default code and no previous task list.
     * Sets importance to 5.
     *
     * @param code
     */
    public Class(String code) {
        classCode = code;
        tasks = new ArrayList<GradedItem>();
        importance = 5;
    }

    public void addTask(GradedItem gi) {
        tasks.add(gi);
    }

    public List<GradedItem> getTasks() {
        return this.tasks;
    }

    public int getImp() {
        return importance;
    }

    public String getClassCode() {
        return classCode;
    }

    public void setClassCode(String classCode) {
        this.classCode = classCode;
    }

    public void setImportance(int importance) {
        this.importance = importance;
    }
}
