package controller;

import domain.Course;
import domain.DoublyLinkedList;
import domain.ListException;
import domain.Node;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextInputDialog;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class CourseController {
    @FXML
    private Text txtMessage;
    @FXML
    private TextArea taShowMessages;
    @FXML
    private Pane mainPain;
    @FXML
    private TableColumn idTableColumn;
    @FXML
    private TableColumn nameTableColumn;
    @FXML
    private TableColumn creditsTableColumn;
    @FXML
    private Pane buttonPane;
    @FXML
    private TableView studentTableColumn;

    private DoublyLinkedList courseList;
    private static DoublyLinkedList courseListGlobal = new DoublyLinkedList();

    // Métodos para acceder a la lista global de cursos
    public static DoublyLinkedList getCourseList() {
        return courseListGlobal;
    }

    public static void setCourseList(DoublyLinkedList list) {
        courseListGlobal = list;
    }

    @FXML
    public void initialize() {
        // Configuramos el TextArea
        taShowMessages.setEditable(false);
        taShowMessages.setWrapText(true);

        courseList = courseListGlobal;

        // Solo agregar cursos iniciales si la lista está vacía
        if(courseList.isEmpty()) {
            courseList.add(new Course("IF-3001", "Algoritmos y Estructuras de Datos", 4));
            courseList.add(new Course("IF-4001", "Sistemas Operativos", 4));
            courseList.add(new Course("IF-2000", "Programación 1", 4));
            courseList.add(new Course("IF-3000", "Programación 2", 4));
            courseList.add(new Course("IF-4000", "Arquitectura", 3));
            courseList.add(new Course("IF-5000", "Redes", 4));
            courseList.add(new Course("IF-5100", "Bases de Datos", 4));
            courseList.add(new Course("IF-4101", "Lenguajes app Comerciales", 4));
            courseList.add(new Course("IF-3100", "Sistemas de Información", 3));
        }

        // Configuramos las columnas de la tabla
        idTableColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        nameTableColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        creditsTableColumn.setCellValueFactory(new PropertyValueFactory<>("credits"));

        // Cargamos los datos en la tabla
        studentTableColumn.setItems(convertToObservableList(courseList));

        // Mostramos un mensaje inicial
        try {
            taShowMessages.setText("Lista de cursos cargada con éxito. Total de cursos: " + courseList.size());
        } catch (ListException e) {
            taShowMessages.setText("Error al cargar la lista de cursos: " + e.getMessage());
        }
    }

    // Método para convertir la lista enlazada a ObservableList para mostrar en TableView
    public ObservableList<Course> convertToObservableList(DoublyLinkedList list) {
        ObservableList<Course> courses = FXCollections.observableArrayList();

        try {
            if (!list.isEmpty()) {
                Node aux = list.getNode(1);
                int i = 1;
                while (aux != null) {
                    Object data = aux.data;
                    Course current = (Course) data;
                    courses.add(current);
                    i++;
                    try {
                        aux = list.getNode(i);
                    } catch (ListException e) {
                        aux = null;
                    }
                }
            }
        } catch (ListException e) {
            taShowMessages.setText("Error al convertir la lista a ObservableList: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al convertir la lista a ObservableList: " + e.getMessage());
        }

        return courses;
    }

    @FXML
    public void addFirstOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.laboratory4.HelloApplication", "addCourseFirst-view.fxml", mainPain);
        courseListGlobal = courseList; // Actualizamos la lista global
    }

    @FXML
    public void clearOnAction(ActionEvent actionEvent) {
        try {
            courseList.clear();
            studentTableColumn.setItems(convertToObservableList(courseList));
            taShowMessages.setText("Lista de cursos limpiada exitosamente.");
            courseListGlobal = courseList; // Actualizamos la lista global
        } catch (Exception e) {
            taShowMessages.setText("Error al limpiar la lista: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al limpiar la lista: " + e.getMessage());
        }
    }

    @FXML
    public void removeOnAction(ActionEvent actionEvent) {
        try {
            Course selectedCourse = (Course) studentTableColumn.getSelectionModel().getSelectedItem();
            if (selectedCourse != null) {
                courseList.remove(selectedCourse);
                studentTableColumn.setItems(convertToObservableList(courseList));
                taShowMessages.setText("Curso eliminado exitosamente:\n" + selectedCourse.toString());
                courseListGlobal = courseList; // Actualizamos la lista global
            } else {
                taShowMessages.setText("No se ha seleccionado ningún curso para eliminar.");
                util.FXUtility.showErrorAlert("Error", "No se ha seleccionado ningún curso");
            }
        } catch (ListException e) {
            taShowMessages.setText("Error al eliminar el curso: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al eliminar el curso: " + e.getMessage());
        }
    }

    @FXML
    public void addOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.laboratory4.HelloApplication", "addCourse-view.fxml", mainPain);
        courseListGlobal = courseList; // Actualizamos la lista global
    }

    @FXML
    public void addSortedOnAction(ActionEvent actionEvent) {
        util.FXUtility.loadPage("ucr.laboratory4.HelloApplication", "addCourseSorted-view.fxml", mainPain);
        courseListGlobal = courseList; // Actualizamos la lista global
    }

    @FXML
    public void getFirstOnAction(ActionEvent actionEvent) {
        try {
            Course firstCourse = (Course) courseList.getFirst();
            taShowMessages.setText("Primer curso:\n" + firstCourse.toString());
        } catch (ListException e) {
            taShowMessages.setText("Error al obtener el primer curso: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al obtener el primer curso: " + e.getMessage());
        }
    }

    @FXML
    public void removeFirstOnAction(ActionEvent actionEvent) {
        try {
            Course removedCourse = (Course) courseList.removeFirst();
            studentTableColumn.setItems(convertToObservableList(courseList));
            taShowMessages.setText("Primer curso eliminado:\n" + removedCourse.toString());
            courseListGlobal = courseList; // Actualizamos la lista global
        } catch (ListException e) {
            taShowMessages.setText("Error al eliminar el primer curso: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al eliminar el primer curso: " + e.getMessage());
        }
    }

    @FXML
    public void containsOnAction(ActionEvent actionEvent) {
        try {
            TextInputDialog dialog = new TextInputDialog();
            dialog.setTitle("Verificar Curso");
            dialog.setHeaderText("Ingrese el ID del curso");
            dialog.setContentText("ID:");

            dialog.showAndWait().ifPresent(id -> {
                try {
                    Course course = new Course(id, "", 0);
                    boolean contains = courseList.contains(course);
                    if (contains) {
                        taShowMessages.setText("El curso con ID " + id + " existe en la lista.");
                    } else {
                        taShowMessages.setText("El curso con ID " + id + " no existe en la lista.");
                    }
                } catch (ListException e) {
                    taShowMessages.setText("Error al verificar si el curso existe: " + e.getMessage());
                    util.FXUtility.showErrorAlert("Error", "Error al verificar si el curso existe: " + e.getMessage());
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
            Course lastCourse = (Course) courseList.getLast();
            taShowMessages.setText("Último curso:\n" + lastCourse.toString());
        } catch (ListException e) {
            taShowMessages.setText("Error al obtener el último curso: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al obtener el último curso: " + e.getMessage());
        }
    }

    @FXML
    public void sizeOnAction(ActionEvent actionEvent) {
        try {
            int size = courseList.size();
            taShowMessages.setText("Tamaño de la lista de cursos: " + size);
        } catch (ListException e) {
            taShowMessages.setText("Error al obtener el tamaño de la lista: " + e.getMessage());
            util.FXUtility.showErrorAlert("Error", "Error al obtener el tamaño de la lista: " + e.getMessage());
        }
    }
}