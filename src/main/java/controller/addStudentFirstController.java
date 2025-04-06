package controller;

import domain.SinglyLinkedList;
import domain.Student;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class addStudentFirstController {
    @FXML
    private Text txtMessage;
    @FXML
    private TextArea taShowMessages;
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
        // Configurar TextArea si existe
        if (taShowMessages != null) {
            taShowMessages.setEditable(false);
            taShowMessages.setWrapText(true);
            taShowMessages.setText("Ingrese los datos del estudiante y haga clic en 'Add' para agregarlo al inicio de la lista.");
        }
    }

    @FXML
    public void addOnAction(ActionEvent actionEvent) {
        try {
            // Validación de campos
            if (idTextfield.getText().isEmpty() || nameTextfield.getText().isEmpty() ||
                    ageTextfield.getText().isEmpty() || addressTextfield.getText().isEmpty()) {

                String message = "Por favor, complete todos los campos.";
                if (taShowMessages != null) {
                    taShowMessages.setText(message);
                }
                util.FXUtility.showErrorAlert("Campos requeridos", message);
                return;
            }

            int edad = Integer.parseInt(ageTextfield.getText());
            if (edad <= 0) {
                String message = "La edad debe ser un número positivo.";
                if (taShowMessages != null) {
                    taShowMessages.setText(message);
                }
                util.FXUtility.showErrorAlert("Edad inválida", message);
                return;
            }

            SinglyLinkedList list = util.Utility.getStudentList(); // Se toma la lista ya existente
            Student newStudent = new Student(
                    idTextfield.getText(),
                    nameTextfield.getText(),
                    edad,
                    addressTextfield.getText()
            );

            list.addFirst(newStudent); // Añadir al inicio de la lista
            util.Utility.setStudentList(list); // Actualizar la lista global

            // Mensaje de éxito
            String successMessage = "Estudiante agregado al inicio de la lista exitosamente:\n" + newStudent.toString();
            if (taShowMessages != null) {
                taShowMessages.setText(successMessage);
            }

            // Limpiar campos
            cleanOnAction(null);

        } catch (NumberFormatException e) {
            String errorMessage = "Error de formato: La edad debe ser un número entero.";
            if (taShowMessages != null) {
                taShowMessages.setText(errorMessage);
            }
            util.FXUtility.showErrorAlert("FORMATO NO VÁLIDO", errorMessage);
        } catch (Exception e) {
            String errorMessage = "Error al agregar el estudiante: " + e.getMessage();
            if (taShowMessages != null) {
                taShowMessages.setText(errorMessage);
            }
            util.FXUtility.showErrorAlert("Error", errorMessage);
        }
    }

    @FXML
    public void exitView(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.laboratory4.HelloApplication", "student-view.fxml", mainPain);
    }

    @FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        nameTextfield.setText("");
        idTextfield.setText("");
        ageTextfield.setText("");
        addressTextfield.setText("");

        if (taShowMessages != null) {
            taShowMessages.setText("Campos limpiados. Ingrese los datos del nuevo estudiante.");
        }
    }
}