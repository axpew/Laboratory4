package controller;

import domain.SinglyLinkedList;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.TextField;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import ucr.laboratory4.HelloApplication;
import javafx.scene.control.TextFormatter;
import javafx.util.converter.IntegerStringConverter;

import java.io.IOException;

public class addStudentController {
    @javafx.fxml.FXML
    private Text txtMessage;
    @javafx.fxml.FXML
    private TextField idTextfield;
    @javafx.fxml.FXML
    private TextField addressTextfield;
    @javafx.fxml.FXML
    private Pane buttonPane;
    @javafx.fxml.FXML
    private TextField ageTextfield;
    @javafx.fxml.FXML
    private TextField nameTextfield;
    @javafx.fxml.FXML
    private Pane mainPain;


    @javafx.fxml.FXML
    public void addOnAction(ActionEvent actionEvent) {

        try {
            int edad = Integer.parseInt(ageTextfield.getText());
            SinglyLinkedList list = util.Utility.getStudentList(); //Se toma la lista ya existente y se le agrega el nuevo estudiante
            Student newStudent = new Student(idTextfield.getText(),nameTextfield.getText(),edad,addressTextfield.getText());
            list.add(newStudent);

        } catch (NumberFormatException e) {
            util.FXUtility.showErrorAlert("FORMATO NO VÁLIDO", "Escriba el ID y edad en números");
        }

    }

    @javafx.fxml.FXML
    public void exitView(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.laboratory4.HelloApplication", "student-view.fxml", mainPain);
    }

    @javafx.fxml.FXML
    public void cleanOnAction(ActionEvent actionEvent) {

        nameTextfield.setText("");
        idTextfield.setText("");
        ageTextfield.setText("");
        addressTextfield.setText("");
    }


}
