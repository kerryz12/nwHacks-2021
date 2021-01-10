package main.model;

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
        weightedTasks = new HashMap<>();
        for (Class c : classlist){
            int curr = c.getImp();
            List<GradedItem> tasks = c.getTasks();
            for (GradedItem g : tasks){
                long hoursLeft = (g.getDate() - currTime)/MILLI_IN_HOUR;
                long val = g.getWeight() * curr * hoursLeft;
                weightedTasks.put(g,val);
            }
        }
        weightedTasks = sortByValue(weightedTasks);

    }

    public static <K, V extends Comparable<? super V>> Map<K, V> sortByValue(Map<K, V> map) {
        List<Map.Entry<K, V>> list = new ArrayList<>(map.entrySet());
        list.sort(new Comparator<Map.Entry<K, V>>() {
            @Override
            public int compare(Map.Entry<K, V> o1, Map.Entry<K, V> o2) {
                return 0;
            }
        });
        Map<K, V> result = new LinkedHashMap<>();
        for (Map.Entry<K, V> entry : list) {
            result.put(entry.getKey(), entry.getValue());
        }
        return result;
    }



}
