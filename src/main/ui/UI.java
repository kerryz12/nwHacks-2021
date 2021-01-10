package main.ui;

import javafx.application.Application;
import javafx.event.ActionEvent;
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
import javafx.stage.Stage;
import javafx.scene.shape.Rectangle;
import javafx.scene.shape.Line;

public class UI extends Application {
    public static void main(String[] args) {
        launch(args);
    }

    @Override
    public void start(Stage primaryStage) {
        primaryStage.setTitle("The Scheduler");

        Group calendarGroup = new Group();
        Group listGroup = new Group();
        Group todoGroup = new Group();
        Group menuGroup = new Group();

        GridPane grid = new GridPane();
        grid.setAlignment(Pos.CENTER_LEFT);
        grid.setHgap(1);
        grid.setVgap(1);
        grid.setPadding(new Insets(25, 25, 25, 25));

        Text scenetitle = new Text("January");
        scenetitle.setFont(Font.font("Segue UI", FontWeight.NORMAL, 40));
        grid.add(scenetitle, 0, 0, 2, 1);

        //need to somehow generate the grid (calendar)

        //when the scene is created, it should just render all the groups

        Scene scene = new Scene(grid, 1500, 1000);
        primaryStage.setScene(scene);
        primaryStage.show();

    }
}
