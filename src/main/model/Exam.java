package main.model;

import java.time.LocalDateTime;

public class Exam extends GradedItem {
    public Exam (String name, LocalDateTime date, int weight) {
        super(name, date, weight);
    }

}
