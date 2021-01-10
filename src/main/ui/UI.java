package main.ui;

import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.StackPane;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.shape.Rectangle;
import javafx.stage.Stage;
import main.model.Class;
import main.model.ClassList;
import main.model.GradedItem;

import java.util.ArrayList;
import java.util.Scanner;

public class UI extends Application {
    private ClassList classlist;
    private ArrayList<GradedItem> todolist;
    private Scanner input;
    private String user;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        classlist = new ClassList();
        Class sampleClass = new Class("MEME 999", 5);
        classlist.addClass(sampleClass);

        primaryStage.setTitle("The Scheduler");
        Button btn = new Button();
        btn.setText("Say 'Check Schedule'");
        btn.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                System.out.println("Finish Your Assignments!");
            }
        });

        Rectangle rect = new Rectangle(200, 1900, Color.RED);
        ScrollPane s1 = new ScrollPane();
        s1.setPrefSize(120, 120);
        s1.setContent(rect);

        ClassList sampleList = new ClassList();
        sampleList.addClass(sampleClass);
        ObservableList<String> classes = FXCollections.observableArrayList(sampleClass.getClassCode());
        ListView<String> classList = new ListView<String>(classes);

        // gridpane!
        GridPane gridpane = new GridPane();

        GridPane.setConstraints(s1, 0, 0); // column=2 row=0
        GridPane.setConstraints(btn, 2, 0); // column=2 row=0
        GridPane.setConstraints(classList, 5, 0);

        // don't forget to add children to gridpane
        gridpane.getChildren().addAll(s1, btn, classList);
        // gridpane!

        primaryStage.setScene(new Scene(gridpane, 1600, 900));
        primaryStage.show();

    }

}
