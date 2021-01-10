package main.ui;

import com.calendarfx.model.Calendar;
import com.calendarfx.model.CalendarSource;
import com.calendarfx.view.CalendarView;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.concurrent.Task;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import javafx.scene.text.FontWeight;
import javafx.scene.text.Text;
import javafx.stage.Modality;
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;
import main.model.Class;
import main.model.ClassList;
import main.model.GradedItem;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.List;
import java.util.Random;

public class UI extends Application {
    private ClassList classlist;
    private ListView<String> list;
    private ObservableList<String> items;
    private LocalDate dateIn;
    private final static int MAX_IMPORTANCE = 10;
    private CalendarView calendarView;

    private void setup() {
        classlist = new ClassList();
        list = new ListView<String>();
        items = FXCollections.observableArrayList();
    }

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        setup();

        primaryStage.setTitle("The Scheduler");

        Group todoGroup = new Group();
        Group menuGroup = new Group();

        final Random rng = new Random();
        VBox taskBox = new VBox(5);
        ScrollPane taskList = new ScrollPane(taskBox);
        taskList.setFitToWidth(true);

        createCalendar();

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            AnchorPane anchorPane = new AnchorPane();
            String style = String.format("-fx-background: rgb(%d, %d, %d);" + "-fx-background-color: -fx-background;",
                    rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));

            anchorPane.setStyle(style);
            Label label = new Label("Pane "+(taskBox.getChildren().size()+1));
            AnchorPane.setLeftAnchor(label, 5.0);
            AnchorPane.setTopAnchor(label, 5.0);
            Button button = new Button("Remove");
            button.setOnAction(evt -> taskBox.getChildren().remove(anchorPane));
            AnchorPane.setRightAnchor(button, 5.0);
            AnchorPane.setTopAnchor(button, 5.0);
            AnchorPane.setBottomAnchor(button, 5.0);
            anchorPane.getChildren().addAll(label, button);
            taskBox.getChildren().add(anchorPane);
        });

        Button addClass = new Button();
        addClass.setText("Add Class");
        addClass.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                Label secondLabel = new Label("I'm a Label on new Window");
                GridPane grid = new GridPane();
                grid.setAlignment(Pos.TOP_LEFT);
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(25, 25, 25, 25));

                Label codePrompt = new Label("Class Code:");
                grid.add(codePrompt, 0, 0, 20, 20);

                TextField codeIn = new TextField();
                grid.add(codeIn, 15, 0,20,20);

                Label importancePrompt = new Label("Importance:");
                grid.add(importancePrompt, 0, 5, 20, 20);

                TextField importanceIn = new TextField();
                grid.add(importanceIn, 15, 5,20,20);

                Button submit = new Button();
                submit.setText(" Submit ");
                grid.add(submit, 28,10,20,20);

                Scene secondScene = new Scene(grid, 400, 300);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Add Class");
                newWindow.setScene(secondScene);

                // Specifies the modality for new window.
                newWindow.initModality(Modality.WINDOW_MODAL);

                // Specifies the owner Window (parent) for new window
                newWindow.initOwner(primaryStage);

                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX() + 200);
                newWindow.setY(primaryStage.getY() + 100);

                newWindow.show();

                submit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (!classlist.contains(codeIn.getCharacters().toString())) {
                            classlist.addClass(new Class(codeIn.getCharacters().toString(),
                                    (Integer.parseInt(importanceIn.getCharacters().toString()) < 0) ? 0 :
                                    Math.min(Integer.parseInt(importanceIn.getCharacters().toString()), MAX_IMPORTANCE)));

                            AnchorPane anchorPane = new AnchorPane();
                            String style = String.format("-fx-background: rgb(%d, %d, %d);" + "-fx-background-color: -fx-background;",
                                    rng.nextInt(256), rng.nextInt(256), rng.nextInt(256));

                            anchorPane.setStyle(style);
                            Label label = new Label(classlist.getClass(taskBox.getChildren().size()).getClassCode());
                            AnchorPane.setLeftAnchor(label, 5.0);
                            AnchorPane.setTopAnchor(label, 5.0);

                            Button removeButton = new Button("Remove");
                            removeButton.setOnAction(evt -> taskBox.getChildren().remove(anchorPane));
                            AnchorPane.setRightAnchor(removeButton, 5.0);
                            AnchorPane.setTopAnchor(removeButton, 5.0);
                            AnchorPane.setBottomAnchor(removeButton, 5.0);

                            Button addAssignment = createAssignmentButton(primaryStage);

                            AnchorPane.setRightAnchor(addAssignment, 75.0);
                            AnchorPane.setTopAnchor(addAssignment, 5.0);
                            AnchorPane.setBottomAnchor(addAssignment, 5.0);
                            anchorPane.getChildren().addAll(label, removeButton, addAssignment);
                            taskBox.getChildren().add(anchorPane);
                        }
                        else {
                            createOkayWindow(primaryStage, "That class already exists!");
                        }

                        newWindow.close();
                    }
                });

            }
        });

        BorderPane rootPane = new BorderPane(taskList, null, null, null, addClass);

        rootPane.getChildren().addAll(taskBox);

        //when the scene is created, it should just render all the groups

        Scene scene = new Scene(rootPane, 1600, 900);

        primaryStage.setScene(scene);
        primaryStage.show();

    }

    private void createCalendar() {
        calendarView = new CalendarView();

        Calendar taskCalendar = new Calendar("Tasks");

        taskCalendar.setStyle(Calendar.Style.STYLE1);

        CalendarSource myCalendarSource = new CalendarSource("My Calendars");
        myCalendarSource.getCalendars().add(taskCalendar);

        calendarView.getCalendarSources().add(myCalendarSource);

        calendarView.setRequestedTime(LocalTime.now());

        Thread updateTimeThread = new Thread("Calendar: Update Time Thread") {
            @Override
            public void run() {
                while (true) {
                    Platform.runLater(() -> {
                        calendarView.setToday(LocalDate.now());
                        calendarView.setTime(LocalTime.now());
                    });

                    try {
                        // update every 10 seconds
                        sleep(10000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }

                }
            };
        };

        updateTimeThread.setPriority(Thread.MIN_PRIORITY);
        updateTimeThread.setDaemon(true);
        updateTimeThread.start();
    }

    private Button createAssignmentButton(Stage primaryStage) {
        Button addAssignment = new Button("Add Assignment");
        addAssignment.setOnAction(new EventHandler<ActionEvent>() {
            @Override
            public void handle(ActionEvent actionEvent) {
                GridPane grid = new GridPane();
                grid.setAlignment(Pos.TOP_LEFT);
                grid.setHgap(10);
                grid.setVgap(10);
                grid.setPadding(new Insets(25, 25, 25, 25));

                Label namePrompt = new Label("Assignment Name:");
                grid.add(namePrompt, 0, 0, 20, 20);

                TextField nameIn = new TextField();
                grid.add(nameIn, 15, 0,20,20);

                Label datePrompt = new Label("Due Date: ");
                grid.add(datePrompt, 0, 5, 20, 20);

                final DatePicker datePicker = new DatePicker();
                datePicker.setOnAction(new EventHandler() {
                    public void handle(Event t) {
                        dateIn = datePicker.getValue();
                    }
                });
                grid.add(datePicker, 10, 5, 20, 30);

                Button submitAssignment = new Button();
                submitAssignment.setText(" Submit ");
                grid.add(submitAssignment, 28,10,20,20);

                Scene secondScene = new Scene(grid, 400, 300);

                // New window (Stage)
                Stage newWindow = new Stage();
                newWindow.setTitle("Add Class");
                newWindow.setScene(secondScene);

                // Specifies the modality for new window.
                newWindow.initModality(Modality.WINDOW_MODAL);

                // Specifies the owner Window (parent) for new window
                newWindow.initOwner(primaryStage);

                // Set position of second window, related to primary window.
                newWindow.setX(primaryStage.getX() + 200);
                newWindow.setY(primaryStage.getY() + 100);

                newWindow.show();

                submitAssignment.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        classlist.getClass(0).addTask(
                                new GradedItem(nameIn.toString(), dateIn.atTime(1, 0), 5));
                    }
                } );
            }
        } );
        return addAssignment;
    }

    private void createOkayWindow (Stage primaryStage, String text) {
        GridPane grid = new GridPane();
        grid.setAlignment(Pos.TOP_LEFT);
        grid.setHgap(10);
        grid.setVgap(10);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Label namePrompt = new Label(text);
        grid.add(namePrompt, 0, 0, 20, 20);

        Button okay = new Button();
        okay.setText(" OK ");
        grid.add(okay, 28,10,20,20);

        Scene okScene = new Scene(grid, 400, 300);

        // Okay window (Stage)
        Stage okWindow = new Stage();
        okWindow.setTitle("Add Class");
        okWindow.setScene(okScene);

        // Specifies the modality for new window.
        okWindow.initModality(Modality.WINDOW_MODAL);

        // Specifies the owner Window (parent) for new window
        okWindow.initOwner(primaryStage);

        // Set position of second window, related to primary window.
        okWindow.setX(primaryStage.getX() + 200);
        okWindow.setY(primaryStage.getY() + 100);

        okWindow.show();

        okay.setOnAction(e -> {
            okWindow.close();
        });
    }

}
