package controller;

import domain.Course;
import domain.DoublyLinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class addCourseFirstController {
    @FXML
    private Text txtMessage;
    @FXML
    private Pane mainPain;
    @FXML
    private TextField creditsTextfield;
    @FXML
    private TextField idTextfield;
    @FXML
    private Pane buttonPane;
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
            int credits = Integer.parseInt(creditsTextfield.getText());

            // Validamos los datos
            if (id.isEmpty() || name.isEmpty() || credits <= 0) {
                util.FXUtility.showErrorAlert("Error", "Todos los campos son obligatorios y los créditos deben ser mayores a 0");
                return;
            }

            // Creamos el curso
            Course newCourse = new Course(id, name, credits);

            // Obtenemos la lista global de cursos
            DoublyLinkedList courseList = CourseController.getCourseList();

            // Añadimos el curso al principio de la lista
            courseList.addFirst(newCourse);

            // Actualizamos la lista global
            CourseController.setCourseList(courseList);

            // Mostramos mensaje de éxito
            txtMessage.setText("Curso añadido al principio de la lista exitosamente");

            // Limpiamos los campos
            cleanOnAction(null);

        } catch (NumberFormatException e) {
            util.FXUtility.showErrorAlert("Error", "Los créditos deben ser un número entero");
        } catch (Exception e) {
            util.FXUtility.showErrorAlert("Error", "Error al añadir el curso: " + e.getMessage());
        }
    }

    @FXML
    public void exitView(ActionEvent actionEvent) {
        // Volvemos a la vista de cursos
        util.FXUtility.loadPage("ucr.laboratory4.HelloApplication", "course-view.fxml", mainPain);
    }

    @FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        // Limpiamos todos los campos
        idTextfield.setText("");
        nameTextfield.setText("");
        creditsTextfield.setText("");
    }
}