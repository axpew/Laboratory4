package controller;

import domain.*;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.DatePicker;
import javafx.scene.control.TextField;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

import java.time.LocalDateTime;
import java.time.LocalTime;
import java.util.ArrayList;
import java.util.List;

public class addRegisterController {
    @FXML
    private Text txtMessage;
    @FXML
    private Pane mainPain;
    @FXML
    private DatePicker dpDateAndHourOfRegister;
    @FXML
    private TextField idTextfield;
    @FXML
    private Pane buttonPane;
    @FXML
    private ComboBox<String> cbSelectCourse;
    @FXML
    private ComboBox<String> cbSelectStudent;
    @FXML
    private AnchorPane AP;

    private List<Student> students = new ArrayList<>();
    private List<Course> courses = new ArrayList<>();

    @FXML
    public void initialize() {
        // Cargamos los estudiantes y cursos para los ComboBox
        loadStudents();
        loadCourses();
    }

    private void loadStudents() {
        SinglyLinkedList studentList = util.Utility.getStudentList();
        ObservableList<String> studentItems = FXCollections.observableArrayList();

        try {
            if (!studentList.isEmpty()) {
                Node aux = studentList.first;
                while (aux != null) {
                    Student student = (Student) aux.data;
                    students.add(student); // Guardamos el objeto Student para usarlo después
                    studentItems.add(student.getId() + ", " + student.getName() + ", " + student.getAge() + ", " + student.getAddress());
                    aux = aux.next;
                }
            }
        } catch (Exception e) {
            util.FXUtility.showErrorAlert("Error", "Error al cargar los estudiantes: " + e.getMessage());
        }

        cbSelectStudent.setItems(studentItems);
    }

    private void loadCourses() {
        DoublyLinkedList courseList = CourseController.getCourseList();
        ObservableList<String> courseItems = FXCollections.observableArrayList();

        try {
            if (!courseList.isEmpty()) {
                for (int i = 1; i <= courseList.size(); i++) {
                    Course course = (Course) courseList.getNode(i).data;
                    courses.add(course); // Guardamos el objeto Course para usarlo después
                    courseItems.add(course.getId() + ", " + course.getName() + ", " + course.getCredits());
                }
            }
        } catch (ListException e) {
            util.FXUtility.showErrorAlert("Error", "Error al cargar los cursos: " + e.getMessage());
        }

        cbSelectCourse.setItems(courseItems);
    }

    @FXML
    public void addOnAction(ActionEvent actionEvent) {
        try {
            // Obtenemos los datos de los campos
            int id = Integer.parseInt(idTextfield.getText());

            // Verificamos que se haya seleccionado una fecha
            if (dpDateAndHourOfRegister.getValue() == null) {
                util.FXUtility.showErrorAlert("Error", "Debe seleccionar una fecha");
                return;
            }

            // Obtenemos la fecha y hora
            LocalDateTime registerDate = LocalDateTime.of(dpDateAndHourOfRegister.getValue(), LocalTime.now());

            // Verificamos que se haya seleccionado un estudiante
            int studentIndex = cbSelectStudent.getSelectionModel().getSelectedIndex();
            if (studentIndex == -1) {
                util.FXUtility.showErrorAlert("Error", "Debe seleccionar un estudiante");
                return;
            }

            // Verificamos que se haya seleccionado un curso
            int courseIndex = cbSelectCourse.getSelectionModel().getSelectedIndex();
            if (courseIndex == -1) {
                util.FXUtility.showErrorAlert("Error", "Debe seleccionar un curso");
                return;
            }

            // Obtenemos el estudiante y curso seleccionados
            Student selectedStudent = students.get(studentIndex);
            Course selectedCourse = courses.get(courseIndex);

            // Creamos el registro
            Register newRegister = new Register(id, registerDate, selectedStudent.getId(), selectedCourse.getId());

            // Obtenemos la lista global de registros
            DoublyLinkedList registerList = RegisterController.getRegisterList();

            // Añadimos el registro a la lista
            registerList.add(newRegister);

            // Actualizamos la lista global
            RegisterController.setRegisterList(registerList);

            // Mostramos mensaje de éxito
            txtMessage.setText("Registro añadido exitosamente");

            // Limpiamos los campos
            cleanOnAction(null);

        } catch (NumberFormatException e) {
            util.FXUtility.showErrorAlert("Error", "El ID debe ser un número entero");
        } catch (Exception e) {
            util.FXUtility.showErrorAlert("Error", "Error al añadir el registro: " + e.getMessage());
        }
    }

    @FXML
    public void exitView(ActionEvent actionEvent) {
        // Volvemos a la vista de registros
        util.FXUtility.loadPage("ucr.laboratory4.HelloApplication", "register-view.fxml", mainPain);
    }

    @FXML
    public void cleanOnAction(ActionEvent actionEvent) {
        // Limpiamos todos los campos
        idTextfield.setText("");
        dpDateAndHourOfRegister.setValue(null);
        cbSelectStudent.getSelectionModel().clearSelection();
        cbSelectCourse.getSelectionModel().clearSelection();
    }
}