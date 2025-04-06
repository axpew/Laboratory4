package controller;

import domain.ListException;
import domain.Node;
import domain.SinglyLinkedList;
import domain.Student;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.Utility;

public class StudentController {

    @FXML
    private Text txtMessage;
    @FXML
    private TableColumn idTableColumn;
    @FXML
    private TableColumn nameTableColumn;
    @FXML
    private TableColumn addressTableColumn;
    @FXML
    private TableColumn ageTableColumn;
    @FXML
    private Pane buttonPane;
    @FXML
    private TableView studentTableColumn;

    private SinglyLinkedList studentList;
    @FXML
    private Pane mainPain;

    @FXML
    public void initialize() {
        // Cargamos la lista general
        this.studentList = Utility.getStudentList();

        // Asignar la lista al TableView
        studentTableColumn.setItems(convertToObservableList(studentList));

        // Configuramos las columnas de la tabla
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        addressTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));
    }

    public ObservableList<Student> convertToObservableList(SinglyLinkedList list) {
        ObservableList<Student> students = FXCollections.observableArrayList();
        Node aux = studentList.first;

        while (aux != null) { // Recorre la lista
            Object data = aux.data;
            Student current = (Student) data;
            students.add(current); // Agrega el estudiante a la ObservableList
            aux = aux.next; // Avanzamos al siguiente nodo
        } // End while

        return students;
    }

    @FXML
    public void addOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.laboratory4.HelloApplication", "addStudent-view.fxml", mainPain);
        // Después de agregar el objeto debemos actualizar la lista general
        Utility.setStudentList(this.studentList); // Para setear la lista general
    }

    @FXML
    public void addFirstOnAction(ActionEvent actionEvent) {
        // Cargamos la vista para añadir un estudiante al principio
        util.FXUtility.loadPage("ucr.laboratory4.HelloApplication", "addStudendFirst-view.fxml", mainPain);
    }

    @FXML
    public void clearOnAction(ActionEvent actionEvent) {
        try {
            studentList.clear();
            studentTableColumn.setItems(convertToObservableList(studentList));
            txtMessage.setText("Lista de estudiantes limpiada exitosamente");

            // Actualizamos la lista global
            util.Utility.setStudentList(this.studentList);
        } catch (Exception e) {
            util.FXUtility.showErrorAlert("Error", "Error al limpiar la lista: " + e.getMessage());
        }
    }

    @FXML
    public void removeOnAction(ActionEvent actionEvent) {
        try {
            Student selectedStudent = (Student) studentTableColumn.getSelectionModel().getSelectedItem();
            if (selectedStudent != null) {
                studentList.remove(selectedStudent);
                studentTableColumn.setItems(convertToObservableList(studentList));
                txtMessage.setText("Estudiante eliminado exitosamente");

                // Actualizamos la lista global
                util.Utility.setStudentList(this.studentList);
            } else {
                util.FXUtility.showErrorAlert("Error", "No se ha seleccionado ningún estudiante");
            }
        } catch (ListException e) {
            util.FXUtility.showErrorAlert("Error", "Error al eliminar estudiante: " + e.getMessage());
        }
    }

    @FXML
    public void addSortedOnAction(ActionEvent actionEvent) {
        // Cargamos la vista para añadir un estudiante de forma ordenada
        util.FXUtility.loadPage("ucr.laboratory4.HelloApplication", "addStudentSorted-view.fxml", mainPain);
    }

    @FXML
    public void getFirstOnAction(ActionEvent actionEvent) {
        try {
            Student firstStudent = (Student) studentList.getFirst();
            txtMessage.setText("Primer estudiante: " + firstStudent.toString());
        } catch (ListException e) {
            util.FXUtility.showErrorAlert("Error", "Error al obtener el primer estudiante: " + e.getMessage());
        }
    }

    @FXML
    public void removeFirstOnAction(ActionEvent actionEvent) {
        try {
            Student removedStudent = (Student) studentList.removeFirst();
            studentTableColumn.setItems(convertToObservableList(studentList));
            txtMessage.setText("Primer estudiante eliminado: " + removedStudent.toString());

            // Actualizamos la lista global
            util.Utility.setStudentList(this.studentList);
        } catch (ListException e) {
            util.FXUtility.showErrorAlert("Error", "Error al eliminar el primer estudiante: " + e.getMessage());
        }
    }

    @FXML
    public void containsOnAction(ActionEvent actionEvent) {
        try {
            javafx.scene.control.TextInputDialog dialog = new javafx.scene.control.TextInputDialog();
            dialog.setTitle("Verificar Estudiante");
            dialog.setHeaderText("Ingrese el ID del estudiante");
            dialog.setContentText("ID:");

            dialog.showAndWait().ifPresent(id -> {
                try {
                    Student student = new Student(id);
                    boolean contains = studentList.contains(student);
                    if (contains) {
                        txtMessage.setText("El estudiante con ID " + id + " existe en la lista");
                    } else {
                        txtMessage.setText("El estudiante con ID " + id + " no existe en la lista");
                    }
                } catch (ListException e) {
                    util.FXUtility.showErrorAlert("Error", "Error al verificar si el estudiante existe: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            util.FXUtility.showErrorAlert("Error", "Error: " + e.getMessage());
        }
    }

    @FXML
    public void getLastOnAction(ActionEvent actionEvent) {
        try {
            Student lastStudent = (Student) studentList.getLast();
            txtMessage.setText("Último estudiante: " + lastStudent.toString());
        } catch (ListException e) {
            util.FXUtility.showErrorAlert("Error", "Error al obtener el último estudiante: " + e.getMessage());
        }
    }

    @FXML
    public void sizeOnAction(ActionEvent actionEvent) {
        try {
            int size = studentList.size();
            txtMessage.setText("Tamaño de la lista de estudiantes: " + size);
        } catch (ListException e) {
            util.FXUtility.showErrorAlert("Error", "Error al obtener el tamaño de la lista: " + e.getMessage());
        }
    }
}