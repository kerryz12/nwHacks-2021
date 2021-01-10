package main.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
import javafx.event.Event;
import javafx.event.EventHandler;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Group;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.StackPane;
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

public class UI extends Application{

    private ClassList classlist;
    Button addClass;

    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("The Scheduler");
        classlist = new ClassList();
        Group calendarGroup = new Group();
        Group listGroup = new Group();
        Group todoGroup = new Group();
        Group menuGroup = new Group();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(25, 25, 25, 25));

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

        Text scenetitle = new Text("January");
        scenetitle.setFont(Font.font("Segue UI", FontWeight.NORMAL, 40));
        grid.add(scenetitle, 0, 0, 2, 1);
        grid.add(addClass, 1,1,2,1);

        //need to somehow generate the grid (calendar)

        //when the scene is created, it should just render all the groups

        Scene scene = new Scene(grid, 1500, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();

    }

}
