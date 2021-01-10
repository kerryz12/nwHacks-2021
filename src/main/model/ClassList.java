package main.model;

import java.util.ArrayList;
import java.util.List;

public class ClassList {

    private List<Class> classlist;

    public ClassList() {
        classlist = new ArrayList<>();
    }

    /**
     * @param c
     * @return adds class to list of classes
     */
    public void addClass(Class c) {
        classlist.add(c);
    }

    /**
     * @param c
     * @return removes class from list of classes
     */
    public void removeClass(Class c) {
        int i = 0;
        for (Class cl : classlist) {

        }
    }

    public List<Class> getClasslist() {
        return classlist;
    }

    public void setClasslist(List<Class> classlist) {
        this.classlist = classlist;
    }

}
