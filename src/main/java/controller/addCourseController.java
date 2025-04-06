package controller;

import domain.Course;
import domain.DoublyLinkedList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class addCourseController {
    @FXML
    private Text txtMessage;
    @FXML
    private TextArea taShowMessages;
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
        // Configurar TextArea si existe
        if (taShowMessages != null) {
            taShowMessages.setEditable(false);
            taShowMessages.setWrapText(true);
            taShowMessages.setText("Ingrese los datos del curso y haga clic en 'Add' para agregarlo.");
        }
    }

    @FXML
    public void addOnAction(ActionEvent actionEvent) {
        try {
            // Validación de campos
            if (idTextfield.getText().isEmpty() || nameTextfield.getText().isEmpty() ||
                    creditsTextfield.getText().isEmpty()) {

                String message = "Por favor, complete todos los campos.";
                if (taShowMessages != null) {
                    taShowMessages.setText(message);
                }
                util.FXUtility.showErrorAlert("Campos requeridos", message);
                return;
            }

            int credits = Integer.parseInt(creditsTextfield.getText());
            if (credits <= 0) {
                String message = "Los créditos deben ser un número positivo.";
                if (taShowMessages != null) {
                    taShowMessages.setText(message);
                }
                util.FXUtility.showErrorAlert("Créditos inválidos", message);
                return;
            }

            // Obtener lista global de cursos
            DoublyLinkedList courseList = CourseController.getCourseList();

            // Crear el nuevo curso
            Course newCourse = new Course(
                    idTextfield.getText(),
                    nameTextfield.getText(),
                    credits
            );

            // Añadir a la lista
            courseList.add(newCourse);

            // Actualizar la lista global
            CourseController.setCourseList(courseList);

            // Mensaje de éxito
            String successMessage = "Curso agregado exitosamente:\n" + newCourse.toString();
            if (taShowMessages != null) {
                taShowMessages.setText(successMessage);
            }

            // Limpiar campos
            cleanOnAction(null);

        } catch (NumberFormatException e) {
            String errorMessage = "Error de formato: Los créditos deben ser un número entero.";
            if (taShowMessages != null) {
                taShowMessages.setText(errorMessage);
            }
            util.FXUtility.showErrorAlert("FORMATO NO VÁLIDO", errorMessage);
        } catch (Exception e) {
            String errorMessage = "Error al agregar el curso: " + e.getMessage();
            if (taShowMessages != null) {
                taShowMessages.setText(errorMessage);
            }
            util.FXUtility.showErrorAlert("Error", errorMessage);
        }
    }

    @FXML
    public void exitView(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.laboratory4.HelloApplication", "course-view.fxml", mainPain);
    }

    @FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        idTextfield.setText("");
        nameTextfield.setText("");
        creditsTextfield.setText("");

        if (taShowMessages != null) {
            taShowMessages.setText("Campos limpiados. Ingrese los datos del nuevo curso.");
        }
    }
}