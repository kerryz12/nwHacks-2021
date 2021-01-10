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
     * @param code
     * @return removes class from list of classes
     */
    public void removeClass(String code) {
        int i = 0;
        for (Class c : classlist) {
            if (c.getClassCode().equals(code)) {
                classlist.remove(i);
            }
            i++;
        }
    }

    public Class findClass(String code) {
        int i = 0;
        for (Class c : classlist) {
            if (c.getClassCode().equals(code)) {
                return classlist.get(i);
            }
            i++;
        }
        return null;
    }

    public List<Class> getClasslist() {
        return classlist;
    }

    public void setClasslist(List<Class> classlist) {
        this.classlist = classlist;
    }
}
