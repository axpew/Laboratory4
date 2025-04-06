package controller;

import domain.SinglyLinkedList;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class addStudentFirstController {
    @FXML
    private Text txtMessage;
    @FXML
    private Pane mainPain;
    @FXML
    private TextField idTextfield;
    @FXML
    private TextField addressTextfield;
    @FXML
    private Pane buttonPane;
    @FXML
    private TextField ageTextfield;
    @FXML
    private AnchorPane AP;
    @FXML
    private TextField nameTextfield;

    @FXML
    public void initialize() {
        // No es necesario inicializar nada específico aquí
    }

    @FXML
    public void addOnAction(ActionEvent actionEvent) {
        try {
            // Obtenemos los datos de los campos de texto
            String id = idTextfield.getText();
            String name = nameTextfield.getText();
            int age = Integer.parseInt(ageTextfield.getText());
            String address = addressTextfield.getText();

            // Validamos los datos
            if (id.isEmpty() || name.isEmpty() || address.isEmpty() || age <= 0) {
                util.FXUtility.showErrorAlert("Error", "Todos los campos son obligatorios y la edad debe ser mayor a 0");
                return;
            }

            // Creamos el estudiante
            Student newStudent = new Student(id, name, age, address);

            // Obtenemos la lista global de estudiantes
            SinglyLinkedList studentList = util.Utility.getStudentList();

            // Añadimos el estudiante al principio de la lista
            studentList.addFirst(newStudent);

            // Actualizamos la lista global
            util.Utility.setStudentList(studentList);

            // Mostramos mensaje de éxito
            txtMessage.setText("Estudiante añadido al principio de la lista exitosamente");

            // Limpiamos los campos
            cleanOnAction(null);

        } catch (NumberFormatException e) {
            util.FXUtility.showErrorAlert("Error", "La edad debe ser un número entero");
        } catch (Exception e) {
            util.FXUtility.showErrorAlert("Error", "Error al añadir el estudiante: " + e.getMessage());
        }
    }

    @FXML
    public void exitView(ActionEvent actionEvent) {
        // Volvemos a la vista de estudiantes
        util.FXUtility.loadPage("ucr.laboratory4.HelloApplication", "student-view.fxml", mainPain);
    }

    @FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        // Limpiamos todos los campos
        idTextfield.setText("");
        nameTextfield.setText("");
        ageTextfield.setText("");
        addressTextfield.setText("");
    }
}