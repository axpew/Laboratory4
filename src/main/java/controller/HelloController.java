package controller;

import javafx.event.Event;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.BorderPane;
import javafx.scene.text.Text;
import javafx.event.ActionEvent;
import ucr.laboratory4.HelloApplication;

import java.io.IOException;


public class HelloController {

    @FXML
    private Text txtMessage;
    @FXML
    private BorderPane bp;
    @FXML
    private AnchorPane ap;


    private void load(String form) throws IOException {
        FXMLLoader fxmlLoader = new FXMLLoader(HelloApplication.class.getResource(form));
        this.bp.setCenter(fxmlLoader.load());
    }

    @FXML
    public void Home(ActionEvent actionEvent) {
        this.bp.setCenter(ap);
        this.txtMessage.setText("Laboratory No. 4");
    }


    @FXML
    public void studentView(ActionEvent actionEvent) {
        try {
            load("student-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void coursesView(ActionEvent actionEvent) {
        try {
            load("course-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void registerView(ActionEvent actionEvent) {
        try {
            load("register-view.fxml");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @FXML
    public void Exit(ActionEvent actionEvent) {
        System.exit(0);
    }

    @Deprecated
    public void loadingOnMousePressed(Event event)  {
        this.txtMessage.setText("Estamos cargando tu vista...");
    }




}