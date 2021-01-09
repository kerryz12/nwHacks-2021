package main.model;

import java.util.ArrayList;
import java.util.List;

public class ClassList {

    private List<Class> classlist;

    // creates new classlist
    // EFFECTS: creates an empty list of classes
    public ClassList() {
        classlist = new ArrayList<>();
    }

    // adds class to classlist
    // MODIFIES: this
    // EFFECTS: adds a class to list of classes
    public void addClass(Class c) {
        classlist.add(c);
    }

    // removes class from classlist
    // MODIFIES: this
    // EFFECTS: removes a class from list of classes
    public void removeClass(Class c) {
        int i = 0;
        for (Class cl : classlist) {

        }
    }

    public List<Class> getList() { return this.classlist; }

}
