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
import javafx.scene.control.TextArea;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import util.Utility;

public class StudentController {

    @FXML
    private Text txtMessage;
    @FXML
    private TextArea taShowMessages;
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
        // Configuramos el TextArea
        taShowMessages.setEditable(false);
        taShowMessages.setWrapText(true);

        // Cargamos la lista general
        this.studentList = Utility.getStudentList();

        // Si la lista está vacía, agregamos algunos estudiantes iniciales
        if(studentList.isEmpty()) {
            studentList.add(new Student("1", "María", 20, "Cartago"));
            studentList.add(new Student("2", "Carlos", 22, "San José"));
            studentList.add(new Student("3", "Laura", 20, "Paraíso"));
            studentList.add(new Student("4", "Paula", 18, "Turrialba"));
            studentList.add(new Student("5", "Carlos", 21, "Limón"));
            studentList.add(new Student("6", "Fabiana", 19, "Paraíso"));
            studentList.add(new Student("7", "María", 23, "Guanacaste"));
            studentList.add(new Student("8", "Carlos", 25, "San Carlos"));
            studentList.add(new Student("9", "Laura", 20, "Turrialba"));
            studentList.add(new Student("10", "Pedro", 24, "Heredia"));
        }

        // Asignar la lista al TableView
        studentTableColumn.setItems(convertToObservableList(studentList));

        // Configuramos las columnas de la tabla
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        ageTableColumn.setCellValueFactory(new PropertyValueFactory<>("age"));
        addressTableColumn.setCellValueFactory(new PropertyValueFactory<>("address"));

        // Mostramos un mensaje inicial
        try {
            taShowMessages.setText("Lista de estudiantes cargada con éxito. Total de estudiantes: " + this.studentList.size());
        } catch (ListException e) {
            taShowMessages.setText("Error al cargar la lista de estudiantes: " + e.getMessage());
        }
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
            taShowMessages.setText("Lista de estudiantes limpiada exitosamente.");

            // Actualizamos la lista global
            util.Utility.setStudentList(this.studentList);
        } catch (Exception e) {
            taShowMessages.setText("Error al limpiar la lista: " + e.getMessage());
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
                taShowMessages.setText("Estudiante eliminado exitosamente:\n" + selectedStudent.toString());

                // Actualizamos la lista global
                util.Utility.setStudentList(this.studentList);
            } else {
                taShowMessages.setText("No se ha seleccionado ningún estudiante para eliminar.");
                util.FXUtility.showErrorAlert("Error", "No se ha seleccionado ningún estudiante");
            }
        } catch (ListException e) {
            taShowMessages.setText("Error al eliminar estudiante: " + e.getMessage());
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
            taShowMessages.setText("Primer estudiante:\n" + firstStudent.toString());
        } catch (ListException e) {
            taShowMessages.setText("Error al obtener el primer estudiante: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al obtener el primer estudiante: " + e.getMessage());
        }
    }

    @FXML
    public void removeFirstOnAction(ActionEvent actionEvent) {
        try {
            Student removedStudent = (Student) studentList.removeFirst();
            studentTableColumn.setItems(convertToObservableList(studentList));
            taShowMessages.setText("Primer estudiante eliminado:\n" + removedStudent.toString());

            // Actualizamos la lista global
            util.Utility.setStudentList(this.studentList);
        } catch (ListException e) {
            taShowMessages.setText("Error al eliminar el primer estudiante: " + e.getMessage());
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
                        taShowMessages.setText("El estudiante con ID " + id + " existe en la lista.");
                    } else {
                        taShowMessages.setText("El estudiante con ID " + id + " no existe en la lista.");
                    }
                } catch (ListException e) {
                    taShowMessages.setText("Error al verificar si el estudiante existe: " + e.getMessage());
                    util.FXUtility.showErrorAlert("Error", "Error al verificar si el estudiante existe: " + e.getMessage());
                }
            });
        } catch (Exception e) {
            taShowMessages.setText("Error: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error: " + e.getMessage());
        }
    }

    @FXML
    public void getLastOnAction(ActionEvent actionEvent) {
        try {
            Student lastStudent = (Student) studentList.getLast();
            taShowMessages.setText("Último estudiante:\n" + lastStudent.toString());
        } catch (ListException e) {
            taShowMessages.setText("Error al obtener el último estudiante: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al obtener el último estudiante: " + e.getMessage());
        }
    }

    @FXML
    public void sizeOnAction(ActionEvent actionEvent) {
        try {
            int size = studentList.size();
            taShowMessages.setText("Tamaño de la lista de estudiantes: " + size);
        } catch (ListException e) {
            taShowMessages.setText("Error al obtener el tamaño de la lista: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al obtener el tamaño de la lista: " + e.getMessage());
        }
    }
}