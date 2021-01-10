package main.model;

import java.lang.reflect.Array;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.util.*;

public class ToDoList {
    private final static int MILLI_IN_HOUR = 3600000;
    private long currTime;
    private ClassList classes;
    private Map<GradedItem, Long> weightedTasks;
    private ArrayList<GradedItem> sortedList;

    public ToDoList(ClassList cl){
        this.classes = cl;
    }

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
                long val = g.getWeight() * curr * hoursLeft;
                weightedTasks.put(g,val);
            }
        }
        weightedTasks = sortByValue(weightedTasks);
        sortedList.addAll(weightedTasks.keySet());
    }

    public static <GradedItem, Long> Map<GradedItem, Long> sortByValue(Map<GradedItem, Long> map) {
        List<Map.Entry<GradedItem, Long>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<GradedItem, Long>>() {
            @Override
            //if o1 is greater, return 1, if o2 is greater, return -1.
            // greater in this case, is defined as having a lesser value.
            public int compare(Map.Entry<GradedItem, Long> o1, Map.Entry<GradedItem, Long> o2) {
                if (((Number)o1.getValue()).longValue() > ((Number)o2.getValue()).longValue()){
                    return -1;
                }
                else if (((Number)o1.getValue()).longValue() < ((Number)o2.getValue()).longValue()){
                    return 1;
                }
                else {
                    return 0;
                }
            }
        });
        Map<GradedItem, Long> result = new LinkedHashMap<>();
        for (Map.Entry<GradedItem, Long> entry : list) {
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
