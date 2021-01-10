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

import java.util.List;
import java.util.Random;

public class UI extends Application {
    private ClassList classlist;
    private ListView<String> list;
    private ObservableList<String> items;


    private void setup() {
        classlist = new ClassList();
        list = new ListView<String>();
        items = FXCollections.observableArrayList();
    }

    private ClassList classlist;
    Button addClass;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("The Scheduler");
      
        Group todoGroup = new Group();
        Group menuGroup = new Group();

        final Random rng = new Random();
        VBox taskBox = new VBox(5);
        ScrollPane taskList = new ScrollPane(taskBox);
        taskList.setFitToWidth(true);

        Button addButton = new Button("Add");
        addButton.setOnAction(e -> {
            AnchorPane anchorPane = new AnchorPane();
            String style = String.format("-fx-background: rgb(%d, %d, %d);"+
                            "-fx-background-color: -fx-background;",
                    rng.nextInt(256),
                    rng.nextInt(256),
                    rng.nextInt(256));
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
      
        BorderPane rootPane = new BorderPane(taskList, null, null, null, addButton);
=======
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
                submit.setOnAction(new EventHandler<ActionEvent>() {
                    @Override
                    public void handle(ActionEvent actionEvent) {
                        if (!classlist.contains(codeIn.getCharacters().toString())) {
                            classlist.addClass(new Class(codeIn.getCharacters().toString(), Integer.parseInt(importanceIn.getCharacters().toString())));
                        }
                        else {
                            //class already added. do something.
                        }
                    }
                });

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
            }
        });

        rootPane.getChildren().addAll(taskBox);

        //when the scene is created, it should just render all the groups

        Scene scene = new Scene(rootPane, 1500, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
