package main.model;

import java.lang.reflect.Array;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class ToDoList {
    private final static int MILLI_IN_HOUR = 3600000;
    private long currTime;
    private ClassList classes;
    private Map<GradedItem, Double> weightedTasks;
    private ArrayList<GradedItem> sortedList;

    /** To do list is just a sorted list of tasks.
     *
     *  Each task has a "urgency" value assigned to it.
     *  Urgency = Weight * Importance of Class * Hours Left Until Due Date
     *
     * @param cl ClassList of user that wants ToDoList.
     */
    public ToDoList(ClassList cl){
        this.classes = cl;
    }

    /**
     * Sorts this.classes based off of urgency. Defined above.
     */
    private void sortList(){
        currTime = System.currentTimeMillis();
        List<Class> classlist = classes.getClasslist();
        weightedTasks = new LinkedHashMap<>();
        for (Class c : classlist){
            int curr = c.getImp();
            List<GradedItem> tasks = c.getTasks();
            for (GradedItem g : tasks){

                ZonedDateTime zdt = g.getDate().atZone(ZoneId.of("America/Los_Angeles"));
                long millis = zdt.toInstant().toEpochMilli();
                long hoursLeft = (millis - currTime)/MILLI_IN_HOUR;
                double val = g.getWeight() * curr * hoursLeft;
                weightedTasks.put(g,val);
            }
        }
        weightedTasks = sortByValue(weightedTasks);
        sortedList.addAll(weightedTasks.keySet());
    }

    /**
     * Sorts a Map based off the values.
     * @param map Input map to be sorted. Must not be null.
     * @param <GradedItem> Keys of the map, must not be null.
     * @param <Double> Must be a positive number.
     * @return Sorted Map with Graded Items as Keys and Longs as the Values.
     */
    public static <GradedItem, Double> Map<GradedItem, Double> sortByValue(Map<GradedItem, Double> map) {
        List<Map.Entry<GradedItem, Double>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<GradedItem, Double>>() {
            @Override
            //if o1 is greater, return 1, if o2 is greater, return -1.
            // greater in this case, is defined as having a lesser value.
            public int compare(Map.Entry<GradedItem, Double> o1, Map.Entry<GradedItem, Double> o2) {
                if (((Number)o1.getValue()).doubleValue() > ((Number)o2.getValue()).doubleValue()){
                    return -1;
                }
                else if (((Number)o1.getValue()).doubleValue() < ((Number)o2.getValue()).doubleValue()){
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });
        Map<GradedItem, Double> result = new LinkedHashMap<>();
        for (Map.Entry<GradedItem, Double> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }

    public ArrayList<GradedItem> getSortedList() { return sortedList; }

    public static ArrayList<GradedItem> getToDoList(ClassList cl){
        ToDoList t = new ToDoList(cl);
        t.sortList();
        return t.getSortedList();
    }
}
