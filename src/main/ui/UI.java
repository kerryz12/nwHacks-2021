package main.ui;

import javafx.application.Application;
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
import main.model.ToDoList;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

public class UI extends Application {
    private ClassList classlist;
    private ListView<String> list;
    private ObservableList<String> items;
    private LocalDate dateIn;
    private final static int MAX_IMPORTANCE = 10;

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
        VBox toDoBox = new VBox(5);

        ScrollPane taskList = new ScrollPane(taskBox);
        //taskList.setFitToWidth(true);

        ScrollPane toDoList = new ScrollPane(toDoBox);
        //toDoList.setFitToWidth(true);

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

        Button refreshButton = new Button("Refresh");
        refreshButton.setOnAction(e -> {
            for (Class c : classlist.getClasslist()) {
                List<GradedItem> tasks = c.getTasks();
            }
        });

        Button refreshToDo = new Button("ToDo");
        refreshToDo.setOnAction(e -> {
            toDoBox.getChildren().clear();
            List<GradedItem> toDo = ToDoList.getToDoList(classlist);
            AnchorPane anchorPane = new AnchorPane();
            String style = String.format("-fx-background: rgb(%d, %d, %d);" + "-fx-background-color: -fx-background;",
                    173, 173, 173);
            anchorPane.setStyle(style);
            for (int i = 0; i < toDo.size(); i++){
                Label label = new Label(toDo.get(i).getName() + "\t" + toDo.get(i).getDate());
                //change font or something
                AnchorPane.setLeftAnchor(label, 5.0);
                AnchorPane.setTopAnchor(label, 5.0 + 20*i);
                anchorPane.getChildren().addAll(label);
            }
            toDoBox.getChildren().add(anchorPane);
        });

        Button addClass = new Button();
        addClass.setText("Add Class");
        addClass.setOnAction(actionEvent -> {
            Label secondLabel = new Label("I'm a Label on new Window");
            GridPane grid = new GridPane();
            grid.setAlignment(Pos.TOP_LEFT);
            grid.setHgap(10);
            grid.setVgap(10);
            grid.setPadding(new Insets(25, 25, 25, 25));

            Label codePrompt = new Label("Class Code:");
            grid.add(codePrompt, 0, 0, 20, 20);

            TextField codeIn = new TextField();
            grid.add(codeIn, 15, 0, 20, 20);

            Label importancePrompt = new Label("Importance:");
            grid.add(importancePrompt, 0, 5, 20, 20);

            TextField importanceIn = new TextField();
            grid.add(importanceIn, 15, 5, 20, 20);

            Button submit = new Button();
            submit.setText(" Submit ");
            grid.add(submit, 28, 10, 20, 20);

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

            submit.setOnAction(actionEvent1 -> {
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
                } else {
                    createOkayWindow(primaryStage, "That class already exists!");
                }

                newWindow.close();
            });

        });

        GridPane buttons = new GridPane();
        buttons.add(addClass,0,0,3,3);
        buttons.add(refreshToDo, 0,3,3,3);
        BorderPane rootPane = new BorderPane(null, null, null, null, buttons);
        rootPane.getChildren().addAll(taskBox, toDoBox);

        //when the scene is created, it should just render all the groups

        Scene scene = new Scene(rootPane, 1600, 900);

        primaryStage.setScene(scene);
        primaryStage.show();

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

                Label weightPrompt = new Label("Weight (0-100): ");
                grid.add(weightPrompt, 0, 5, 20, 20);

                TextField weightIn = new TextField();
                grid.add(weightIn, 15, 5, 20, 20);

                Label datePrompt = new Label("Due Date: ");
                grid.add(datePrompt, 0, 10, 20, 20);

                final DatePicker datePicker = new DatePicker();
                datePicker.setOnAction(new EventHandler() {
                    public void handle(Event t) {
                        dateIn = datePicker.getValue();
                    }
                });
                grid.add(datePicker, 10, 10, 20, 30);

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
                        //should add a check if weight field is actually a parseable double.
                        classlist.getClass(0).addTask(
                                new GradedItem(nameIn.getCharacters().toString(), dateIn.atTime(11, 59), Double.parseDouble(weightIn.getCharacters().toString())));
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

    private Button refreshToDoList(Stage primaryStage){
        return null;
    }

}
